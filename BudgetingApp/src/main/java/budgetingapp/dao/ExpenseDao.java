/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

import budgetingapp.domain.Category;
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
    private CategoryDao categoryDao;
    private UserDao userDao;

    public ExpenseDao(Connection db) {
        this.db = db;
        this.categoryDao = new CategoryDao(db);
        this.userDao = new UserDao(db);
    }

    public Expense getExpenseById(int id) {
        Expense expense = null;
        try {
            ps = db.prepareStatement("SELECT * FROM expense WHERE id=(?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                expense = new Expense(
                        categoryDao.getCategoryById(rs.getInt("category_id")),
                        rs.getInt("amount"),
                        userDao.getUserById(rs.getInt("user_id"))
                );
            }
        } catch (SQLException e) {

        }

        return expense;
    }

}
