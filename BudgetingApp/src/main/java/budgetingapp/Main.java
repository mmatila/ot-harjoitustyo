/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp;

import budgetingapp.ui.TUI;
import java.sql.SQLException;

/**
 *
 * @author mmatila
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TUI textUserInterface = new TUI();
        textUserInterface.run();
//        App app = new App();
//        app.Main(args);
    }
}