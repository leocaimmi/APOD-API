package org.example;

import BBDD.ConectarBaseDeDatosMySql;
import ControladorAPI.Controlador;

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
        conectarBaseDeDatosMySql.cargarBaseDeDatos("hola","07-07-2007","hola");

    }
}