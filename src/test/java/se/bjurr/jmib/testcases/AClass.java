package se.bjurr.jmib.testcases;

import java.awt.geom.Point2D;
import java.io.Serializable;
import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder
public class AClass {
  public String customType( //
      CustomType customType) {
    return "";
  }

  public String defaultValues( //
      @Default("PROJ") String projectKey, //
      @Default("other") String other, //
      String at) {
    return "";
  }

  public String genericType( //
      GenericType<String> customType) {
    return null;
  }

  public void methodWithVoid(String argument) {
    System.out.println(argument);
  }

  String packagePrivate( //
      @Default("PROJ") String projectKey) {
    return "";
  }

  public <T> void methodWithGenericType(T argument) {
    System.out.println("Called methodWithGenericType!");
  }

  public <T, Q> void methodWithTwoGenericTypes(T argument1, Q argument2) {
    System.out.println("Called methodWithTwoGenericTypes!");
  }

  public <T extends Point2D> void methodWithBoundedGenericType(T argument) {
    System.out.println("Called methodWithBoundedGenericType!");
  }

  public <T extends Serializable & Comparable> void methodWithDoublyBoundedGenericType(T argument) {
    System.out.println("Called methodWithBoundedGenericType!");
  }
}
