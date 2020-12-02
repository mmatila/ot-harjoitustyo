/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

import budgetingapp.domain.Expense;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mmatila
 */
public class ExpenseDao {

    private Connection db;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;
    private CategoryDao categoryDao;
    private UserDao userDao;
    private Database database;

    public ExpenseDao(Connection db, Database database) {
        this.db = db;
        this.database = database;
        this.categoryDao = database.categoryDao;
        this.userDao = database.userDao;
    }

    public Expense getExpenseById(int id) {
        updateDaos();
        Expense expense = null;
        try {
            ps = db.prepareStatement("SELECT * FROM expense WHERE id=(?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                expense = new Expense(
                        categoryDao.getCategoryById(rs.getInt("category_id")),
                        rs.getInt("amount"),
                        userDao.getUserById(rs.getInt("user_id")),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {

        }

        return expense;
    }
    
    public ArrayList<Expense> getExpensesByCategoryId(int id) {
        updateDaos();
        ArrayList<Expense> expenses = new ArrayList<>();
        
        try {
            ps = db.prepareStatement("SELECT * FROM expense WHERE category_id=(?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                expenses.add(new Expense(
                categoryDao.getCategoryById(id), 
                rs.getInt("amount"), 
                userDao.getUserById(rs.getInt("user_id")), 
                rs.getString("description")));
            }
            
        } catch (SQLException e) {
            
        }
        
        return expenses;
    }

    public String add(Expense expense) {
        updateDaos();
        String message = "";
        int categoryId = categoryDao.getCategoryId(expense.getCategory());
        int userId = userDao.getUserId(expense.getUser());
        int amount = expense.getAmount();
        String description = expense.getDescription();
        
        try {
            ps = db.prepareStatement("INSERT INTO expense (user_id, amount, category_id, description) VALUES (?, ?, ?, ?)");
            ps.setInt(1, userId);
            ps.setInt(2, amount);
            ps.setInt(3, categoryId);
            ps.setString(4, description);
            ps.execute();
            message = "New expense created successfully";
            
        } catch (SQLException e) {
            message = "Error adding new expense: " + e.getMessage();
        }
        
        return message;
    }
    
    // For some weird reason, the daos get nulled when a method gets called from TUI. Will try to fix it later
    public void updateDaos() {
        this.categoryDao = database.categoryDao;
        this.userDao = database.userDao;
    }
}
