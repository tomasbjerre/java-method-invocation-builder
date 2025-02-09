package se.bjurr.jmib.testcases;

import java.awt.geom.Point2D;

public final class AClassMethodWithBoundedGenericTypeBuilder<T extends Point2D> {
  private T argument;

  private AClassMethodWithBoundedGenericTypeBuilder() {
  }

  public AClassMethodWithBoundedGenericTypeBuilder<T> withArgument(final T argument) {
    this.argument = argument;
    return this;
  }

  public static <T extends Point2D> AClassMethodWithBoundedGenericTypeBuilder<T> methodWithBoundedGenericType(
      ) {
    return new AClassMethodWithBoundedGenericTypeBuilder<>();
  }

  public void invoke(final AClass instance) {
    instance.methodWithBoundedGenericType(argument);
  }
}
