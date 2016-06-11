package se.bjurr.jmib.testcases;

import java.lang.String;
import java.math.BigDecimal;

public final class AnInterfaceBigDecimalInParameterBuilder {
  private BigDecimal customType;

  private AnInterfaceBigDecimalInParameterBuilder() {
  }

  public AnInterfaceBigDecimalInParameterBuilder withCustomType(final BigDecimal customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceBigDecimalInParameterBuilder bigDecimalInParameter() {
    return new AnInterfaceBigDecimalInParameterBuilder();
  }

  public String invoke(final AnInterface instance) {
    return instance.bigDecimalInParameter(customType);
  }
}
