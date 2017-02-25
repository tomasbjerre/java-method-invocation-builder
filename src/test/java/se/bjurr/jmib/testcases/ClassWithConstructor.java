package se.bjurr.jmib.testcases;

import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_IN_CONSTRUCTOR;

import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder(style = SUPPLY_INSTANCE_IN_CONSTRUCTOR)
public class ClassWithConstructor {
  private final Integer age;

  public ClassWithConstructor(Integer age) {
    this.age = age;
  }

  public Integer getAgeAfter(Integer years) {
    return this.age + years;
  }
}
