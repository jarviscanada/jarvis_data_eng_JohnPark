# Introduction
This is a Java application that interacts with Twitter REST API to perform create, read and delete (CRD) operations of a tweet of a designated Twitter account. The Twitter account used to perform the CRD operation is indicated by the keys and tokens of OAuth 1.0. Each operation involves sending an HTTP request using URI to the Twitter REST API and its response is received as a JSON object. The libraries utilized include Jackson and Mockito. Handling of all dependencies and structuring of the project was accomplished through the use of Maven. Finally, the project was deployed using Docker.

# Quick Start
- how to package your app using mvn?
```
mvn clean package
```

```
java -jar twitter-1.0-SNAPSHOT.jar post|show|delete [options]
```
- how to run your app with docker?

# Design
## UML diagram
## explain each component(app/main, controller, service, DAO) (30-50 words each)
## Models
Talk about tweet model
## Spring
- How you managed the dependencies using Spring?

# Test
How did you test you app using Junit and mockito?

## Deployment
How did you dockerize your app.

# Improvements
- Imporvement 1
- Imporvement 2
- Imporvement 3
