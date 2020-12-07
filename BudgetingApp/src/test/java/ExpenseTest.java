
import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mmatila
 */
public class ExpenseTest {

    private Database testDatabase;
    private Connection db;
    private Statement stmt;
    private CategoryDao categoryDao;

    @Before
    public void initialize() throws SQLException {
        testDatabase = new Database("testDatabase.db");
        db = testDatabase.connect();
        testDatabase.createSchema();
    }
    
    @After
    public void tearDown() {
        testDatabase.delete();
    }
}
