package org.example;

import BBDD.ConectarBaseDeDatosMySql;
import ControladorAPI.APODClase;
import ControladorAPI.Controlador;
import GUI.JframeMenuPrincipal;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {

        /** API NASA APOD */
      /* Controlador controlador= new Controlador();
        controlador.cargarCliente();//me conecto con la API
        if(controlador.getApodClase().getMedia_type().equalsIgnoreCase("image"))
        {
            System.out.println("image");
            controlador.descargarImagenAPI();//descargo la imagen del dia
        }
        else if(controlador.getApodClase().getMedia_type().equalsIgnoreCase("video"))
        {
            System.out.println("video");
            controlador.descargarVideoAPI();
        }
        System.out.println(controlador.hacerPDFConRecursoAPI());//con el recurso que descargue creo el PDF
*/
        /** Base de datos MySQL */
        /*ConectarBaseDeDatosMySql conectarBaseDeDatosMySql = new ConectarBaseDeDatosMySql();//me conecto con la base de datos
        if(!conectarBaseDeDatosMySql.verificarRepetidoXtitulo(controlador.getApodClase()))
        {
            conectarBaseDeDatosMySql.cargarDato(controlador.getApodClase());//con lo que obtenemos de la API lo subimos a la base de datos
        }else
        {
            System.out.println("estoy repetido\n");
        }*/
//            ArrayList<APODClase> lista= conectarBaseDeDatosMySql.obtenerAPODBaseDatos();
//            System.out.println(lista);
        JframeMenuPrincipal jframeMenuPrincipal = new JframeMenuPrincipal(null);
        jframeMenuPrincipal.setVisible(true);
    }
}