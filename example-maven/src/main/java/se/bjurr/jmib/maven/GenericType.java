package se.bjurr.jmib.maven;

public class GenericType<T> {
  private final T it;

  public GenericType(T it) {
    this.it = it;
  }

  public T getIt() {
    return this.it;
  }
}
