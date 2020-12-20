/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.services;

import budgetingapp.dao.UserDao;
import budgetingapp.domain.User;

/**
 *
 * @author mmatila
 */
public class UserService {

    private UserDao userDao;

    /**
     * Constructor
     *
     * @param userDao Data-access-object for users
     */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Adds new user to the database
     *
     * @param firstname First name of the user
     * @param lastname Last name of the user
     * @param username Username of the user
     * @param password Password of the user
     * @param balance User account balance
     * @return Success message indicating whether the addition to database was
     * successful or not
     */
    public String addNewUser(String firstname, String lastname, String username, String password, int balance) {
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

    /**
     * Deletes user with the username given as a parameter from the database
     *
     * @param username Username of the user
     * @return Success message indicating whether the deletion was successful or
     * not
     */
    public String deleteUser(String username) {
        String message = userDao.delete(username);
        if (message.equals("Success")) {
            return "User " + username + " deleted.";
        } else if (message.equals("Doesnt exist")) {
            return "Could not find user " + username;
        } else {
            return "Could not delete user " + username;
        }
    }

    /**
     * Returns user with the username given as a parameter from the database
     *
     * @param username Username of the user
     * @return User object if user was found. Otherwise null
     */
    public User getUser(String username) {
        return userDao.get(username);
    }

    /**
     * Returns user id that corresponds to the user object given as a parameter
     *
     * @param user User to search for from the database
     * @return Id of the user. '0' otherwise
     */
    public int getIdByUser(User user) {
        return userDao.getIdByUser(user);
    }

    /**
     * Returns user object from the database that corresponds to the user id
     * given as a parameter
     *
     * @param id Id of the user
     * @return User object if user was found. Otherwise null
     */
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    /**
     * Updates the balance of the user corresponding to the user id given as a
     * parameter
     *
     * @param id Id of the user
     * @param amount Update value
     * @param type Decides whether to increase or decrease the account balance
     */
    public void updateBalance(int id, double amount, String type) {
        User user = getUserById(id);
        if (type.equals("decrease")) {
            user.decreaseBalance(amount);
        } else if (type.equals("increase")) {
            user.increaseBalance(amount);
        }
        double newAmount = user.getBalance();
        userDao.updateBalance(id, newAmount);
    }

    /**
     * Checks if user with given credentials exists
     *
     * @param username Username of the user
     * @param password Password of the user
     * @return Success message indicating whether login was successful or not
     */
    public String handleLogin(String username, String password) {
        String message = userDao.login(username, password);
        if (message.equals("Success")) {
            return "User " + username + " logged in";
        } else {
            return "Incorrect username or password";
        }
    }

    /**
     * Double checks if user wants to log out
     *
     * @param choice "Y" or "y" to confirm. Anything else to cancel
     * @return Success message indicating whether log out was successful or not
     */
    public String handleLogout(String choice) {
        if (choice.equals("Y") || choice.equals("y")) {
            return "User logged out";
        } else {
            return "Logout cancelled";
        }
    }
}
