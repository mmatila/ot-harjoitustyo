/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.services;

import budgetingapp.dao.ExpenseDao;
import java.util.HashMap;

/**
 *
 * @author mmatila
 */
public class ExpenseService {

    private ExpenseDao expenseDao;

    /**
     * Constructor
     *
     * @param expenseDao Data-access-object for expenses
     */
    public ExpenseService(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    /**
     * Adds new expense to the database
     *
     * @param categoryId Id of the expense category
     * @param amount Expense amount in euros (€)
     * @param description Additional information about the expense
     * @param userId Id of the user creating the expense
     * @return Success message indicating whether the addition was successful or
     * not
     */
    public String addNewExpense(int categoryId, double amount, String description, int userId) {
        String message = expenseDao.add(userId, amount, description, categoryId);
        if (message.equals("Success")) {
            return "New expense added";
        } else {
            return "Could not add expense";
        }
    }

    /**
     * Returns all expenses that correspond to the category id and user id given
     * as a parameter
     *
     * @param categoryId Id of the category
     * @param userId Id of the user who created the expense
     * @return HashMap of amount-description pairs found from the database
     */
    public HashMap<Double, String> getExpenses(int categoryId, int userId) {
        return expenseDao.getAllByCategoryAndUserId(categoryId, userId);
    }

}
