package org.example;

import BBDD.ConectarBaseDeDatosMySql;
import ControladorAPI.APODClase;
import ControladorAPI.Controlador;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        /*Controlador controlador= new Controlador();
        controlador.cargarCliente();
        System.out.println(controlador);
        controlador.descargarImagenAPI();
        System.out.println(controlador.hacerPDFConImagenAPI());*/
        ConectarBaseDeDatosMySql conectarBaseDeDatosMySql = new ConectarBaseDeDatosMySql();
       ArrayList<APODClase> apodClases = conectarBaseDeDatosMySql.obtenerAPODBaseDatos();
        System.out.println(apodClases);

    }
}