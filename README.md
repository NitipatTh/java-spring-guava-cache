# Guava Cache - Spring Boot
The Guava Cache implementation - basic usage and refreshing the cache and some bulk operations in a **spring boot**. 
This project try to download a bulk JSON string of list of movies in the first, then save to in memory using guava cache.

## Dependency
Latest release is **`27.0.1`**. To use the core module, you can import the following dependency in Maven.

```xml
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>27.0.1-jre</version>
</dependency>
```
Modelmapper-spring, I use for mapping JSON string to dto.

```xml
<dependency>
    <groupId>org.modelmapper.extensions</groupId>
    <artifactId>modelmapper-spring</artifactId>
    <version>2.3.0</version>
</dependency>
```
