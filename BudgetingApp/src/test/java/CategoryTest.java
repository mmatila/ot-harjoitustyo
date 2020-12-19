/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budgetingapp.dao.CategoryDao;
import budgetingapp.dao.Database;
import budgetingapp.services.CategoryService;
import java.io.IOException;
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

    Database testDatabase;
    Connection db;
    Statement stmt;
    CategoryDao categoryDao;
    CategoryService categoryService;

    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testDatabase = new Database("testDatabase.db");
        db = testDatabase.connect();
        testDatabase.createSchema();
        this.categoryDao = new CategoryDao(db);
        this.categoryService = new CategoryService(categoryDao);
    }

    @Test
    public void categoryGetsAddedToDatabase() throws SQLException {
        String message = "Category test added successfully";
        assertEquals(message, categoryService.addNewCategory("test"));
    }

    @Test
    public void noDuplicateCategories() throws SQLException {
        String message = "Could not add category test. Category already exists.";
        categoryService.addNewCategory("test");
        assertEquals(message, categoryService.addNewCategory("test"));
    }

    @After
    public void tearDown() {
        try {
            db.close();
            testDatabase.delete();
        } catch (IOException | SQLException e) {
            System.out.println("Error deleting the file: " + e.getMessage());
        }
    }
}
