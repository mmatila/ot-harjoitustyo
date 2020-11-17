/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.SQLiteDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class DatabaseTest {
    
    private Connection testDatabase;
    private Statement stmt;
    private PreparedStatement ps;

    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testDatabase = DriverManager.getConnection("jdbc:sqlite:testDatabase.db");
        Class.forName("org.sqlite.JDBC");
        stmt = testDatabase.createStatement();
        
        stmt.execute("CREATE TABLE Users (id PRIMARY KEY, name TEXT, balance INTEGER)");
        stmt.execute("INSERT INTO Users (name, balance) VALUES ('Manu', 0");
    }
    
    
    @Test
    public void applicationConnectsToDatabase() {
        assertTrue(testDatabase == null);
    }
    
    @Test
    public void usersGetAdded() throws SQLException {
        SQLiteDB testdb = new SQLiteDB(testDatabase);
        assertEquals(1, testdb.getUserCount());
    }
    
    @After
    public void tearDown() throws SQLException {
        stmt.execute("DROP TABLE Users");
    }
}
