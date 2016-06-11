package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestInterfaceGenericTypeReturnBuilder {
  private String customType;

  private RestInterfaceGenericTypeReturnBuilder() {
  }

  public RestInterfaceGenericTypeReturnBuilder withCustomType(final String customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceGenericTypeReturnBuilder genericTypeReturn() {
    return new RestInterfaceGenericTypeReturnBuilder();
  }

  public GenericType<String> invoke(final RestInterface instance) {
    return instance.genericTypeReturn(customType);
  }
}
