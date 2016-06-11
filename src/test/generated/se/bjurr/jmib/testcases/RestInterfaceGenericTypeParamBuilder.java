package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestInterfaceGenericTypeParamBuilder {
  private GenericType<String> customType;

  private RestInterfaceGenericTypeParamBuilder() {
  }

  public RestInterfaceGenericTypeParamBuilder withCustomType(final GenericType<String> customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceGenericTypeParamBuilder genericTypeParam() {
    return new RestInterfaceGenericTypeParamBuilder();
  }

  public String invoke(final RestInterface instance) {
    return instance.genericTypeParam(customType);
  }
}
