package se.bjurr.jmib.testcases;

import java.lang.String;

public final class AClassPackagePrivateBuilder {
  private String projectKey;

  private AClassPackagePrivateBuilder() {
    this.projectKey = "PROJ";
  }

  public AClassPackagePrivateBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public static AClassPackagePrivateBuilder packagePrivate() {
    return new AClassPackagePrivateBuilder();
  }

  public String invoke(final AClass instance) {
    return instance.packagePrivate(projectKey);
  }
}
