package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnector {
    Connection c = null;
    Statement st = null;

    public void connectToDB() {

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o",
                            "tilcavmrsuhbzj", "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public Connection getConnection(){
        return c;
    }

    public Statement getSt() {
        return st;
    }

}