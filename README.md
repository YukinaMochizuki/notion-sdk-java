## Project Set Up

Gradle:
```groovy
compile 'tw.yukina.notion.sdk:notion-sdk-java:0.0.2'
```

Maven:
```xml
<dependency>
  <groupId>tw.yukina.notion.sdk</groupId>
  <artifactId>notion-sdk-java</artifactId>
  <version>0.0.2</version>
</dependency>
```

### Known issues
- When executing in java 11 or above, an illegal reflective access warning may appear. This is caused by [cglib](https://github.com/cglib/cglib). In the near future, [ByteBuddy](https://github.com/raphw/byte-buddy) will be used instead of cglib to try to solve this problem.