package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestInterfaceIntInParameterBuilder {
  private int customType;

  private RestInterfaceIntInParameterBuilder() {
  }

  public RestInterfaceIntInParameterBuilder withCustomType(final int customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceIntInParameterBuilder intInParameter() {
    return new RestInterfaceIntInParameterBuilder();
  }

  public String invoke(final RestInterface instance) {
    return instance.intInParameter(customType);
  }
}
