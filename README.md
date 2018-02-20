# Code Challenge - True North (Campsite RESTfull API)

Camspite REST API requires [java 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) to run.

### Comments and Considerations
In this project I used Spring Boot, Spring Data Rest, SpringFoxConfiguration (Swagger 2), PostgreSQL, REST Assured.
ETag support in Spring was also used.

1. The Junit were written to test the RESTfull API. Currently if you use the ```mvn clean package``` to build, it will fail. It is necessary start up the app first.
In this case you can build with:
```mvn package clean -Dmaven.test.skip=true```

2. Once finished successfully, copy the ```true-north-test\src\main\resources\application.properties``` file into the ```\true-north-test\target\``` folder

3. To start its neccesary create a new PostgreSQL database. In this case I used "campsite" as a DB name, and my local PostgreSQL is on port 5432. 
Configure this according to your local environment.

Before start the application, the following properties must be modified.

In case your DB is called "campsite" and your PostgreSQL is running on port 5432 do not modify the value of spring.datasource.url
```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/campsite
```

Complete with your authentication data to PostgreSQL: username / password. 
```sh
spring.datasource.username=
spring.datasource.password=
```

4. From a command line, standing inside the ```\true-north-test\target\``` execute ```java -jar campsite-0.0.1-SNAPSHOT.jar```, the app starts.

5. By default the app start at 8080 port. To view all the services exposed in this REST API access to:
```sh
http://localhost:8080/swagger-ui.html
```

## With the App started

Spring Data Rest generated methods: 
```sh
POST 	/api/reservation      > Create a Reservation.

DELETE 	/api/reservation/{id} > Cancel a Reservation.

GET 	/api/reservation/{id} > Get a Reservation.

GET 	/api/reservation/{dateStart}/{dateEnd}, Get reservations between a date range.

GET 	/api/reservation/default > Get reservation list, default being 1 month

PUT 	/api/reservation/{id} > Edit a Reservation.

PATCH 	/api/reservation/{id} > Update partial Reservation.
```

Having said that, I know that I am not following some best practices in the design of a REST API.
I had to create that method, because when trying to define the ```GET /api/reservation``` in my ```@RestController```, the error occurred that POST was no longer available.
```POST /api/reservation```, retrieves: "405 Method Not Allowed".

When a Reservation its created or edited, first a validator check the constrains defined in the system.

In this example, I tried to implement optimistic locking using the ETags (If-None-Match | If-Match) (GET: 304 Not Modified | PUT : 412 Precondition Failed)
Sources : 
> https://stackoverflow.com/questions/36853343/with-spring-data-rest-why-is-the-version-property-becoming-an-etag-and-not-inc

> https://sookocheff.com/post/api/optimistic-locking-in-a-rest-api/