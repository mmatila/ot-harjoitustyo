/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.Database;
import budgetingapp.dao.UserDao;
import budgetingapp.domain.User;
import budgetingapp.services.UserService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mmatila
 */
public class UserTest {

    Database testDatabase;
    Connection db;
    Statement stmt;
    UserService userService;
    UserDao userDao;

    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testDatabase = new Database("testDatabase.db");
        db = testDatabase.connect();
        testDatabase.createSchema();
        this.userDao = new UserDao(db);
        this.userService = new UserService(userDao);
    }

    @Test
    public void newUserGetsAddedToDatabase() throws SQLException {
        String successMessage = "User username created successfully!";
        assertEquals(successMessage, userService.addNewUser("firstname", "lastname", "username", "password", 1000));
    }

    @Test
    public void noDuplicateUsernames() throws SQLException {
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        String message = "Error creating a new user. Username username already exists.";
        assertEquals(message, userService.addNewUser("firstname", "lastname", "username", "password", 1000));
    }

    @Test
    public void existingUserGetsDeletedFromDatabse() throws SQLException {
        String message = "User username deleted.";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.deleteUser("username"));
    }

    @Test
    public void nonExistingUserDeletionReturnsCorrectErrorMessage() throws SQLException {
        String message = "Could not find user usernameeeeee";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.deleteUser("usernameeeeee"));
    }

    @Test
    public void loginWorksWithCorrectCredentials() throws SQLException {
        String message = "User username logged in";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.handleLogin("username", "password"));
    }

    @Test
    public void loginFailsWithIncorrectCredentials() throws SQLException {
        String message = "Incorrect username or password";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.handleLogin("username", "passsssword"));
    }

    @Test
    public void logoutWorksCorrectlyWithCapitalY() throws SQLException {
        String message = "User logged out";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.handleLogout("Y"));
    }

    @Test
    public void logoutWorksCorrectlyWithLowerCaseY() throws SQLException {
        String message = "User logged out";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.handleLogout("y"));
    }

    @Test
    public void logoutReturnsCorrectMessageIfCancelled() throws SQLException {
        String message = "Logout cancelled";
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(message, userService.handleLogout("z"));
    }

    @Test
    public void getUserReturnsCorrectUser() throws SQLException {
        User user = new User("firstname", "lastname", "username", "password", 1000);
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        assertEquals(user.getFirstName(), userService.getUser("username").getFirstName());
        assertEquals(user.getLastName(), userService.getUser("username").getLastName());
        assertEquals(user.getUsername(), userService.getUser("username").getUsername());
        assertEquals(user.getPassword(), userService.getUser("username").getPassword());
    }

    @Test
    public void existingUserReturnsCorrectId() throws SQLException {
        User user = new User("firstname", "lastname", "username", "password", 1000);
        userService.addNewUser("test", "test", "test", "test", 1000);
        userDao.add(user);
        assertEquals(userService.getIdByUser(user), 2);
    }

    @Test
    public void updateBalanceDecreasesBalance() throws SQLException {
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        userService.updateBalance(1, 100, "decrease");
        assertTrue(900 == userService.getUser("username").getBalance());
    }

    @Test
    public void updateBalanceIncreasesBalance() throws SQLException {
        userService.addNewUser("firstname", "lastname", "username", "password", 1000);
        userService.updateBalance(1, 100, "increase");
        assertTrue(1100 == userService.getUser("username").getBalance());
    }

    @After
    public void tearDown() {
        try {
            db.close();
            testDatabase.delete();
        } catch (SQLException e) {
            
        }
    }
}
