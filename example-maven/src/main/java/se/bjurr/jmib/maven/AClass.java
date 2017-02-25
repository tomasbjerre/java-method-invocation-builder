package se.bjurr.jmib.maven;

import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_IN_CONSTRUCTOR;

import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder(style = SUPPLY_INSTANCE_IN_CONSTRUCTOR)
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
}
