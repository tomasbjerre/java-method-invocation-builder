package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AClassGenericTypeBuilder {
  private GenericType<String> customType;

  private AClassGenericTypeBuilder() {
  }

  public AClassGenericTypeBuilder withCustomType(final GenericType<String> customType) {
    this.customType = customType;
    return this;
  }

  public static AClassGenericTypeBuilder genericType() {
    return new AClassGenericTypeBuilder();
  }

  public String invoke(final AClass instance) {
    return instance.genericType(customType);
  }
}
