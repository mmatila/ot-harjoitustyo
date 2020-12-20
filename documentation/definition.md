# Budgeting application

## What it is

A very basic application for one to keep track of their savings and expenses

## Users

In the beginning there's only going to be one user role: *standard user.*

## Functionalities

### Authentication  

**User must be signed in to use the app's features**  
#### Before signing in
  - User can login to an existing account
  - User can create a new account
    - Username must be unique
    - error if username doesn't exist or password is incorrect  
    
#### After signing in
  - User gets access to all core functionalities
    - Deleting existing account
    - Adding categories
    - Adding expenses & income
    - Listing expenses
  
### Expense tracking  

- Adding new expenses and categorizing them
  - eg. entertainment, food, transportation... 
  - additional information: "Spotify subscription" or "groceries"
  - Listing all expenses and the total amount spent on particular category  

### Other

- Ability to delete authenticated user
    
## Further development

The application ended up being a lot simpler that supposed. 

- Deleting expenses
  - An extra 0 is accidentally included in the expense and needs to be deleted
- Savings tracking
  - Use the income/expense ratio to calculate savings rate (%)
- GUI!!!
  - Would make the application a lot better even with a simple GUI
