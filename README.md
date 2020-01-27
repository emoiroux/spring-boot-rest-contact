# Spring boot RESTful Web API message scheduler

A Spring boot RESTful Web API that schedules messages to be printed in the console.

## Prerequisites

- Install Eclipse with Spring Tools 4
- To test the API, you can install SoapUI (https://www.soapui.org/downloads/soapui.html)

I used awaitility (https://github.com/awaitility/awaitility) to write a test case.
All the rest are spring boot and its libraries.

## Installing

### Import the project in Eclipse 

Import the Git project: https://github.com/emoiroux/spring-boot-rest-message-scheduler.git
 
## Run Spring Boot App

Right-click on the project -> Run As -> Spring Boot App 

## Test the API

Send HTTP Post request to /api/v1/messages/message with the following Json structure :

{"hour":H,"minute":M,"second":S, "message":"MSG"}

With 
- H = hour between 0 and 23 (one-digit number if < 10)
- M = Minute between 0 and 59 (one-digit number if < 10)
- S = Second between 0 and 59 (one-digit number if < 10)
- MSG = message to be scheduled (not empty)

## Running the JUnit tests

- Run src/test/java/com.challenge.scheduler.UserMessageIntegrationTest with ** Junit test 4 **
- For more informations about the test cases, check the comments.

## Authors

* **Etienne Moiroux** - *Initial work* - (https://github.com/emoiroux)
