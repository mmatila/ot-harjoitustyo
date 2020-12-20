/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
     *
     * @param databaseName name of the database file
     */
    public Database(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Connects to the database. Creates one if it doesn't exist.
     *
     * @return current database connection
     */
    public Connection connect() {
        // try connect to database
        try {
            db = DriverManager.getConnection("jdbc:sqlite:" + this.databaseName);
            Class.forName("org.sqlite.JDBC");
            createSchema();
            return db;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates the schema for the database.
     *
     */
    public void createSchema() {
        if (!tableExists("user")) {
            try {
                Statement stmt = db.createStatement();
                stmt.execute("CREATE TABLE user ("
                        + "id INTEGER PRIMARY KEY, "
                        + "firstname TEXT,"
                        + "lastname TEXT, "
                        + "username TEXT UNIQUE, "
                        + "password TEXT, "
                        + "balance INTEGER)");
                stmt.execute("CREATE TABLE category ("
                        + "id INTEGER PRIMARY KEY, "
                        + "name TEXT UNIQUE)");
                stmt.execute("CREATE TABLE expense ("
                        + "id INTEGER PRIMARY KEY, "
                        + "user_id INTEGER, "
                        + "amount INTEGER, "
                        + "description TEXT,"
                        + "category_id INTEGER)");
            } catch (SQLException e) {

            }
        }
    }

    /**
     * Given a table name as a parameter, checks if it exists in the database.
     *
     * @param name of the table
     * @return true if table exists
     */
    public boolean tableExists(String name) {
        boolean result = false;
        try {
            Statement stmt = db.createStatement();
            ResultSet res = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + name + "'");

            while (res.next()) {
                result = !res.getString("name").isBlank();
            }

            return result;
        } catch (SQLException e) {

        }
        return result;
    }

    /**
     * Deletes the whole database. Mostly for deleting test databases.
     *
     */
    public void delete() {
        try {
            File databaseFile = new File(databaseName);
            Path path = databaseFile.toPath();
            Files.delete(path);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
