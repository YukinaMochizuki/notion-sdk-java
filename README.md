# Notion in Java


## Project Structure
This project is composed of the following parts:

### Model and Static Method Define
Contains all data structures and endpoint definitions used by Notion API. And most of the data structure has added static factories for use. There are also many serializers and deserializers in this package to ensure that the model meets the requirements of the Notion API.

- See the [implementation status of Notion API](https://www.notion.so/Implementation-status-of-Notion-API-9d9a1e71dc4f4c75af0f173ed09bc215)

### API Client
Responsible for initializing and managing HTTP Client and JSON Processor. It also provide log output and more friendly exception classes. 

This is a well-configured client, developers can easily implement Notion API endpoint operations through API Client in default setting. However, using API Client requires developers to be sufficiently familiar with Notion API and its rules, or you may be confused with many methods and usage rules. 

> For example, page is actually a type of block, so to delete a page, you should use Delete a block endpoint.

### Notion Cient


## Usage
### Setup

**Gradle**
```groovy
compile 'tw.yukina.notion.sdk:notion-sdk-java:0.0.1'
```

**Maven**
```xml
<dependency>
  <groupId>tw.yukina.notion.sdk</groupId>
  <artifactId>notion-sdk-java</artifactId>
  <version>0.0.1</version>
</dependency>
```

If your development environment does not include logging library, you may need to add one:

**Gradle**
```groovy
compile 'ch.qos.logback:logback-classic:1.2.6'
```

**Maven**
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.6</version>
</dependency>
```


### Known issues
- When executing in java 11 or above, an illegal reflective access warning may appear. This is caused by our code generation library [cglib](https://github.com/cglib/cglib). In the near future, [ByteBuddy](https://github.com/raphw/byte-buddy) will be used instead of cglib to solve this problem.
