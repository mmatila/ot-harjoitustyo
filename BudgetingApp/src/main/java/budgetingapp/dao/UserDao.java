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
     */
    public UserDao(Connection db) {
        this.db = db;
    }

    /**
     * Add new User object to the database
     * @param user object to add
     * @return true if 
     * @throws SQLException 
     */
    public String add(User user) throws SQLException {
        ps = db.prepareStatement("INSERT INTO user (firstname, lastname, username, password, balance) VALUES (?, ?, ?, ?, ?)");
        try {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getBalance());
            ps.execute();
            return "Success";
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                return "Exists";
            }
            return "Error";
        }
    }
    
    public String delete(String username) throws SQLException {
        ps = db.prepareStatement("DELETE FROM user WHERE username=(?)");
        try {
            ps.setString(1, username);
            ps.execute();
            return "Success";
        } catch (SQLException e){
            return "Failure";
        }
    }
    
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

}
