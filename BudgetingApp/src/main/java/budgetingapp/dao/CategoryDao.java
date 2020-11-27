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

/**
 *
 * @author mmatila
 */
public class CategoryDao {

    private Connection db;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CategoryDao(Connection db) {
        this.db = db;
    }
    
    public Category getCategoryById(int id) {
        Category category = null;
        try {
            ps = db.prepareStatement("SELECT * FROM category WHERE id=(?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getString("name"));
            }
        } catch (SQLException e) {
            
        }
        
        return category;
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
            message = "Error adding a new category: " + e.getMessage();
        }
        
        return message;
    }
}
