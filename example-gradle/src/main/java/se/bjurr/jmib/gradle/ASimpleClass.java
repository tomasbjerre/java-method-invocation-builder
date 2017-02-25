package se.bjurr.jmib.gradle;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder
public class ASimpleClass {

  public BigDecimal getSomething(String someString, BigDecimal someBigDecimal, int anInt) {
    return ZERO;
  }
}
