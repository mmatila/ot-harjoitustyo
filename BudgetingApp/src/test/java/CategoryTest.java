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
import java.util.ArrayList;
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
        this.categoryDao = testDatabase.categoryDao;
        this.testCategory = new Category("test", new ArrayList<>());
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
    
    @Test
    public void getCategoryIdWorksProperly() {
        categoryDao.add(testCategory);
        assertEquals(1, categoryDao.getCategoryId(testCategory));
    }
    
    @Test
    public void getAllCategoriesWorksProperly() {
        Category testCategory2 = new Category("test2", new ArrayList<>());
        Category testCategory3 = new Category("test3", new ArrayList<>());
        Category testCategory4 = new Category("test4", new ArrayList<>());
        
        categoryDao.add(testCategory);
        categoryDao.add(testCategory2);
        categoryDao.add(testCategory3);
        categoryDao.add(testCategory4);
        
        assertEquals(4, categoryDao.getAllCategories().size());
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
