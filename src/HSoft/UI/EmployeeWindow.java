package HSoft.UI;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class EmployeeWindow extends javax.swing.JFrame {
    Timer timer;
    Date currentTime;
    
    public static String timeFormat = "";
    public static String dateFormat = "";
    
    HomePanel homePanel;
    SettingsPanelEmployee settingsPanel;
  
    public EmployeeWindow() {
        initComponents();
        init ();
    }
    
    private void init () {
         
        getContentPane().setBackground( new Color (51, 51, 51) );
        
        ImageIcon posIcon = new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\posIcon.png");
        
        setIconImage (posIcon.getImage());
        
        cardPanel.setLayout(new CardLayout ( ));
        
        updateAccountInformation ();
        updateShopInformation ();
        startTime ();
            
        homePanel = new HomePanel ();
      
        settingsPanel = new SettingsPanelEmployee();
        
        popupMenu.setBorder(BorderFactory.createLineBorder(new Color (35, 35, 35), 1));

        miLogOut.setBackground(new Color (35, 35, 35));
        miLogOut.setOpaque (true);
        miLogOut.setBorder (BorderFactory.createLineBorder(new Color (35, 35, 35), 1));
        miLogOut.setForeground(new Color (235, 235, 235));
        
        miExit.setBackground (new Color (35, 35, 35));
        miExit.setOpaque (true);
        miExit.setBorder (BorderFactory.createLineBorder (new Color (35, 35, 35), 1));
        miExit.setForeground (new Color (235, 235, 235));
        
        popupMenu.setPreferredSize(new Dimension (btnPopupButton.getWidth() , 80));
        
        popupMenu.add(miLogOut);
        popupMenu.add(miExit);
        
        cardPanel.add(homePanel, "homePanel");
        cardPanel.add(settingsPanel, "settingsPanel");
    }
    
     private void updateShopInformation () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT NAME, DESCRIPTION, ADDRESS, SHOPIMAGE, SHOPIMAGELOCATION, TIMEFORMAT, DATEFORMAT FROM TBLSHOP");
            
            for (; sqlResult.next() ;) {

                Blob imageBlob = null;
                ImageIcon image = null;
                
                lblShopName.setText(sqlResult.getString("NAME"));
                lblDescription.setText(sqlResult.getString("DESCRIPTION"));
                lblAddress.setText(sqlResult.getString("ADDRESS"));
                
                timeFormat = sqlResult.getString("TIMEFORMAT");
                dateFormat = sqlResult.getString("DATEFORMAT");
                   
                imageBlob = sqlResult.getBlob("SHOPIMAGE");
                
                try {
                    if (imageBlob != null) {
                    ObjectInputStream oos = new ObjectInputStream (imageBlob.getBinaryStream());
                    try {
                        image = (ImageIcon) oos.readObject();
                      
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    } else {
                        image = new ImageIcon ("C:\\Users\\Gina\\Downloads\\cwcw.jpg");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                lblShopImage.setIcon (new ImageIcon (image.getImage().getScaledInstance(68, 63, Image.SCALE_DEFAULT)));
             
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void updateAccountInformation () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT FIRSTNAME, LASTNAME, EMAIL, ADDRESS, USERIMAGE FROM TBL" + LoginUI.currentTypeUser + " WHERE USERNAME = '" + LoginUI.currentUser + "'");
            
            for (; sqlResult.next () ;) {
               
                lblAccountFullName.setText(sqlResult.getString("FIRSTNAME") + " " + sqlResult.getString("LASTNAME"));
                lblAccountEmail.setText(sqlResult.getString("EMAIL"));
                lblAccountAddress.setText(sqlResult.getString("ADDRESS"));
                
                ImageIcon image = null;
                         Blob photo = sqlResult.getBlob("USERIMAGE");
                         ObjectInputStream ois = null;
                        try {
                            if (photo != null) {
                               ois = new ObjectInputStream(photo.getBinaryStream());
                            
                               try {
                                image = new ImageIcon(((ImageIcon) ois.readObject()).getImage().getScaledInstance(btnPopupButton.getWidth(), btnPopupButton.getHeight(), Image.SCALE_DEFAULT));
                                 } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                File getFileLocation = new File("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                              
                                image = new ImageIcon(new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(btnPopupButton.getWidth(), btnPopupButton.getHeight(), Image.SCALE_DEFAULT));
                            }
                           
                        } catch (IOException ex) {
                            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        btnPopupButton.setIcon(image);        
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeWindow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null)
                    conn.close ();
                
                if (sqlStatement != null)
                    sqlStatement.close ();
                
                if (sqlResult != null)
                    sqlResult.close ();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void startTime () {
        timer = new Timer (1000, new ActionListener () {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentTime = new Date ();
            SimpleDateFormat time = new SimpleDateFormat(timeFormat);
            SimpleDateFormat date = new SimpleDateFormat(dateFormat);
            
            lblTime.setText(time.format(currentTime));
            lblDate.setText(date.format(currentTime));
            
        }
        }); 
        
        Thread t = new Thread (new Runnable () {
            @Override
            public void run() {
                    timer.start();
            }
        });
        
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        miLogOut = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblAccountFullName = new javax.swing.JLabel();
        lblAccountEmail = new javax.swing.JLabel();
        lblShopName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblAccountType = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblShopImage = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblAccountAddress = new javax.swing.JLabel();
        btnPopupButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();

        miLogOut.setText("log out");
        miLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogOutActionPerformed(evt);
            }
        });

        miExit.setText("exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("hPOS");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(35, 35, 35));

        lblAccountFullName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblAccountFullName.setForeground(new java.awt.Color(235, 235, 235));
        lblAccountFullName.setText("Kryssel T De Leon");

        lblAccountEmail.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lblAccountEmail.setForeground(new java.awt.Color(235, 235, 235));
        lblAccountEmail.setText("krysseltillada@gmail.com");

        lblShopName.setFont(new java.awt.Font("Roboto", 0, 48)); // NOI18N
        lblShopName.setForeground(new java.awt.Color(235, 235, 235));
        lblShopName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblDate.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblDate.setForeground(new java.awt.Color(235, 235, 235));
        lblDate.setText("july 20 2020");

        lblAccountType.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lblAccountType.setForeground(new java.awt.Color(235, 235, 235));
        lblAccountType.setText("Employee");

        lblTime.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(235, 235, 235));
        lblTime.setText("4:20 pm");

        lblShopImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/220px-Ivan_III_of_Russia_3.jpg"))); // NOI18N

        lblDescription.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(235, 235, 235));
        lblDescription.setText("cause we live in harmony");

        lblAddress.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(235, 235, 235));
        lblAddress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddress.setText("  hells kitchen ave las pinas city");

        lblAccountAddress.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lblAccountAddress.setForeground(new java.awt.Color(235, 235, 235));
        lblAccountAddress.setText("address: ");

        btnPopupButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/button (6).png"))); // NOI18N
        btnPopupButton.setContentAreaFilled(false);
        btnPopupButton.setFocusable(false);
        btnPopupButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPopupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPopupButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnPopupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAccountEmail)
                            .addComponent(lblAccountAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(339, 339, 339)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAccountType)
                            .addComponent(lblAccountFullName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                        .addComponent(lblShopName, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblShopImage, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblShopName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lblDescription))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTime))
                    .addComponent(lblShopImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAddress)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblAccountFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAccountType)
                .addGap(11, 11, 11)
                .addComponent(lblAccountEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAccountAddress)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnPopupButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/HomeButtonNormal.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusable(false);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHomeMousePressed(evt);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/SettingsNormal.png"))); // NOI18N
        btnSettings.setFocusable(false);
        btnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSettingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSettingsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSettingsMousePressed(evt);
            }
        });
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
        );

        cardPanel.setBackground(new java.awt.Color(0, 255, 204));

        javax.swing.GroupLayout cardPanelLayout = new javax.swing.GroupLayout(cardPanel);
        cardPanel.setLayout(cardPanelLayout);
        cardPanelLayout.setHorizontalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1181, Short.MAX_VALUE)
        );
        cardPanelLayout.setVerticalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed

        CardLayout getLayout = (CardLayout) (cardPanel.getLayout());
        getLayout.show(cardPanel, "homePanel");
        
        cardPanel.setPreferredSize(new Dimension(cardPanel.getPreferredSize().width, cardPanel.getPreferredSize().height));
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed

        CardLayout getLayout = (CardLayout) (cardPanel.getLayout());
        getLayout.show(cardPanel, "settingsPanel");

        cardPanel.setPreferredSize(new Dimension(cardPanel.getPreferredSize().width, cardPanel.getPreferredSize().height));
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnPopupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPopupButtonActionPerformed

        popupMenu.show(btnPopupButton, btnPopupButton.getX(), btnPopupButton.getY() + btnPopupButton.getHeight() - 4);
    }//GEN-LAST:event_btnPopupButtonActionPerformed

    private void miLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogOutActionPerformed

        setVisible(false);
        dispose ();
        
        LoginUI Login = new LoginUI ();
        LoginUI.updateLog("user has log out", lblDate.getText(), lblTime.getText(), LoginUI.currentUser, LoginUI.currentTypeUser);
        
        Login.setLocationRelativeTo(null);
        Login.pack ();
        Login.setVisible(true);
    }//GEN-LAST:event_miLogOutActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed

        int state = JOptionPane.showConfirmDialog(this, "are you sure you want to exit?", "exit", JOptionPane.YES_NO_OPTION);
        
        if (state == 0) {
            
        LoginUI.updateLog("user has log out", lblDate.getText(), lblTime.getText(), LoginUI.currentUser, LoginUI.currentTypeUser);    
        
        setVisible (false);
        dispose ();
        System.exit(0);
        
        }
    }//GEN-LAST:event_miExitActionPerformed

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered

        btnHome.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\HomeButtonFocused.png"));
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited

        btnHome.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\HomeButtonNormal.png"));
        
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMousePressed

        btnHome.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\HomeButtonPressed.png"));
    }//GEN-LAST:event_btnHomeMousePressed

    private void btnSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsMouseEntered

        btnSettings.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\SettingsFocused.png"));
    }//GEN-LAST:event_btnSettingsMouseEntered

    private void btnSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsMouseExited

        btnSettings.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\SettingsNormal.png"));
    }//GEN-LAST:event_btnSettingsMouseExited

    private void btnSettingsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsMousePressed

        btnSettings.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\SettingsPressed.png"));
    }//GEN-LAST:event_btnSettingsMousePressed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    public static javax.swing.JButton btnPopupButton;
    private javax.swing.JButton btnSettings;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel lblAccountAddress;
    public static javax.swing.JLabel lblAccountEmail;
    public static javax.swing.JLabel lblAccountFullName;
    private javax.swing.JLabel lblAccountType;
    public static javax.swing.JLabel lblAddress;
    public static javax.swing.JLabel lblDate;
    public static javax.swing.JLabel lblDescription;
    public static javax.swing.JLabel lblShopImage;
    public static javax.swing.JLabel lblShopName;
    public static javax.swing.JLabel lblTime;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miLogOut;
    private javax.swing.JPopupMenu popupMenu;
    // End of variables declaration//GEN-END:variables
}
