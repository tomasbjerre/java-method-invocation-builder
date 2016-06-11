package se.bjurr.jmib.maven;

import static se.bjurr.jmib.maven.ASimpleClassGetSomethingBuilder.getSomething;

public class ClassUsingGeneratedCode {
 public static void main(String[] args) {

  ASimpleClass aSimpleClass = new ASimpleClass();

  getSomething()//
    .withSomeString("someString")//
    .invoke(aSimpleClass);
 }
}
