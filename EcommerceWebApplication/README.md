# Spring Boot Jwt Authentication Security Enabled Ecommerce Rest Application

This is a sample project to provide example on how to add JWT token authentication in a spring boot application.
The example uses maven as a build tool and also sample script to run on application startup so that anybody can get started by simply running Application.java
 
## Technology Used

 1. Spring Boot (1.5.8.RELEASE)
 2. JWT (0.6.0)
 3. H2 Database
 4. Java 1.8
 
 
## Module Involved

REST service for an e-commerce system with the following functionalities


1. Users Module 

    - Login API - Once login is successful, the system should generate a JWT token with a user role within the payload.

    - Register API 

2. Admin Module  

    - Admin should be able to log in with the above Login API 

3. Category API

    -  Admin should be able to Create, View, Update, Delete Categories 

4. Products API

    - Admin should be able to Create, View, Update, Delete Products 

    - Associate product to Category 

    - Product Should Have Following Attributes 

        - Name 

        - Code 

        - Pictures 

        - Category 

        - Price  

5. Cart API

    - Logged In users (Non Admin) should be able to add products to Cart 

    - User should be able to retrieve the cart items and total amount


