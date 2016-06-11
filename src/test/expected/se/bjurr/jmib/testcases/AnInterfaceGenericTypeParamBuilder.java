package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AnInterfaceGenericTypeParamBuilder {
  private GenericType<String> customType;

  private AnInterfaceGenericTypeParamBuilder() {
  }

  public AnInterfaceGenericTypeParamBuilder withCustomType(final GenericType<String> customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceGenericTypeParamBuilder genericTypeParam() {
    return new AnInterfaceGenericTypeParamBuilder();
  }

  public String invoke(final AnInterface instance) {
    return instance.genericTypeParam(customType);
  }
}
