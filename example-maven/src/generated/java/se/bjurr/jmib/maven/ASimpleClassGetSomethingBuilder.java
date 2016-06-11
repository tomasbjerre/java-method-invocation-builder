package se.bjurr.jmib.maven;

import java.lang.String;
import java.math.BigDecimal;

public final class ASimpleClassGetSomethingBuilder {
  private String someString;

  private BigDecimal someBigDecimal;

  private int anInt;

  private ASimpleClassGetSomethingBuilder() {
  }

  public ASimpleClassGetSomethingBuilder withSomeString(final String someString) {
    this.someString = someString;
    return this;
  }

  public ASimpleClassGetSomethingBuilder withSomeBigDecimal(final BigDecimal someBigDecimal) {
    this.someBigDecimal = someBigDecimal;
    return this;
  }

  public ASimpleClassGetSomethingBuilder withAnInt(final int anInt) {
    this.anInt = anInt;
    return this;
  }

  public static ASimpleClassGetSomethingBuilder getSomething() {
    return new ASimpleClassGetSomethingBuilder();
  }

  public BigDecimal invoke(final ASimpleClass instance) {
    return instance.getSomething(someString,someBigDecimal,anInt);
  }
}
