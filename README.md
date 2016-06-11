# Java Method Invocation Builder [![Build Status](https://travis-ci.org/tomasbjerre/java-method-invocation-builder.svg?branch=master)](https://travis-ci.org/tomasbjerre/java-method-invocation-builder) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.bjurr.jmib/java-method-invocation-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.bjurr.jmib/java-method-invocation-builder)

This is a Java library that enables automatic generation of builders at compile time. Builders used for invoking methods on instantiated objects. It enables default values of method parameters and is making the invocations readable.

It does not solve the same problem as [Immutables](https://immutables.github.io/) or [POJOBuilder](https://github.com/mkarneim/pojobuilder). They are generating builders for creating instances of types. This is about invoking methods on previously instantiated types.

Sometimes you are forced to invoke methods that take alot of parameters. This makes the invoking code dirty and hard to read. The problem may be solved with a builder. The builder can have default values for each parameter and when you want to call the method, you just supply the parameters you want to change. This library automates the creation of such builders.

There is a Gradle example [here](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/example-gradle).

There is a Maven example [here](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/example-maven).

There are some [examples here](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/src/test/java/se/bjurr/jmib/testcases) that generates [these builders](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/src/test/expected/se/bjurr/jmib/testcases).

## Examples
Here is one small example to quickly show what the tool does. And one, bigger, example that is the real use case that gave the idea to the tool.

### Small example

The repo includes examples of how to setup [Maven](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/example-maven) and/or [Gradle](https://github.com/tomasbjerre/java-method-invocation-builder/tree/master/example-gradle) to do annotation processing. Aside from that you need to annotate the code that should have builders generated.

The library adds the `@GenerateMethodInvocationBuilder` and `@Default` annotations. They can be added to interfaces or classes.
```
@GenerateMethodInvocationBuilder
public class CarService {
 public CarService() {
 }

 public String getCarsByFilter(//
   @Default("Color.BLUE") Color color, //
   @Default("new ProductionYear(2001)") ProductionYear productionYear,//
   @Default("Tomas") String owner//
 ) {
  return "Filtering... " + color + productionYear + owner;
 }
}
```

A [builder will be generated](https://github.com/tomasbjerre/java-method-invocation-builder/blob/master/src/test/expected/se/bjurr/jmib/testcases/CarServiceGetCarsByFilterBuilder.java) so that you can do this:
```
 CarService instance = new CarService();
 String carsByFilter = CarServiceGetCarsByFilterBuilder.getCarsByFilter()//
  .invoke(instance);
```
To invoke it with Blue, 2001 and Tomas. Or set any of the default values to something else:
```
 CarService instance = new CarService();
 String carsByFilter = CarServiceGetCarsByFilterBuilder.getCarsByFilter()//
  .withColor(Color.YELLOW)//
  .invoke(instance);
```

In this case the `CarService` is simply new:ed. But it may also be an interface.
```
@GenerateMethodInvocationBuilder
public interface CarService {
 String getCarsByFilter(//
   @Default("Color.BLUE") Color color, //
   @Default("new ProductionYear(2001)") ProductionYear productionYear,//
   @Default("Tomas") String owner//
 );
}
```

And perhaps you don't even have access to the implementation.
```
public class CarServiceUser {
 @Inject
 private CarServie carService;

 public String someMethod() {
  return CarServiceGetCarsByFilterBuilder.getCarsByFilter()//
   .withColor(Color.YELLOW)//
   .invoke(instance);
 }
}
```

### REST API client with interface

This is the original use case that triggered me to start working with this project. **Note that this project has actually nothing to do with [Retrofit](http://square.github.io/retrofit/) or REST services at all**. It can be used on any class, or interface, instance.

I am using [Retrofit](http://square.github.io/retrofit/) to create a client for BitBucket Server. That project is hosted [here](https://github.com/tomasbjerre/bitbucket-server-java-client). Here is the interface that specifies the *pullrequests* REST resource.

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
