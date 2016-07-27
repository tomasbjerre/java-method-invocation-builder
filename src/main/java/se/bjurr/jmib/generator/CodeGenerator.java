package se.bjurr.jmib.generator;

import static com.squareup.javapoet.JavaFile.builder;
import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.MethodSpec.methodBuilder;
import static com.squareup.javapoet.ParameterSpec.builder;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import java.util.List;

import se.bjurr.jmib.model.ClassMethod;
import se.bjurr.jmib.model.ClassMethodParameter;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

public class CodeGenerator {

 public JavaFile generateJavaFile(String packageName, String classToInvoke, String newClassName, ClassMethod classMethod) {

  MethodSpec.Builder constructor = constructorBuilder()//
    .addModifiers(PRIVATE);
  for (ClassMethodParameter classMethodParameter : classMethod.getParameters()) {
   if (classMethodParameter.getDefaultValue().isPresent()) {
    constructor//
      .addStatement("this." + classMethodParameter.getName() + " = " + classMethodParameter.getDefaultValue().get());
   }
  }

  Builder javaFile = classBuilder(newClassName)//
    .addModifiers(PUBLIC, FINAL)//
    .addMethod(constructor//
      .build());

  ClassName self = ClassName.get(packageName, newClassName);
  for (ClassMethodParameter classMethodParameter : classMethod.getParameters()) {
   TypeName fieldType = TypeName.get(classMethodParameter.getType());
   String fieldName = classMethodParameter.getName();
   javaFile//
     .addField(fieldType, fieldName, PRIVATE)//
     .addMethod(methodBuilder("with" + ucFirst(fieldName))//
       .addModifiers(PUBLIC)//
       .addParameter(builder(fieldType, fieldName, FINAL)//
         .build())//
       .addStatement("this." + fieldName + " = " + fieldName)//
       .addStatement("return this")//
       .returns(self)//
       .build());
  }

  String callStatement = "instance." + classMethod.getName() + "(" + parameters(classMethod.getParameters()) + ")";
  TypeName returns = TypeName.get(classMethod.getReturnType());
  if (!returns.equals(TypeName.VOID)) {
   callStatement = "return " + callStatement;
  }
  TypeSpec typeSpec = javaFile//
    .addMethod(methodBuilder(classMethod.getName())//
      .addModifiers(PUBLIC, STATIC)//
      .addStatement("return new " + newClassName + "()")//
      .returns(self)//
      .build())//
    .addMethod(methodBuilder("invoke")//
      .addModifiers(PUBLIC)//
      .addParameter(builder(ClassName.get(packageName, classToInvoke), "instance", FINAL)//
        .build())//
      .addStatement(callStatement)//
      .returns(returns)//
      .build())//
    .build();

  return builder(packageName, typeSpec)//
    .build();
 }

 private String parameters(List<ClassMethodParameter> parameters) {
  StringBuilder sb = new StringBuilder();
  for (ClassMethodParameter p : parameters) {
   if (parameters.indexOf(p) == 0) {
    sb.append(p.getName());
   } else {
    sb.append("," + p.getName());
   }
  }
  return sb.toString();
 }

 private String ucFirst(String s) {
  return s.substring(0, 1).toUpperCase() + s.substring(1);
 }
}
