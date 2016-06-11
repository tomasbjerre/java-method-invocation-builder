package se.bjurr.jmib.testcases;

import java.math.BigDecimal;

public final class AnInterfaceIntInReturnBuilder {
  private BigDecimal customType;

  private AnInterfaceIntInReturnBuilder() {
  }

  public AnInterfaceIntInReturnBuilder withCustomType(final BigDecimal customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceIntInReturnBuilder intInReturn() {
    return new AnInterfaceIntInReturnBuilder();
  }

  public int invoke(final AnInterface instance) {
    return instance.intInReturn(customType);
  }
}
