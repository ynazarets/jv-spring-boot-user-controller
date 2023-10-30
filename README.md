# Implement a simple User Management REST API

In this task, you are required to create a simple REST API for managing users. You will be working with Spring Boot to
set up the application and create the necessary endpoints.

### Requirements:

- Verify all necessary dependencies (starters) are added to the `pom.xml`.

- There is a class UserController. Complete its implementation to have the following endpoints:

    - GET: `/users` - will return the hardcoded list of two
      users: `List.of(new User(1L, "bob@i.ua"), new User(2L, "alice@i.ua"))`
    - POST: `/users` - will accept User object as an input data, and return the String in the following
      format: `User created. Id: %s, email: %s`, where instead of `%s` the received user id and email should be provided

### Testing Your Application:

- Once you have implemented the above functionalities, test your application to ensure everything is working as
  expected.
- You can use tools like Postman or curl to send HTTP requests to your endpoints.
- There is one test present in this project. Don't deep dive into what is happening there, we will learn how to write
  tests later.
