package se.bjurr.jmib.testcases;

import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder
public class ClassWithConstructor {
 private final Integer age;

 public ClassWithConstructor(Integer age) {
  this.age = age;
 }

 public Integer getAgeAfter(Integer years) {
  return this.age + years;
 }
}
