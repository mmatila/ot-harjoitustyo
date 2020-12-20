
import budgetingapp.dao.Database;
import budgetingapp.dao.ExpenseDao;
import budgetingapp.services.ExpenseService;
import java.io.IOException;
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
    
    @Test
    public void getExpensesReturnsAllExpensesOfThatCategory() throws SQLException {
        expenseService.addNewExpense(1, 1.0, "CategoryOneBeer", 1);
        expenseService.addNewExpense(1, 2.0, "CategoryOneBeerNumberTwo", 1);
        expenseService.addNewExpense(2, 3.0, "CategoryTwoBeer", 1);
        expenseService.addNewExpense(2, 4.0, "CategoryTwoBeerNumberTwo", 1);
        expenseService.addNewExpense(2, 5.0, "CategoryBeerNumberThree", 1);
        expenseService.addNewExpense(3, 6.0, "CategoryThreeBeer", 1);
        assertEquals(2, expenseService.getExpenses(1, 1).size());
    }

    @After
    public void tearDown() {
        try {
            db.close();
            testDatabase.delete();
        } catch (IOException | SQLException e) {
            System.out.println("Error deleting the file:" + e.getMessage());
        }
    }
}
