/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion{
    
    public final String base = "usuarios";
    public final String URL = "jdbc:mysql://localhost:3306/" + base;
    public final String USER = "root";
    public final String PASSWORD = "Juanca8313.";
    private Connection con = null;
    
    public Connection getConexion(){//corroborar que est[a conectado
        //a la base de datos
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");//conectar con la libreria
            con = (Connection) DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
            
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
