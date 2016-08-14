/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSoft.UI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gina
 */
public class SettingsPanel extends javax.swing.JPanel {
    
    Point MouseCoordinates;
    Point MouseCoordinates2;
    
    
    File getFileLocation;
    
    String accountImageLocation = "";
    String shopImageLocation = "";
    
    String prevUsername = "";
    String prevPassword = "";
    String prevFirstname = "";
    String prevLastname = "";
    String prevEmail = "";
    String prevAddress = "";
    String prevAge = "";
    String prevGender = "";
    String prevAccountImageLocation = "";
    
    String prevShopname = "";
    String prevShopDescription = "";
    String prevShopAddress = "";
    String prevShopImageLocation = "";
    
    String getTimeFormat = "";
    String getDateFormat = "";
    
    String prevTimeFormat = "";
    String prevDateFormat = "";
    
    /**
     * Creates new form SettingsPanel
     */
    public SettingsPanel() {
        initComponents();
        
        setBackground (new Color (51, 51, 51)); 
        
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        
        txtfAccountImageTextBox.setEditable(false);
        txtfShopImageTextBox.setEditable(false);
 
      
        prevTimeFormat = ManagerWindow.timeFormat;
        prevDateFormat = ManagerWindow.dateFormat;
        
        cbTimeFormat.setSelectedIndex((prevTimeFormat.equals("hh:mm:ss a") ? 0 : 1));
        cbDateFormat.setSelectedIndex((prevDateFormat.equals("MMMM dd yyyy") ? 0 : 1));
        
        cbTimeFormat.setEditor (new ComboBoxUI ());
        cbTimeFormat.setEditable (true);
        
        cbDateFormat.setEditor (new ComboBoxUI ());
        cbDateFormat.setEditable (true);
        
        accountImageDialog.setUndecorated(true);
        accountImageDialog.getContentPane().setBackground (new Color (35, 35, 35));
        
        shopImageDialog.setUndecorated (true);
        shopImageDialog.getContentPane().setBackground (new Color (35, 35, 35));
        
        updateAccountInformation ();
        updateShopInformation ();
        
        System.out.println (prevTimeFormat);
        System.out.println (prevDateFormat);
    }
    
    private void updateShopInformation () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLSHOP");
            
            for (; sqlResult.next() ;) {
                Blob imageBlob = conn.createBlob ();
                ImageIcon image = null;
               
                
                prevShopname = sqlResult.getString("NAME");
                prevShopDescription = sqlResult.getString("DESCRIPTION");
                prevShopAddress = sqlResult.getString("ADDRESS");
                
                prevShopImageLocation = sqlResult.getString("SHOPIMAGELOCATION");
              
                imageBlob = sqlResult.getBlob("SHOPIMAGE");
                
                  try {
                    ObjectInputStream oos = new ObjectInputStream(imageBlob.getBinaryStream());
                    try {
                        image = (ImageIcon) oos.readObject();
                        lblShopImage.setIcon (new ImageIcon(image.getImage().getScaledInstance(194, 156, Image.SCALE_DEFAULT)));
            
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                    } catch (IOException ex) {
                        Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                  File getFileName = new File(prevShopImageLocation);
            
                  txtfShopName.setText(prevShopname);
                  txtfShopDescription.setText(prevShopDescription);
                  taShopAddress.setText(prevShopAddress);
                  
                  txtfShopImageTextBox.setText(getFileName.getName());
                  
       
           
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
            sqlResult = sqlStatement.executeQuery("SELECT USERNAME, PASSWORD, FIRSTNAME, LASTNAME, " +
                                                  "EMAIL, ADDRESS, AGE, GENDER, USERIMAGE, USERIMAGELOCATION FROM TBLMANAGER WHERE USERNAME = '" + LoginUI.currentUser + "'");
            
            
            for (; sqlResult.next() ;) {
                Blob imageBlob = null;
                ImageIcon image = null;
                
                prevUsername = sqlResult.getString("USERNAME");
                prevPassword = sqlResult.getString("PASSWORD");
                prevFirstname = sqlResult.getString("FIRSTNAME");
                prevLastname = sqlResult.getString("LASTNAME");
                prevEmail = sqlResult.getString("EMAIL");
                prevAddress = sqlResult.getString("ADDRESS");
                prevAge = sqlResult.getString("AGE");
                prevGender = sqlResult.getString("GENDER");
                prevAccountImageLocation = sqlResult.getString("USERIMAGELOCATION");    
                imageBlob = sqlResult.getBlob("USERIMAGE");
        
                   
                try {
                    ObjectInputStream oos = new ObjectInputStream(imageBlob.getBinaryStream());
                    try {
                        image = (ImageIcon) oos.readObject();
                        lblAccountImage2.setIcon (new ImageIcon(image.getImage().getScaledInstance(190, 179, Image.SCALE_DEFAULT)));
            
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                    } catch (IOException ex) {
                        Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
         
            
            File getFileName = new File(prevAccountImageLocation);
            
            txtfAccountUsername.setText(prevUsername);
            txtfAccountPassword.setText(prevPassword);
            txtfAccountFirstname.setText(prevFirstname);
            txtfAccountLastname.setText(prevLastname);
            txtfAccountEmail.setText(prevEmail);
            taAccountAddress.setText(prevAddress);
            txtfAccountImageTextBox.setText(getFileName.getName());
        
           
            spAge.setValue(Integer.parseInt(prevAge));
            
            System.out.println (prevGender);
            
            if (prevGender.equals("Male")) {
                rbMale.setSelected(true);
            } else {
                rbFemale.setSelected(true);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accountImageDialog = new javax.swing.JDialog();
        jLabel20 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        txtfImageLocation2 = new javax.swing.JTextField();
        btnCancel1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnClose = new javax.swing.JButton();
        imageFileChooser = new javax.swing.JFileChooser();
        bgGender = new javax.swing.ButtonGroup();
        shopImageDialog = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        lblImage1 = new javax.swing.JLabel();
        btnBrowse1 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        txtfImageLocation3 = new javax.swing.JTextField();
        btnCancel2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnClose2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAccountAddress = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        spAge = new javax.swing.JSpinner();
        txtfAccountEmail = new javax.swing.JTextField();
        txtfAccountUsername = new javax.swing.JTextField();
        txtfAccountPassword = new javax.swing.JTextField();
        txtfAccountFirstname = new javax.swing.JTextField();
        txtfAccountLastname = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taShopAddress = new javax.swing.JTextArea();
        txtfShopName = new javax.swing.JTextField();
        txtfShopDescription = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbTimeFormat = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        lblTimePreview = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbDateFormat = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        lblDatePreview = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblAccountImage2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblShopImage = new javax.swing.JLabel();
        txtfAccountImageTextBox = new javax.swing.JTextField();
        btnUploadAccountImage = new javax.swing.JButton();
        btnUploadShopImage = new javax.swing.JButton();
        txtfShopImageTextBox = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        accountImageDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                accountImageDialogMouseDragged(evt);
            }
        });
        accountImageDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountImageDialogMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(235, 235, 235));
        jLabel20.setText("Upload an image");

        btnBrowse.setBackground(new java.awt.Color(51, 51, 51));
        btnBrowse.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnBrowse.setForeground(new java.awt.Color(235, 235, 235));
        btnBrowse.setText("Browse");
        btnBrowse.setContentAreaFilled(false);
        btnBrowse.setFocusable(false);
        btnBrowse.setOpaque(true);
        btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBrowseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBrowseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBrowseMousePressed(evt);
            }
        });
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnSave1.setBackground(new java.awt.Color(51, 51, 51));
        btnSave1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(235, 235, 235));
        btnSave1.setText("Save");
        btnSave1.setContentAreaFilled(false);
        btnSave1.setFocusable(false);
        btnSave1.setOpaque(true);
        btnSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSave1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSave1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSave1MousePressed(evt);
            }
        });
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        txtfImageLocation2.setEditable(false);
        txtfImageLocation2.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfImageLocation2.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        txtfImageLocation2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnCancel1.setBackground(new java.awt.Color(51, 51, 51));
        btnCancel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancel1.setForeground(new java.awt.Color(235, 235, 235));
        btnCancel1.setText("Cancel");
        btnCancel1.setContentAreaFilled(false);
        btnCancel1.setFocusable(false);
        btnCancel1.setOpaque(true);
        btnCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancel1MousePressed(evt);
            }
        });
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator1.setForeground(new java.awt.Color(35, 35, 35));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusable(false);
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCloseMousePressed(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accountImageDialogLayout = new javax.swing.GroupLayout(accountImageDialog.getContentPane());
        accountImageDialog.getContentPane().setLayout(accountImageDialogLayout);
        accountImageDialogLayout.setHorizontalGroup(
            accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountImageDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accountImageDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountImageDialogLayout.createSequentialGroup()
                                .addComponent(txtfImageLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(accountImageDialogLayout.createSequentialGroup()
                                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))
                        .addGroup(accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(accountImageDialogLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(accountImageDialogLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        accountImageDialogLayout.setVerticalGroup(
            accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountImageDialogLayout.createSequentialGroup()
                .addGroup(accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(accountImageDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBrowse)
                    .addGroup(accountImageDialogLayout.createSequentialGroup()
                        .addComponent(txtfImageLocation2)
                        .addGap(3, 3, 3)))
                .addGap(7, 7, 7)
                .addGroup(accountImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave1)
                    .addComponent(btnCancel1))
                .addContainerGap())
        );

        shopImageDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                shopImageDialogMouseDragged(evt);
            }
        });
        shopImageDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shopImageDialogMousePressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(235, 235, 235));
        jLabel23.setText("Upload an image");

        btnBrowse1.setBackground(new java.awt.Color(51, 51, 51));
        btnBrowse1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnBrowse1.setForeground(new java.awt.Color(235, 235, 235));
        btnBrowse1.setText("Browse");
        btnBrowse1.setContentAreaFilled(false);
        btnBrowse1.setFocusable(false);
        btnBrowse1.setOpaque(true);
        btnBrowse1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBrowse1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBrowse1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBrowse1MousePressed(evt);
            }
        });
        btnBrowse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowse1ActionPerformed(evt);
            }
        });

        btnSave2.setBackground(new java.awt.Color(51, 51, 51));
        btnSave2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(235, 235, 235));
        btnSave2.setText("Save");
        btnSave2.setContentAreaFilled(false);
        btnSave2.setFocusable(false);
        btnSave2.setOpaque(true);
        btnSave2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSave2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSave2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSave2MousePressed(evt);
            }
        });
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        txtfImageLocation3.setEditable(false);
        txtfImageLocation3.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation3.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfImageLocation3.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation3.setText(".jpg, .png, .gif only");
        txtfImageLocation3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnCancel2.setBackground(new java.awt.Color(51, 51, 51));
        btnCancel2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancel2.setForeground(new java.awt.Color(235, 235, 235));
        btnCancel2.setText("Cancel");
        btnCancel2.setContentAreaFilled(false);
        btnCancel2.setFocusable(false);
        btnCancel2.setOpaque(true);
        btnCancel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancel2MousePressed(evt);
            }
        });
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator2.setForeground(new java.awt.Color(35, 35, 35));

        btnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose2.setContentAreaFilled(false);
        btnClose2.setFocusable(false);
        btnClose2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClose2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClose2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClose2MousePressed(evt);
            }
        });
        btnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shopImageDialogLayout = new javax.swing.GroupLayout(shopImageDialog.getContentPane());
        shopImageDialog.getContentPane().setLayout(shopImageDialogLayout);
        shopImageDialogLayout.setHorizontalGroup(
            shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shopImageDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shopImageDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shopImageDialogLayout.createSequentialGroup()
                                .addComponent(txtfImageLocation3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(shopImageDialogLayout.createSequentialGroup()
                                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))
                        .addGroup(shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBrowse1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(shopImageDialogLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(shopImageDialogLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        shopImageDialogLayout.setVerticalGroup(
            shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shopImageDialogLayout.createSequentialGroup()
                .addGroup(shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(shopImageDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBrowse1)
                    .addGroup(shopImageDialogLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtfImageLocation3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shopImageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave2)
                    .addComponent(btnCancel2))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(35, 35, 35));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Settings");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(35, 35, 35));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Account information");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(485, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("username");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("password");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 235, 235));
        jLabel6.setText("firstname");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(235, 235, 235));
        jLabel7.setText("lastname");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(235, 235, 235));
        jLabel8.setText("email");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(235, 235, 235));
        jLabel9.setText("gender");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(235, 235, 235));
        jLabel10.setText("address");

        taAccountAddress.setBackground(new java.awt.Color(35, 35, 35));
        taAccountAddress.setColumns(20);
        taAccountAddress.setForeground(new java.awt.Color(235, 235, 235));
        taAccountAddress.setRows(5);
        taAccountAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        jScrollPane1.setViewportView(taAccountAddress);

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(235, 235, 235));
        jLabel11.setText("Edit your Account's information");

        rbMale.setBackground(new java.awt.Color(35, 35, 35));
        rbMale.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        rbMale.setForeground(new java.awt.Color(235, 235, 235));
        rbMale.setText("Male");

        rbFemale.setBackground(new java.awt.Color(35, 35, 35));
        rbFemale.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        rbFemale.setForeground(new java.awt.Color(235, 235, 235));
        rbFemale.setText("Female");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(235, 235, 235));
        jLabel12.setText("Age");

        spAge.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N

        txtfAccountEmail.setBackground(new java.awt.Color(35, 35, 35));
        txtfAccountEmail.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfAccountEmail.setForeground(new java.awt.Color(235, 235, 235));
        txtfAccountEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfAccountUsername.setBackground(new java.awt.Color(35, 35, 35));
        txtfAccountUsername.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfAccountUsername.setForeground(new java.awt.Color(235, 235, 235));
        txtfAccountUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfAccountPassword.setBackground(new java.awt.Color(35, 35, 35));
        txtfAccountPassword.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfAccountPassword.setForeground(new java.awt.Color(235, 235, 235));
        txtfAccountPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfAccountFirstname.setBackground(new java.awt.Color(35, 35, 35));
        txtfAccountFirstname.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfAccountFirstname.setForeground(new java.awt.Color(235, 235, 235));
        txtfAccountFirstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfAccountLastname.setBackground(new java.awt.Color(35, 35, 35));
        txtfAccountLastname.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfAccountLastname.setForeground(new java.awt.Color(235, 235, 235));
        txtfAccountLastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtfAccountUsername)
                            .addComponent(txtfAccountPassword)
                            .addComponent(txtfAccountFirstname)
                            .addComponent(txtfAccountLastname, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtfAccountEmail))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbMale)
                                        .addGap(63, 63, 63)
                                        .addComponent(rbFemale)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(32, 32, 32)
                                .addComponent(spAge, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtfAccountEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfAccountUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbFemale)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfAccountPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtfAccountFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfAccountLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(35, 35, 35));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 235, 235));
        jLabel4.setText("Shop information");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(35, 35, 35));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(235, 235, 235));
        jLabel13.setText("Edit your shop's information");

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(235, 235, 235));
        jLabel14.setText("name");

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(235, 235, 235));
        jLabel15.setText("Description");

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(235, 235, 235));
        jLabel16.setText("Address");

        taShopAddress.setBackground(new java.awt.Color(35, 35, 35));
        taShopAddress.setColumns(20);
        taShopAddress.setForeground(new java.awt.Color(235, 235, 235));
        taShopAddress.setRows(5);
        taShopAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        jScrollPane2.setViewportView(taShopAddress);

        txtfShopName.setBackground(new java.awt.Color(35, 35, 35));
        txtfShopName.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfShopName.setForeground(new java.awt.Color(235, 235, 235));
        txtfShopName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfShopDescription.setBackground(new java.awt.Color(35, 35, 35));
        txtfShopDescription.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfShopDescription.setForeground(new java.awt.Color(235, 235, 235));
        txtfShopDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(235, 235, 235));
        jLabel17.setText("change date / time format");

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(235, 235, 235));
        jLabel18.setText("Time ");

        cbTimeFormat.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        cbTimeFormat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "standard time", "military time" }));
        cbTimeFormat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbTimeFormat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTimeFormatItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(235, 235, 235));
        jLabel19.setText("Preview");

        lblTimePreview.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblTimePreview.setForeground(new java.awt.Color(235, 235, 235));
        lblTimePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimePreview.setText("2:20pm");

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(235, 235, 235));
        jLabel21.setText("Date");

        cbDateFormat.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        cbDateFormat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "in words", "in digits" }));
        cbDateFormat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbDateFormat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDateFormatItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(235, 235, 235));
        jLabel22.setText("Preview");

        lblDatePreview.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblDatePreview.setForeground(new java.awt.Color(235, 235, 235));
        lblDatePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDatePreview.setText("july 20 2020");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtfShopDescription, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(txtfShopName))))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addGap(18, 18, 18)
                            .addComponent(cbTimeFormat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lblTimePreview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addGap(59, 59, 59)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cbDateFormat, 0, 138, Short.MAX_VALUE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblDatePreview)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfShopName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTimeFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDateFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTimePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDatePreview))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtfShopDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(15, 15, 15))))
        );

        btnSave.setBackground(new java.awt.Color(35, 35, 35));
        btnSave.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSave.setForeground(new java.awt.Color(235, 235, 235));
        btnSave.setText("Save");
        btnSave.setContentAreaFilled(false);
        btnSave.setOpaque(true);
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveMousePressed(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(35, 35, 35));
        btnCancel.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(235, 235, 235));
        btnCancel.setText("Cancel");
        btnCancel.setContentAreaFilled(false);
        btnCancel.setOpaque(true);
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelMousePressed(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblAccountImage2.setBackground(new java.awt.Color(35, 35, 35));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAccountImage2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAccountImage2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblShopImage, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblShopImage, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        txtfAccountImageTextBox.setBackground(new java.awt.Color(35, 35, 35));
        txtfAccountImageTextBox.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfAccountImageTextBox.setForeground(new java.awt.Color(235, 235, 235));
        txtfAccountImageTextBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnUploadAccountImage.setBackground(new java.awt.Color(35, 35, 35));
        btnUploadAccountImage.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnUploadAccountImage.setForeground(new java.awt.Color(235, 235, 235));
        btnUploadAccountImage.setText("Upload");
        btnUploadAccountImage.setContentAreaFilled(false);
        btnUploadAccountImage.setOpaque(true);
        btnUploadAccountImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUploadAccountImageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUploadAccountImageMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUploadAccountImageMousePressed(evt);
            }
        });
        btnUploadAccountImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadAccountImageActionPerformed(evt);
            }
        });

        btnUploadShopImage.setBackground(new java.awt.Color(35, 35, 35));
        btnUploadShopImage.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnUploadShopImage.setForeground(new java.awt.Color(235, 235, 235));
        btnUploadShopImage.setText("Upload");
        btnUploadShopImage.setContentAreaFilled(false);
        btnUploadShopImage.setOpaque(true);
        btnUploadShopImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUploadShopImageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUploadShopImageMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUploadShopImageMousePressed(evt);
            }
        });
        btnUploadShopImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadShopImageActionPerformed(evt);
            }
        });

        txtfShopImageTextBox.setBackground(new java.awt.Color(35, 35, 35));
        txtfShopImageTextBox.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfShopImageTextBox.setForeground(new java.awt.Color(235, 235, 235));
        txtfShopImageTextBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        jPanel9.setBackground(new java.awt.Color(35, 35, 35));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(35, 35, 35));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel26.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(235, 235, 235));
        jLabel26.setText("ver: 1.0");

        jLabel27.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(235, 235, 235));
        jLabel27.setText("ver: 1.0");
        jLabel27.setToolTipText("");

        jLabel24.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(171, 197, 54));
        jLabel24.setText("H-developers / H-soft");
        jLabel24.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtfAccountImageTextBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUploadAccountImage)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtfShopImageTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUploadShopImage))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUploadAccountImage)
                            .addComponent(txtfAccountImageTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSave)
                                        .addComponent(btnCancel))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel27))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUploadShopImage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfShopImageTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(235, 235, 235));
        jLabel25.setText("ver: 1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 298, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(0, 299, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

     private boolean checkImageType (String val, String []imageTypes) {
        for (int imageCount = 0; imageCount != imageTypes.length; ++imageCount) {
            
            if (imageTypes[imageCount].equals(val))
                return true;
        }
        
        return false;
    }
    
    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        String []imageTypes = new String[]{".jpg", ".png", ".gif"};

        if (imageFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            getFileLocation = imageFileChooser.getSelectedFile();

            String getImageType = getFileLocation.getName();
            getImageType = getImageType.substring(getImageType.indexOf("."));

            if (checkImageType(getImageType, imageTypes)) {

                lblImage.setIcon( new ImageIcon(new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_DEFAULT)));
                txtfImageLocation2.setText(getFileLocation.getName());

            } else {
                JOptionPane.showMessageDialog(this, "invalid file type", "upload", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "upload an image", "upload ", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
        if (!txtfImageLocation2.getText().isEmpty()) {

            txtfAccountImageTextBox.setText(txtfImageLocation2.getText());
            try {

                accountImageLocation = getFileLocation.getAbsolutePath();
                lblAccountImage2.setIcon(new ImageIcon( new ImageIcon( getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblAccountImage2.getWidth(), lblAccountImage2.getHeight(), Image.SCALE_DEFAULT) )  );
                accountImageDialog.dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(accountImageDialog, "image location not found", "upload image", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "you must upload an image", "upload image", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        lblImage.setIcon(null);
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        accountImageLocation = "";
        accountImageDialog.dispose();
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnUploadAccountImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadAccountImageActionPerformed
        // TODO add your handling code here:
        accountImageDialog.pack ();
        accountImageDialog.setLocationRelativeTo(null);
        accountImageDialog.setVisible(true);
    }//GEN-LAST:event_btnUploadAccountImageActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        File getFileName = new File(prevAccountImageLocation);
        
        this.accountImageLocation = prevAccountImageLocation;
        this.shopImageLocation = prevShopImageLocation;

        txtfAccountUsername.setText(prevUsername);
        txtfAccountPassword.setText(prevPassword);
        txtfAccountFirstname.setText(prevFirstname);
        txtfAccountLastname.setText(prevLastname);
        txtfAccountEmail.setText(prevEmail);
        taAccountAddress.setText(prevAddress);
        txtfAccountImageTextBox.setText(getFileName.getName());

        lblAccountImage2.setIcon (new ImageIcon ( new ImageIcon(prevAccountImageLocation).getImage().getScaledInstance(190, 179, WIDTH))  );

        lblShopImage.setIcon (new ImageIcon (new ImageIcon(prevShopImageLocation).getImage().getScaledInstance(194, 156, Image.SCALE_DEFAULT)));
        
        txtfShopName.setText(prevShopname);
        txtfShopDescription.setText(prevShopDescription);
        taShopAddress.setText(prevShopAddress);
        
        cbTimeFormat.setSelectedIndex( (prevTimeFormat.equals("hh:mm:ss a") ? 0 : 1 ));
        cbDateFormat.setSelectedIndex( (prevDateFormat.equals("MMMM dd yyyy") ? 0 : 1));
        
        if (prevGender.equals("Male")) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }

        spAge.setValue(Integer.parseInt(prevAge));

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if ((int) spAge.getValue() <= 0) {
            JOptionPane.showMessageDialog (this, "negative values aren't allowed", "settings", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement sqlStatement = null;


        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.prepareStatement("UPDATE TBLMANAGER SET USERNAME = ?, PASSWORD = ?, FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, ADDRESS = ?," +
                "AGE = ?, GENDER = ?, USERIMAGE = ?, USERIMAGELOCATION = ? WHERE EMAIL = ?");

            sqlStatement.setString(1, txtfAccountUsername.getText());
            sqlStatement.setString(2, txtfAccountPassword.getText());
            sqlStatement.setString(3, txtfAccountFirstname.getText());
            sqlStatement.setString(4, txtfAccountLastname.getText());
            sqlStatement.setString(5, txtfAccountEmail.getText());
            sqlStatement.setString(6, taAccountAddress.getText());
            sqlStatement.setInt(7, Integer.parseInt(spAge.getValue().toString()));

            if (rbMale.isSelected())
            sqlStatement.setString(8, "Male");
            else
            sqlStatement.setString(8, "Female");

            ImageIcon getImage;

            if (accountImageLocation.isEmpty()) {
                getImage = new ImageIcon (prevAccountImageLocation);
                System.out.println (prevAccountImageLocation + " prev");
                sqlStatement.setString(10, prevAccountImageLocation);
            } else {
                getImage = new ImageIcon (accountImageLocation);
                sqlStatement.setString(10, accountImageLocation);
                System.out.println (accountImageLocation + "  new");
            }

            Blob imageBlob = conn.createBlob();

            ObjectOutputStream oos = null;
            
            try {
                oos = new ObjectOutputStream (imageBlob.setBinaryStream(1));
                oos.writeObject(getImage);
               
            } catch (IOException ex) {
                Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            sqlStatement.setBlob(9, imageBlob);

            sqlStatement.setString(11, prevEmail);

            sqlStatement.execute();

            System.out.println ("save");

            LoginUI.currentUser = this.txtfAccountUsername.getText();
            
            ManagerWindow.lblAccountFullName.setText(this.txtfAccountFirstname.getText() + " " + this.txtfAccountLastname.getText());
            ManagerWindow.lblAccountEmail.setText(this.txtfAccountEmail.getText());
            ManagerWindow.lblAccountAddress.setText(this.taAccountAddress.getText());
     
            ManagerWindow.btnPopupButton.setIcon(new ImageIcon(getImage.getImage().getScaledInstance(131, 114, Image.SCALE_DEFAULT)));
            
            updateAccountInformation();
            
        } catch (SQLException ex) {
            Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Connection conn2 = null;
        PreparedStatement sqlStatement2 = null;
        
        try {
            conn2 = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement2 = conn2.prepareStatement("UPDATE TBLSHOP SET NAME = ?, DESCRIPTION = ?, ADDRESS = ?, SHOPIMAGE = ?, SHOPIMAGELOCATION = ?, TIMEFORMAT = ?, DATEFORMAT = ? " +
                                                    "WHERE NAME = ?");
            
          ImageIcon shopImage = null; 
            
            if (this.shopImageLocation.isEmpty()) {
               
                shopImage = new ImageIcon (this.prevShopImageLocation);
                sqlStatement2.setString(5, this.prevShopImageLocation);
            } else {
             
                shopImage = new ImageIcon (shopImageLocation);
                sqlStatement2.setString(5, shopImageLocation);
            }

            
            Blob imageBlob = conn2.createBlob();
            
            ObjectOutputStream imageToBlob = null;                     
            
            try {
                imageToBlob = new ObjectOutputStream (imageBlob.setBinaryStream(1));
                imageToBlob.writeObject(shopImage);
            } catch (IOException ex) {
                Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            sqlStatement2.setString(1, txtfShopName.getText());
            sqlStatement2.setString(2, txtfShopDescription.getText()); 
            sqlStatement2.setString(3, taShopAddress.getText());
            sqlStatement2.setBlob (4, imageBlob);
            
            if (getTimeFormat.isEmpty()) {
                sqlStatement2.setString(6, prevTimeFormat);
            } else {
                   ManagerWindow.timeFormat = getTimeFormat;
                sqlStatement2.setString(6, getTimeFormat);
            }
            
            if (getDateFormat.isEmpty()) {
                sqlStatement2.setString(7, prevDateFormat);
            } else{
                ManagerWindow.dateFormat = getDateFormat;  
                sqlStatement2.setString(7, getDateFormat);
            }
            sqlStatement2.setString(8, prevShopname);
            
            sqlStatement2.execute();
           
            ManagerWindow.lblShopName.setText(txtfShopName.getText());
            ManagerWindow.lblDescription.setText(txtfShopDescription.getText());
            ManagerWindow.lblShopImage.setIcon (new ImageIcon ( shopImage.getImage().getScaledInstance(68, 63, Image.SCALE_DEFAULT) ));
                    
            
            
            updateShopInformation();
            
        } catch (SQLException ex) {
            Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
     
      
        JOptionPane.showMessageDialog (this, "settings saved", "settings", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
        // TODO add your handling code here:
        lblImage1.setIcon (null);
        txtfImageLocation3.setText(".jpg, .png, .gif only");
        shopImageLocation = "";
        shopImageDialog.dispose();
    }//GEN-LAST:event_btnCancel2ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
        if (!txtfImageLocation3.getText().isEmpty()) {

            txtfShopImageTextBox.setText(txtfImageLocation3.getText());
            try {

                shopImageLocation = getFileLocation.getAbsolutePath();
                lblShopImage.setIcon(new ImageIcon( new ImageIcon( getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblShopImage.getWidth(), lblShopImage.getHeight(), Image.SCALE_DEFAULT) )  );
                shopImageDialog.dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(shopImageDialog, "image location not found", "upload image", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "you must upload an image", "upload image", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void btnBrowse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowse1ActionPerformed
        // TODO add your handling code here:
        String []imageTypes = new String[]{".jpg", ".png", ".gif"};

        if (imageFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            getFileLocation = imageFileChooser.getSelectedFile();

            String getImageType = getFileLocation.getName();
            getImageType = getImageType.substring(getImageType.indexOf("."));

            if (checkImageType(getImageType, imageTypes)) {

                lblImage1.setIcon( new ImageIcon(new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(144, 136, Image.SCALE_DEFAULT)));
                txtfImageLocation3.setText(getFileLocation.getName());

            } else {
                JOptionPane.showMessageDialog(this, "invalid file type", "upload", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "upload an image", "upload ", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBrowse1ActionPerformed

    private void btnUploadShopImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadShopImageActionPerformed
        // TODO add your handling code here:
        shopImageDialog.pack();
        shopImageDialog.setLocationRelativeTo(null);
        shopImageDialog.setVisible(true);
    }//GEN-LAST:event_btnUploadShopImageActionPerformed

    private void cbDateFormatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDateFormatItemStateChanged
        // TODO add your handling code here:
        getDateFormat = ( removeWhiteSpace(cbDateFormat.getSelectedItem().toString()).toUpperCase().equals("INWORDS") ) ? "MMMM dd yyyy" : "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat (getDateFormat);
        java.util.Date getCurrentDate = new java.util.Date ();

        lblDatePreview.setText(dateFormat.format(getCurrentDate));
    }//GEN-LAST:event_cbDateFormatItemStateChanged

    private void cbTimeFormatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTimeFormatItemStateChanged
        // TODO add your handling code here:
        getTimeFormat = (removeWhiteSpace(cbTimeFormat.getSelectedItem().toString()).toUpperCase().equals("STANDARDTIME")) ? "hh:mm:ss a" : "HH:mm:ss";
        SimpleDateFormat timeFormat = new SimpleDateFormat(getTimeFormat);
        java.util.Date getCurrentTime = new java.util.Date ();

        lblTimePreview.setText(timeFormat.format(getCurrentTime));

    }//GEN-LAST:event_cbTimeFormatItemStateChanged

    private void btnUploadAccountImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadAccountImageMouseEntered
        // TODO add your handling code here:
        btnUploadAccountImage.setBackground (new Color (74, 74, 74)); 
    }//GEN-LAST:event_btnUploadAccountImageMouseEntered

    private void btnUploadAccountImageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadAccountImageMouseExited
        // TODO add your handling code here:
        btnUploadAccountImage.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnUploadAccountImageMouseExited

    private void btnUploadAccountImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadAccountImageMousePressed
        // TODO add your handling code here:
        btnUploadAccountImage.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnUploadAccountImageMousePressed

    private void btnUploadShopImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadShopImageMouseEntered
        // TODO add your handling code here:
        btnUploadShopImage.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnUploadShopImageMouseEntered

    private void btnUploadShopImageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadShopImageMouseExited
        // TODO add your handling code here:
        btnUploadShopImage.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnUploadShopImageMouseExited

    private void btnUploadShopImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadShopImageMousePressed
        // TODO add your handling code here:
        btnUploadShopImage.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnUploadShopImageMousePressed

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        btnSave.setBackground (new Color (74, 74, 74)); 
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        // TODO add your handling code here:
        btnSave.setBackground (new Color (35, 35, 35)); 
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
        btnSave.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnCancelMousePressed

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        // TODO add your handling code here:
        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        // TODO add your handling code here:
       btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
        // TODO add your handling code here:
        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnBrowseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseEntered
        // TODO add your handling code here:
        btnBrowse.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnBrowseMouseEntered

    private void btnBrowseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseExited
        // TODO add your handling code here:
        btnBrowse.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnBrowseMouseExited

    private void btnBrowseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMousePressed
        // TODO add your handling code here:
        btnBrowse.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnBrowseMousePressed

    private void btnCancel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MouseEntered
        // TODO add your handling code here:
        btnCancel1.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnCancel1MouseEntered

    private void btnCancel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MouseExited
        // TODO add your handling code here:
        btnCancel1.setBackground(new Color (51, 51, 51));
    }//GEN-LAST:event_btnCancel1MouseExited

    private void btnCancel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MousePressed
        // TODO add your handling code here:
        btnCancel1.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnCancel1MousePressed

    private void btnSave1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave1MouseEntered
        // TODO add your handling code here:
        btnSave1.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSave1MouseEntered

    private void btnSave1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave1MouseExited
        // TODO add your handling code here:
        btnSave1.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSave1MouseExited

    private void btnSave1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave1MousePressed
        // TODO add your handling code here:
        btnSave1.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSave1MousePressed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        lblImage.setIcon(null);
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        accountImageLocation = "";
        accountImageDialog.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void accountImageDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountImageDialogMousePressed
        // TODO add your handling code here:
        MouseCoordinates = evt.getPoint();
    }//GEN-LAST:event_accountImageDialogMousePressed

    private void accountImageDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountImageDialogMouseDragged
        // TODO add your handling code here 
        Point currentCoords = evt.getLocationOnScreen();
               accountImageDialog.setLocation(currentCoords.x - MouseCoordinates.x, currentCoords.y - MouseCoordinates.y);
    }//GEN-LAST:event_accountImageDialogMouseDragged

    private void btnClose2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MouseEntered
        // TODO add your handling code here:
        btnClose2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnClose2MouseEntered

    private void btnClose2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MouseExited
        // TODO add your handling code here:
        btnClose2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnClose2MouseExited

    private void btnClose2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MousePressed
        // TODO add your handling code here:
        btnClose2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnClose2MousePressed

    private void btnBrowse1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowse1MouseEntered
        // TODO add your handling code here:
        btnBrowse1.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnBrowse1MouseEntered

    private void btnBrowse1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowse1MouseExited
        // TODO add your handling code here:
        btnBrowse1.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnBrowse1MouseExited

    private void btnBrowse1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowse1MousePressed
        // TODO add your handling code here:
        btnBrowse1.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnBrowse1MousePressed

    private void btnCancel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel2MouseEntered
        // TODO add your handling code here:
        btnCancel2.setBackground (new Color (74, 74, 74)); 
    }//GEN-LAST:event_btnCancel2MouseEntered

    private void btnCancel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel2MouseExited
        // TODO add your handling code here:
        btnCancel2.setBackground (new Color (51, 51, 51)); 
    }//GEN-LAST:event_btnCancel2MouseExited

    private void btnCancel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel2MousePressed
        // TODO add your handling code here:
        btnCancel2.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnCancel2MousePressed

    private void btnSave2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave2MouseEntered
        // TODO add your handling code here:
        btnSave2.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSave2MouseEntered

    private void btnSave2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave2MouseExited
        // TODO add your handling code here:
        btnSave2.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSave2MouseExited

    private void btnSave2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave2MousePressed
        // TODO add your handling code here:
        btnSave2.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSave2MousePressed

    private void shopImageDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shopImageDialogMousePressed
        // TODO add your handling code here:
          MouseCoordinates2 = evt.getPoint();
    }//GEN-LAST:event_shopImageDialogMousePressed

    private void shopImageDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shopImageDialogMouseDragged
        // TODO add your handling code here:
         Point currentCoords = evt.getLocationOnScreen();
                shopImageDialog.setLocation(currentCoords.x - MouseCoordinates2.x, currentCoords.y - MouseCoordinates2.y);
    }//GEN-LAST:event_shopImageDialogMouseDragged

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
        // TODO add your handling code here:
        lblImage1.setIcon (null);
        txtfImageLocation3.setText(".jpg, .png, .gif only");
        shopImageLocation = "";
        shopImageDialog.dispose();
    }//GEN-LAST:event_btnClose2ActionPerformed
     
    private String removeWhiteSpace (String word) {
        String rwsWord = "";
        
        for (int i = 0; i != word.length(); ++i) {
            if (!Character.isWhitespace(word.charAt(i)))
                rwsWord += word.charAt(i);
        }
        
        return rwsWord;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog accountImageDialog;
    private javax.swing.ButtonGroup bgGender;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnBrowse1;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnUploadAccountImage;
    private javax.swing.JButton btnUploadShopImage;
    private javax.swing.JComboBox<String> cbDateFormat;
    private javax.swing.JComboBox<String> cbTimeFormat;
    private javax.swing.JFileChooser imageFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAccountImage2;
    private javax.swing.JLabel lblDatePreview;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblShopImage;
    private javax.swing.JLabel lblTimePreview;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JDialog shopImageDialog;
    private javax.swing.JSpinner spAge;
    private javax.swing.JTextArea taAccountAddress;
    private javax.swing.JTextArea taShopAddress;
    private javax.swing.JTextField txtfAccountEmail;
    private javax.swing.JTextField txtfAccountFirstname;
    private javax.swing.JTextField txtfAccountImageTextBox;
    private javax.swing.JTextField txtfAccountLastname;
    private javax.swing.JTextField txtfAccountPassword;
    private javax.swing.JTextField txtfAccountUsername;
    private javax.swing.JTextField txtfImageLocation2;
    private javax.swing.JTextField txtfImageLocation3;
    private javax.swing.JTextField txtfShopDescription;
    private javax.swing.JTextField txtfShopImageTextBox;
    private javax.swing.JTextField txtfShopName;
    // End of variables declaration//GEN-END:variables
}
