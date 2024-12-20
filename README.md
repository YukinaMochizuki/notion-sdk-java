# Notion in Java
An SDK focused on making [Notion API](https://developers.notion.com/reference/intro) simple.

**Not recommended for production environment**<br>
The basic functions of this project are still under development, and it is only recommended to try them in non-critical applications.

## Project Structure
This project is composed of the following parts:

### Model and Static Method
Contains all data structures and endpoint definitions used by Notion API. Most of the data structure has added static factories for use. There are also many serializers and deserializers in this package to ensure that the model meets the requirements of the Notion API.

- See the [implementation status of Notion API](https://www.notion.so/Implementation-status-of-Notion-API-9d9a1e71dc4f4c75af0f173ed09bc215)

### API Client
Responsible for initializing and managing HTTP Client and JSON Processor. It also provide log output and more readable exception classes.

This is a well-configured client, developers can easily implement Notion API endpoint operations through API Client in default setting. However, using API Client requires developers to be sufficiently familiar with Notion API and its rules, or you may be confused with many methods and usage rules.

> For example, page is actually a type of block, so if you want to delete a page, you should use Delete a block endpoint

### Notion Cient
Notion Client will organize low-level API operations to provide an intuitive and friendly CRUD development experience.

It will package the model into an entity. Similar to the session of the ORM framework, developers do not need to care about the update and remove operations of data in most cases. Notion Client will take care of these for you.

> **Notice**: Notion Client is still in the early stages of development.

## Getting started
### Setup

**Gradle**
```groovy
compile 'tw.yukina.notion.sdk:notion-sdk-java:0.0.4'
```

**Maven**
https://mvnrepository.com/artifact/tw.yukina.notion.sdk/notion-sdk-java
```xml
<dependency>
  <groupId>tw.yukina.notion.sdk</groupId>
  <artifactId>notion-sdk-java</artifactId>
  <version>1.0.0</version>
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

### Usage

#### Notion Client

In Notion Client, you don't need to explicitly call update or remove methods. Just remember to flush Notion Client at the end of the transaction.

```java
import tw.yukina.notion.sdk.client.NotionClient;
import tw.yukina.notion.sdk.client.NotionClientImpl;
import tw.yukina.notion.sdk.model.page.Page;
import tw.yukina.notion.sdk.model.page.property.EmailProperty;

NotionClient notionClient = new NotionClientImpl(yourBotToken);
Page page = notionClient.getPageByUuid(yourPageUuid);

EmailProperty emailProperty = (EmailProperty)page.getPropertyMap().get("Email");
emailProperty.setEmail("1p41p4jejo@gmail.com");

notionClient.flush();
```

#### Manually operate API Client

Use `ApiClientFactory` to create an API Client. The `applyDefaultSetting` method will handle most of the client settings.

```java
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.api.ApiClientFactory;

ApiClientFactory apiClientFactory = new ApiClientFactory();
apiClientFactory.setToken(yourBotToken);
apiClientFactory.applyDefaultSetting();
ApiClient apiClient = apiClientFactory.build();
```

Then you can directly operate the Notion API endpoint like this.

```java
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;

ResponseBlockList responseBlockList = apiClient
      .retrieveBlockChildren("889530e779fa4f6096ff4c5ad4edcfac");

List<Block> blocks = responseBlockList.getBlocks();
for(Block block: blocks)System.out.println(block.toString());
```

Now let's add a Heading with italics and color to the page:

```java
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.block.heading.HeadingOneBlock;
import tw.yukina.notion.sdk.model.helper.HeadingBlockHelper;
import tw.yukina.notion.sdk.model.endpoint.block.RequestAppendChildrenBlockList;

TextBuilder textBuilder = new TextBuilder();
textBuilder.setContent("Hello world").setItalic().setColor(TextColor.GREEN);

HeadingOneBlock headingOneBlock = HeadingBlockHelper
      .createDefaultHeadingOne(Arrays.asList(textBuilder.build()));

RequestAppendChildrenBlockList requestBlocks = new RequestAppendChildrenBlockList();
requestBlocks.getBlocks().add(headingOneBlock);

apiClient.appendBlockChildren("889530e779fa4f6096ff4c5ad4edcfac", requestBlocks);
```

It will look [like this](https://dented-fang-21f.notion.site/Supported-block-889530e779fa4f6096ff4c5ad4edcfac#1acb276b755f48bca3bb955f4ee05e0a) after adding it.

### Known issues
- When executing in java 11 or above, an illegal reflective access warning may appear. This is caused by our code generation library [cglib](https://github.com/cglib/cglib). In the near future, [ByteBuddy](https://github.com/raphw/byte-buddy) will be used instead of cglib to solve this problem.
