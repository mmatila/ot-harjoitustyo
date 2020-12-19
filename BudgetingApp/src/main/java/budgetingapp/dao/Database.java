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
     * @throws SQLException
     */
    public Database(String databaseName) throws SQLException {
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
     * @throws SQLException
     */
    public void createSchema() throws SQLException {
        if (!tableExists("user")) {
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
        Statement stmt = db.createStatement();
        ResultSet res = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + name + "'");

        boolean result = false;
        while (res.next()) {
            result = !res.getString("name").isBlank();
        }

        return result;
    }

    /**
     * Deletes the whole database. Mostly for deleting test database.
     * 
     * @throws IOException
     */
    public void delete() throws IOException {
        File databaseFile = new File(databaseName);
        Path path = databaseFile.toPath();
        Files.delete(path);
    }
}
