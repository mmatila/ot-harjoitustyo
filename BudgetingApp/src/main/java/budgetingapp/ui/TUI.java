/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.ui;

import budgetingapp.dao.Database;
import budgetingapp.services.CategoryService;
import budgetingapp.services.ExpenseService;
import budgetingapp.domain.User;
import budgetingapp.services.UserService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class TUI {

    private Scanner scanner;
    private Database db;
    private UserService userService;
    private CategoryService categoryService;
    private ExpenseService expenseService;
    private User loggedUser;

    public TUI(Database db, UserService userService, CategoryService categoryService, ExpenseService expenseService) throws SQLException {
        this.scanner = new Scanner(System.in);
        this.db = db;
        this.userService = userService;
        this.categoryService = categoryService;
        this.expenseService = expenseService;
        this.loggedUser = null;
    }

    public void run() throws SQLException {
        printInfo("");

        while (true) {
            String choice = scanner.nextLine();

            switch (choice) {
                case "exit":
                    handleExit();
                case "1":
                    handleLogin();
                    break;
                case "2":
                    createAccount();
                    break;
                case "3":
                    deleteAccount();
                    break;
                case "4":
                    addCategory();
                    break;
                case "5":
                    addExpense();
                    break;
//                case "6":
//                    moreOptions();
//                    break;
                default:
                    printInfo("Invalid command");
            }
        }
    }

    public void printInfo(String message) {
        printNonLoggedFeatures();
        if (loggedUser != null) {
            printLoggedFeatures();
        }
        System.out.println("\n\t Type 'exit' to exit the program\n");
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        System.out.print("\nEnter command: ");
    }

    public void handleLogin() throws SQLException {
        if (loggedUser == null) {
            System.out.println("\n--- Welcome back! ---");
            System.out.print("\tUsername: ");
            String username = scanner.nextLine();
            System.out.print("\tPassword: ");
            String password = scanner.nextLine();
            String message = userService.handleLogin(username, password);
            validateMessage(message, username);
            printInfo(userService.handleLogin(username, password));
        } else {
            handleLogout();
        }
    }

    public void handleLogout() {
        System.out.print("\nAre you sure you want to log out? [Y]es / [N]o: ");
        String choice = scanner.nextLine();
        String message = userService.handleLogout(choice);
        if (message.contains("logged out")) {
            loggedUser = null;
        }
        printInfo(userService.handleLogout(choice));
    }

    public void createAccount() throws SQLException {
        System.out.println("\n--- Create new user ---");
        System.out.print("\tFirst name: ");
        String firstName = scanner.nextLine();
        System.out.print("\tLast name: ");
        String lastname = scanner.nextLine();
        System.out.print("\tUsername: ");
        String username = scanner.nextLine();
        System.out.print("\tPassword: ");
        String password = scanner.nextLine();
        System.out.print("\tAccount starting balance: ");
        int startingBalance = scanner.nextInt();
        scanner.nextLine();
        printInfo(userService.addNewUser(firstName, lastname, username, password, startingBalance));
    }

    public void deleteAccount() throws SQLException {
        if (isLoggedIn()) {   
            System.out.println("\n--- Delete user ---");
            System.out.print("\tUser do you wish to delete (username): ");
            String username = scanner.nextLine();
            printInfo(userService.deleteUser(username));
        }
    }

    public void addCategory() throws SQLException {
        if (isLoggedIn()) {
            System.out.println("\n--- Add new expense category ---");
            printCategoryList("Existing categories:");
            System.out.print("\tName of the category: ");
            String categoryName = scanner.nextLine();
            printInfo(categoryService.addNewCategory(categoryName));
        }
    }

    public void addExpense() throws SQLException {
        if (isLoggedIn()) {
            System.out.println("\n--- Create a new expense ---");
            printCategoryList("All categories:");
            System.out.print("\tSelect category number from list above: ");
            int categoryId = Integer.valueOf(scanner.nextLine());
            if (categoryService.categoryExists(categoryId)) {
                System.out.print("\tAmount in euros (eg. 1.85): ");
                double amount = Double.valueOf(scanner.nextLine());
                System.out.print("\tDescription: ");
                String description = scanner.nextLine();
                int userId = userService.getIdByUser(loggedUser);
                printInfo(expenseService.addNewExpense(categoryId, amount, description, userId));
                userService.updateBalance(userId, amount);
            } else {
                printInfo("Category does not exist. Returning to main menu...");
            }
        }
    }
    
    public void moreOptions() {
        if (isLoggedIn()) {
            printMoreOptions();
        }
    }

    public void handleExit() {
        System.out.print("\nExit successful! Thank you for using BudgetingApp!");
        System.exit(0);
    }
    
    public void printCategoryList(String msg) throws SQLException {
        System.out.println("");
        System.out.println(msg);
        String message = "";
        ArrayList<String> names = categoryService.getCategories();
        if (names.size() == 0) {
            message = "\n\tSeems empty in here. Add some new categories first!\n";
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + names.get(i));
        }
        System.out.println(message);
    }
    
    public void validateMessage(String message, String username) throws SQLException {
        if (message.contains("logged in")) {
            loggedUser = userService.getUser(username);
        } else if (message.contains("logged out")) {
            loggedUser = null;
        }
    }
    
    public boolean isLoggedIn() {
        if (loggedUser != null) {
            return true;
        } else {
            printInfo("You must be logged in to use this feature");
            return false;
        }
    }
    
    public void printNonLoggedFeatures() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t 1. Login / Logout");
        System.out.println("\t 2. Create new user");
    }
    
    public void printLoggedFeatures() {
        System.out.println("\t 3. Delete an existing user");
        System.out.println("\t 4. Create a new expense category");
        System.out.println("\t 5. Add new expense");
//        System.out.println("\t 6. More options -->");
    }
    
    public void printMoreOptions() {
        System.out.println("\nMore options...");
        System.out.println("\t 1. ");
        System.out.println("\t 2. Create new user");
    }
    
    
    
}
