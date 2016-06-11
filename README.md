# Java Method Invocation Builder [![Build Status](https://travis-ci.org/tomasbjerre/java-method-invocation-builder.svg?branch=master)](https://travis-ci.org/tomasbjerre/java-method-invocation-builder) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.bjurr.jmib/java-method-invocation-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.bjurr.jmib/java-method-invocation-builder)

This is a Java library that enables builders, for invoking methods on instantiated objects, to be generated.

Sometimes you are forced to invoke methods that take alot of parameters. This makes the invoking code dirty and hard to read. The problem may be solved with a builder. The builder can have default values for each parameter and when you want to call the method, you just supply the parameters you want to change. This library automates the creation of such builders.

There are some example builders generated [here](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/src/test/expected/se/bjurr/jmib/testcases).

There is a Gradle example [here](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/example-gradle).

There is a Maven example [here](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/example-maven).

## Use case
This is the original use case that triggered me to start working with this project.

I am using [Retrofit](http://square.github.io/retrofit/) to create a client for BitBucket Server. Here is the interface that specifies the *pullrequests* REST resource.

```
public interface BitBucketServerService {
 @GET("/rest/api/1.0/projects/{projectkey}/repos/{repositoryslug}/pull-requests?direction={direction}&at={at}&state={state}&order={order}&withattributes={withattributes}&withproperties={withproperties}")
 Call<BitbucketServerResponse<BitBucketServerPullRequest>> pullRequests(//
   @Query("projectkey") String projectKey,//
   @Query("repositoryslug") String repositoryslug,//
   @Query("direction") String direction,//
   @Query("at") Integer at,//
   @Query("state") String state,//
   @Query("order") String order,//
   @Query("withattributes") String withattributes,//
   @Query("withproperties") String withproperties);
}
```

When invoking that method without this library it would look something like this.
```
bitBucketServerService.pullRequests("PROJ","REPO","INCOMING","23","OPEN","NEWEST","true","true");
bitBucketServerService.pullRequests("PROJ","REPO","INCOMING","24","OPEN","NEWEST","true","true");
bitBucketServerService.pullRequests("PROJ","REPO","INCOMING","25","OPEN","NEWEST","true","true");
```

This library provides the `@GenerateMethodInvocationBuilder` annotation that can be added to a type like this.
```
@GenerateMethodInvocationBuilder
public interface BitBucketServerService {
 @GET("/rest/api/1.0/projects/{projectkey}/repos/{repositoryslug}/pull-requests?direction={direction}&at={at}&state={state}&order={order}&withattributes={withattributes}&withproperties={withproperties}")
 Call<BitbucketServerResponse<BitBucketServerPullRequest>> pullRequests(//
   @Default("PROJ") @Query("projectkey") String projectKey,//
   @Default("REPO") @Query("repositoryslug") String repositoryslug,//
   @Default("INCOMING") @Query("direction") String direction,//
   @Default("23") @Query("at") String at,//
   @Default("OPEN") @Query("state") String state,//
   @Default("NEWEST") @Query("order") String order,//
   @Default("true") @Query("withattributes") String withattributes,//
   @Default("true") @Query("withproperties") String withproperties);
}
```

A builder is automatically generated and we can do.
```
BitBucketServerServicePullRequestsBuilder.pullRequests()
 .invoke(bitBucketServerService);

BitBucketServerServicePullRequestsBuilder.pullRequests()
 .withAt("24")
 .invoke(bitBucketServerService);

BitBucketServerServicePullRequestsBuilder.pullRequests()
 .withAt("25")
 .invoke(bitBucketServerService);
```

## Developer instructions

To build the code, have a look at `.travis.yml`.

To do a release you need to do `./gradlew release` and release the artifact from [staging](https://oss.sonatype.org/#stagingRepositories). More information [here](http://central.sonatype.org/pages/releasing-the-deployment.html).
