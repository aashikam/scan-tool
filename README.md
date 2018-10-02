# Automating Multiple Usage Detection

I have implemented the tool as an MSF4J microservice which exposes two endpoints to populate the database and to query the data in the database for a given method name. The method usages are extracted using the ASM Java Bytecode Manipulation and Analysis Framework. The application scans jar files and extracts the details about all the method invocations including the java util methods. All the extracted method references are stored in a database which was implemented as a mongodb database.
## Built With

* [WSO2 MSF4J](https://github.com/wso2/msf4j) - WSO2 Microservices Framework for Java (MSF4J)
* [MongoDB](https://github.com/jknack/handlebars.java) - Handlebars.java is a Java port of Handlebars.js.
* [ASM](https://handlebarsjs.com/) - Javascript template engine language. 

## Prerequisites 
 
* Install Oracle Java SE Development Kit (JDK) version 1.8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html 
* Install Maven: https://maven.apache.org/install.html

## Configurations 

In order to add any new input adapters update the config.properties file with the corresponding file type extension and the path to the input adapter. Below example is given for the excel (.xlsx file type) input adapter. 

```
xlsx=org.wso2.security.tool.adapter.ExcelInputAdapter
```

## How to Run

From this directory, run

```
mvn clean install
```

From the target directory, run

```
java -jar feedback-tool-1.0.jar
```

## How to Test

To test the service you can use a simple html form like below or a rest client e.g. Postman, Advanced RestClient.




