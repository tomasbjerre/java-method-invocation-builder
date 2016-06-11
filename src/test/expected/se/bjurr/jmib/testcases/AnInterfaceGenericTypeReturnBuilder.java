package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AnInterfaceGenericTypeReturnBuilder {
  private String customType;

  private AnInterfaceGenericTypeReturnBuilder() {
  }

  public AnInterfaceGenericTypeReturnBuilder withCustomType(final String customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceGenericTypeReturnBuilder genericTypeReturn() {
    return new AnInterfaceGenericTypeReturnBuilder();
  }

  public GenericType<String> invoke(final AnInterface instance) {
    return instance.genericTypeReturn(customType);
  }
}
