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

    /**
     * Constructor
     *
     * @param db Database connection
     */
    public CategoryDao(Connection db) {
        this.db = db;
    }

    /**
     * Adds new category to the database
     *
     * @param name Name of the category
     * @return Success message. "Success" if category was added successfully.
     * "Exists" if category already exists. "Failure" otherwise
     */
    public String add(String name) {
        String result = "Failure";
        if (!name.isBlank()) {
            try {
                ps = db.prepareStatement("INSERT INTO category (name) VALUES (?)");
                ps.setString(1, name);
                ps.execute();
                result = "Success";
            } catch (SQLException e) {
                if (e.getMessage().contains("UNIQUE")) {
                    result = "Exists";
                }
            }
        }
        return result;
    }

    /**
     * Returns category corresponding to the id given as parameter from the
     * database
     *
     * @param id category id
     * @return Category with given id
     */
    public Category get(int id) {
        Category category = null;
        try {
            ps = db.prepareStatement("SELECT * FROM category WHERE id=(?)");
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

    /**
     * Returns all categories from the database
     *
     * @return List of categories
     */
    public ArrayList<String> getAll() {
        ArrayList<String> names = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT name FROM category");
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
