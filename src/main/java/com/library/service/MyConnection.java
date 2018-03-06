package com.library.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {


    private Connection myConnection;

    public Connection getConnection() {
        if(myConnection == null)
        {try {
            myConnection = conn();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }

        return myConnection;
    }

    public void setConnection(Connection connection) {
        this.myConnection = connection;
    }

    private java.sql.Connection conn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "sakshi");
    }




}
