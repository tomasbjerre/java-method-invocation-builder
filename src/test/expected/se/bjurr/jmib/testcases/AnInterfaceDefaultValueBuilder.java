package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AnInterfaceDefaultValueBuilder {
  private String projectKey;

  private AnInterfaceDefaultValueBuilder() {
    this.projectKey = "PROJ";
  }

  public AnInterfaceDefaultValueBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public static AnInterfaceDefaultValueBuilder defaultValue() {
    return new AnInterfaceDefaultValueBuilder();
  }

  public String invoke(final AnInterface instance) {
    return instance.defaultValue(projectKey);
  }
}
