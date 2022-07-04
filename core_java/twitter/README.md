# Introduction
This is a Java application that interacts with Twitter REST API to perform create, read and delete (CRD) operations of a tweet of a designated Twitter account. The Twitter account used to perform the CRD operation is indicated by the keys and tokens of OAuth 1.0. Each operation involves sending an HTTP request using URI to the Twitter REST API and its response is received as a JSON object. The libraries utilized include Jackson and Mockito. Handling of all dependencies and structuring of the project was accomplished through the use of Maven. Finally, the project was deployed using Docker.

# Quick Start
### Generate Jar and run the application through Uber Jar file
<!--- how to package your app using mvn?-->
First, clone the app to your local machine. Then, set the environment variables for OAuth 1.0
- `consumerKey`: The consumer key or called API key
- `consumerSecret`: Consumer Secrete or called API secrete key
- `accessToken`: The access token for read and write access
- `tokenSecret`: The secret key for access token

Then, package your application through mvn:
```
mvn clean package
```
Above input to the terminal at the directory of the app will generate Uber Jar. Then, run the application through following command:

```
java -jar twitter-1.0-SNAPSHOT.jar post|show|delete [options]
```

<!--- how to run your app with docker?-->
### Running through Docker
Generate Jar file. Then run the following command:
```
docker build -t ${docker_user}/twitter .
```
This will generate the runnable docker container. Then run:
```
docker run --rm \
-e consumerKey=YOUR_VALUE \
-e consumerSecret=YOUR_VALUE \
-e accessToken=YOUR_VALUE \
-e tokenSecret=YOUR_VALUE \
<username>/twitter show|post|delete [options]
```

# Design
## UML diagram
![UML Diagram](./assets/twitter_uml_diagram.png)

## explain each component(app/main, controller, service, DAO) (30-50 words each)

## Models

Talk about tweet model
## Spring
- How you managed the dependencies using Spring?
The dependencies in spring is managed through Inversion of Control and Dependency injection. Inversion of Control is principle in software engineering which transfers the control of objects or portions of a program to a container or framework. Dependency Injection is injecting the object that is dependent on to the depending object. Spring uses annotation-based syntax to specificy specific role of each component and marks each component as `Bean` and sets up dependency accordingly. The annotation the spring uses include `@Component`, `@Controller`, `@Service`, and `@Repository`. The `@Autowire` annotation was used before each constructor to indicate Spring that it must inject the dependencies through the constructor.

The application include three different approaches in using Spring. 
- The first approach is written in `TwitterCLIBean` class which is manual construction of each individual beans and setting up dependencies manually. 
- The second approach is written in `TwitterCLIComponentScan` where i set up the component annotation - `@Component`, `@Controller`, `@Service`, and `@Repository` and the spring will automatically set up the dependencies according to a built-in template from spring framework
- The third approach is written in `TwitterCLISpringBoot`. It uses SpringBoot to manage dependencies. Simply writing `@SpringBootApplication` at the main/app component will configure the dependencies automatically. The only required is the source code where the beans are located - `@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")` in this case.

Note that, the deployed version uses the last approach to run the application. 

# Test
How did you test you app using Junit and mockito?
The frameworks used to test this application are Junit4 and Mockito. For each classes, Integration tests and unit tests were written to test functionality of multiple classes as a group and each class' individual methods

The integration testing was written for each classes and it involved testing its functionality when interacting with other dependent classes and the twitter API itself. It made sure that there was no issues when performing cross-classes methods

The Unit testing testing the functionality of each methods. It involved use of mockito to mock other components to rule out its behaviour dependent on other classes. Mockito allowed testing each component in isolated environment


# Deployment
The application was dockerized by deriving 

# Improvements
- Improved frontend, which is the at the user-interface level could have been implemented. Convenient way to input environment variable, and show indication of invalid input would be much helpful to the users 
- Option of current location, instead of manually inputting the coordinates could be helpful to the user 
- Additional feature of retrieving multiple tweets with ID (ex 20 tweets of last tweets) would be helpful. That way, it would be easier for the user to zoom in to the specific tweet through 'show' operation 
