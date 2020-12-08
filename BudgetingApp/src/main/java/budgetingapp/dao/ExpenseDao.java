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

/**
 *
 * @author mmatila
 */
public class ExpenseDao {

    private Connection db;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Constructor
     * @param db Database connection
     */
    public ExpenseDao(Connection db) {
        this.db = db;
    }
    
    /**
     * Adds a new expense to the database
     * @param userId id of user whose expense it is
     * @param amount the expense cost in euros
     * @param description additional information about the expense. For example "Rent"
     * @param categoryId id of the category that the expense belongs to
     * @return "Success" if expense was added successfully. Otherwise "Failure"
     * @throws SQLException Exception
     */
    public String add(int userId, double amount, String description, int categoryId) throws SQLException {
        ps = db.prepareStatement("INSERT INTO expense (user_id, amount, description, category_id) VALUES (?, ?, ?, ?)");
        try {
            ps.setInt(1, userId);
            ps.setDouble(2, amount);
            ps.setString(3, description);
            ps.setInt(4, categoryId);
            ps.execute();
            return "Success";
        } catch (SQLException e) {
            return "Failure";
        }
    }
}
