/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.ui;

import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import budgetingapp.dao.UserDao;
import budgetingapp.domain.Category;
import budgetingapp.domain.User;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author mmatila
 */
public class TUI {

    private Scanner scanner;
    private Database db;
    private UserDao userDao;
    private CategoryDao categoryDao;

    public TUI() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.db = new Database("database.db");
        this.userDao = new UserDao(db.connect());
        this.categoryDao = new CategoryDao(db.connect());
    }

    public void run() {

        System.out.println("Welcome!\n");
        System.out.println("This text UI is just to help test things\n");
        printInfo();

        while (true) {

            switch (scanner.nextLine()) {
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
        Category newCategory = new Category(name);
        System.out.println("\n" + categoryDao.add(newCategory));
    }

    public void printInfo() {
        System.out.println("\nWhat would you like to do? (1-2)");
        System.out.println("\t 1. Create new user");
        System.out.println("\t 2. Delete an existing user");
        System.out.println("\t 3. Create a new expense category\n");
        System.out.print("Enter: ");
    }
}
