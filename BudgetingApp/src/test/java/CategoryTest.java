/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author mmatila
 */
public class CategoryTest {

    Database testDatabase;
    Connection db;
    Statement stmt;

    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testDatabase = new Database("testDatabase.db");
        db = testDatabase.connect();
        testDatabase.createSchema();
    }
    
    @After
    public void tearDown() {
        testDatabase.delete();
    }
}
