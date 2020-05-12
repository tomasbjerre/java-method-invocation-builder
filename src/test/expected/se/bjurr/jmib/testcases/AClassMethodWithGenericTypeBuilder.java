package se.bjurr.jmib.testcases;

public final class AClassMethodWithGenericTypeBuilder<T> {
  private T argument;

  private AClassMethodWithGenericTypeBuilder() {
  }

  public AClassMethodWithGenericTypeBuilder<T> withArgument(final T argument) {
    this.argument = argument;
    return this;
  }

  public static <T> AClassMethodWithGenericTypeBuilder<T> methodWithGenericType() {
    return new AClassMethodWithGenericTypeBuilder<>();
  }

  public void invoke(final AClass instance) {
    instance.methodWithGenericType(argument);
  }
}
