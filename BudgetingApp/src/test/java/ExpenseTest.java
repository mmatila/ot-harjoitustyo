
import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import budgetingapp.dao.ExpenseDao;
import budgetingapp.domain.Category;
import budgetingapp.domain.Expense;
import budgetingapp.domain.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    private String databaseName = "testDatabase.db";
    private Database testDatabase;
    private Connection db;
    private Statement stmt;
    private CategoryDao categoryDao;
    private ExpenseDao expenseDao;
    private User testUser;
    private Category testCategory;
    private Expense testExpense;

    @Before
    public void initialize() throws SQLException {
        testDatabase = new Database(databaseName);
        db = testDatabase.connect();
        testDatabase.createSchema();
        this.categoryDao = testDatabase.categoryDao;
        this.expenseDao = testDatabase.expenseDao;
        this.testUser = new User("firstname", "lastname", "username", "password", 1000);
        this.testCategory = new Category("test", new ArrayList<>());
        this.testExpense = new Expense(testCategory, 100, testUser, "description");
    }
    
    @Test
    public void expenseGetsAddedToDatabase() {
        assertEquals("New expense created successfully", expenseDao.add(testExpense));
    }
    
    @Test
    public void getExpenseByIdWorksProperly() {
        expenseDao.add(testExpense);
        assertEquals(testExpense.getDescription(), expenseDao.getExpenseById(1).getDescription());
    }
    
    @Test
    public void getAllExpensesByCategoryIdWorksProperly() {
        Expense testExpense2 = new Expense(testCategory, 200, testUser, "description");
        int categoryId = categoryDao.getCategoryId(testCategory);
        expenseDao.add(testExpense);
        expenseDao.add(testExpense2);
        
        assertEquals(2, expenseDao.getExpensesByCategoryId(categoryId).size());
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
