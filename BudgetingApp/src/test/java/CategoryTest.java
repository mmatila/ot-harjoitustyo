/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import budgetingapp.domain.Category;
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
public class CategoryTest {

    String databaseName = "testDatabase.db";
    Database testDatabase;
    Connection db;
    Statement stmt;
    CategoryDao categoryDao;
    Category testCategory;

    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testDatabase = new Database(databaseName);
        db = testDatabase.connect();
        testDatabase.createSchema();
        this.categoryDao = new CategoryDao(db);
        this.testCategory = new Category("test");
    }
    
    @Test
    public void categoryGetsAddedToDatabase() {
        assertEquals("New category added: test", categoryDao.add(testCategory));
    }
    
    @Test
    public void getCategoryByIdWorksProperly() {
        categoryDao.add(testCategory);
        Category testCategory2 = categoryDao.getCategoryById(1);
        assertEquals(testCategory.getName(), testCategory2.getName());
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
