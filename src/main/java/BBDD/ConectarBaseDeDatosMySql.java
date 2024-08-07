package BBDD;


import ControladorAPI.APODClase;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ConectarBaseDeDatosMySql {
    //atributos
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String urlBaseDatos = "jdbc:mysql://127.0.0.1/apod_images";
    private String usuario = "root";//default
    private String contrasenia = "";//default
    private Connection connection;

    //constructores
    public ConectarBaseDeDatosMySql() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driver);//me permite cargar el driver
            connection = DriverManager.getConnection(urlBaseDatos, usuario, contrasenia);
            System.out.println("La base de datos ha sido conectada correctamente");
        } catch (ClassNotFoundException e) {

            throw e;
        } catch (SQLException e) {

            throw e;
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
    public boolean verificarRepetidoXtitulo(APODClase apodAux)
    {
        boolean rta = false;
        if(connection!= null)//necesito tener acceso a la base de datos
        {
            Statement statement = null;
            ResultSet resultSet = null;
            try
            {
                // Preparar la consulta SQL

                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM `apod`");
               while(resultSet.next())
               {
                   if(apodAux.getTitle().equalsIgnoreCase(resultSet.getString("titulo")))
                   {
                        rta = true;//si el titulo coindice son iguales
                   }
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rta;
    }

    public ArrayList<APODClase> obtenerAPODBaseDatos() {
        ArrayList<APODClase> listaBaseDeDatos = new ArrayList<>();
        String tituloSinCaracterEspecial = null;
        if (connection != null)
        {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM `apod`")) {

                while (resultSet.next())
                {
                    APODClase auxAPOD = new APODClase();
                    auxAPOD.setTitle(resultSet.getString("titulo"));
                    tituloSinCaracterEspecial = auxAPOD.getTitle();
                    //ESTO ES FUNDAMENTAL, SI EL TITULO CONTIENE CARACTERES ESPECIALES HAY QUE SACARLOS SINO CORTA EL NOMBRE Y NO PERMITE DESCARGAR DE LA BASE DE DATOS
                    if(auxAPOD.getTitle().contains("/"))
                    {
                        tituloSinCaracterEspecial = auxAPOD.getTitle().replace("/", "_");

                    }else if(auxAPOD.getTitle().contains(":"))
                    {
                        tituloSinCaracterEspecial = auxAPOD.getTitle().replace(":", " ");
                    }
                    else if(auxAPOD.getTitle().contains(","))
                    {
                        tituloSinCaracterEspecial = auxAPOD.getTitle().replace(",", " ");
                    }

                    auxAPOD.setDate(resultSet.getString("fecha"));
                    auxAPOD.setExplanation(resultSet.getString("explicacion"));
                    auxAPOD.setMedia_type(resultSet.getString("formato"));

                    if (auxAPOD.getMedia_type().equalsIgnoreCase("image"))
                    {
                        Blob blob = resultSet.getBlob("imagen");
                        if (blob != null && blob.length() > 0)
                        {
                            guardarImagenVideoEnRecursos(blob,"image","recursosBD/image/" + tituloSinCaracterEspecial + ".jpg");
                        } else
                        {
                            System.out.println("El Blob de la imagen está vacío o es nulo para: " + tituloSinCaracterEspecial);
                        }
                    } else if (auxAPOD.getMedia_type().equalsIgnoreCase("video"))
                    {
                        Blob blob = resultSet.getBlob("video");
                        if (blob != null && blob.length() > 0)
                        {
                            guardarImagenVideoEnRecursos(blob,"video","recursosBD/video/" + tituloSinCaracterEspecial + ".mp4");
                        } else
                        {
                            System.out.println("El Blob del video está vacío o es nulo para: " + tituloSinCaracterEspecial);
                        }
                    }

                    Blob blob = resultSet.getBlob("pdf");
                    if (blob != null && blob.length() > 0)
                    {
                        ;
                        guardarBlobEnArchivo(blob,"recursosBD/pdf/"+tituloSinCaracterEspecial+".pdf");
                    } else
                    {
                        System.out.println("El Blob del pdf está vacío o es nulo para: " + tituloSinCaracterEspecial);
                    }
                    listaBaseDeDatos.add(auxAPOD);
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return listaBaseDeDatos;
    }
    private void guardarImagenVideoEnRecursos(Blob blob,String formato,String filePath)
    {
        if(formato.equalsIgnoreCase("image"))
        {
            boolean rta = guardarBlobEnArchivo(blob,filePath);
            if (!rta)
            {
                System.out.println("Error al guardar la imagen: " + filePath);
            }
        }
        else if(formato.equalsIgnoreCase("video"))
        {
            boolean rta = guardarBlobEnArchivo(blob, filePath);
            if (!rta)
            {
                System.out.println("Error al guardar el video: " + filePath);
            }
        }


    }

    private boolean guardarBlobEnArchivo(Blob blob, String filePath)
    {
        boolean rta = false;
        try
        {

            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileBytes = byteArrayOutputStream.toByteArray();

            // Verificación del tamaño de los bytes leídos
            if (fileBytes.length == 0) {
                System.out.println("El archivo leído está vacío: " + filePath);
                return false;
            }

            // Guardar el archivo en el disco
            fileOutputStream.write(fileBytes);
            System.out.println("Archivo descargado correctamente: " + filePath + " con tamaño: " + fileBytes.length);
            rta = true;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return rta;
    }

    public void cargarDato(APODClase aSubir)
    {
        PreparedStatement statement = null;
        File archivoRecurso = null;
        FileInputStream fis = null;
        if (connection != null)
        {
            try
            {

                // Preparar la consulta SQL
                statement = connection.prepareStatement("INSERT INTO apod (titulo, fecha, explicacion, formato, imagen, video, pdf) VALUES (?,?,?,?,?,?,?)");

                // Cargo los datos al statement
                statement.setString(1, aSubir.getTitle());
                statement.setString(2, aSubir.getDate());
                statement.setString(3, aSubir.getExplanation());
                statement.setString(4,aSubir.getMedia_type());
                if(aSubir.getMedia_type().equalsIgnoreCase("image"))
                {
                     archivoRecurso = new File("src/main/resources/location_NASA.jpg");
                    fis = new FileInputStream(archivoRecurso);
                    statement.setBlob(5, fis);
                    statement.setNull(6, Types.BLOB); // Dejar video como NULL
                }else if(aSubir.getMedia_type().equalsIgnoreCase("video"))
                {
                    archivoRecurso = new File("src/main/resources/video.mp4");
                    fis = new FileInputStream(archivoRecurso);
                    statement.setBlob(6, fis);
                    statement.setNull(5, Types.BLOB); // Dejar imagen como NULL
                }
                archivoRecurso = new File("TextoNASAAPI.pdf");
                fis = new FileInputStream(archivoRecurso);
                statement.setBlob(7,fis);

                // Ejecuta la consulta de inserción
                statement.executeUpdate();

                System.out.println("Datos cargados correctamente.");
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                // Cierra la consulta
                try
                {
                    fis.close();
                    statement.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
