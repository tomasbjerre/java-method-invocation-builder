package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestInterfaceDefaultValueBuilder {
  private String projectKey;

  private RestInterfaceDefaultValueBuilder() {
    this.projectKey = "PROJ";
  }

  public RestInterfaceDefaultValueBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public static RestInterfaceDefaultValueBuilder defaultValue() {
    return new RestInterfaceDefaultValueBuilder();
  }

  public String invoke(final RestInterface instance) {
    return instance.defaultValue(projectKey);
  }
}
