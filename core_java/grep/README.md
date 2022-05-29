# Introduction
This is an app that performs the basic functionality of the grep command from Linux. With a given path to a directory, regex expression and an output file, the app will recursively look through all files in all depth within the directory and look for lines that match the regex expression given and store them in the given output file. There are two versions of the app one using simple Java language and the other using lambda and stream API. The app was mainly developed using IntelliJ IDE with help of Maven. Then, the app is containerized and its image is deployed on the docker hub.

# Quick Start
Download and install IntelliJ as instructed from the IntelliJ website. Then, Download the code from here and set up a proper pom.xml file. The pom.xml file is as follows:  
  
```
<project>
  <modelVersion>4.0.0</modelVersion> 
  <groupId>ca.jrvs.apps</groupId>
  <artifactId>grep</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.28</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <artifactId>maven-shade-plugin</artifactId>
        <executions>
          <!-- Run shade goal on package phase -->
          <execution>
            <configuration>
              <transformers>
                <!-- add Main-Class to manifest file -->
                <transformer
                  implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <mainClass>ca.jrvs.apps.grep.JavaGrepImpl</mainClass>
                </transformer>
              </transformers>
            </configuration>
            <goals>
              <goal>shade</goal>
            </goals>
            <phase>package</phase>
          </execution>
        </executions>
        <groupId>org.apache.maven.plugins</groupId>
        <version>2.3</version>
      </plugin>
    </plugins>
  </build>
</project>
```

Store this pom.xml file in `grep` folder. 

##1. Using IntelliJ IDE 
Ensure Maven structure project format is applied. Using intelliJ navigate to either `JavaGrepImpl` or `JavaGrepLambdaImpl` using the project tab on the left. Then, set the run configuration with the arguments `<regex pattern> <source folder> <output file>`. Then, run the main class in the file.  

##2. Using `Jar` file 
If one wishes to run the app through the `Jar` file, first build a `Uber/Fat Jar` file for the app:
```
mvn clean package
```
Then, run the following command:

```
java -jar ./target/grep-1.0-SNAPSHOT.jar <regex expression> <path to folder> <path to output file>
```

##3. Using Docker
Pull the image from the docker hub,
```
docker pull johnphk/grep
```
Run the image is a container
```
docker run --rm \
-v <path to source folder in host machine>:<path to source folder in container> \
-v <path to output folder in host machine>:<path output folder in container> \
johnphk/grep <regex pattern> <source folder> <output folder>
```


#Implemenation
The implementation involves looking through all files recursively and listing them in an array. Then, the app loops through each file and checks through every line to get the ones that match the regex pattern given in the argument. Then, it is appended to the file indicated in the third argument. If the out file does not exist, the app creates a new one. 

## Pseudocode
```
let matchedLines[..] be a new array
for file in array of recursively listed files from the source folder
    for line in array of all lines from file
        if line matches regex
            matchedLines.append(line)
return matchedLines
```

## Performance Issue
If there is a file that is almost as large as or larger than the memory allocated for the running app, the program encounters an out-of-memory error. This is because the app stores the content of the file to an array so it can iterate and find the line that matches the regex expression. Therefore, the file size must be reasonably smaller than the memory allocated for the app.

The issue can be mitigated by using a stream data structure. Instead of uploading each line into an array of strings, upload the content of the file into a stream\<String>. Stream does not store, it simply conveys elements from a source through a pipeline of computational operations. Therefore, it will optimize the memory usage better from the app.  

# Test
Created arbitrary text files with and without lines containing the certain regex expression. Ran the app on these files multiple times and ensured that the output was correct. 

# Deployment
Make sure maven is properly installed and add shade plugin to it. Run,
```
cat > Dockerfile << EOF
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
EOF
```
The above instructs docker to create a layer of `openjdk:8-alpine`. Openjdk is an open-source implementation of the Java platform and 8-alpine is the version of openjdk. It then commands to copy the jar file to your Docker client’s current directory to be pushed. The last line contains the command with parameters the docker will run.

Compose a jar file by running the following command,
```
mvn clean package
```
Build a docker image by
```
docker build -t ${docker_user}/grep .
```
Then, finally push to the docker hub
```
docker push ${docker_user}/grep
```

# Improvement
1. Having proper unit tests with a framework would be a better method to test the app
2. This app is a very primitive version of grep command from Linux. It could be great if the app has a feature that the original grep command does not contain. ex. showing the size of lines containing the regex pattern
3. Use the multithreaded program so that each file from the files list is handled by each thread
