/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.ui;

import budgetingapp.dao.Database;
import budgetingapp.domain.CategoryService;
import budgetingapp.domain.ExpenseService;
import budgetingapp.domain.User;
import budgetingapp.domain.UserService;
import java.io.Console;
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
            }
        }
    }

    public void printInfo(String message) {
//        validateMessage(message);
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t 1. Login / Logout");
        System.out.println("\t 2. Create new user");
        if (loggedUser != null) {
            System.out.println("\t 3. Delete an existing user");
            System.out.println("\t 4. Create a new expense category");
            System.out.println("\t 5. Add new expense");
        }
        System.out.println("\n\t Type 'exit' to exit the program\n");
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        System.out.print("\nEnter [1-5]: ");
    }

    public void handleLogin() throws SQLException {
        if (loggedUser == null) {
            System.out.println("\nWelcome back!");
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
        Console console = System.console();
        System.out.println("\nCreate new user");
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
        printInfo(userService.addNewUser(firstName, lastname, username, password, startingBalance));
    }

    public void deleteAccount() throws SQLException {
        System.out.println("\nDelete user");
        System.out.print("\tUser do you wish to delete (username): ");
        String username = scanner.nextLine();
        printInfo(userService.deleteUser(username));
    }

    public void addCategory() throws SQLException {
        System.out.println("\nAdd new expense category");
        System.out.print("\tName of the category: ");
        String categoryName = scanner.nextLine();
        printInfo(categoryService.addNewCategory(categoryName));
    }

    public void addExpense() throws SQLException {
        System.out.println("\nAdd new expense");
        printCategoryList();
        System.out.print("\tPlease enter category number: ");
        int categoryId = Integer.valueOf(scanner.nextLine());
        System.out.print("\tAmount in euros (eg. 1.85): ");
        double amount = Double.valueOf(scanner.nextLine());
        System.out.print("\tDescription: ");
        String description = scanner.nextLine();
        int userId = userService.getIdByUser(loggedUser);
        printInfo(expenseService.addNewExpense(categoryId, amount, description, userId));
    }

    public void handleExit() {
        System.out.print("\nExit successful! Thank you for using BudgetingApp!");
        System.exit(0);
    }
    
    public void printCategoryList() throws SQLException {
        System.out.println("");
        ArrayList<String> names = categoryService.getCategories();
        for (int i = 0; i < names.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + names.get(i));
        }
        System.out.println("");
    }
    
    public void validateMessage(String message, String username) throws SQLException {
        if (message.contains("logged in")) {
            loggedUser = userService.getUser(username);
        } else if (message.contains("logged out")) {
            loggedUser = null;
        }
    }
}
