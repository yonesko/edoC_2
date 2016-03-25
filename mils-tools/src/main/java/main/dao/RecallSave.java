package main.dao;

import org.h2.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RecallSave {
    public static void saveRecall(String recallText) {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
