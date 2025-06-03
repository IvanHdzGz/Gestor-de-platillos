/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wyrnm
 */
public class Conectar {

    // Propiedades para la conexión a la base de datos
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static final String URL_BASEDEDATOS = "jdbc:mysql://localhost:3306/platillos?useSSL=false";

    private static Connection conn = null;

    public static Connection realizarConexion() throws SQLException {
        try {
            // carga la clase CONTROLADOR
            Class.forName(CONTROLADOR);

            // Establece  la conexión a la base de datos 
            conn = DriverManager.getConnection(URL_BASEDEDATOS, USUARIO, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    } //Fin del metodo realizar conexión

    public static void realizarDesconexion() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
