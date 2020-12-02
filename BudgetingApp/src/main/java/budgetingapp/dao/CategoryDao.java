/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

import budgetingapp.domain.Category;
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
public class CategoryDao {

    private Connection db;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;
    private ExpenseDao expenseDao;
    private Database database;

    public CategoryDao(Connection db, Database database) {
        this.db = db;
        this.database = database;
        this.expenseDao = database.expenseDao;
    }

    public int getCategoryId(Category category) {
        int id = 0;
        try {
            ps = db.prepareStatement("SELECT * FROM category WHERE name=(?)");
            ps.setString(1, category.getName());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error getting categoryId");
        }
        
        return id;
    }

    public Category getCategoryById(int id) {
        Category category = null;
        try {
            ps = db.prepareStatement("SELECT * FROM category WHERE id=(?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(
                        rs.getString("name"),
                        expenseDao.getExpensesByCategoryId(id)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting category by id");
        }

        return category;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            ps = db.prepareStatement("SELECT * FROM category");
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getString("name"),
                        expenseDao.getExpensesByCategoryId(rs.getInt("id"))
                );
                categories.add(category);
            }
        } catch (SQLException e) {

        }
        
        return categories;
    }

    public String add(Category category) {
        String message = "";
        String categoryName = category.getName();

        try {
            ps = db.prepareStatement("INSERT INTO category (name) VALUES (?)");
            ps.setString(1, categoryName);
            ps.execute();
            message = "New category added: " + categoryName;

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                message = "Category already exists";
            } else {
                message = "Error adding a new category: " + e.getMessage();
            }
        }

        return message;
    }
    
    public void updateDaos() {
        this.expenseDao = database.expenseDao;
    }
}
