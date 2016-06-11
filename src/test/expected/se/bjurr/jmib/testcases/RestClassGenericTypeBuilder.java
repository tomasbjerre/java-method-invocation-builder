package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestClassGenericTypeBuilder {
  private GenericType<String> customType;

  private RestClassGenericTypeBuilder() {
  }

  public RestClassGenericTypeBuilder withCustomType(final GenericType<String> customType) {
    this.customType = customType;
    return this;
  }

  public static RestClassGenericTypeBuilder genericType() {
    return new RestClassGenericTypeBuilder();
  }

  public String invoke(final RestClass instance) {
    return instance.genericType(customType);
  }
}
