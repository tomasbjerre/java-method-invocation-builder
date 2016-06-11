package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestClassCustomTypeBuilder {
  private CustomType customType;

  private RestClassCustomTypeBuilder() {
  }

  public RestClassCustomTypeBuilder withCustomType(final CustomType customType) {
    this.customType = customType;
    return this;
  }

  public static RestClassCustomTypeBuilder customType() {
    return new RestClassCustomTypeBuilder();
  }

  public String invoke(final RestClass instance) {
    return instance.customType(customType);
  }
}
