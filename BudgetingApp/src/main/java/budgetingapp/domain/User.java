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
public class User {
    
    private String firstName;
    private String lastName;
    private String username;
    private String password; // This isn't encrypted for now
    private double balance;
    private ArrayList<Expense> expenses;
    
    /**
     * Constructor
     * @param firstName First name of the user
     * @param lastName Last name of the user
     * @param balance Current balance of the user. Default is 0
     */
    public User(String firstName, String lastName, String username, String password, int balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.expenses = new ArrayList<>();
    }
    
    /**
     * Constructor
     * @param firstName First name of the user
     * @param lastName Last name of the user
     */
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = 0;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    
    public double getBalance() {
        return this.balance;
    }
    
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
    
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
    
    public void increaseBalance(double amount) {
        this.balance += amount;
    }
    
    public void decreaseBalance(double amount) {
        this.balance -= amount;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
