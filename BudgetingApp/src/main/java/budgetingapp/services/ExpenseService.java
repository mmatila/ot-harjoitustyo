/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.services;

import budgetingapp.dao.ExpenseDao;
import java.sql.SQLException;

/**
 *
 * @author mmatila
 */
public class ExpenseService {
    private ExpenseDao expenseDao;
    
    public ExpenseService(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }
    
    public String addNewExpense(int categoryId, double amount, String description, int userId) throws SQLException {
        String message = expenseDao.add(userId, amount, description, categoryId);
        if (message.equals("Success")) {
            return "New expense added";
        } else {
            return "Could not add expense";
        }
    }
    
}
