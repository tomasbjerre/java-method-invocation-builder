package se.bjurr.jmib.testcases;

import java.lang.String;
import java.math.BigDecimal;

public final class AnInterfaceBigDecimalInReturnBuilder {
  private GenericType<String> customType;

  private AnInterfaceBigDecimalInReturnBuilder() {
  }

  public AnInterfaceBigDecimalInReturnBuilder withCustomType(final GenericType<String> customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceBigDecimalInReturnBuilder bigDecimalInReturn() {
    return new AnInterfaceBigDecimalInReturnBuilder();
  }

  public BigDecimal invoke(final AnInterface instance) {
    return instance.bigDecimalInReturn(customType);
  }
}
