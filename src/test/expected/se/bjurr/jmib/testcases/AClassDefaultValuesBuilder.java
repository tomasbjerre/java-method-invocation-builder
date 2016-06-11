package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AClassDefaultValuesBuilder {
  private String projectKey;

  private String other;

  private String at;

  private AClassDefaultValuesBuilder() {
    this.projectKey = "PROJ";
    this.other = "other";
  }

  public AClassDefaultValuesBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public AClassDefaultValuesBuilder withOther(final String other) {
    this.other = other;
    return this;
  }

  public AClassDefaultValuesBuilder withAt(final String at) {
    this.at = at;
    return this;
  }

  public static AClassDefaultValuesBuilder defaultValues() {
    return new AClassDefaultValuesBuilder();
  }

  public String invoke(final AClass instance) {
    return instance.defaultValues(projectKey,other,at);
  }
}
