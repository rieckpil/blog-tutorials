# Minimum Reproducible Example for a Spring Boot + Kotlin + Spring Cloud AWS Lambda Setup

**Fixed since Spring Cloud Function > 3.2.0-M2**: https://github.com/spring-cloud/spring-cloud-function/issues/747

Steps to reproduce:

1. `npm install -g serverless`
2. `mvn package`
3. `sls invoke local -f test`

```
19:04:10.497 [main] INFO org.springframework.cloud.function.utils.FunctionClassUtils - Searching for start class in manifest: jar:file:/Users/rieckpil/Development/git/blog-tutorials/spring-cloud-function-aws-kotlin/./target/spring-cloud-function-aws-kotlin-1.0.0-aws.jar!/META-INF/MANIFEST.MF

Manifest-Version: 1.0
Created-By: Maven Jar Plugin 3.2.0
Build-Jdk-Spec: 11
Implementation-Title: spring-cloud-function-aws-kotlin
Implementation-Version: 1.0.0
Main-Class: org.springframework.boot.loader.wrapper.ThinJarWrapper
Start-Class: de.rieckpil.ApplicationKt

Spring-Boot-Version: 2.5.5
Spring-Boot-Classes:


java.lang.IllegalStateException: Failed to discover main class. An attempt was made to discover main class as 'MAIN_CLASS' environment variable, system property as well as entry in META-INF/MANIFEST.MF (in that order).

        at org.springframework.cloud.function.utils.FunctionClassUtils.getStartClass(FunctionClassUtils.java:83)
        at org.springframework.cloud.function.utils.FunctionClassUtils.getStartClass(FunctionClassUtils.java:60)
        at org.springframework.cloud.function.adapter.aws.FunctionInvoker.start(FunctionInvoker.java:123)
        at org.springframework.cloud.function.adapter.aws.FunctionInvoker.<init>(FunctionInvoker.java:70)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)

        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
        at java.base/java.lang.Class.newInstance(Class.java:584)
        at com.serverless.InvokeBridge.getInstance(InvokeBridge.java:71)
        at com.serverless.InvokeBridge.<init>(InvokeBridge.java:36)
        at com.serverless.InvokeBridge.main(InvokeBridge.java:137)

Caused by: java.lang.IllegalArgumentException: Failed to locate main class
        at org.springframework.util.Assert.notNull(Assert.java:201)
        at org.springframework.cloud.function.utils.FunctionClassUtils.getStartClass(FunctionClassUtils.java:79)

        ... 11 more


```

Second try:

1. Add `MAIN_CLASS` to the `serverless.yml`:
```yaml
# ...
functions:
  test:
    handler: org.springframework.cloud.function.adapter.aws.FunctionInvoker
    environment:
      MAIN_CLASS: org.springframework.boot.loader.wrapper.ThinJarWrapper
```

2. `mvn clean package`
3. `sls invoke local -f test`

```
  .   ____          _            __ _ _

 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v1.0.0)


2021-09-23 19:05:45.381  INFO 49067 --- [           main] com.serverless.InvokeBridge              : Starting InvokeBridge using Java 11.0.11 on Philips-MBP.fritz.box with PID 49067 (/Users/rieckpil/.nvm/versions/node/v15.14.0/lib/node_modules/serverless/lib/plugins/aws/invokeLocal/runtimeWrappers/java/target/invoke-bridge-1.0.1.jar started by rieckpil in /Users/rieckpil/Development/git/blog-tutorials/spring-cloud-function-aws-kotlin)

2021-09-23 19:05:45.382  INFO 49067 --- [           main] com.serverless.InvokeBridge              : No active profile set, falling back to default profiles: default

2021-09-23 19:05:45.427  INFO 49067 --- [           main] com.serverless.InvokeBridge              : Started InvokeBridge in 0.253 seconds (JVM running for 0.533)

org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.cloud.function.context.FunctionCatalog' available

        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:351)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:342)
        at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1172)
        at org.springframework.cloud.function.adapter.aws.FunctionInvoker.start(FunctionInvoker.java:131)
        at org.springframework.cloud.function.adapter.aws.FunctionInvoker.<init>(FunctionInvoker.java:70)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)

        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
        at java.base/java.lang.Class.newInstance(Class.java:584)
        at com.serverless.InvokeBridge.getInstance(InvokeBridge.java:71)
        at com.serverless.InvokeBridge.<init>(InvokeBridge.java:36)

        at com.serverless.InvokeBridge.main(InvokeBridge.java:137)
```

-> Spring Context starts, but fails to inject beans
