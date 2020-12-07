/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.domain;

/**
 *
 * @author mmatila
 */
public class Expense {
    private Category category;
    private double amount;
    private User user;
    private String description;

    
    public Expense(Category category, double amount, User user, String description) {
        this.category = category;
        this.amount = amount;
        this.user = user;
        this.description = description;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public User getUser() {
        return user;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
