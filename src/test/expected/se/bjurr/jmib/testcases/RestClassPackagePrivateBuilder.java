package se.bjurr.jmib.testcases;

import java.lang.String;

public final class RestClassPackagePrivateBuilder {
  private String projectKey;

  private RestClassPackagePrivateBuilder() {
    this.projectKey = "PROJ";
  }

  public RestClassPackagePrivateBuilder withProjectKey(final String projectKey) {
    this.projectKey = projectKey;
    return this;
  }

  public static RestClassPackagePrivateBuilder packagePrivate() {
    return new RestClassPackagePrivateBuilder();
  }

  public String invoke(final RestClass instance) {
    return instance.packagePrivate(projectKey);
  }
}
