/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.services;

import budgetingapp.dao.CategoryDao;
import budgetingapp.domain.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mmatila
 */
public class CategoryService {
    private CategoryDao categoryDao;
    
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public String addNewCategory(String name) throws SQLException {
        String message = categoryDao.add(name);
        if (message.equals("Success")) {
            return "Category " + name + " added successfully";
        } else if (message.equals("Exists")) {
            return "Could not add category " + name + ". Category already exists.";
        } else {
            return "Could not add category " + name;
        }
    }
    
    public Category getById(int id) throws SQLException {
        return categoryDao.get(id);
    }
    
    public ArrayList<String> getCategories() throws SQLException {
        return categoryDao.getAll();
    }
    
    public boolean categoryExists(int id) throws SQLException {
        if (getById(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
