/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.Database;
import budgetingapp.dao.UserDao;
import budgetingapp.domain.User;
import budgetingapp.services.UserService;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
        User testUser = new User("firstname", "lastname", "username", "password", 1000);
    }
    
    @Test
    public void newUserGetsAddedToDatabase() throws SQLException {
        String successMessage = "User username created successfully!";
        assertEquals(successMessage, userService.addNewUser("firstname", "lastname", "username", "password", 1000));
    }

    @After
    public void tearDown() {
        testDatabase.delete();
    }
}
