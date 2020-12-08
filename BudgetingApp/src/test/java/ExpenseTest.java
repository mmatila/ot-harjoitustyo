
import budgetingapp.dao.Database;
import budgetingapp.dao.ExpenseDao;
import budgetingapp.services.ExpenseService;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

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
    private ExpenseDao expenseDao;
    private ExpenseService expenseService;

    @Before
    public void initialize() throws SQLException {
        testDatabase = new Database("testDatabase.db");
        db = testDatabase.connect();
        testDatabase.createSchema();
        this.expenseDao = new ExpenseDao(db);
        this.expenseService = new ExpenseService(expenseDao);
    }
    
    @Test
    public void newExpenseGetsAddedToDatabase() throws SQLException {
        String message = "New expense added";
        assertEquals(message, expenseService.addNewExpense(1, 1.00, "test", 1));
    }
    
    @After
    public void tearDown() {
        testDatabase.delete();
    }
}
