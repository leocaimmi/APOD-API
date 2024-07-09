/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Gc
 */
public class JframeVerRecurso extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JButton jBotonVolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecurso;
    // End of variables declaration
    /**
     * Creates new form JframeVerRecurso
     */
    public JframeVerRecurso()
    {
        JframeMenuPrincipal.descargarRecursoAPINASA();
        //JfrEsperaPopUp JfrEsperaPopUp = new JfrEsperaPopUp(this,"Descargando recurso");
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/icono.png");
        setIconImage(imageIcon.getImage());
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelRecurso = new javax.swing.JPanel()
        {
            ImageIcon icon = new ImageIcon("src/main/resources/location_NASA.jpg"); // ruta
            Image img = icon.getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        jBotonVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(843, 500));

        jPanel1.setBackground(new java.awt.Color(0, 11, 61));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setPreferredSize(new java.awt.Dimension(843, 500));

        jPanelRecurso.setBackground(new java.awt.Color(0, 11, 61));

        javax.swing.GroupLayout jPanelRecursoLayout = new javax.swing.GroupLayout(jPanelRecurso);
        jPanelRecurso.setLayout(jPanelRecursoLayout);
        jPanelRecursoLayout.setHorizontalGroup(
                jPanelRecursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 549, Short.MAX_VALUE)
        );
        jPanelRecursoLayout.setVerticalGroup(
                jPanelRecursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 457, Short.MAX_VALUE)
        );

        jBotonVolver.setText("Volver al Menu");
        jBotonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jBotonVolver)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(119, Short.MAX_VALUE)
                                .addComponent(jPanelRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanelRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBotonVolver)
                                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void jBotonVolverActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String path = "src/main/resources/location_NASA.jpg";//ruta, si no existe se encarga de poner la default el constructor
        boolean rta= JframeMenuPrincipal.verificarSiNoExisteElArchivo(path);
        if(rta)
        {
            JframeMenuPrincipal menuPrincipal = new JframeMenuPrincipal(null);//si es true no existe la imagen del dia
            setVisible(false);
        }
        else
        {
            JframeMenuPrincipal menuPrincipal = new JframeMenuPrincipal(path);//si es true no existe la imagen del dia
            setVisible(false);
        }

    }




}
