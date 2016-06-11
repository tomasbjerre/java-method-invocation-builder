package se.bjurr.jmib.testcases;

import java.lang.Integer;

public final class ClassWithConstructorGetAgeAfterBuilder {
  private Integer years;

  private ClassWithConstructorGetAgeAfterBuilder() {
  }

  public ClassWithConstructorGetAgeAfterBuilder withYears(final Integer years) {
    this.years = years;
    return this;
  }

  public static ClassWithConstructorGetAgeAfterBuilder getAgeAfter() {
    return new ClassWithConstructorGetAgeAfterBuilder();
  }

  public Integer invoke(final ClassWithConstructor instance) {
    return instance.getAgeAfter(years);
  }
}
