/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp;

import java.sql.SQLException;

/**
 *
 * @author mmatila
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        SQLiteDB db = new SQLiteDB();
        App app = new App();
        app.main(args);
    }
}
