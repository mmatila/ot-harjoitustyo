/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

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
public class Database {

    private Connection db;
    private String databaseName;

    /**
     * Constructor for the actual database
     * @param databaseName name of the database file
     * @throws SQLException 
     */
    public Database(String databaseName) throws SQLException {
        this.databaseName = databaseName;
        this.db = connect();
        createSchema();
    }

    /**
     * Connects to the database. Creates one if it doesn't exist.
     * @return current database connection
     */
    public Connection connect() throws SQLException {
        // try connect to database
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.databaseName);
            Class.forName("org.sqlite.JDBC");
            return db;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates the schema for the database.
     *
     * @throws SQLException
     */
    public void createSchema() throws SQLException {
        if (!tableExists("user")) {
            Statement stmt = db.createStatement();

            stmt.execute("CREATE TABLE user ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT, "
                    + "username TEXT UNIQUE, "
                    + "password TEXT, "
                    + "balance INTEGER)");
            stmt.execute("CREATE TABLE category ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT)");
            stmt.execute("CREATE TABLE expense ("
                    + "id INTEGER PRIMARY KEY, "
                    + "user_id INTEGER, "
                    + "amount INTEGER, "
                    + "category_id INTEGER)");
        }
    }

    /**
     * Given a table name as a parameter, checks if it exists in the database.
     *
     * @param name of the table
     * @return true if table exists
     * @throws SQLException
     */
    public boolean tableExists(String name) throws SQLException {
        Statement  stmt = db.createStatement();
        ResultSet res = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + name + "'");

        boolean result = false;
        while (res.next()) {
            result = !res.getString("name").isBlank();
        }

        return result;
    }
    
    /**
     * Deletes the whole database. Mostly for deleting test database.
     */
    public void delete() {
        File databaseFile = new File(databaseName);
        databaseFile.delete();
    }
    
    /**
     * Returns the database connection.
     * @return current database connection
     */
    public Connection getDb() {
        return this.db;
    }
    
    /**
     * Returns the name of the connected database.
     * @return name of the connected database
     */
    public String getDatabaseName() {
        return this.databaseName;
    }

}
