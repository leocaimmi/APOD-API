/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BBDD.ConectarBaseDeDatosMySql;
import ControladorAPI.Controlador;
import jdk.swing.interop.SwingInterOpUtils;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Gc
 */

public class JframeMenuPrincipal extends javax.swing.JFrame {
    // Variables declaration - do not modify
    private javax.swing.JButton jBotonGenerarPDF;
    private javax.swing.JPanel jImagenFondo;
    private javax.swing.JPanel jMenuPrincipal;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jTitulo;
    private javax.swing.JButton jVerBaseDatos;
    private javax.swing.JButton jVerFotoVideo;
    private static Controlador controladorNASAAPI = null;
    private static ConectarBaseDeDatosMySql conexionBD = null;
    // End of variables declaration

    public JframeMenuPrincipal(String pathFondo)
    {
        //Apertura API NASA
        try
        {
            conectarAPINASA();//me conecto con APOD api
            conectarBD();//me conecto con la base de datos local
        } catch (ClassNotFoundException e)
        {
            //todo agregar POPUP
            e.printStackTrace();
        } catch (SQLException e)
        {
            //todo agregar POPUP
            e.printStackTrace();
        } catch (JSONException e)
        {
            //todo agregar POPUP
            e.printStackTrace();
        } catch (IOException e)
        {
            //todo agregar POPUP
            e.printStackTrace();
        }
        initComponents(pathFondo);

        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/icono.png");
        setIconImage(imageIcon.getImage());
        setVisible(true);
    }
    public static void conectarAPINASA() throws JSONException, IOException
    {
        /** API NASA APOD */
       if(controladorNASAAPI == null)
       {
           controladorNASAAPI = new Controlador();
           controladorNASAAPI.cargarCliente();//me conecto con la API
       }


    }
    public static boolean conectarBD() throws SQLException, ClassNotFoundException
    {
        boolean rta  =false;
        if(conexionBD == null)
        {
            conexionBD = new ConectarBaseDeDatosMySql();
            rta = true;
        }
        return rta;//false si ya hay conexion, true si se genero la conexion
    }
    public static void descargarRecursoAPINASA()
    {
       if(controladorNASAAPI != null)
       {
           if(!controladorNASAAPI.getApodClase().isRecurso()|| verificarSiNoExisteElArchivo("src/main/resources/location_NASA.jpg")|| verificarSiNoExisteElArchivo("src/main/resources/video.mp4"))//si el recurso es false o si no existe la imagen la descargo
           {
               if(controladorNASAAPI.getApodClase().getMedia_type().equalsIgnoreCase("image"))
               {
                   System.out.println("image");
                   controladorNASAAPI.descargarImagenAPI();//descargo la imagen del dia
               }
               else if(controladorNASAAPI.getApodClase().getMedia_type().equalsIgnoreCase("video"))
               {
                   System.out.println("video");
                   controladorNASAAPI.descargarVideoAPI();
               }
               controladorNASAAPI.getApodClase().setRecurso(true);
           }
//           else
//           {
//               //todo agregar PopUp de que ya existe o ver
//           }

       }
//       else
//       {
//           //todo agregar popup de error con la conexion
//       }



    }
    public static boolean verificarSiNoExisteElArchivo(String path)
    {

        boolean rta= false;
        File recurso = new File(path);
        if(!recurso.exists() ||!recurso.isFile())
        {
            rta = true;
        }
        return rta;
    }
    private void initComponents(String pathFondo) {

        jMenuPrincipal = new javax.swing.JPanel();
        jTitulo = new javax.swing.JLabel();
        jBotonGenerarPDF = new javax.swing.JButton();
        jVerBaseDatos = new javax.swing.JButton();
        jVerFotoVideo = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jImagenFondo = new javax.swing.JPanel()
        {
            ImageIcon icon = new ImageIcon(pathFondo); // ruta
            Image img = icon.getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
               if(pathFondo == null||pathFondo.equalsIgnoreCase(""))
               {
                    icon = new ImageIcon("src/main/resources/fondoDefault.png"); // ruta
                    img = icon.getImage();
                   g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

               }
               else
               {
                   g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

               }
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuPrincipal.setBackground(new java.awt.Color(0, 11, 61));

        jTitulo.setFont(new java.awt.Font("Book Antiqua", 1, 36)); // NOI18N
        jTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jTitulo.setText("NASA API");
        jTitulo.setToolTipText("");

        jBotonGenerarPDF.setText("GENERAR PDF");
        jBotonGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonGenerarPDFActionPerformed(evt);
            }
        });

        jVerBaseDatos.setText("VER TODOS LOS RECURSOS");
        jVerBaseDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVerBaseDatosActionPerformed(evt);
            }
        });

        jVerFotoVideo.setText("VER FOTO/VIDEO DEL DIA");
        jVerFotoVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVerFotoVideoActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 11, 61));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("-Picture of the day-");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jImagenFondo.setBackground(new java.awt.Color(0, 11, 61));

        javax.swing.GroupLayout jImagenFondoLayout = new javax.swing.GroupLayout(jImagenFondo);
        jImagenFondo.setLayout(jImagenFondoLayout);
        jImagenFondoLayout.setHorizontalGroup(
                jImagenFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 323, Short.MAX_VALUE)
        );
        jImagenFondoLayout.setVerticalGroup(
                jImagenFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 243, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jMenuPrincipalLayout = new javax.swing.GroupLayout(jMenuPrincipal);
        jMenuPrincipal.setLayout(jMenuPrincipalLayout);
        jMenuPrincipalLayout.setHorizontalGroup(
                jMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jMenuPrincipalLayout.createSequentialGroup()
                                .addGroup(jMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jMenuPrincipalLayout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(jBotonGenerarPDF)
                                                .addGap(43, 43, 43)
                                                .addComponent(jVerFotoVideo)
                                                .addGap(28, 28, 28)
                                                .addComponent(jVerBaseDatos))
                                        .addGroup(jMenuPrincipalLayout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(jImagenFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jMenuPrincipalLayout.createSequentialGroup()
                                                .addGap(227, 227, 227)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jMenuPrincipalLayout.createSequentialGroup()
                                                .addGap(198, 198, 198)
                                                .addComponent(jTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(41, Short.MAX_VALUE))
        );
        jMenuPrincipalLayout.setVerticalGroup(
                jMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jMenuPrincipalLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jImagenFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBotonGenerarPDF)
                                        .addComponent(jVerFotoVideo)
                                        .addComponent(jVerBaseDatos))
                                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jBotonGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JfrEsperaPopUp popUp = new JfrEsperaPopUp((Frame) SwingUtilities.getWindowAncestor(this), "Generando PDF...");
        popUp.showWindow();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception //Permite trabajar en un segundo plano
            {

                if (controladorNASAAPI.getApodClase().isRecurso())//si tengo un recurso del dia
                {
                    controladorNASAAPI.hacerPDFConRecursoAPI();
                    try
                    {
                        controladorNASAAPI.abrirPDF("TextoNASAAPI.pdf");
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                        //todo agregar popup
                    }

                }
                return null;
            }
            @Override
            protected void done() { //Este metodo oculta la ventana

                popUp.hideWindow();
                //JOptionPane.showMessageDialog(null, "Codigo QR generado correctamente!");
            }

        };
        worker.execute();

    }



    private void jVerBaseDatosActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JframeVerBaseDatos verBaseDatos = new JframeVerBaseDatos();
        setVisible(false);
    }

    private void jVerFotoVideoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JframeVerRecurso verRecurso = new JframeVerRecurso();
        setVisible(false);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static Controlador getControladorNASAAPI() {
        return controladorNASAAPI;
    }

    public static ConectarBaseDeDatosMySql getConexionBD() {
        return conexionBD;
    }
}

