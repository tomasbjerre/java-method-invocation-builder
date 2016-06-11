package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestInterfaceCustomTypeBuilder {
  private CustomType customType;

  private RestInterfaceCustomTypeBuilder() {
  }

  public RestInterfaceCustomTypeBuilder withCustomType(final CustomType customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceCustomTypeBuilder customType() {
    return new RestInterfaceCustomTypeBuilder();
  }

  public String invoke(final RestInterface instance) {
    return instance.customType(customType);
  }
}
