/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.services;

import budgetingapp.dao.UserDao;
import budgetingapp.domain.User;
import java.sql.SQLException;

/**
 *
 * @author mmatila
 */
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String addNewUser(String firstname, String lastname, String username, String password, int balance) throws SQLException {
        User user = new User(firstname, lastname, username, password, balance);
        String message = userDao.add(user);
        if (message.equals("Success")) {
            return "User " + username + " created successfully!";
        } else if (message.equals("Exists")) {
            return "Error creating a new user. Username " + username + " already exists.";
        } else {
            return "Something went wrong adding a new user.";
        }
    }

    public String deleteUser(String username) throws SQLException {
        String message = userDao.delete(username);
        if (message.equals("Success")) {
            return "User " + username + " deleted.";
        } else if (message.equals("Doesnt exist")) {
            return "Could not find user " + username;
        } else {
            return "Could not delete user " + username;
        }
    }
    
    public User getUser(String username) throws SQLException {
        return userDao.get(username);
    }
    
    public int getIdByUser(User user) throws SQLException {
        return userDao.getIdByUser(user);
    }
    
    public User getUserById(int id) throws SQLException {
        return userDao.getById(id);
    }
    
    public void updateBalance(int id, double amount) throws SQLException {
        User user = getUserById(id);
        user.decreaseBalance(amount);
        double newAmount = user.getBalance();
        userDao.updateBalance(id, newAmount);
    }

    public String handleLogin(String username, String password) throws SQLException {
        String message = userDao.login(username, password);
        if (message.equals("Success")) {
            return "User " + username + " logged in";
        } else {
            return "Incorrect username or password";
        }
    }

    public String handleLogout(String choice) {
        if (choice.equals("Y") || choice.equals("y")) {
            return "User logged out";
        } else {
            return "Logout cancelled";
        }
    }
}
