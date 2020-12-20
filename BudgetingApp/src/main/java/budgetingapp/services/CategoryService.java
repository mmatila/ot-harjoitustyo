/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.services;

import budgetingapp.dao.CategoryDao;
import budgetingapp.domain.Category;
import java.util.ArrayList;

/**
 *
 * @author mmatila
 */
public class CategoryService {

    private CategoryDao categoryDao;

    /**
     * Constructor
     *
     * @param categoryDao Data-access-object for categories
     */
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * Adds new category to the database
     *
     * @param name Name of the category
     * @return Success message indicating whether category was added
     * successfully or not
     */
    public String addNewCategory(String name) {
        String message = categoryDao.add(name);
        if (message.equals("Success")) {
            return "Category " + name + " added successfully";
        } else if (message.equals("Exists")) {
            return "Could not add category " + name + ". Category already exists.";
        } else {
            return "Could not add category " + name;
        }
    }

    /**
     * Returns a category with given id from the database
     *
     * @param id Id of the category
     * @return Category with the id given as a parameter or 'null' if category
     * was not found
     */
    public Category getById(int id) {
        return categoryDao.get(id);
    }

    /**
     * Returns all existing categories
     * @return List of category names
     */
    public ArrayList<String> getCategories() {
        return categoryDao.getAll();
    }

    /**
     * Checks if a category with given id exists in the database
     * @param id Id of the category
     * @return True if category exists. False otherwise
     */
    public boolean categoryExists(int id) {
        if (getById(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
