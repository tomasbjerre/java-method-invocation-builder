package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AClassCustomTypeBuilder {
  private CustomType customType;

  private AClassCustomTypeBuilder() {
  }

  public AClassCustomTypeBuilder withCustomType(final CustomType customType) {
    this.customType = customType;
    return this;
  }

  public static AClassCustomTypeBuilder customType() {
    return new AClassCustomTypeBuilder();
  }

  public String invoke(final AClass instance) {
    return instance.customType(customType);
  }
}
