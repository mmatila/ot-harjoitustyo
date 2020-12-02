/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

import budgetingapp.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mmatila
 */
public class UserDao {

    private Connection db;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;
    private Database database;

    /**
     * Constructor
     */
    public UserDao(Connection db, Database database) {
        this.db = db;
        this.database = database;
    }

    public User getUserById(int id) {
        User user = null;

        try {
            ps = db.prepareStatement("SELECT * FROM user WHERE id=(?)");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("name").split(" ")[0],
                        rs.getString("name").split(" ")[1],
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("balance")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }
    
    public int getUserId(User user) {
        int id = 0;
        try {
            ps = db.prepareStatement("SELECT * FROM user WHERE username=(?)");
            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            
        }
        
        return id;
    }

    /**
     * Adds new user to the database
     *
     * @param user user object
     * @return Success message or error message
     */
    public String add(User user) {
        String message = "";

        try {
            ps = db.prepareStatement("INSERT INTO user (name, username, password, balance) VALUES (?, ?, ?, ?);");
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getBalance());
            ps.execute();
            message = "User " + user.getUsername() + " created successfully";

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                message = "Username already exists";
            } else {
                message = "Error: " + e.getMessage();
            }
        }

        return message;
    }

    public User getUserByUsername(String username) {
        User user = null;
        try {
            ps = db.prepareStatement("SELECT * FROM user WHERE username=(?)");
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getString("name").split(" ")[0], rs.getString("name").split(" ")[1], rs.getString("username"), rs.getString("password"), rs.getInt("balance"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }

    public int getIdByUsername(String username) {
        int id = 0;
        try {
            ps = db.prepareStatement("SELECT id FROM user WHERE username=(?)");
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return id;
    }

    /**
     * Deletes user from the database
     *
     * @param user user object
     * @return
     */
    public String delete(User user) {
        String message = "";

        try {
            ps = db.prepareStatement("DELETE FROM user WHERE username=(?)");
            ps.setString(1, user.getUsername());
            ps.execute();
            message = "User " + user.getUsername() + " deleted successfully";

        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }

        return message;
    }
    
    public void updateDaos() {
        
    }
}
