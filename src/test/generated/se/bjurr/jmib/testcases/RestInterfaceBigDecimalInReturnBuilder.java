package se.bjurr.jmib.testcases;

import java.lang.String;
import java.math.BigDecimal;

public final class RestInterfaceBigDecimalInReturnBuilder {
  private GenericType<String> customType;

  private RestInterfaceBigDecimalInReturnBuilder() {
  }

  public RestInterfaceBigDecimalInReturnBuilder withCustomType(final GenericType<String> customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceBigDecimalInReturnBuilder bigDecimalInReturn() {
    return new RestInterfaceBigDecimalInReturnBuilder();
  }

  public BigDecimal invoke(final RestInterface instance) {
    return instance.bigDecimalInReturn(customType);
  }
}
