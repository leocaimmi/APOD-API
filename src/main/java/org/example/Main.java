package org.example;

import BBDD.ConectarBaseDeDatosMySql;
import ControladorAPI.APODClase;
import ControladorAPI.Controlador;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Controlador controlador= new Controlador();//
        controlador.cargarCliente();//me conecto con la API
        controlador.descargarImagenAPI();//descargo la imagen del dia
        controlador.hacerPDFConImagenAPI();//con la imagen que descargue creo el PDF

        ConectarBaseDeDatosMySql conectarBaseDeDatosMySql = new ConectarBaseDeDatosMySql();//me conecto con la base de datos
        conectarBaseDeDatosMySql.cargarDato(controlador.getApodClase());//con lo que obtenemos de la API lo subimos a la base de datos




    }
}