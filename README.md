# Password-Validator

Write a password validation service, meant to be configurable via IoC (using dependency
injection engine of your choice). The service is meant to check a text string for compliance
to any number of password validation rules. The rules currently known are:
- Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
- Must be between 5 and 12 characters in length. 
- Must not contain any sequence of characters immediately followed by the same sequence.

# Using Language & Framework & IDE

- Java
- Spring Boot
- Junit / Mockito
- Maven
- Eclipse

# System requirements

- Java/JDK 1.8
- Maven 3.8.X

# How to run

1. Build the project then cd to the target folder.
```
$> mvn clean install
$> cd ./target
```  

2. Run the jar file using following commands.
```
#  This is a Spring Boot project, so a jar will be build with an embedded tomcat server.  
$> java -jar ./passwordvalidator-1.0.0.jar
```
3. The server will start listening on port 8080. Now we can start testing!
4. Send a GET request to the URL below.
```
# You can change the string "password545" to the string you want to validate.
curl -v GET http://localhost:8080/brunch/validate/password/password545
```
5. After sending a GET request. It will return validate result. 
```
# when success: Password verified.
# when fail: Password verify failed. Error message = [Password must be between 5 and 12 characters in length.]
```

# Design

The design is following the `SOLID` principles. The main concept is
- All validation rule implements the `Validator` interface.
- The `ValidateService` collects all the validate rules then execute validations.

So if we have a new validation rule request, we just need to implement the `Validator` interface and give it to `ValidateService`. Then it will apply the new validation rule for the next validation.

# Implementation
1. Following the `SOLID` principles.
2. Using Spring dependency injection annotations.
3. A rest controller as client for password validation service.
4. Unit test & Integration test in the project. When using `mvn clean install`, it will automatically running tests.
5. Using slf4j for logs.

