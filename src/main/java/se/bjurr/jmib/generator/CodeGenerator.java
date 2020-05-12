package se.bjurr.jmib.generator;

import static com.google.common.collect.Lists.newArrayList;
import static com.squareup.javapoet.JavaFile.builder;
import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.MethodSpec.methodBuilder;
import static com.squareup.javapoet.ParameterSpec.builder;
import static com.squareup.javapoet.TypeName.VOID;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static java.util.Locale.US;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_AS_INVOKE_PARAMETER;
import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_IN_CONSTRUCTOR;
import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_WITH_ON_METHOD;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import java.util.List;
import se.bjurr.jmib.anotations.BuilderStyle;
import se.bjurr.jmib.model.ClassMethod;
import se.bjurr.jmib.model.ClassMethodParameter;

public class CodeGenerator {

  public JavaFile generateJavaFile(
      String packageName,
      String classToInvoke,
      String newClassName,
      ClassMethod classMethod,
      BuilderStyle builderStyle) {

    final ClassName instanceClassName = ClassName.get(packageName, classToInvoke);
    final ParameterSpec instanceParameter =
        builder(instanceClassName, "instance", FINAL) //
            .build();

    final MethodSpec.Builder constructor = getConstructor(builderStyle, instanceParameter);
    setDefaltParameters(classMethod.getParameters(), constructor);

    final Builder javaFile =
        classBuilder(newClassName) //
            .addModifiers(PUBLIC, FINAL) //
            .addTypeVariables(classMethod.getTypeParameters().toTypeVariableNameList())
            .addMethod(
                constructor //
                    .build());

    final ClassName selfWithoutGenericTypeArguments=ClassName.get(packageName, newClassName);

    final TypeName self;
    if (classMethod.getTypeParameters().isEmpty())
      self=selfWithoutGenericTypeArguments;
    else self=ParameterizedTypeName.get(selfWithoutGenericTypeArguments,
            classMethod.getTypeParameters().toTypeNameArray());

    addParameters(classMethod, javaFile, self);

    if (builderStyle == SUPPLY_INSTANCE_WITH_ON_METHOD
        || builderStyle == SUPPLY_INSTANCE_IN_CONSTRUCTOR) {
      javaFile.addField(instanceClassName, "instance", PRIVATE);
    }

    if (builderStyle == SUPPLY_INSTANCE_WITH_ON_METHOD) {
      addOnMethod(instanceParameter, javaFile, self);
    }

    final Iterable<MethodSpec> invokeMethodSpec =
        getInvokeMethod(packageName, classToInvoke, classMethod, builderStyle);

    final MethodSpec staticConstructorMethod =
        getStaticConstructorMethod(
            newClassName, classMethod, self, builderStyle, instanceParameter);

    final TypeSpec typeSpec =
        javaFile //
            .addMethod(staticConstructorMethod) //
            .addMethods(invokeMethodSpec) //
            .build();

    return builder(packageName, typeSpec) //
        .build();
  }

  private MethodSpec getStaticConstructorMethod(
      String newClassName,
      ClassMethod classMethod,
      TypeName self,
      BuilderStyle builderStyle,
      ParameterSpec instanceParameter) {
    final String instantiation=
      classMethod.getTypeParameters().isEmpty()
        ?"new "+newClassName
        :"new "+newClassName+"<>";

    if (builderStyle == SUPPLY_INSTANCE_IN_CONSTRUCTOR) {
      return methodBuilder(classMethod.getName()) //
          .addModifiers(PUBLIC, STATIC) //
          .addTypeVariables(classMethod.getTypeParameters().toTypeVariableNameList())
          .addParameter(instanceParameter) //
          .addStatement("return " + instantiation + "(instance)") //
          .returns(self) //
          .build();
    }
    return methodBuilder(classMethod.getName()) //
        .addModifiers(PUBLIC, STATIC) //
        .addTypeVariables(classMethod.getTypeParameters().toTypeVariableNameList())
        .addStatement("return " + instantiation + "()") //
        .returns(self) //
        .build();
  }

  private void addOnMethod(ParameterSpec instanceParameter, Builder javaFile, TypeName self) {
    final MethodSpec onMethod =
        methodBuilder("on") //
            .addModifiers(PUBLIC) //
            .addParameter(instanceParameter) //
            .addStatement("this.instance = instance") //
            .addStatement("return this") //
            .returns(self) //
            .build();
    javaFile.addMethod(onMethod);
  }

  private void addParameters(ClassMethod classMethod, Builder javaFile, TypeName self) {
    for (final ClassMethodParameter classMethodParameter : classMethod.getParameters()) {
      final TypeName fieldType = TypeName.get(classMethodParameter.getType());
      final String fieldName = classMethodParameter.getName();
      javaFile //
          .addField(fieldType, fieldName, PRIVATE) //
          .addMethod(
              methodBuilder("with" + ucFirst(fieldName)) //
                  .addModifiers(PUBLIC) //
                  .addParameter(
                      builder(fieldType, fieldName, FINAL) //
                          .build()) //
                  .addStatement("this." + fieldName + " = " + fieldName) //
                  .addStatement("return this") //
                  .returns(self) //
                  .build());
    }
  }

  private void setDefaltParameters(
      List<ClassMethodParameter> classMethodParameters, MethodSpec.Builder constructor) {
    for (final ClassMethodParameter classMethodParameter : classMethodParameters) {
      if (classMethodParameter.getDefaultValue().isPresent()) {
        constructor //
            .addStatement(
            "this."
                + classMethodParameter.getName()
                + " = "
                + classMethodParameter.getDefaultValue().get());
      }
    }
  }

  private MethodSpec.Builder getConstructor(
      BuilderStyle builderStyle, ParameterSpec instanceParameter) {
    if (builderStyle == SUPPLY_INSTANCE_IN_CONSTRUCTOR) {
      return constructorBuilder() //
          .addParameter(instanceParameter) //
          .beginControlFlow("if (instance == null)") //
          .addStatement(
              "throw new java.lang.IllegalStateException(\"You must supply an instance.\")")
          .endControlFlow() //
          .addStatement("this.instance = instance") //
          .addModifiers(PRIVATE);
    }
    return constructorBuilder() //
        .addModifiers(PRIVATE);
  }

  private Iterable<MethodSpec> getInvokeMethod(
      String packageName,
      String classToInvoke,
      ClassMethod classMethod,
      BuilderStyle builderStyle) {
    final TypeName returns = TypeName.get(classMethod.getReturnType());
    final boolean shouldReturn = !returns.equals(VOID);

    final String callStatementArguments =
        classMethod.getName() + "(" + parameters(classMethod.getParameters()) + ")";
    String callStatementParameter = "instance." + callStatementArguments;
    String callStatementAttribute = "this.instance." + callStatementArguments;
    if (shouldReturn) {
      callStatementParameter = "return " + callStatementParameter;
      callStatementAttribute = "return " + callStatementAttribute;
    }

    final List<MethodSpec> methods = newArrayList();
    if (builderStyle == SUPPLY_INSTANCE_WITH_ON_METHOD
        || builderStyle == SUPPLY_INSTANCE_IN_CONSTRUCTOR) {
      methods.add(
          methodBuilder("invoke") //
              .addModifiers(PUBLIC) //
              .beginControlFlow("if (this.instance == null)") //
              .addStatement(
                  "throw new java.lang.IllegalStateException(\"You must supply an instance to the builder!\")")
              .endControlFlow() //
              .addStatement(callStatementAttribute) //
              .returns(returns) //
              .build());
    }

    if (builderStyle == SUPPLY_INSTANCE_AS_INVOKE_PARAMETER) {
      methods.add(
          methodBuilder("invoke") //
              .addModifiers(PUBLIC) //
              .addParameter(
                  builder(ClassName.get(packageName, classToInvoke), "instance", FINAL) //
                      .build()) //
              .addStatement(callStatementParameter) //
              .returns(returns) //
              .build());
    }
    if (methods.isEmpty()) {
      throw new RuntimeException("Could not create invoke method.");
    }
    return methods;
  }

  private String parameters(List<ClassMethodParameter> parameters) {
    final StringBuilder sb = new StringBuilder();
    for (final ClassMethodParameter p : parameters) {
      if (parameters.indexOf(p) == 0) {
        sb.append(p.getName());
      } else {
        sb.append("," + p.getName());
      }
    }
    return sb.toString();
  }

  private String ucFirst(String s) {
    return s.substring(0, 1).toUpperCase(US) + s.substring(1);
  }
}
