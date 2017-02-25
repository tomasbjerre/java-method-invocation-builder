package se.bjurr.jmib.testcases;

import java.lang.Integer;

public final class ClassWithConstructorGetAgeAfterBuilder {
  private Integer years;

  private ClassWithConstructor instance;

  private ClassWithConstructorGetAgeAfterBuilder(final ClassWithConstructor instance) {
    if (instance == null) {
      throw new java.lang.IllegalStateException("You must supply an instance.");
    }
    this.instance = instance;
  }

  public ClassWithConstructorGetAgeAfterBuilder withYears(final Integer years) {
    this.years = years;
    return this;
  }

  public static ClassWithConstructorGetAgeAfterBuilder getAgeAfter(final ClassWithConstructor instance) {
    return new ClassWithConstructorGetAgeAfterBuilder(instance);
  }

  public Integer invoke() {
    if (this.instance == null) {
      throw new java.lang.IllegalStateException("You must supply an instance to the builder!");
    }
    return this.instance.getAgeAfter(years);
  }
}
