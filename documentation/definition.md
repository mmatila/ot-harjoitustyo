# Budgeting application

## What it is

Basic application for one to keep track of their savings and expenses

## Users

In the beginning there's only going to be one user role: *standard user.*

## Functionality

### Authentication  

**User must be signed in to use the app's features**  
- Signing up (register form)
  - unique username
  - password
  - (optional) profile picture?
- Signing in
  - separate login screen
  - error if username doesn't exist or password is incorrect

### Expense tracking  

- Adding new expenses and categorizing them
  - eg. entertainment, food, transportation...
  - additional information: "Spotify subscription" or "groceries"
  
### Savings tracking  

- Adding new savings and categorizing them
  - normal savings or an investment?
  - automatic savings rate calculation (%)

### Other  

- Overall balance
  - Adding new income increases the overall balance
  - Expenses and savings decrease the balance
  - Displayed
    - Possibility to disable
    
## Further development
- Family accounts
  - Shared budget for multiple users
    - New additional user type with more restricted usage
- Automated expenses
  - Rent, streaming services...
    - Certain day each month
- Automated savings