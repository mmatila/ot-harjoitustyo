/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.Database;
import budgetingapp.dao.UserDao;
import budgetingapp.domain.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mmatila
 */
public class UserTest {

    String firstName;
    String lastName;
    String username;
    String password;
    int balance;
    String databaseName = "testDatabase.db";
    Database testDatabase;
    Connection db;
    Statement stmt;
    UserDao userDao;

    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testDatabase = new Database(databaseName);
        db = testDatabase.connect();
        testDatabase.createSchema();
        userDao = new UserDao(db, testDatabase);
        firstName = "Jane";
        lastName = "Doe";
        username = "username";
        password = "password";
        balance = 10000;
    }

    @Test
    public void userGetsCreatedCorrectly() {
        User user = new User(firstName, lastName, username, password, balance);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(balance, user.getBalance());
    }

    @Test
    public void userBalanceDefaultsToZero() {
        User user = new User(firstName, lastName, username, password);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(0, user.getBalance());
    }

    @Test
    public void userGetsAddedToDatabase() {
        User user = new User(firstName, lastName, username, password, balance);
        assertEquals("User " + username + " created successfully", userDao.add(user));
    }
    
    @Test
    public void getUserByIdWorksProperly() {
        User user = new User(firstName, lastName, "username1", password, balance);
        User user2 = new User(firstName, lastName, "username2", password, balance);
        userDao.add(user);
        userDao.add(user2);
        assertEquals("username1", userDao.getUserById(1).getUsername());
        assertEquals("username2", userDao.getUserById(2).getUsername());
    }
    
    @Test
    public void existingUsernameDoesntGetAddedToDatabase() {
        User first = new User(firstName, lastName, username, password, balance);
        assertEquals("User username created successfully", userDao.add(first));
        User second = new User("John", lastName, username, password, balance);
        assertEquals("Username already exists", userDao.add(second));
    }
    
    @Test
    public void userGetsDeletedFromDatabase() {
        User user = new User(firstName, lastName, username, password, balance);
        userDao.add(user);
        assertEquals("User username deleted successfully", userDao.delete(user));
    }

    @After
    public void tearDown() {
        testDatabase.delete();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
