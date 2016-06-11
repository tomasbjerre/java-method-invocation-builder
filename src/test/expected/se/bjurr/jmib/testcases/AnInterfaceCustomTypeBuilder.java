package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AnInterfaceCustomTypeBuilder {
  private CustomType customType;

  private AnInterfaceCustomTypeBuilder() {
  }

  public AnInterfaceCustomTypeBuilder withCustomType(final CustomType customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceCustomTypeBuilder customType() {
    return new AnInterfaceCustomTypeBuilder();
  }

  public String invoke(final AnInterface instance) {
    return instance.customType(customType);
  }
}
