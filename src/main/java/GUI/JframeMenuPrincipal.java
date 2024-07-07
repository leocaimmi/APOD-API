/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 * @author Gc
 */
import javax.swing.*;
import java.awt.*;

public class JframeMenuPrincipal extends javax.swing.JFrame {
    // Variables declaration - do not modify
    private javax.swing.JButton jBotonGenerarPDF;
    private javax.swing.JPanel jImagenFondo;
    private javax.swing.JPanel jMenuPrincipal;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jTitulo;
    private javax.swing.JButton jVerBaseDatos;
    private javax.swing.JButton jVerFotoVideo;
    // End of variables declaration

    public JframeMenuPrincipal() //todo agregar esto para el fondo default  String pathPictureOfTheDay
    {
        initComponents("src/main/resources/fondoDefault.png");
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/icono.png");
        setIconImage(imageIcon.getImage());
        setVisible(true);
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
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
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
}

