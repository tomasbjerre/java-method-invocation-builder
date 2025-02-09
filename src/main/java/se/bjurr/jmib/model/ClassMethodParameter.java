package se.bjurr.jmib.model;

import java.util.Optional;
import javax.lang.model.type.TypeMirror;

public class ClassMethodParameter {

  private final String defaultValue;
  private final String name;
  private final TypeMirror type;

  public ClassMethodParameter(TypeMirror type, String name, String defaultValue) {
    this.type = type;
    this.name = name;
    this.defaultValue = defaultValue;
  }

  public Optional<String> getDefaultValue() {
    return Optional.ofNullable(this.defaultValue);
  }

  public String getName() {
    return this.name;
  }

  public TypeMirror getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "ClassMethodParameter [name=" + this.name + ", type=" + this.type + "]";
  }
}
