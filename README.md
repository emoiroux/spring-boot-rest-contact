# Spring boot RESTful Web API message scheduler

A Spring boot RESTful Web API that schedules messages to be printed in the console.

## Prerequisites

- Install Eclipse With Spring Tools 4
- To test the API, you can install SoapUI (https://www.soapui.org/downloads/soapui.html)

I used awaitility (https://github.com/awaitility/awaitility) to perform some Junit test.

## Installing

### Import the project in Eclipse 

Import Git project from https://github.com/emoiroux/spring-boot-rest-scheduler.git
 
## Run Spring Boot App

Right click on the project -> Run As -> Spring Boot App 

## Test the API

Send HTTP Post request to /api/v1/messages/message with the following Json structure :

{"hour":H,"minute":M,"second":S, "message":"MSG"}

With 
- H = hour betwee 0 and 23 (one-digit number if < 10)
- M = Minute betwee 0 and 59 (one-digit number if < 10)
- S = Second betwee 0 and 59 (one-digit number if < 10)
- MSG = message to be scheduled (not empty)

## Running the JUnit tests

- Run src/test/java/com.challenge.scheduler.UserMessageIntegrationTest with Junit test 4
- For more info about, check the documentation comments in the class.

## Authors

* **Etienne Moiroux** - *Initial work* - (https://github.com/emoiroux)