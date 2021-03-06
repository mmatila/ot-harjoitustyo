/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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
     *
     * @param db Database connection
     */
    public ExpenseDao(Connection db) {
        this.db = db;
    }

    /**
     * Adds a new expense to the database
     *
     * @param userId id of user whose expense it is
     * @param amount the expense cost in euros
     * @param description additional information about the expense. For example
     * "Rent"
     * @param categoryId id of the category that the expense belongs to
     * @return "Success" if expense was added successfully. Otherwise "Failure"
     */
    public String add(int userId, double amount, String description, int categoryId) {
        try {
            ps = db.prepareStatement("INSERT INTO expense (user_id, amount, description, category_id) VALUES (?, ?, ?, ?)");
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

    /**
     * Return all expenses that correspond to the category and user given as a
     * parameter
     *
     * @param categoryId Id of the category
     * @param userId Id of the user
     * @return Map of expenses that correspond to the category and user given as
     * a parameter
     */
    public HashMap<Double, String> getAllByCategoryAndUserId(int categoryId, int userId) {
        HashMap expenses = new HashMap<Double, String>();
        try {
            ps = db.prepareStatement("SELECT * FROM expense WHERE category_id=(?) AND user_id=(?)");
            ps.setInt(1, categoryId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                expenses.put(Double.valueOf(rs.getInt("amount")), rs.getString("description"));
            }
        } catch (SQLException e) {
            return null;
        }
        return expenses;
    }

}
