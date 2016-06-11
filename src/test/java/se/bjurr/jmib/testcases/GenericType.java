package se.bjurr.jmib.testcases;

public class GenericType<T> {
 private final T it;

 public GenericType(T it) {
  this.it = it;
 }

 public T getIt() {
  return this.it;
 }
}
