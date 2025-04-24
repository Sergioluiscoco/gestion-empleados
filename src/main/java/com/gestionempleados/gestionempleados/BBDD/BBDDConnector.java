package com.gestionempleados.gestionempleados.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BBDDConnector {
    private static BBDDConnector instance = null;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/gestion_empleados?autoReconnect=true&useSSL=false";
    private Connection conn;


    public BBDDConnector(){
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "1234");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, prop);

            System.out.println("Conexion exitosa");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fallo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static BBDDConnector GetInstance(){
        if(instance == null){
            instance = new BBDDConnector();

            System.out.println("Instancia creada");
            System.out.println(instance.conn != null);
        }

        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            System.out.println("Conexión perdida, intentando reconectar...");
            instance = new BBDDConnector(); // recrea la instancia
        }
        return conn;
    }
}
