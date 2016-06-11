package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestInterfaceDefaultValuesBuilder {
  private String projectKey;

  private String other;

  private String at;

  private RestInterfaceDefaultValuesBuilder() {
    this.projectKey = "PROJ";
    this.other = "other";
  }

  public RestInterfaceDefaultValuesBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public RestInterfaceDefaultValuesBuilder withOther(final String other) {
    this.other = other;
    return this;
  }

  public RestInterfaceDefaultValuesBuilder withAt(final String at) {
    this.at = at;
    return this;
  }

  public static RestInterfaceDefaultValuesBuilder defaultValues() {
    return new RestInterfaceDefaultValuesBuilder();
  }

  public String invoke(final RestInterface instance) {
    return instance.defaultValues(projectKey,other,at);
  }
}
