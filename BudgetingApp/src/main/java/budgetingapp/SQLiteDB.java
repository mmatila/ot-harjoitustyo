/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mmatila
 */
public class SQLiteDB {

    Connection db = null;
    Statement stmt = null;
    ResultSet res = null;

    public SQLiteDB() throws SQLException {
        db = connect();
    }
    
    public SQLiteDB(Connection testdb) throws SQLException {
        db = testdb;
    }

    /**
     * Connects to the database. Creates one if it doesn't exist
     */
    public Connection connect() throws SQLException {
        // try connect to database
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:database.db");
            Class.forName("org.sqlite.JDBC");

            System.out.println("Connection succesful");
            return db;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     * @param username Name of the account to be added to the database
     * @throws SQLException 
     */
    public void addUser(String username) throws SQLException {
        stmt = db.createStatement();
        if (!tableExists("Users")) {
            stmt.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, name TEXT, balance INTEGER) ");
        }
        
        stmt.execute("INSERT INTO Users (name, balance) VALUES ('" + username + "', 0)");
    }
    
    
    public int getUserCount() throws SQLException {
        stmt = db.createStatement();
        res = stmt.executeQuery("SELECT COUNT(*) FROM Users");
        int count = res.getInt(1);
        
        return count;
    }
    
    /**
     * 
     * @param name of the table
     * @return true if table exists
     * @throws SQLException 
     */
    public boolean tableExists(String name) throws SQLException {
        stmt = db.createStatement();
        res = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + name + "'");
        
        return !res.getString("name").isBlank();
    }

}
