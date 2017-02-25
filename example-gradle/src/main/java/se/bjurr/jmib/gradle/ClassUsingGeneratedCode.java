package se.bjurr.jmib.gradle;

import static se.bjurr.jmib.gradle.ASimpleClassGetSomethingBuilder.getSomething;

public class ClassUsingGeneratedCode {
  public static void main(String[] args) {

    ASimpleClass aSimpleClass = new ASimpleClass();

    getSomething() //
        .withSomeString("someString") //
        .invoke(aSimpleClass);
  }
}
