# Architecture

## Overall structure

<img src=https://github.com/mmatila/ot-harjoitustyo/blob/master/documentation/images/ClassArchitecture.png/>

### Packages

* "budgetingapp.domain" contains all the objects used in the application
* "budgetingapp.dao" contains the data access objects
  * All communication with database is here
  * Database.java connects the application to the database and passes it to the daos
* "budgetingapp.services" communicates with daos and takes care of most of the application logic
  * Service classes use the daos and modify the sent or received data
* "budgetingapp.ui" takes care of the text-based user interface

### User interface

Right now the user interface is text-based and contains a few different types of "views" depending on the state of the application. 

### Application logic



### Storing information

Application uses a database to store information about users, categories and expenses.

#### Database schema 

<img src=https://github.com/mmatila/ot-harjoitustyo/blob/master/documentation/images/databaseSchema.png/>
