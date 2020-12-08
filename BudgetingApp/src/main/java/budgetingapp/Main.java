/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp;

import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import budgetingapp.dao.ExpenseDao;
import budgetingapp.dao.UserDao;
import budgetingapp.services.CategoryService;
import budgetingapp.services.ExpenseService;
import budgetingapp.services.UserService;
import budgetingapp.ui.TUI;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author mmatila
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database db = new Database("database.db");
        Connection conn = db.connect();
        UserDao userDao = new UserDao(conn);
        CategoryDao categoryDao = new CategoryDao(conn);
        ExpenseDao expenseDao = new ExpenseDao(conn);
        UserService userService = new UserService(userDao);
        CategoryService categoryService = new CategoryService(categoryDao);
        ExpenseService expenseService = new ExpenseService(expenseDao);
        TUI textUserInterface = new TUI(db, userService, categoryService, expenseService);
        textUserInterface.run();
    }
}
