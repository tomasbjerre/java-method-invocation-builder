package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AnInterfaceIntInParameterBuilder {
  private int customType;

  private AnInterfaceIntInParameterBuilder() {
  }

  public AnInterfaceIntInParameterBuilder withCustomType(final int customType) {
    this.customType = customType;
    return this;
  }

  public static AnInterfaceIntInParameterBuilder intInParameter() {
    return new AnInterfaceIntInParameterBuilder();
  }

  public String invoke(final AnInterface instance) {
    return instance.intInParameter(customType);
  }
}
