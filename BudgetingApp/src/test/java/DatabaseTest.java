/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mmatila
 */
public class DatabaseTest {

    String databaseName = "testDatabase.db";
    Database testDatabase;
    Connection conn;
    Statement stmt;

    @Before
    public void initialize() throws SQLException {
        testDatabase = new Database(databaseName);
        conn = testDatabase.connect();
        testDatabase.createSchema();
    }

    @Test
    public void applicationConnectsToDatabase() throws SQLException {
        assertTrue(conn != null);
    }

    @Test
    public void userTableGetsCreatedCorrectly() throws SQLException {
        stmt = conn.createStatement();
        assertTrue(testDatabase.tableExists("user"));
    }

    @Test
    public void expenseTableGetsCreatedCorrectly() throws SQLException {
        stmt = conn.createStatement();
        assertTrue(testDatabase.tableExists("expense"));
    }

    @Test
    public void categoryTableGetsCreatedCorrectly() throws SQLException {
        stmt = conn.createStatement();
        assertTrue(testDatabase.tableExists("category"));
    }

    @After
    public void tearDown() {
        try {
            conn.close();
            testDatabase.delete();
        } catch (SQLException e) {

        }
    }
}
