package com.example;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

import com.example.repository.Database;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        try {
            Database db = new Database();
            System.out.println("Database and Hibernate tables initialized");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    // void connectToDatabase() {
    //     String url = "postgresql://postgres:COMP3005-FinalProject@db.lenhbdsuegmwuxmuzrtu.supabase.co:5432/postgres";
    //     String username = "postgres";
    //     String password = "COMP3005-FinalProject";

    //     try {
    //         Class.forName("org.postgresql.Driver");

    //         Connection connection = DriverManager.getConnection(url, username, password);
    //          if (connection != null){
    //             System.out.println("Connected to the database");
    //         }
    //         else {
    //             System.out.println("Failed to connect to db");
    //         }
    //     } catch (ClassNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}
