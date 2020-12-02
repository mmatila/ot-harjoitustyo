/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.ui;

import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import budgetingapp.dao.ExpenseDao;
import budgetingapp.dao.UserDao;
import budgetingapp.domain.Category;
import budgetingapp.domain.Expense;
import budgetingapp.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class TUI {

    private Scanner scanner;
    public Database db;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private ExpenseDao expenseDao;
    private User currentUser;

    public TUI() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.db = new Database("database.db");
        this.userDao = db.userDao;
        this.categoryDao = db.categoryDao;
        this.expenseDao = db.expenseDao;
    }

    public void run() {

        System.out.println("Welcome!\n");
        System.out.println("This text UI is just to help test things\n");
        printInfo();

        while (true) {

            switch (scanner.nextLine()) {
                case "exit":
                    handleExit();
                case "1":
                    handleCaseOne();
                    printInfo();
                    break;
                case "2":
                    handleCaseTwo();
                    printInfo();
                    break;
                case "3":
                    handleCaseThree();
                    printInfo();
                    break;
                case "4":
                    handleCaseFour();
                    printInfo();
                    break;
                default:
                    printDefault();
            }
        }
    }

    public void handleCaseOne() {
        System.out.print("\nEnter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password (this isn't hidden for now): ");
        String password = scanner.nextLine();
        System.out.print("Enter your current checkings account balance: ");
        int balance = scanner.nextInt();
        User user = new User(firstName, lastName, username, password, balance);
        this.currentUser = user;
        System.out.println("\n" + userDao.add(user));
    }

    public void handleCaseTwo() {
        System.out.print("\nAccount to be deleted (username): ");
        String username = scanner.nextLine();
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            System.out.println("\n" + userDao.delete(user));
        } else {
            System.out.println("Username doesn't exist");
        }
    }
    
    public void handleCaseThree() {
        System.out.print("\nName of the category: ");
        String name = scanner.nextLine();
        Category newCategory = new Category(name, new ArrayList<>());
        System.out.println("\n" + categoryDao.add(newCategory));
    }
    
    public void handleCaseFour() {
        System.out.print("\nTo which user is the expense added to? Username: ");
        String username = scanner.nextLine();
        User user = userDao.getUserByUsername(username);
        System.out.print("Amount (€): ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Description (eg. 'Electricity bill'): ");
        String description = scanner.nextLine();
        printCategories();
        System.out.print("\nSelect category by typing the category id from above: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        Expense expense = createExpense(user, categoryId, amount, description);
        System.out.println("\n" + expenseDao.add(expense));
        
    }
    
    public void printCategories() {
        System.out.println("\n");
        ArrayList<Category> categories = categoryDao.getAllCategories();
        
        if (categories.size() > 0) {
            for (Category category : categories) {
                System.out.println(categoryDao.getCategoryId(category) + ". " + category.getName());
            }
        } else {
            System.out.println("List of categories is empty.\n");
        }
    }

    public void printInfo() {
        System.out.println("\nWhat would you like to do? (1-4)");
        System.out.println("\t 1. Create new user");
        System.out.println("\t 2. Delete an existing user");
        System.out.println("\t 3. Create a new expense category");
        System.out.println("\t 4. Add new expense\n");
        System.out.println("\t Type 'exit' to exit the program\n");
        System.out.print("Enter: ");
    }
    
    public void printDefault() {
        System.out.println("\n\tCommand not found\n");
        System.out.print("Enter: ");
    }
    
    public Expense createExpense(User user, int categoryId, int amount, String description) {
        Expense expense = new Expense(
                categoryDao.getCategoryById(categoryId),
                amount,
                user,
                description
        );
        
        return expense;
    }
    
    public void handleExit() {
        System.exit(0);
    }
}
