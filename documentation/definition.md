# Budgeting application

## What it is

A very basic application for one to keep track of their savings and expenses

## Users

In the beginning there's only going to be one user role: *standard user.*

## Functionality

### Authentication  

**User must be signed in to use the app's features**  
#### Before signing in
  - User can login to an existing account
  - User can create a new account
    - Username must be unique
    - error if username doesn't exist or password is incorrect  
    
#### After signing in
  - User gets access to all core functionalities
  
### Expense tracking  

- Adding new expenses and categorizing them
  - eg. entertainment, food, transportation... 
  - additional information: "Spotify subscription" or "groceries"
  
### Savings tracking  

- Adding new savings and categorizing them
- Listing all existing savings and the total amount spent on particular category

### Other  

- Overall balance
  - Adding new income increases the overall balance
  - Expenses decrease the balance  
    
## Further development
- Family accounts
  - Shared budget for multiple users
    - New additional user type with more restricted usage
- Automated expenses
  - Rent, streaming services...
    - Certain day each month
- Automated savings
