package BBDD;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class ConectarBaseDeDatosMySql {
    //atributos
    private String driver = "com.mysql.jdbc.Driver";
    private String urlBaseDatos = "jdbc:mysql://127.0.0.1/apod_images";
    private String usuario = "root";//default
    private String contrasenia = "";//default
    private Connection connection;

    //constructores
    public ConectarBaseDeDatosMySql() {
        try {
            Class.forName(driver);//me permite cargar el driver
            connection = DriverManager.getConnection(urlBaseDatos, usuario, contrasenia);
            System.out.println("La base de datos ha sido conectada correctamente");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    //getters y setters

    public Connection getConnection() {
        return connection;//obtengo la conexion
    }

    //metodos
    public boolean cerrarConexion() {
        boolean rta = false;
        if (connection != null) {
            try {
                connection.close();
                rta = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rta;
    }

    
}
