# grocery-management-service
An API which acts like a store management service for a grocery.

## Prerequisites
* Java 17 (or newer)
* Maven
* An IDE
* Postman

##Deployment
It's a simple Spring Boot Aplication, could run directly from IDE

##Testing
* Import the GroceryManagementService.postman_collection.json into Postman.
* The collection contains the url (http://localhost:8080/products) and different parameters depending on method and type of request;
* The authorization is of type basic auth for 2 users: admin and user(both with **secret** as password);
* **user** is able just to get data:
####GetAllProducts
####GetProductByType
* **admin** could execute all operations available:
####GetAllProducts
####GetProductByType
####AddNewProduct
####ChangePrice
####DeleteProduct


