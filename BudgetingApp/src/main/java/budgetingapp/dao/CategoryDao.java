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

    public CategoryDao(Connection db) {
        this.db = db;
    }

    public String add(String name) throws SQLException {
        System.out.println(name);
        try {
            ps = db.prepareStatement("INSERT INTO category (name) VALUES (?)");
            ps.setString(1, name);
            ps.execute();
            return "Success";
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                return "Exists";
            }
            return "Failure";
        }
    }
    
    public Category get(int id) throws SQLException {
        Category category = null;
        ps = db.prepareStatement("SELECT * FROM category WHERE id=(?)");
        try {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(
                        rs.getString("name"),
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            
        }
        return category;
    }
    
    public ArrayList<String> getAll() throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        ps = db.prepareStatement("SELECT name FROM category");
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                names.add(name);
            }
        } catch (SQLException e) {
            
        }
        return names;
    }
}
