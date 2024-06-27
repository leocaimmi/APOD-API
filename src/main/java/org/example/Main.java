package org.example;

import BBDD.ConectarBaseDeDatosMySql;
import ControladorAPI.APODClase;
import ControladorAPI.Controlador;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        //todo ver como implementear un video/gif en el pdf
        /** API NASA APOD */
        Controlador controlador= new Controlador();
        controlador.cargarCliente();//me conecto con la API
        //controlador.descargarVideoAPI();
        //controlador.descargarImagenAPI();//descargo la imagen del dia
        controlador.hacerPDFConRecursoAPI();//con la imagen que descargue creo el PDF

        /** Base de datos MySQL */
        /*ConectarBaseDeDatosMySql conectarBaseDeDatosMySql = new ConectarBaseDeDatosMySql();//me conecto con la base de datos
        if(!conectarBaseDeDatosMySql.verificarRepetidoXtitulo(controlador.getApodClase()))
        {
            conectarBaseDeDatosMySql.cargarDato(controlador.getApodClase());//con lo que obtenemos de la API lo subimos a la base de datos
        }else
        {
            System.out.println("estoy repetido");
        }
            ArrayList<APODClase> lista= conectarBaseDeDatosMySql.obtenerAPODBaseDatos();
            System.out.println(lista);*/

    }
}