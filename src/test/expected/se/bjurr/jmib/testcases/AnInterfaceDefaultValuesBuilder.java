package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AnInterfaceDefaultValuesBuilder {
  private String projectKey;

  private String other;

  private String at;

  private AnInterfaceDefaultValuesBuilder() {
    this.projectKey = "PROJ";
    this.other = "other";
  }

  public AnInterfaceDefaultValuesBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public AnInterfaceDefaultValuesBuilder withOther(final String other) {
    this.other = other;
    return this;
  }

  public AnInterfaceDefaultValuesBuilder withAt(final String at) {
    this.at = at;
    return this;
  }

  public static AnInterfaceDefaultValuesBuilder defaultValues() {
    return new AnInterfaceDefaultValuesBuilder();
  }

  public String invoke(final AnInterface instance) {
    return instance.defaultValues(projectKey,other,at);
  }
}
