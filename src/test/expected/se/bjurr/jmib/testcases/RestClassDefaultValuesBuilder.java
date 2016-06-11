package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestClassDefaultValuesBuilder {
  private String projectKey;

  private String other;

  private String at;

  private RestClassDefaultValuesBuilder() {
    this.projectKey = "PROJ";
    this.other = "other";
  }

  public RestClassDefaultValuesBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public RestClassDefaultValuesBuilder withOther(final String other) {
    this.other = other;
    return this;
  }

  public RestClassDefaultValuesBuilder withAt(final String at) {
    this.at = at;
    return this;
  }

  public static RestClassDefaultValuesBuilder defaultValues() {
    return new RestClassDefaultValuesBuilder();
  }

  public String invoke(final RestClass instance) {
    return instance.defaultValues(projectKey,other,at);
  }
}
