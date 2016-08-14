package HSoft.UI;

import HSoft.User.Account;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreateUI extends javax.swing.JFrame {
    Point MouseCoordinates;
    Point MouseCoordinates2;
    
    String imageUserLocation;
    File getFileLocation;
    
    public CreateUI() {
        setUndecorated(true);
        
        initComponents();
        
        imageDialog.setUndecorated(true);
        
        Component getEditor = spAge.getEditor().getComponent(0);
        
        getEditor.setBackground(new Color (35, 35, 35));
        getEditor.setForeground (new Color (235, 235, 235));
        
        imageDialog.getContentPane().setBackground(new Color (35, 35, 35));
        
        Font roboto = new Font ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\fonts\\roboto\\Roboto-Regular.ttf", Font.PLAIN, 11);
        
        lblUploadMessage.setFont (roboto);
        
        txtfImageLocation2.setFont(roboto);
        
        cbUser.setEditor (new ComboBoxUI ());
        cbUser.setEditable (true);
       
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(new Color(35, 35, 35) );
        
        bgGender.add(rbMale);
        bgGender.add(rbFemale);       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageDialog = new javax.swing.JDialog();
        lblUploadMessage = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtfImageLocation2 = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnClose2 = new javax.swing.JButton();
        imageFileChooser = new javax.swing.JFileChooser();
        bgGender = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnCancel2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtfImageLocation = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        txtfUsername = new javax.swing.JTextField();
        txtfPassword = new javax.swing.JTextField();
        txtfFirstname = new javax.swing.JTextField();
        txtfLastname = new javax.swing.JTextField();
        txtfEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAddress = new javax.swing.JTextArea();
        btnSignup = new javax.swing.JButton();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        spAge = new javax.swing.JSpinner();
        cbUser = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnClose = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();

        imageDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                imageDialogMouseDragged(evt);
            }
        });
        imageDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageDialogMousePressed(evt);
            }
        });

        lblUploadMessage.setForeground(new java.awt.Color(235, 235, 235));
        lblUploadMessage.setText("Upload an image");

        btnBrowse.setBackground(new java.awt.Color(51, 51, 51));
        btnBrowse.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnBrowse.setForeground(new java.awt.Color(235, 235, 235));
        btnBrowse.setText("Browse");
        btnBrowse.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/SaveNormal.png"))); // NOI18N
        btnSave.setToolTipText("");
        btnSave.setContentAreaFilled(false);
        btnSave.setFocusable(false);
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

        txtfImageLocation2.setEditable(false);
        txtfImageLocation2.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation2.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        txtfImageLocation2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CancelNormal.png"))); // NOI18N
        btnCancel.setContentAreaFilled(false);
        btnCancel.setFocusable(false);
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

        jSeparator2.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator2.setForeground(new java.awt.Color(35, 35, 35));

        btnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose2.setBorderPainted(false);
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

        javax.swing.GroupLayout imageDialogLayout = new javax.swing.GroupLayout(imageDialog.getContentPane());
        imageDialog.getContentPane().setLayout(imageDialogLayout);
        imageDialogLayout.setHorizontalGroup(
            imageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(imageDialogLayout.createSequentialGroup()
                .addGroup(imageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imageDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(imageDialogLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(imageDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtfImageLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(imageDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUploadMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imageDialogLayout.setVerticalGroup(
            imageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageDialogLayout.createSequentialGroup()
                .addGroup(imageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imageDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUploadMessage))
                    .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfImageLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(imageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Create your Account");

        btnCancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/SCancelNormal.png"))); // NOI18N
        btnCancel2.setToolTipText("cancel the registration");
        btnCancel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancel2.setContentAreaFilled(false);
        btnCancel2.setFocusable(false);
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

        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Username");

        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Password");

        jLabel4.setForeground(new java.awt.Color(235, 235, 235));
        jLabel4.setText("Email");

        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("Firstname");

        jLabel6.setForeground(new java.awt.Color(235, 235, 235));
        jLabel6.setText("Lastname");

        jLabel7.setForeground(new java.awt.Color(235, 235, 235));
        jLabel7.setText("Address");

        jLabel10.setForeground(new java.awt.Color(235, 235, 235));
        jLabel10.setText("Upload Image");

        txtfImageLocation.setEditable(false);
        txtfImageLocation.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation.setToolTipText("upload your image");
        txtfImageLocation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/UploadNormal.png"))); // NOI18N
        btnUpload.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnUpload.setContentAreaFilled(false);
        btnUpload.setFocusable(false);
        btnUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUploadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUploadMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUploadMousePressed(evt);
            }
        });
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        txtfUsername.setBackground(new java.awt.Color(35, 35, 35));
        txtfUsername.setForeground(new java.awt.Color(235, 235, 235));
        txtfUsername.setToolTipText("your username");
        txtfUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfPassword.setBackground(new java.awt.Color(35, 35, 35));
        txtfPassword.setForeground(new java.awt.Color(235, 235, 235));
        txtfPassword.setToolTipText("your password");
        txtfPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfFirstname.setBackground(new java.awt.Color(35, 35, 35));
        txtfFirstname.setForeground(new java.awt.Color(235, 235, 235));
        txtfFirstname.setToolTipText("your first name");
        txtfFirstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfLastname.setBackground(new java.awt.Color(35, 35, 35));
        txtfLastname.setForeground(new java.awt.Color(235, 235, 235));
        txtfLastname.setToolTipText("your last name");
        txtfLastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfEmail.setBackground(new java.awt.Color(35, 35, 35));
        txtfEmail.setForeground(new java.awt.Color(235, 235, 235));
        txtfEmail.setToolTipText("your email");
        txtfEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        taAddress.setBackground(new java.awt.Color(35, 35, 35));
        taAddress.setColumns(20);
        taAddress.setForeground(new java.awt.Color(235, 235, 235));
        taAddress.setRows(5);
        taAddress.setToolTipText("your address");
        taAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        taAddress.setSelectionColor(new java.awt.Color(35, 35, 35));
        jScrollPane1.setViewportView(taAddress);

        btnSignup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/SignupNormal.png"))); // NOI18N
        btnSignup.setToolTipText("register your account");
        btnSignup.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSignup.setContentAreaFilled(false);
        btnSignup.setFocusable(false);
        btnSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSignupMousePressed(evt);
            }
        });
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });

        rbMale.setBackground(new java.awt.Color(35, 35, 35));
        rbMale.setForeground(new java.awt.Color(235, 235, 235));
        rbMale.setText("Male");
        rbMale.setToolTipText("select your gender");

        rbFemale.setBackground(new java.awt.Color(35, 35, 35));
        rbFemale.setForeground(new java.awt.Color(235, 235, 235));
        rbFemale.setText("Female");
        rbFemale.setToolTipText("select your gender");

        jLabel13.setForeground(new java.awt.Color(235, 235, 235));
        jLabel13.setText("Gender");

        jLabel14.setForeground(new java.awt.Color(235, 235, 235));
        jLabel14.setText("Age");

        spAge.setToolTipText("your age");
        spAge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        cbUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Employee" }));
        cbUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        jLabel8.setForeground(new java.awt.Color(235, 235, 235));
        jLabel8.setText("for");

        jSeparator3.setBackground(new java.awt.Color(51, 153, 255));
        jSeparator3.setForeground(new java.awt.Color(35, 35, 35));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusable(false);
        btnClose.setPreferredSize(new java.awt.Dimension(57, 53));
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

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/RemoveIconNormal.png"))); // NOI18N
        btnMinimize.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMinimize.setBorderPainted(false);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setFocusable(false);
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMinimizeMousePressed(evt);
            }
        });
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(27, 27, 27)
                                .addComponent(txtfFirstname))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfImageLocation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(26, 26, 26)
                                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtfLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(70, 70, 70)
                                .addComponent(rbMale)
                                .addGap(43, 43, 43)
                                .addComponent(rbFemale))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(95, 95, 95)
                                .addComponent(spAge, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbMale)
                    .addComponent(rbFemale)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfImageLocation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        LoginUI.ifCreateWindowOpened = false;
    }//GEN-LAST:event_formWindowClosed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed

       imageDialog.pack();
       imageDialog.setLocationRelativeTo(null);
       imageDialog.setVisible(true);

    }//GEN-LAST:event_btnUploadActionPerformed

    private boolean checkImageType (String val, String []imageTypes) {
        for (int imageCount = 0; imageCount != imageTypes.length; ++imageCount) {
            if (imageTypes[imageCount].equals(val))
                return true;
        }
        
        return false;
    }
    
    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed

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

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed

        txtfImageLocation2.setText(".jpg, .png, .gif only");
        imageUserLocation = "";
        imageDialog.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (!txtfImageLocation2.getText().isEmpty()) {
        
        txtfImageLocation.setText(txtfImageLocation2.getText());
        try {
        
        imageUserLocation = getFileLocation.getAbsolutePath();
        imageDialog.dispose();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(imageDialog, "image location not found", "upload image", JOptionPane.ERROR_MESSAGE);
        }
        } else {
            JOptionPane.showMessageDialog(this, "you must upload an image", "upload image", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private boolean checkWhiteSpace (String str) {
        for (int letterCount = 0; letterCount != str.length(); ++letterCount) {
            if (!Character.isWhitespace(str.charAt(letterCount)))
                return true;
        }
        
        return false;
    }
    
    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        Connection conn = null;
        PreparedStatement sqlStatement = null;
        Statement sqlStatement2 = null;
        ResultSet sqlResult = null;
        
        if ( (rbFemale.isSelected() || rbMale.isSelected()) &&
             (!taAddress.getText().isEmpty()) &&
             (!txtfEmail.getText().isEmpty()) &&
             (!txtfFirstname.getText().isEmpty()) &&
             (!txtfImageLocation.getText().isEmpty()) &&
             (!txtfLastname.getText().isEmpty()) &&
             (!txtfPassword.getText().isEmpty()) &&
             (!txtfUsername.getText().isEmpty()) &&
               checkWhiteSpace (taAddress.getText()) &&
               checkWhiteSpace (txtfEmail.getText()) &&
               checkWhiteSpace (txtfFirstname.getText()) &&
               checkWhiteSpace (txtfImageLocation.getText()) &&
               checkWhiteSpace (txtfLastname.getText()) &&
               checkWhiteSpace (txtfPassword.getText()) &&
               checkWhiteSpace (txtfUsername.getText()) )  {
            
        try {
            
            if ( (int) spAge.getValue() <= 0) {
               JOptionPane.showMessageDialog (this, "negative values aren't allowed", "creating an account", JOptionPane.ERROR_MESSAGE);
               return;
            }
            
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            
            sqlStatement = null;
            
            String strUsertype = ((String)cbUser.getSelectedItem()).toUpperCase();
            
            Account registerAccount = new Account ();
            
            Blob imageBlob = conn.createBlob();
      
            ImageIcon iconBlob = new ImageIcon(imageUserLocation);
           
            java.util.Date currentDate = new java.util.Date ();
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMMM dd yyyy");
            java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm a");
            
             try {
                ObjectOutputStream oos = new ObjectOutputStream (imageBlob.setBinaryStream(1));
                oos.writeObject(iconBlob);
                oos.close ();
            } catch (Exception e) {
               
                JOptionPane.showMessageDialog(this, "invalid image", "create account", JOptionPane.ERROR_MESSAGE);
                return;
            }
             
            registerAccount.username = txtfUsername.getText ();
            registerAccount.password = txtfPassword.getText (); 
            registerAccount.firstname = txtfFirstname.getText ();
            registerAccount.lastname = txtfLastname.getText();
            registerAccount.email = txtfEmail.getText();
            registerAccount.address = taAddress.getText();
            registerAccount.age = (int)spAge.getValue();
            registerAccount.gender = (rbMale.isSelected()) ? "Male" : "Female";
            registerAccount.dateRegistered = dateFormat.format(currentDate);
            registerAccount.timeRegistered = timeFormat.format(currentDate);
   
            registerAccount.hired = cbUser.getSelectedItem().equals("Manager");
            
            sqlStatement2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            sqlResult = sqlStatement2.executeQuery("SELECT username, email FROM TBL" + strUsertype + " WHERE username = '" +  registerAccount.username + "' OR email = '" + registerAccount.email + "'");
            
            if (sqlResult.isBeforeFirst()) {
                
                for (;sqlResult.next();) {
                
                if ( (sqlResult.getString("username").equals(registerAccount.username) &&
                      sqlResult.getString("email").equals(registerAccount.email)) ) {
                    JOptionPane.showMessageDialog(this, "account exists", "creating an account", JOptionPane.ERROR_MESSAGE); 
                    return;
                }  
                else if (sqlResult.getString("username").equals(registerAccount.username)) {
                    JOptionPane.showMessageDialog(this, "username exists", "creating an account", JOptionPane.ERROR_MESSAGE);
                    return;
                } else  {
                    JOptionPane.showMessageDialog(this, "email exists", "creating an account", JOptionPane.ERROR_MESSAGE); 
                    return;
                }
                
                }
            } 
           
            if (registerAccount.hired) {
                sqlStatement = conn.prepareStatement("INSERT INTO TBL" + strUsertype +
                                                    "(username, password, firstname, lastname, email, address, age, gender, userimage, dateregistered, timeregistered, userimagelocation, ishired)" +
                                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                sqlStatement.setString (1, registerAccount.username);
                sqlStatement.setString (2, registerAccount.password);
                sqlStatement.setString (3, registerAccount.firstname);
                sqlStatement.setString (4, registerAccount.lastname);
                sqlStatement.setString (5, registerAccount.email);
                sqlStatement.setString (6, registerAccount.address);
                sqlStatement.setInt (7, registerAccount.age);
                sqlStatement.setString (8, registerAccount.gender);
                sqlStatement.setBlob (9, imageBlob);
                sqlStatement.setString(10, registerAccount.dateRegistered);
                sqlStatement.setString(11, registerAccount.timeRegistered);
                sqlStatement.setString(12, imageUserLocation);
                sqlStatement.setBoolean (13, false);
            
            } else {
                
                sqlStatement = conn.prepareStatement("INSERT INTO TBL" + strUsertype +
                                                    "(username, password, firstname, lastname, email, address, age, gender, hired, userimage, dateregistered, timeregistered, userimagelocation)" +
                                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                sqlStatement.setString (1, registerAccount.username);
                sqlStatement.setString (2, registerAccount.password);
                sqlStatement.setString (3, registerAccount.firstname);
                sqlStatement.setString (4, registerAccount.lastname);
                sqlStatement.setString (5, registerAccount.email);
                sqlStatement.setString (6, registerAccount.address);
                sqlStatement.setInt (7, registerAccount.age);
                sqlStatement.setString (8, registerAccount.gender);
                sqlStatement.setBoolean(9, registerAccount.hired);
                sqlStatement.setBlob (10, imageBlob);
                sqlStatement.setString (11, registerAccount.dateRegistered);
                sqlStatement.setString (12, registerAccount.timeRegistered);
                sqlStatement.setString(13, imageUserLocation);
              
            }
            
            sqlStatement.execute();
            
            JOptionPane.showMessageDialog(this, "account created", "creating an account", JOptionPane.INFORMATION_MESSAGE);
            
            dispose ();
        } catch (SQLException ex) {
            Logger.getLogger(CreateUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                if (conn != null)
                    conn.close ();
                
                if (sqlStatement != null)
                    sqlStatement.close ();
                
                if (sqlStatement2 != null)
                    sqlStatement2.close ();
                
                if (sqlResult != null)
                    sqlResult.close ();
            } catch (SQLException ex) {
                Logger.getLogger(CreateUI.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
        } else {
            JOptionPane.showMessageDialog(this, "must fill the required fields", "creating an account", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSignupActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
  
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCancel2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        MouseCoordinates = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

         Point currentCoords = evt.getLocationOnScreen();
         setLocation(currentCoords.x - MouseCoordinates.x, currentCoords.y - MouseCoordinates.y);
    }//GEN-LAST:event_formMouseDragged

    private void btnSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseEntered

        btnSignup.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SignupFocused.png"));
    }//GEN-LAST:event_btnSignupMouseEntered

    private void btnSignupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMousePressed
  
        btnSignup.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SignupPressed.png"));
    }//GEN-LAST:event_btnSignupMousePressed

    private void btnSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseExited
 
        btnSignup.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SignupNormal.png"));
    }//GEN-LAST:event_btnSignupMouseExited

    private void btnCancel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel2MouseEntered

        btnCancel2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SCancelFocused.png"));
    }//GEN-LAST:event_btnCancel2MouseEntered

    private void btnCancel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel2MousePressed

        btnCancel2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SCancelPressed.png"));
    }//GEN-LAST:event_btnCancel2MousePressed

    private void btnCancel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel2MouseExited

        btnCancel2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SCancelNormal.png"));
    }//GEN-LAST:event_btnCancel2MouseExited

    private void btnUploadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMouseEntered

        btnUpload.setIcon  (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\UploadFocused.png"));
    }//GEN-LAST:event_btnUploadMouseEntered

    private void btnUploadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMouseExited

        btnUpload.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\UploadNormal.png"));
    }//GEN-LAST:event_btnUploadMouseExited

    private void btnUploadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMousePressed

        btnUpload.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\UploadPressed.png"));
    }//GEN-LAST:event_btnUploadMousePressed

    private void imageDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageDialogMousePressed

        MouseCoordinates2 = evt.getPoint();
    }//GEN-LAST:event_imageDialogMousePressed

    private void imageDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageDialogMouseDragged

        Point currentCoords = evt.getLocationOnScreen();
                imageDialog.setLocation(currentCoords.x - MouseCoordinates2.x, currentCoords.y - MouseCoordinates2.y);
    }//GEN-LAST:event_imageDialogMouseDragged

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered

        btnSave.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SaveFocused.png"));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited

        btnSave.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SaveNormal.png"));
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed

        btnSave.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\SavePressed.png"));
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
 
        btnCancel.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ICancelFocused.png"));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnBrowseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseEntered

        btnBrowse.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnBrowseMouseEntered

    private void btnBrowseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseExited

        btnBrowse.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnBrowseMouseExited

    private void btnBrowseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMousePressed

        btnBrowse.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnBrowseMousePressed

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited

        btnCancel.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ICancelNormal.png"));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed

        btnCancel.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ICancelPressed.png"));
    }//GEN-LAST:event_btnCancelMousePressed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed

        setState (JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

        setVisible (false);
        dispose ();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered

        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited

        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed

        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered

        btnMinimize.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\RemoveIconFocused.png"));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited

        btnMinimize.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\RemoveIconNormal.png"));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMousePressed

        btnMinimize.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\RemoveIconPressed.png"));
    }//GEN-LAST:event_btnMinimizeMousePressed

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
 
        imageDialog.setVisible (false);
        imageDialog.dispose ();
    }//GEN-LAST:event_btnClose2ActionPerformed

    private void btnClose2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MouseEntered

        btnClose2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnClose2MouseEntered

    private void btnClose2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MouseExited

        btnClose2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnClose2MouseExited

    private void btnClose2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MousePressed

        btnClose2.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnClose2MousePressed

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
            java.util.logging.Logger.getLogger(CreateUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgGender;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSignup;
    private javax.swing.JButton btnUpload;
    private javax.swing.JComboBox<String> cbUser;
    private javax.swing.JDialog imageDialog;
    private javax.swing.JFileChooser imageFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblUploadMessage;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JSpinner spAge;
    private javax.swing.JTextArea taAddress;
    private javax.swing.JTextField txtfEmail;
    private javax.swing.JTextField txtfFirstname;
    private javax.swing.JTextField txtfImageLocation;
    private javax.swing.JTextField txtfImageLocation2;
    private javax.swing.JTextField txtfLastname;
    private javax.swing.JTextField txtfPassword;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables

}
