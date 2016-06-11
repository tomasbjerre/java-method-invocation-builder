package se.bjurr.jmib.testcases;

import java.lang.String;
import java.math.BigDecimal;

public final class RestInterfaceBigDecimalInParameterBuilder {
  private BigDecimal customType;

  private RestInterfaceBigDecimalInParameterBuilder() {
  }

  public RestInterfaceBigDecimalInParameterBuilder withCustomType(final BigDecimal customType) {
    this.customType = customType;
    return this;
  }

  public static RestInterfaceBigDecimalInParameterBuilder bigDecimalInParameter() {
    return new RestInterfaceBigDecimalInParameterBuilder();
  }

  public String invoke(final RestInterface instance) {
    return instance.bigDecimalInParameter(customType);
  }
}
