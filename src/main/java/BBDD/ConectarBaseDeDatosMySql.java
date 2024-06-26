package BBDD;


import ControladorAPI.APODClase;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<APODClase> obtenerAPODBaseDatos() {
        ArrayList<APODClase> listaBaseDeDatos = null;
        FileOutputStream fileOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Statement statement = null;
        ResultSet resultSet = null;
        if (connection != null)
        {
            try
            {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM `apod`");
                listaBaseDeDatos = new ArrayList<>();

                while (resultSet.next())
                {

                    APODClase auxAPOD = new APODClase();
                    auxAPOD.setTitle(resultSet.getString("titulo"));
                    auxAPOD.setExplanation(resultSet.getString("explicacion"));
                    auxAPOD.setDate(resultSet.getString("fecha"));
                    // Obtener el Blob de la imagen
                    Blob blob = resultSet.getBlob("imagen"); //todo si ya existe la imagen en la base de datos(binario) no subir

                    if (blob != null)
                    {
                        InputStream inputStream = blob.getBinaryStream();
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;

                        while ((bytesRead = inputStream.read(buffer)) != -1)
                        {
                            byteArrayOutputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();
                        File imageFile = new File(""+auxAPOD.getTitle()+".jpg");

                        // Guardar los bytes de la imagen como un archivo JPG
                        fileOutputStream = new FileOutputStream(imageFile);
                        fileOutputStream.write(imageBytes);

                    }


                    listaBaseDeDatos.add(auxAPOD);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally
            {

                try
                {
                    statement.close();
                    resultSet.close();
                    byteArrayOutputStream.close();
                    fileOutputStream.close();
                    cerrarConexion();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return listaBaseDeDatos;
    }

    public void cargarDato(APODClase aSubir) {
        PreparedStatement statement = null;
        FileInputStream fis = null;

        if (connection != null) {
            try {
                // Ruta de la imagen en el repositorio local
                File archivoImagen = new File("src/main/resources/location_NASA.jpg");
                fis = new FileInputStream(archivoImagen);

                // Preparar la consulta SQL
                statement = connection.prepareStatement("INSERT INTO apod (titulo, fecha, imagen, explicacion) VALUES (?,?,?,?)");

                // Cargo los datos al statement
                statement.setString(1, aSubir.getTitle());
                statement.setString(2, aSubir.getDate());
                statement.setBlob(3, fis);
                statement.setString(4, aSubir.getExplanation());

                // Ejecuta la consulta de inserción
                statement.executeUpdate();

                // Cierra la consulta
                statement.close();
                fis.close();

                System.out.println("Datos cargados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) fis.close();
                    if (statement != null) statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
