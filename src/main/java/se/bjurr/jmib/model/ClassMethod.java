package se.bjurr.jmib.model;

import java.util.List;

import javax.lang.model.type.TypeMirror;

public class ClassMethod {

 private final String name;
 private final List<ClassMethodParameter> parameters;
 private final TypeMirror returnType;

 public ClassMethod(String name, TypeMirror returnType, List<ClassMethodParameter> parameters) {
  this.name = name;
  this.returnType = returnType;
  this.parameters = parameters;
 }

 public String getName() {
  return this.name;
 }

 public List<ClassMethodParameter> getParameters() {
  return this.parameters;
 }

 public TypeMirror getReturnType() {
  return this.returnType;
 }

 @Override
 public String toString() {
  return "ClassMethod [name=" + this.name + ", parameters=" + this.parameters + ", returnType=" + this.returnType + "]";
 }

}
