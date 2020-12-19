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

     /**
      * Constructor
      * @param db Database connection
      */
    public UserDao(Connection db) {
        this.db = db;
    }

    /**
     * Add new User object to the database
     *
     * @param user User object to be added to the database
     * @return "Success" if user was added successfully. "Exists" if user already exists in the database. Otherwise "Failure"
     * @throws SQLException Exception
     */
    public String add(User user) throws SQLException {
        ps = db.prepareStatement("INSERT INTO user (firstname, lastname, username, password, balance) VALUES (?, ?, ?, ?, ?)");
        try {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setDouble(5, user.getBalance());
            ps.execute();
            return "Success";
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                return "Exists";
            }
            return "Error";
        }
    }

    /**
     * Deletes a user from the database
     * @param username username of the user to be deleted
     * @return "Success" if user was deleted successfully". "Doesn't exist" if user was not found from database. Otherwise "Failure"
     * @throws SQLException Exception
     */
    public String delete(String username) throws SQLException {
        if (get(username) != null) {
            ps = db.prepareStatement("DELETE FROM user WHERE username=(?)");
            try {
                ps.setString(1, username);
                ps.execute();
                return "Success";
            } catch (SQLException e) {
                return "Failure";
            }
        } else {
            return "Doesnt exist";
        }
    }

    /**
     * Returns user from database that's username corresponds to the username given as parameter
     * @param username username of user to return
     * @return User object
     * @throws SQLException Exception 
     */
    public User get(String username) throws SQLException {
        User user = null;
        ps = db.prepareStatement("SELECT * FROM user WHERE username=(?)");
        try {
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("balance"));
            }
        } catch (SQLException e) {

        }
        return user;
    }

    public int getIdByUser(User user) throws SQLException {
        int id = 0;
        ps = db.prepareStatement("SELECT id FROM user WHERE username=(?)");
        try {
            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {

        }
        return id;
    }
    
    public User getById(int id) throws SQLException {
        User user = null;
        ps = db.prepareStatement("SELECT * FROM user WHERE id=(?)");
        try {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("balance"));
            }
        } catch (SQLException e) {
            
        }
        return user;
    }

    public String login(String username, String password) throws SQLException {
        ps = db.prepareStatement("SELECT * FROM user WHERE username=(?) AND password=(?)");
        try {
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return "Success";
            }
        } catch (SQLException e) {
            return "Failure";
        }
        return "Failure";
    }
    
    public String updateBalance(int userId, double newAmount) throws SQLException {
        ps = db.prepareStatement("UPDATE user SET balance=(?) WHERE id=(?)");
        try {
            ps.setDouble(1, newAmount);
            ps.setInt(2, userId);
            ps.executeUpdate();
            return "Success";
        } catch (SQLException e) {
            return "Failure";
        }
    }

}
