package se.bjurr.jmib.testcases;

public final class AClassMethodWithTwoGenericTypesBuilder<T, Q> {
  private T argument1;

  private Q argument2;

  private AClassMethodWithTwoGenericTypesBuilder() {
  }

  public AClassMethodWithTwoGenericTypesBuilder<T, Q> withArgument1(final T argument1) {
    this.argument1 = argument1;
    return this;
  }

  public AClassMethodWithTwoGenericTypesBuilder<T, Q> withArgument2(final Q argument2) {
    this.argument2 = argument2;
    return this;
  }

  public static <T, Q> AClassMethodWithTwoGenericTypesBuilder<T, Q> methodWithTwoGenericTypes() {
    return new AClassMethodWithTwoGenericTypesBuilder<>();
  }

  public void invoke(final AClass instance) {
    instance.methodWithTwoGenericTypes(argument1,argument2);
  }
}
