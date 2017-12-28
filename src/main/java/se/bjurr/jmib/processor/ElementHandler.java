package se.bjurr.jmib.processor;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Locale.US;
import static java.util.logging.Level.SEVERE;
import static javax.lang.model.element.ElementKind.METHOD;
import static javax.lang.model.element.Modifier.PRIVATE;
import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_AS_INVOKE_PARAMETER;

import com.google.common.base.Optional;
import com.squareup.javapoet.JavaFile;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import se.bjurr.jmib.anotations.BuilderStyle;
import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;
import se.bjurr.jmib.generator.CodeGenerator;
import se.bjurr.jmib.model.ClassMethod;
import se.bjurr.jmib.model.ClassMethodParameter;
import se.bjurr.jmib.model.ClassModel;

public class ElementHandler {

  private final Elements elementUtils;
  private final Filer filer;
  private final Messager messager;

  public ElementHandler(Elements elementUtils, Filer filer, Messager messager) {
    this.elementUtils = elementUtils;
    this.filer = filer;
    this.messager = messager;
  }

  public void handle(TypeElement classElement) throws IOException {

    final String packageName = getPackageName(classElement);

    final String classFullyQualified = getClassFulltyQualified(classElement);
    final GenerateMethodInvocationBuilder generateMethodInvocationBuilder =
        classElement.getAnnotation(GenerateMethodInvocationBuilder.class);
    BuilderStyle builderStyle = generateMethodInvocationBuilder.style();
    if (builderStyle == null) {
      builderStyle = SUPPLY_INSTANCE_AS_INVOKE_PARAMETER;
    }

    final List<ClassMethod> methods = findMethods(classElement);

    final ClassModel classModel = new ClassModel(packageName, classFullyQualified, methods);

    writeResults(classElement, classModel, builderStyle);
  }

  private List<ClassMethod> findMethods(TypeElement classElement) {
    final List<ClassMethod> methods = newArrayList();
    final List<? extends Element> allMembers = this.elementUtils.getAllMembers(classElement);
    for (final Element member : allMembers) {
      if (member.getKind() == METHOD && !member.getModifiers().contains(PRIVATE)) {
        if (!objectHasMethod(member.getSimpleName().toString())) {
          methods.add(handle((ExecutableElement) member));
        }
      }
    }
    return methods;
  }

  private String getClassFulltyQualified(TypeElement classElement) {
    return classElement.getQualifiedName().toString();
  }

  private Optional<String> getDefaultValue(VariableElement parameter) {
    final Default defaultAnnotation = parameter.getAnnotation(Default.class);
    if (defaultAnnotation == null) {
      return Optional.absent();
    }
    String defaultValue = defaultAnnotation.value();
    if (parameter.asType().toString().equals(String.class.getName())) {
      defaultValue = "\"" + defaultAnnotation.value() + "\"";
    }
    return Optional.of(defaultValue);
  }

  private String getPackageName(Element element) {
    final PackageElement packageElement = this.elementUtils.getPackageOf(element);
    return packageElement.getQualifiedName().toString();
  }

  private ClassMethod handle(ExecutableElement member) {
    final List<ClassMethodParameter> parameters = newArrayList();
    final TypeMirror returnType = member.getReturnType();
    final String methodName = member.getSimpleName().toString();
    for (final VariableElement parameter : member.getParameters()) {
      final TypeMirror type = parameter.asType();
      final String name = parameter.getSimpleName().toString();
      final Optional<String> defaultValue = getDefaultValue(parameter);
      parameters.add(new ClassMethodParameter(type, name, defaultValue.orNull()));
    }
    return new ClassMethod(methodName, returnType, parameters);
  }

  private boolean objectHasMethod(String name) {
    final Method[] objectMethods = Object.class.getDeclaredMethods();
    for (final Method m : objectMethods) {
      if (m.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  private String ucFirst(String s) {
    return s.substring(0, 1).toUpperCase(US) + s.substring(1);
  }

  private void writeResults(
      TypeElement classElement, ClassModel classModel, BuilderStyle builderStyle)
      throws IOException {
    for (final ClassMethod classMethod : classModel.getMethods()) {
      final String classSuffix = ucFirst(classMethod.getName()) + "Builder";
      final String newClassName = classModel.getClassFullyQualifiedName() + classSuffix;
      final JavaFileObject newJavaFileObject =
          this.filer.createSourceFile(newClassName, classElement);
      final Writer writer = newJavaFileObject.openWriter();
      final JavaFile javaFile =
          new CodeGenerator()
              .generateJavaFile(
                  classModel.getPackageName(),
                  classModel.getClassName(),
                  classModel.getClassName() + classSuffix,
                  classMethod,
                  builderStyle);
      try {
        javaFile.writeTo(writer);
      } catch (final Exception e) {
        Logger.getLogger(this.getClass().getSimpleName()).log(SEVERE, e.getMessage(), e);
      } finally {
        writer.close();
      }
      this.messager.printMessage(
          Diagnostic.Kind.NOTE, //
          "Created: "
              + newJavaFileObject.toUri()
              + "\n" //
              + "Package: "
              + classModel.getPackageName()
              + "\n" //
              + "Class: "
              + newClassName);
    }
  }
}
