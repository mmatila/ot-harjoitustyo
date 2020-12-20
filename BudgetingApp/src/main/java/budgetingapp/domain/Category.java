/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.domain;

import java.util.ArrayList;

/**
 *
 * @author mmatila
 */
public class Category {
    private String name;
    private ArrayList<Expense> expenses;
    
    public Category(String name, ArrayList<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
    }
}
