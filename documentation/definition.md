# Budgeting application

## What it is

Basic application for one to keep track of their savings and expenses

## Users

In the beginning there's only going to be one user role: *standard user.*

## Functionality

### Authentication  

**User must be signed in to use the app's features**  
- Signing up (register form)
  - unique username :heavy_check_mark:
  - password :heavy_check_mark:
  - (optional) profile picture?
- Signing in
  - separate login screen :heavy_check_mark:
  - error if username doesn't exist or password is incorrect :heavy_check_mark:

### Expense tracking  

- Adding new expenses and categorizing them :heavy_check_mark:
  - eg. entertainment, food, transportation... :heavy_check_mark:
  - additional information: "Spotify subscription" or "groceries" :heavy_check_mark:
  
### Savings tracking  

- Adding new savings and categorizing them
  - normal savings or an investment?
  - automatic savings rate calculation (%)

### Other  

- Overall balance
  - Adding new income increases the overall balance
  - Expenses and savings decrease the balance :heavy_check_mark:
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
