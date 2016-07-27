package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AClassMethodWithVoidBuilder {
  private String argument;

  private AClassMethodWithVoidBuilder() {
  }

  public AClassMethodWithVoidBuilder withArgument(final String argument) {
    this.argument = argument;
    return this;
  }

  public static AClassMethodWithVoidBuilder methodWithVoid() {
    return new AClassMethodWithVoidBuilder();
  }

  public void invoke(final AClass instance) {
    instance.methodWithVoid(argument);
  }
}
