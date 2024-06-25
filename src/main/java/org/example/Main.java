package org.example;

import ControladorAPI.Controlador;

public class Main
{
    public static void main(String[] args)
    {
        Controlador controlador= new Controlador();
        controlador.cargarCliente();
        System.out.println(controlador);
        controlador.descargarImagenAPI();
        System.out.println(controlador.hacerPDFConImagenAPI());

    }
}