package se.bjurr.jmib.testcases;

import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder
public class AClass {
 public String customType(//
   CustomType customType) {
  return "";
 }

 public String defaultValues(//
   @Default("PROJ") String projectKey,//
   @Default("other") String other,//
   String at) {
  return "";
 }

 public String genericType(//
   GenericType<String> customType) {
  return null;
 }

 String packagePrivate(//
   @Default("PROJ") String projectKey) {
  return "";
 }
}
