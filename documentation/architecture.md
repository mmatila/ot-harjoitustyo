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

The user interface is text-based and contains a few different types of "views" depending on the state of the application. For example user that is logged in has a different view than one who isn't.  

TUI.java in budgetingapp.ui package takes care of the user interface. Only a very small amount of application logic is left for the user interface, such as checking if user is logged in or not.

### Application logic 

All the application logic is handled by service-classes. They convert the user input into a form so that DAOs can be used to retrieve or store wanted data.  

An example of this could be user logging in:
* TUI takes the user input using scanner and sends it over to userService
* userService calls login(username, password) in userDao, which return a success/failure message depending on the correctiveness of inputs
* userService returns the message to be shown to user based on the response received from userDao  

### Main functionalities

All of the functionalities in the application follow the same flow:
- User types in a number equivalent to the functionality
- UI calls one of the Service classes
- Service class calls for a corresponding DAO
- DAO accesses the database and returns or inserts the wanted data  

#### Creating a new account

When chosen the option to create a new account, the flow looks like this  

<img src=https://github.com/mmatila/ot-harjoitustyo/blob/master/documentation/images/createAccountSequence.png width=1000 />  

#### Logging in

When chosen the option to login to an existing account, the flow looks like this  

<img src=https://github.com/mmatila/ot-harjoitustyo/blob/master/documentation/images/loginSequence.png width=1000 /> 

#### Adding category or expense

Both adding a category and adding an expense follow pretty much the same flow that looks like this  

<img src=https://github.com/mmatila/ot-harjoitustyo/blob/master/documentation/images/addCategorySequence.png width=1000 />

### Storing information

Application uses a simple database implementation to store information about users, categories and expenses. 

#### Database schema

<img src=https://github.com/mmatila/ot-harjoitustyo/blob/master/documentation/images/databaseSchema.png/>
