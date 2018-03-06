package com.library.service;

import java.sql.DriverManager;

public class Connection {


    private  Connection connection;

    public Connection getConnection() {
        if(connection == null)
        {try {
            connection = conn();
        }
        catch(Exception e)
        {

        }
        }

        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private java.sql.Connection conn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "sakshi");
    }




}
