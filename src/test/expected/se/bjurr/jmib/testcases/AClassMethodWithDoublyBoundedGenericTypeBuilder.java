package se.bjurr.jmib.testcases;

import java.io.Serializable;
import java.lang.Comparable;

public final class AClassMethodWithDoublyBoundedGenericTypeBuilder<T extends Serializable & Comparable> {
  private T argument;

  private AClassMethodWithDoublyBoundedGenericTypeBuilder() {
  }

  public AClassMethodWithDoublyBoundedGenericTypeBuilder<T> withArgument(final T argument) {
    this.argument = argument;
    return this;
  }

  public static <T extends Serializable & Comparable> AClassMethodWithDoublyBoundedGenericTypeBuilder<T> methodWithDoublyBoundedGenericType(
      ) {
    return new AClassMethodWithDoublyBoundedGenericTypeBuilder<>();
  }

  public void invoke(final AClass instance) {
    instance.methodWithDoublyBoundedGenericType(argument);
  }
}
