package se.bjurr.jmib.testcases;

import java.math.BigDecimal;

public final class RestInterfaceIntInReturnBuilder {
  private BigDecimal customType;

  private RestInterfaceIntInReturnBuilder() {
  }

  public RestInterfaceIntInReturnBuilder withCustomType(final BigDecimal customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceIntInReturnBuilder intInReturn() {
    return new RestInterfaceIntInReturnBuilder();
  }

  public int invoke(final RestInterface instance) {
    return instance.intInReturn(customType);
  }
}
