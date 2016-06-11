package se.bjurr.jmib.model;

import java.util.List;

public class ClassModel {

 private final String classFullyQualified;
 private final List<ClassMethod> methods;
 private final String packageName;

 public ClassModel(String packageName, String classFullyQualified, List<ClassMethod> methods) {
  this.packageName = packageName;
  this.classFullyQualified = classFullyQualified;
  this.methods = methods;
 }

 public String getClassFullyQualifiedName() {
  return this.classFullyQualified;
 }

 public String getClassName() {
  String[] parts = this.classFullyQualified.split("\\.");
  return parts[parts.length - 1];
 }

 public List<ClassMethod> getMethods() {
  return this.methods;
 }

 public String getPackageName() {
  return this.packageName;
 }

 @Override
 public String toString() {
  return "ClassModel [classFullyQualified=" + this.classFullyQualified + ", methods=" + this.methods + ", packageName="
    + this.packageName + "]";
 }

}
