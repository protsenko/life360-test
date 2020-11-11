### Binary logger 

#### How to build
```
mvnw clean install
``` 

#### How to use
Add maven dependency to your project  
```xml
<dependency>
    <groupId>test.life360</groupId>
    <artifactId>logger</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
Add logger to class
```java
BinaryLogger<Event> logger = new FileSizeBinaryLogger("data.bin", 10_000L);
```

#### Explanation

The logger has two working modes:
- write a message to file one by one;
- read all messages from a file.

Before writing the logger checks file size limit condition. If a new potential size less than the limit write
operation is done. If the size exceeds the limit value new file is created.

The current implementation has three main parts:
- logger - operates by messages, decides whether to write the message of not;
- appender - operates by bytes, choose the file for writing;
- reader - read all messages from specified file.   

#### Propositions

##### Filtering

If we need to log specific events only, we can filter events by implementing:
```java
public interface LoggableFilter<T extends BinaryLoggable> {
    boolean match(T loggable);
}
```

##### Configuration
To make logging more flexible, I'd add possibility to configure logger
```java
public interface LoggerConfiguration<T extends BinaryLoggable> {
    List<T> filters();
}
```
and 
```java
private List<T> filters;

public BinaryLoggerImpl(LoggerConfiguration configuration) {
    this.filters = configuration.filters();
}
```