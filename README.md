# ddd-bank
This is a sample banking application built using Spring Boot, Thymeleaf,
Spring Data, and MongoDB.

## Setup
The application assumes that you are running an unsecured local MongoDB, which is the
default setup. The database will be bank.
  
If you are not running a local MongoDB, you can add properties to src/main/resources/application.properties 
to configure the connection.

`spring.data.mongodb.host= # Mongo server host.`

`spring.data.mongodb.password= # Login password of the mongo server.`

`spring.data.mongodb.port= # Mongo server port.`

`spring.data.mongodb.username= # Login user of the mongo server.`

## Running the application
To run it, type `gradle bootRun` and navigate to http://localhost:8081. If you don't
have gradle installed, you can run `./gradlew bootRun`.

The username is user and the password is password.

 