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
     *
     * @param db Database connection
     */
    public UserDao(Connection db) {
        this.db = db;
    }

    /**
     * Add new User object to the database
     *
     * @param user User object to be added to the database
     * @return "Success" if user was added successfully. "Exists" if user
     * already exists in the database. Otherwise "Failure"
     */
    public String add(User user) {
        try {
            ps = db.prepareStatement("INSERT INTO user (firstname, lastname, username, password, balance) VALUES (?, ?, ?, ?, ?)");
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
     *
     * @param username username of the user to be deleted
     * @return "Success" if user was deleted successfully". "Doesn't exist" if
     * user was not found from database. Otherwise "Failure"
     */
    public String delete(String username) {
        if (get(username) != null) {
            try {
                ps = db.prepareStatement("DELETE FROM user WHERE username=(?)");
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
     * Returns user from database that's username corresponds to the username
     * given as parameter
     *
     * @param username Username of user to return
     * @return User object
     */
    public User get(String username) {
        User user = null;
        try {
            ps = db.prepareStatement("SELECT * FROM user WHERE username=(?)");
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

    /**
     * Returns user id corresponding to the user given as a parameter
     *
     * @param user User to search for from the database
     * @return Id of the user
     */
    public int getIdByUser(User user) {
        int id = 0;
        try {
            ps = db.prepareStatement("SELECT id FROM user WHERE username=(?)");
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
     * Returns a user from the database whose id corresponds to the one given as
     * a parameter
     *
     * @param id Id of the user to search for from the database
     * @return User found from the database. "null" if user with id given as a
     * parameter was not found
     */
    public User getById(int id) {
        User user = null;
        try {
            ps = db.prepareStatement("SELECT * FROM user WHERE id=(?)");
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

    /**
     * Checks if user with the given username and password exists in the
     * database
     *
     * @param username Username of the user
     * @param password Password of the user
     * @return Success message. "Success" if user was found. "Failure"
     * otherwise.
     */
    public String login(String username, String password) {
        try {
            ps = db.prepareStatement("SELECT * FROM user WHERE username=(?) AND password=(?)");
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

    /**
     * Updates the balance of the user corresponding to the user id given as a
     * parameter
     *
     * @param userId Id of the user
     * @param newAmount New account balance
     * @return Success message. "Success" if account balance was updated
     * successfully. "Failure" otherwise.
     */
    public String updateBalance(int userId, double newAmount) {
        String message = "Failure";
        try {
            ps = db.prepareStatement("UPDATE user SET balance=(?) WHERE id=(?)");
            ps.setDouble(1, newAmount);
            ps.setInt(2, userId);
            ps.executeUpdate();
            message = "Success";
        } catch (SQLException e) {

        }
        return message;
    }

}
