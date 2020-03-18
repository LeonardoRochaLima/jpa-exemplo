package com.agenda.springcloudmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Banco {
    public static void connect(){
        Connection conn = null;
        try{
            String url = "jdbc:mysql://remotemysql.com:3306/SFluUYoDMx";
            Properties connectionProps = new Properties();
            connectionProps.put("user", "SFluUYoDMx");
            connectionProps.put("password", "Hdtv17VCmq");
            conn = DriverManager.getConnection(url, connectionProps);
            Statement stmt = conn.createStatement();
            String contacts = "CREATE TABLE Contacts_("+
                    "id INT NOT NULL,"+
                    "name VARCHAR(255),"+
                    "email VARCHAR(255),"+
                    "phone VARCHAR(255),"+
                    "PRIMARY KEY (id)"+
                    ");";

            String groups = "CREATE TABLE Groups_Agenda("+
                    "id INT NOT NULL,"+
                    "description VARCHAR(255),"+
                    "id_contact INT NULL"+
                    ");";
            stmt.execute(contacts);
            stmt.execute(groups);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try{
                if (conn != null){
                    conn.close();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args){ connect();}
}
