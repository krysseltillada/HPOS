package HSoft.UI;

import HSoft.User.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.ColorUIResource;

public class LoginUI extends javax.swing.JFrame {
    
    Point MouseCoordinates;
    Point MouseCoordinates2;
    Point MouseCoordinates3;
    Point MouseCoordinates4;
  
    boolean ifCapsLock = false;
    
    static public String currentUser = "";
    static public String currentTypeUser = "";
    
    static public boolean ifCreateWindowOpened = false;
    static public boolean ifForgotDialogOpened = false;
    
    String generatedCode;
    
    public LoginUI() {
        init ();
    }
    
    private void init () {
        
            if (checkSetup ()) {
               OwnerSetup ow = new OwnerSetup ();
               
               ow.setLocation(200, 200);
               ow.pack();
               ow.setVisible(true);
                     
            }  
        
            setUndecorated(true);
            getContentPane().setBackground(new Color (32, 32, 32)); 
            
            UIManager.put("ComboBox.background", new Color (35, 35, 35));
            UIManager.put("ComboBox.foreground", new Color (235, 235, 235));
            UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color (74,74,74)));
            UIManager.put("ComboBox.selectionForeground", new ColorUIResource(new Color (235, 235, 235)));
            UIManager.put("ComboBox.buttonBackground", new Color (35, 35, 35));
         
            try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception e) {}
            
            initComponents(); 
             
            cbUser.setEditor(new ComboBoxUI ());
            cbUser.setEditable(true);
            
            cbUser2.setEditor (new ComboBoxUI ());
            cbUser2.setEditable(true);  
            
            lblPosIcon.setIcon (  new ImageIcon ( (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\posIconWhite.png")).getImage().getScaledInstance(70, 62, Image.SCALE_DEFAULT)) );
            
            Font roboto = new Font ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\fonts\\roboto\\Roboto-Regular.ttf", Font.TRUETYPE_FONT, 12);
          
            lblUsername.setFont(roboto);
            lblPassword.setFont(roboto);
            lblLoginTitle.setFont(roboto);
            lblMessage.setFont(roboto);
            lblCreate.setFont(roboto);
            lblForgotIt.setFont(roboto);
            lblLoginFor.setFont(roboto);
            
            lblForgotMessage.setFont(roboto);
            lblForgotMessage2.setFont (roboto);
            lblFor.setFont (roboto);
            
            dgForgotPassword.setUndecorated(true);
            dgForgotPassword.getContentPane().setBackground(new Color (35, 35, 35));
            
            dgForgotPassword2.setUndecorated(true);
            dgForgotPassword2.getContentPane().setBackground (new Color (35, 35, 35));
            
            setLocationRelativeTo(null);
    }
    
    private String insertString (String str, char []arr) {
    
        for (char c : arr) 
            str += c;
        
        return str;
    }
    
    private boolean checkAccount (Account dbManager, Account valManager) {
         
        if (dbManager.username.equals(valManager.username)) {
            if (dbManager.password.equals(valManager.password)) {
                return true;
            }
            else {
                lblMessage.setText("password must be wrong");
                return false;
            }
        } else {
            lblMessage.setText ("username is invalid");
            return false;
        }    
    }
    
    private boolean checkSetup () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement ();
            sqlResult = sqlStatement.executeQuery ("SELECT * FROM OWNER");
            
            if  ( (!sqlResult.next()) ){
                return true;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null)
                    conn.close();
                
                if (sqlStatement != null)
                    sqlStatement.close();
                
                if (sqlResult != null)
                    sqlResult.close ();
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dgForgotPassword = new javax.swing.JDialog();
        lblForgotMessage = new javax.swing.JLabel();
        lblForgotMessage2 = new javax.swing.JLabel();
        btnCancel3 = new javax.swing.JButton();
        txtfUsername2 = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        lblFor = new javax.swing.JLabel();
        cbUser2 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        btnClose = new javax.swing.JButton();
        dgForgotPassword2 = new javax.swing.JDialog();
        lblResetMessage = new javax.swing.JLabel();
        lblConfirmMessage2 = new javax.swing.JLabel();
        txtfCode = new javax.swing.JTextField();
        lblConfirmMessage3 = new javax.swing.JLabel();
        lblConfirmMessage4 = new javax.swing.JLabel();
        txtfNewPassword = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblEmail = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnClose3 = new javax.swing.JButton();
        txtfUsername = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        lblLoginTitle = new javax.swing.JLabel();
        cbUser = new javax.swing.JComboBox<>();
        login = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblCreate = new javax.swing.JLabel();
        btnCreateAccount = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
        pwdPassword = new javax.swing.JPasswordField();
        lblForgotIt = new javax.swing.JLabel();
        btnForgotPassword = new javax.swing.JButton();
        lblLoginFor = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JButton();
        lblPosIcon = new javax.swing.JLabel();
        lblHPOS = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        dgForgotPassword.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dgForgotPasswordMouseDragged(evt);
            }
        });
        dgForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgForgotPasswordMousePressed(evt);
            }
        });

        lblForgotMessage.setForeground(new java.awt.Color(235, 235, 235));
        lblForgotMessage.setText("resetting your password");

        lblForgotMessage2.setForeground(new java.awt.Color(235, 235, 235));
        lblForgotMessage2.setText("your username");

        btnCancel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/FCancelNormal.png"))); // NOI18N
        btnCancel3.setBorderPainted(false);
        btnCancel3.setFocusable(false);
        btnCancel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancel3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancel3MousePressed(evt);
            }
        });
        btnCancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel3ActionPerformed(evt);
            }
        });

        txtfUsername2.setBackground(new java.awt.Color(35, 35, 35));
        txtfUsername2.setForeground(new java.awt.Color(235, 235, 235));
        txtfUsername2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/NextNormal.png"))); // NOI18N
        btnNext.setBorderPainted(false);
        btnNext.setFocusable(false);
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNextMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNextMousePressed(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblFor.setForeground(new java.awt.Color(235, 235, 235));
        lblFor.setText("for");

        cbUser2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Employee" }));
        cbUser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        jSeparator1.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator1.setForeground(new java.awt.Color(35, 35, 35));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnClose.setBorderPainted(false);
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

        javax.swing.GroupLayout dgForgotPasswordLayout = new javax.swing.GroupLayout(dgForgotPassword.getContentPane());
        dgForgotPassword.getContentPane().setLayout(dgForgotPasswordLayout);
        dgForgotPasswordLayout.setHorizontalGroup(
            dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgForgotPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dgForgotPasswordLayout.createSequentialGroup()
                        .addComponent(lblForgotMessage2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dgForgotPasswordLayout.createSequentialGroup()
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(btnCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtfUsername2))
                        .addContainerGap())
                    .addGroup(dgForgotPasswordLayout.createSequentialGroup()
                        .addComponent(lblForgotMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgForgotPasswordLayout.createSequentialGroup()
                                .addComponent(lblFor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbUser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING)))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        dgForgotPasswordLayout.setVerticalGroup(
            dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgForgotPasswordLayout.createSequentialGroup()
                .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dgForgotPasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblForgotMessage))
                    .addComponent(btnClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblForgotMessage2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dgForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dgForgotPassword2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dgForgotPassword2MouseDragged(evt);
            }
        });
        dgForgotPassword2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgForgotPassword2MousePressed(evt);
            }
        });

        lblResetMessage.setForeground(new java.awt.Color(235, 235, 235));
        lblResetMessage.setText("changing your password");

        lblConfirmMessage2.setForeground(new java.awt.Color(235, 235, 235));
        lblConfirmMessage2.setText("a generated resetting code is sent to your email ");

        txtfCode.setBackground(new java.awt.Color(35, 35, 35));
        txtfCode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtfCode.setForeground(new java.awt.Color(235, 235, 235));
        txtfCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        lblConfirmMessage3.setForeground(new java.awt.Color(235, 235, 235));
        lblConfirmMessage3.setText("Code");

        lblConfirmMessage4.setForeground(new java.awt.Color(235, 235, 235));
        lblConfirmMessage4.setText("new password");

        txtfNewPassword.setBackground(new java.awt.Color(35, 35, 35));
        txtfNewPassword.setForeground(new java.awt.Color(235, 235, 235));
        txtfNewPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/BackNormal.png"))); // NOI18N
        btnBack.setBorderPainted(false);
        btnBack.setFocusable(false);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackMousePressed(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/ConfirmNormal.png"))); // NOI18N
        btnConfirm.setBorderPainted(false);
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setFocusable(false);
        btnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnConfirmMousePressed(evt);
            }
        });
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lblEmail.setForeground(new java.awt.Color(235, 235, 235));

        jSeparator3.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator3.setForeground(new java.awt.Color(35, 35, 35));

        btnClose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose3.setBorderPainted(false);
        btnClose3.setContentAreaFilled(false);
        btnClose3.setFocusable(false);
        btnClose3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClose3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClose3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClose3MousePressed(evt);
            }
        });
        btnClose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dgForgotPassword2Layout = new javax.swing.GroupLayout(dgForgotPassword2.getContentPane());
        dgForgotPassword2.getContentPane().setLayout(dgForgotPassword2Layout);
        dgForgotPassword2Layout.setHorizontalGroup(
            dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                        .addGroup(dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblConfirmMessage2)
                            .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                                .addComponent(lblConfirmMessage3)
                                .addGap(18, 18, 18)
                                .addComponent(txtfCode))
                            .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                                .addComponent(lblConfirmMessage4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfNewPassword))
                            .addComponent(lblEmail))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                        .addComponent(lblResetMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jSeparator3)
        );
        dgForgotPassword2Layout.setVerticalGroup(
            dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                .addGroup(dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dgForgotPassword2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblResetMessage))
                    .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConfirmMessage2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblEmail)
                .addGap(18, 18, 18)
                .addGroup(dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfCode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConfirmMessage3))
                .addGap(18, 18, 18)
                .addGroup(dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConfirmMessage4)
                    .addComponent(txtfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dgForgotPassword2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        txtfUsername.setBackground(new java.awt.Color(35, 35, 35));
        txtfUsername.setForeground(new java.awt.Color(235, 235, 235));
        txtfUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfUsername.setToolTipText("Enter you username");
        txtfUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        txtfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfUsernameActionPerformed(evt);
            }
        });
        txtfUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfUsernameKeyReleased(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Minecraft", 0, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(235, 235, 235));
        lblUsername.setText("Username");

        lblPassword.setForeground(new java.awt.Color(235, 235, 235));
        lblPassword.setText("Password");

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusable(false);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        lblLoginTitle.setForeground(new java.awt.Color(235, 235, 235));
        lblLoginTitle.setText("Login");

        cbUser.setForeground(new java.awt.Color(235, 235, 235));
        cbUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Employee", "Owner" }));
        cbUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        login.setBackground(new java.awt.Color(52, 52, 52));
        login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/LoginNormal.png"))); // NOI18N
        login.setToolTipText("login");
        login.setContentAreaFilled(false);
        login.setFocusable(false);
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginMousePressed(evt);
            }
        });
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(52, 52, 52));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CancelNormal.png"))); // NOI18N
        btnCancel.setToolTipText("exit");
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

        lblCreate.setForeground(new java.awt.Color(235, 235, 235));
        lblCreate.setText("create a new account?");

        btnCreateAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CreateNormal.png"))); // NOI18N
        btnCreateAccount.setToolTipText("create your account to use this");
        btnCreateAccount.setContentAreaFilled(false);
        btnCreateAccount.setFocusable(false);
        btnCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreateAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreateAccountMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCreateAccountMousePressed(evt);
            }
        });
        btnCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccountActionPerformed(evt);
            }
        });

        lblMessage.setForeground(new java.awt.Color(235, 235, 235));

        pwdPassword.setBackground(new java.awt.Color(35, 35, 35));
        pwdPassword.setForeground(new java.awt.Color(235, 235, 235));
        pwdPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pwdPassword.setToolTipText("Enter your password");
        pwdPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        pwdPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdPasswordActionPerformed(evt);
            }
        });
        pwdPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pwdPasswordKeyReleased(evt);
            }
        });

        lblForgotIt.setForeground(new java.awt.Color(235, 235, 235));
        lblForgotIt.setText("forgot your password?");

        btnForgotPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/ForgotNormal.png"))); // NOI18N
        btnForgotPassword.setToolTipText("retrieve your password using email (gmail only)");
        btnForgotPassword.setContentAreaFilled(false);
        btnForgotPassword.setFocusable(false);
        btnForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnForgotPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnForgotPasswordMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnForgotPasswordMousePressed(evt);
            }
        });
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        lblLoginFor.setForeground(new java.awt.Color(235, 235, 235));
        lblLoginFor.setText("Login For");

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

        lblPosIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPosIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPosIconMouseExited(evt);
            }
        });

        lblHPOS.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblHPOS.setForeground(new java.awt.Color(235, 235, 235));
        lblHPOS.setText("HPOS");
        lblHPOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHPOSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHPOSMouseExited(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator2.setForeground(new java.awt.Color(35, 35, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblForgotIt, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(lblCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLoginFor)
                .addGap(18, 18, 18)
                .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblLoginTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize)
                .addGap(3, 3, 3)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(lblPosIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblHPOS)
                        .addGap(0, 89, Short.MAX_VALUE)))
                .addGap(101, 101, 101))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLoginTitle))
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPosIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHPOS))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblForgotIt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLoginFor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        MouseCoordinates = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

          Point currentCoords = evt.getLocationOnScreen();
                setLocation(currentCoords.x - MouseCoordinates.x, currentCoords.y - MouseCoordinates.y);
          
    }//GEN-LAST:event_formMouseDragged

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed

        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    static public void updateLog (String description, String date, String time, String username, String type) {
        Connection conn = null;
        PreparedStatement sqlCommand = null;
      
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlCommand = conn.prepareStatement("INSERT INTO TBLLOG (DESCRIPTION, DATE, TIME, USERNAME, TYPE)" +
                                              " VALUES (?, ?, ?, ?, ?)");
            
            sqlCommand.setString(1, description);
            sqlCommand.setString(2, date);
            sqlCommand.setString(3, time);
            sqlCommand.setString(4, username); 
            sqlCommand.setString(5, type);
            
            sqlCommand.execute();
          
        } catch (SQLException ex) {
            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null)
                    conn.close ();
                
                if (sqlCommand != null)
                    sqlCommand.close ();
            } catch (SQLException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (type.equals("EMPLOYEE")) {
            Connection conn2 = null;
            PreparedStatement sql2 = null;
            
             try {
                 
                conn2 = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                sql2 = conn2.prepareStatement ("UPDATE TBLEMPLOYEE SET DATELOGIN = ?, TIMELOGIN = ? WHERE USERNAME = ?");
           
                sql2.setString (1, date);
                sql2.setString (2, time);
                sql2.setString (3, username);
           
                sql2.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    if (conn2 != null)
                        conn2.close ();
                    
                    if (sql2 != null)
                        sql2.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            
        }
    }
    
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed

        Connection conn = null;
        Statement sqlCommand = null;
        ResultSet sqlResult = null;
        
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMMM dd yyyy");
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm:ss a");
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlCommand = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                        ResultSet.CONCUR_READ_ONLY);
            
            String strPassword = "";
            String table = cbUser.getSelectedItem().toString().toUpperCase();
           
            strPassword = insertString (strPassword, pwdPassword.getPassword());
            
            if (table.equals("MANAGER")) {
            sqlResult = sqlCommand.executeQuery("SELECT username, password, ishired FROM TBL" + table + " WHERE username = '" + txtfUsername.getText() + "' OR password = '" + strPassword + "'");
            }
             else if (table.equals("OWNER")) {
               
                sqlResult = sqlCommand.executeQuery ("SELECT USERNAME, PASSWORD FROM " + table);
                
                if (sqlResult.isBeforeFirst()) {
                
                sqlResult.next ();
                
                String userOwner = sqlResult.getString ("USERNAME");
                String passOwner = sqlResult.getString ("PASSWORD");
                
                String valUser = txtfUsername.getText();
               
                char []password = pwdPassword.getPassword();
                
                String valPass = "";
                valPass = insertString (valPass, password);
                
                if (userOwner.equals(txtfUsername.getText()) && passOwner.equals(valPass)) {
                
                OwnerWindow ow = new OwnerWindow ();
                
                ow.pack ();
                ow.setLocationRelativeTo (null);
                ow.setVisible (true);
                       
                dispose ();
                 
                } else {
                    
                     if (userOwner.equals(valUser)) {
                            if ( (!passOwner.equals(valPass) ) ) {
                                lblMessage.setText("password must be wrong");
                            }
                     } else {
                                lblMessage.setText ("username is invalid");
                             }    
                }
                
                } else {
                    lblMessage.setText("");
                    JOptionPane.showMessageDialog (this, "owner's account not found please restart the application and complete the setup", "login", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                    sqlResult = sqlCommand.executeQuery("SELECT username, password, hired FROM TBL" + table + " WHERE username = '" + txtfUsername.getText() + "' OR password = '" + strPassword + "'");   
            }
            
            Account dbManager = new Account ();
            Account valManager = new Account ();
            
            valManager.username = txtfUsername.getText ();
            valManager.password = strPassword;
            
            if (sqlResult.isBeforeFirst()) {
          
            for (;sqlResult.next();) {
              
                 dbManager.username = sqlResult.getString("username");
                 dbManager.password = sqlResult.getString("password"); 
                 
                 if (!table.equals("MANAGER")) 
                    dbManager.hired = sqlResult.getBoolean("HIRED");
             
                 if (!checkAccount (dbManager, valManager)) {
                     break;
                 } else {
                       if (cbUser.getSelectedItem().toString().equals("Manager")) {
                         
                           if (sqlResult.getBoolean ("ishired")) {
                           
                           currentUser = valManager.username;
                           currentTypeUser = cbUser.getSelectedItem().toString().toUpperCase();
                            
                           updateLog("user has logged in", dateFormat.format(currentDate), timeFormat.format(currentDate), currentUser, currentTypeUser);
                           
                           setVisible (false);
                           dispose ();
                           
                           ManagerWindow managerWindow = new ManagerWindow ();
                           
                           managerWindow.setLocationRelativeTo(null);
                           managerWindow.pack ();
                           managerWindow.setVisible (true);
                           } else {
                               lblMessage.setText("");
                               JOptionPane.showMessageDialog (this, "you need to activate your account from your owner", "activating account", JOptionPane.INFORMATION_MESSAGE);
                           }
                            
                           
                       } else {
                           
                           if (dbManager.hired) {
                            
                           currentUser = valManager.username;
                           currentTypeUser = cbUser.getSelectedItem().toString().toUpperCase();
                            
                           updateLog("user has logged in", dateFormat.format(currentDate), timeFormat.format(currentDate), currentUser, currentTypeUser);
                           
                           setVisible(false);
                           dispose ();
                           
                           EmployeeWindow employeeWindow = new EmployeeWindow ();
                           
                           employeeWindow.setLocationRelativeTo (null);
                           employeeWindow.pack ();
                           employeeWindow.setVisible(true);
                           } else {
                               lblMessage.setText("");
                               JOptionPane.showMessageDialog(this, "your account has not been activated by your manager or owner please activate your account and try again", "activate your account", JOptionPane.INFORMATION_MESSAGE);
                           }
                       }
                 }
            } 
            } else {
                if ( (valManager.username.isEmpty() && valManager.password.isEmpty()) )
                    lblMessage.setText("enter your username and password");
                else if (valManager.password.isEmpty())
                    lblMessage.setText("enter your password");
                else if (valManager.username.isEmpty())
                    lblMessage.setText("enter your username");
                else
                    lblMessage.setText("username and password wrong");
          
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                    if (conn != null)
                        conn.close ();
                    
                    if (sqlCommand != null)
                        sqlCommand.close ();
                    
                    if (sqlResult != null)
                        sqlResult.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }       
    }//GEN-LAST:event_loginActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed

        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccountActionPerformed
   
        if (!ifCreateWindowOpened) {
            CreateUI createWindow = new CreateUI ();
            
            createWindow.setLocationRelativeTo(null);
            createWindow.pack ();
           
            createWindow.setVisible(true);
           
            ifCreateWindowOpened = true;
        }
    }//GEN-LAST:event_btnCreateAccountActionPerformed

    private void txtfUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfUsernameKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            if (!ifCapsLock) {
                lblMessage.setText ("caps lock on");
                ifCapsLock = true;
            } else {
                lblMessage.setText ("caps lock off");
                ifCapsLock = false;
            }
        } 
    }//GEN-LAST:event_txtfUsernameKeyReleased

    private void pwdPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdPasswordKeyReleased

          if (evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            if (!ifCapsLock) {
                lblMessage.setText ("caps lock on");
                ifCapsLock = true;
            } else {
                lblMessage.setText ("caps lock off");
                ifCapsLock = false;
            }
        } 
    }//GEN-LAST:event_pwdPasswordKeyReleased

    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed

        dgForgotPassword.setLocationRelativeTo(null);
        dgForgotPassword.pack();
        dgForgotPassword.setVisible(true);
        
    }//GEN-LAST:event_btnForgotPasswordActionPerformed

    private boolean sendGmailMessage(Account From, String to, String subject, String StrMessage) {
        Properties props = System.getProperties();
        
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", From.username);
        props.put("mail.smtp.password", From.password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);

        Session session = Session.getInstance(props,null);
        MimeMessage message = new MimeMessage(session);

          try {
            
              InternetAddress from = new InternetAddress(From.email);
             
              message.setSubject(subject);
              message.setFrom(from);
              message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

              Multipart multipart = new MimeMultipart("alternative");

              BodyPart messageBodyPart = new MimeBodyPart();
        
              messageBodyPart.setText("some text to send");

              multipart.addBodyPart(messageBodyPart);

              messageBodyPart = new MimeBodyPart();
        
              String htmlMessage = StrMessage;
        
              messageBodyPart.setContent(htmlMessage, "text/html");

              multipart.addBodyPart(messageBodyPart);

              message.setContent(multipart);

              Transport transport = session.getTransport("smtp");
              
              transport.connect("smtp.gmail.com", From.username, From.password);
              
              transport.sendMessage(message, message.getAllRecipients());

         } catch (AddressException e) {
             return false;
         } catch (MessagingException e) {
             return false;
            }
          return true;
    }
    
    private String generateCode () {
        Random randomNumber = new Random();
        String generatedCode = "";
        
        randomNumber.setSeed(System.currentTimeMillis());
        
        int max = 90, min = 48;
        int count = 0;
        
        int maxUpperCaseLetter = 90, minUpperCaseLetter = 65;
        int maxDigit = 57, minDigit = 48;
        
        for (;true;) {
           if (count < 5) {
            
           int generatedNumber = randomNumber.nextInt((max - min) + 1) + min;
           
           if ( (generatedNumber <= maxUpperCaseLetter && generatedNumber >= minUpperCaseLetter) ||
                 (generatedNumber <= maxDigit && generatedNumber >= minDigit)) {
               generatedCode += (char)(generatedNumber);
               ++count;
           }
           
           } else {
               break;
           }
        }
        
        return generatedCode;
    }
    
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        if (!txtfUsername2.getText().isEmpty()) {
        
        try {
            String table = ((String)cbUser2.getSelectedItem()).toUpperCase();
            
            Account getAccount = new Account ();
            
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            
            sqlStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlResult = sqlStatement.executeQuery("SELECT username, email, dateregistered, timeregistered FROM TBL" + table + " WHERE username = '" + txtfUsername2.getText() + "'");
            
            if (sqlResult.isBeforeFirst()) {
                for (; sqlResult.next(); ) {
                    getAccount.username = sqlResult.getString("username");
                    getAccount.email = sqlResult.getString("email");
                    getAccount.dateRegistered = sqlResult.getString("dateregistered");
                    getAccount.timeRegistered = sqlResult.getString("timeregistered");
                }
                   
                generatedCode = generateCode ();
                
                String message = "<html>  " +
                                 "<body> " +
                                 "<p> hi " + getAccount.username + " your generated code is been created </p>" +
                                 "<p> please paste this following code " + generatedCode + " thank you </p>" +
                                 "<p> date this account registered " + getAccount.dateRegistered + "</p>" +
                                 "<p> time this account registered " + getAccount.timeRegistered + "</p>" +
                                 "</body>" +
                                 "</html>";
                
                Account emailSender = new Account ();
                
                emailSender.username = "krysseltillada@gmail.com";
                emailSender.email = "krysseltillada@gmail.com"; 
                emailSender.password = "kryssel2821";
                
                if (this.sendGmailMessage(emailSender, getAccount.email, "Password Retrieval", message)) {
                
                dgForgotPassword.dispose();
        
                lblEmail.setText("at " + getAccount.email);
                
                dgForgotPassword2.setLocationRelativeTo(null);
                dgForgotPassword2.pack();
                dgForgotPassword2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "internet connection is required", "no connection", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "username is invalid", "resetting password", JOptionPane.ERROR_MESSAGE);
            }   
                
        } catch (SQLException ex) {
            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null)
                    conn.close ();
                
                if (sqlStatement != null)
                    sqlStatement.close ();
                
                if (sqlResult != null)
                    sqlResult.close ();
                } catch (SQLException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        } else {
            JOptionPane.showMessageDialog (this, "enter your username", "resseting password", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        dgForgotPassword2.dispose();
        
        dgForgotPassword.setLocationRelativeTo(null);
        dgForgotPassword.pack();
        dgForgotPassword.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel3ActionPerformed

        dgForgotPassword.dispose();
    }//GEN-LAST:event_btnCancel3ActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed

        String strUserType = cbUser2.getSelectedItem().toString().toUpperCase();
        
        if (generatedCode.equals(txtfCode.getText()) ) {
            if (!txtfNewPassword.getText().isEmpty()) {
                    
                    Connection conn = null;
                    PreparedStatement sqlStatement = null;
                    Statement sqlStatement2 = null;
                    ResultSet sqlResult = null;
                try {
                    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
                    
                    sqlStatement2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    
                    sqlResult = sqlStatement2.executeQuery("SELECT password FROM TBL" + strUserType + " WHERE username = '" + txtfUsername2.getText() + "'");
                    
                    String getpassword = "";
                    
                    for (;sqlResult.next();)
                        getpassword = sqlResult.getString("password");
                    
                    if ( !(getpassword.equals(txtfNewPassword.getText())) ) {
                    
                    sqlStatement = conn.prepareStatement("UPDATE TBL" + strUserType + " SET password = ? WHERE username = ?");
                    
                    sqlStatement.setString(1, txtfNewPassword.getText());
                    sqlStatement.setString(2, txtfUsername2.getText());
                    
                    sqlStatement.execute();
                    
                    JOptionPane.showMessageDialog(this, "password changed successfully", "resetting password", JOptionPane.INFORMATION_MESSAGE);
                    
                    dgForgotPassword2.dispose();
                    
                    } else {
                        JOptionPane.showMessageDialog(this, "that is already the password", "resetting password", JOptionPane.ERROR_MESSAGE);
                    }
                 
                } catch (SQLException ex) {
                    Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                        try {
                            if (conn != null)
                                conn.close ();
                            
                            if (sqlStatement != null)
                                sqlStatement.close();
                            
                            if (sqlResult != null)
                                sqlResult.close ();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "please enter your new password", "resetting password", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "you must paste the generated code", "resetting password", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseEntered

        login.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\LoginFocused.png"));
    }//GEN-LAST:event_loginMouseEntered

    private void loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseExited

        login.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\LoginNormal.png"));
    }//GEN-LAST:event_loginMouseExited

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered

        btnCancel.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\CancelFocused.png"));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited

        btnCancel.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\CancelNormal.png"));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCreateAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateAccountMouseEntered

        this.btnCreateAccount.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\CreateFocused.png"));
    }//GEN-LAST:event_btnCreateAccountMouseEntered

    private void btnCreateAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateAccountMouseExited

        this.btnCreateAccount.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\CreateNormal.png"));
    }//GEN-LAST:event_btnCreateAccountMouseExited

    private void btnForgotPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgotPasswordMouseEntered

         this.btnForgotPassword.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\forgotFocused.png"));
    }//GEN-LAST:event_btnForgotPasswordMouseEntered

    private void btnForgotPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgotPasswordMouseExited

        this.btnForgotPassword.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ForgotNormal.png"));
    }//GEN-LAST:event_btnForgotPasswordMouseExited

    private void loginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMousePressed

        this.login.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\LoginPressed.png"));
    }//GEN-LAST:event_loginMousePressed

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed

        this.btnCancel.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\CancelPressed.png"));
    }//GEN-LAST:event_btnCancelMousePressed

    private void btnCreateAccountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateAccountMousePressed

        this.btnCreateAccount.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\CreatePressed.png"));
    }//GEN-LAST:event_btnCreateAccountMousePressed

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered

        this.exitButton.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited

        this.exitButton.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_exitButtonMouseExited

    private void exitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMousePressed

        this.exitButton.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_exitButtonMousePressed

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered

        this.btnMinimize.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\RemoveIconFocused.png"));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited

        this.btnMinimize.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\RemoveIconNormal.png"));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMousePressed
  
        this.btnMinimize.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\RemoveIconPressed.png"));
    }//GEN-LAST:event_btnMinimizeMousePressed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed

        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void dgForgotPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgForgotPasswordMousePressed

        MouseCoordinates2 = evt.getPoint();
    }//GEN-LAST:event_dgForgotPasswordMousePressed

    private void dgForgotPasswordMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgForgotPasswordMouseDragged

         Point currentCoords = evt.getLocationOnScreen();
                dgForgotPassword.setLocation(currentCoords.x - MouseCoordinates2.x, currentCoords.y - MouseCoordinates2.y);
    }//GEN-LAST:event_dgForgotPasswordMouseDragged

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

        dgForgotPassword.setVisible (false);
        dgForgotPassword.dispose();
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

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered

        this.btnNext.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\NextFocused.png"));
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited

        this.btnNext.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\NextNormal.png"));
    }//GEN-LAST:event_btnNextMouseExited

    private void btnNextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMousePressed
 
        this.btnNext.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\NextPressed.png"));
    }//GEN-LAST:event_btnNextMousePressed

    private void btnCancel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel3MouseEntered

        btnCancel3.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\FCancelFocused.png"));
    }//GEN-LAST:event_btnCancel3MouseEntered

    private void btnCancel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel3MouseExited

        btnCancel3.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\FCancelNormal.png"));
    }//GEN-LAST:event_btnCancel3MouseExited

    private void btnCancel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel3MousePressed

         btnCancel3.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\FCancelPressed.png"));
    }//GEN-LAST:event_btnCancel3MousePressed

    private void dgForgotPassword2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgForgotPassword2MousePressed

        MouseCoordinates3 = evt.getPoint();
    }//GEN-LAST:event_dgForgotPassword2MousePressed

    private void dgForgotPassword2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgForgotPassword2MouseDragged

           Point currentCoords = evt.getLocationOnScreen();
                dgForgotPassword2.setLocation(currentCoords.x - MouseCoordinates3.x, currentCoords.y - MouseCoordinates3.y);
    }//GEN-LAST:event_dgForgotPassword2MouseDragged

    private void btnConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseEntered

        btnConfirm.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ConfirmFocused.png"));
    }//GEN-LAST:event_btnConfirmMouseEntered

    private void btnConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseExited
 
        btnConfirm.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ConfirmNormal.png"));
    }//GEN-LAST:event_btnConfirmMouseExited

    private void btnConfirmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMousePressed

        btnConfirm.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ConfirmPressed.png"));
    }//GEN-LAST:event_btnConfirmMousePressed

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered

        btnBack.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\BackFocused.png"));
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited

        btnBack.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\BackNormal.png"));
    }//GEN-LAST:event_btnBackMouseExited

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed

        btnBack.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\BackPressed.png"));
    }//GEN-LAST:event_btnBackMousePressed

    private void btnClose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose3ActionPerformed

        dgForgotPassword2.setVisible(false);
        dgForgotPassword2.dispose ();
    }//GEN-LAST:event_btnClose3ActionPerformed

    private void btnForgotPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgotPasswordMousePressed

        this.btnForgotPassword.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\buttons\\ForgotPressed.png"));                
    }//GEN-LAST:event_btnForgotPasswordMousePressed

    private void btnClose3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MouseEntered

        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnClose3MouseEntered

    private void btnClose3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MouseExited

        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnClose3MouseExited

    private void btnClose3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MousePressed

        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnClose3MousePressed

    private void txtfUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfUsernameActionPerformed

        Connection conn = null;
        Statement sqlCommand = null;
        ResultSet sqlResult = null;
        
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMMM dd yyyy");
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm:ss a");
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlCommand = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                        ResultSet.CONCUR_READ_ONLY);
            
            String strPassword = "";
            String table = cbUser.getSelectedItem().toString().toUpperCase();
         
            strPassword = insertString (strPassword, pwdPassword.getPassword());
            
            if (table.equals("MANAGER")) {
                
            sqlResult = sqlCommand.executeQuery("SELECT username, password, ishired FROM TBL" + table + " WHERE username = '" + txtfUsername.getText() + "' OR password = '" + strPassword + "'");
            
            }
             else if (table.equals("OWNER")) {
               
                sqlResult = sqlCommand.executeQuery ("SELECT USERNAME, PASSWORD FROM " + table);
                
                if (sqlResult.isBeforeFirst()) {
                
                sqlResult.next ();
                
                String userOwner = sqlResult.getString ("USERNAME");
                String passOwner = sqlResult.getString ("PASSWORD");
                
                String valUser = txtfUsername.getText();
               
                char []password = pwdPassword.getPassword();
                
                String valPass = "";
                valPass = insertString (valPass, password);
                
                if (userOwner.equals(txtfUsername.getText()) && passOwner.equals(valPass)) {
                
                OwnerWindow ow = new OwnerWindow ();
                
                ow.pack ();
                ow.setLocationRelativeTo (null);
                ow.setVisible (true);
                      
                dispose ();
                 
                } else {
                    
                     if (userOwner.equals(valUser)) {
                            if ( (!passOwner.equals(valPass) ) ) {
                                lblMessage.setText("password must be wrong");
                            }
                     } else {
                                lblMessage.setText ("username is invalid");
                             }    
                }
                
                } else {
                    lblMessage.setText("");
                    JOptionPane.showMessageDialog (this, "owner's account not found please restart the application and complete the setup", "login", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                    sqlResult = sqlCommand.executeQuery("SELECT username, password, hired FROM TBL" + table + " WHERE username = '" + txtfUsername.getText() + "' OR password = '" + strPassword + "'");   
            }
            Account dbManager = new Account ();
            Account valManager = new Account ();
            
            valManager.username = txtfUsername.getText ();
            valManager.password = strPassword;
            
            
            if (sqlResult.isBeforeFirst()) {
          
            for (;sqlResult.next();) {
                
                System.out.println ("running");
                
                 dbManager.username = sqlResult.getString("username");
                 dbManager.password = sqlResult.getString("password"); 
                 
                 if (!table.equals("MANAGER")) 
                    dbManager.hired = sqlResult.getBoolean("HIRED");
             
                 if (!checkAccount (dbManager, valManager)) {
                     break;
                 } else {
                       if (cbUser.getSelectedItem().toString().equals("Manager")) {
                         
                           if (sqlResult.getBoolean ("ishired")) {
                           
                           currentUser = valManager.username;
                           currentTypeUser = cbUser.getSelectedItem().toString().toUpperCase();
                            
                           updateLog("user has logged in", dateFormat.format(currentDate), timeFormat.format(currentDate), currentUser, currentTypeUser);
                           
                           setVisible (false);
                           dispose ();
                           
                           ManagerWindow managerWindow = new ManagerWindow ();
                           
                           managerWindow.setLocationRelativeTo(null);
                           managerWindow.pack ();
                           managerWindow.setVisible (true);
                           } else {
                               lblMessage.setText("");
                               JOptionPane.showMessageDialog (this, "you need to activate your account from your owner", "activating account", JOptionPane.INFORMATION_MESSAGE);
                           }
                            
                           
                       } else {
                           
                           if (dbManager.hired) {
                          
                           currentUser = valManager.username;
                           currentTypeUser = cbUser.getSelectedItem().toString().toUpperCase();
                            
                           updateLog("user has logged in", dateFormat.format(currentDate), timeFormat.format(currentDate), currentUser, currentTypeUser);
                           
                           setVisible(false);
                           dispose ();
                           
                           EmployeeWindow employeeWindow = new EmployeeWindow ();
                           
                           employeeWindow.setLocationRelativeTo (null);
                           employeeWindow.pack ();
                           employeeWindow.setVisible(true);
                           } else {
                               lblMessage.setText("");
                               JOptionPane.showMessageDialog(this, "your account has not been activated by your manager or owner please activate your account and try again", "activate your account", JOptionPane.INFORMATION_MESSAGE);
                           }
                       }
                 }
            } 
            } else {
                if ( (valManager.username.isEmpty() && valManager.password.isEmpty()) )
                    lblMessage.setText("enter your username and password");
                else if (valManager.password.isEmpty())
                    lblMessage.setText("enter your password");
                else if (valManager.username.isEmpty())
                    lblMessage.setText("enter your username");
                else
                    lblMessage.setText("username and password wrong");
          
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                    if (conn != null)
                        conn.close ();
                    
                    if (sqlCommand != null)
                        sqlCommand.close ();
                    
                    if (sqlResult != null)
                        sqlResult.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }          
    }//GEN-LAST:event_txtfUsernameActionPerformed

    private void pwdPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdPasswordActionPerformed
       
        Connection conn = null;
        Statement sqlCommand = null;
        ResultSet sqlResult = null;
        
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMMM dd yyyy");
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm:ss a");
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlCommand = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                        ResultSet.CONCUR_READ_ONLY);
            
            String strPassword = "";
            String table = cbUser.getSelectedItem().toString().toUpperCase();
            
            System.out.println ("table: " + table);
            
            strPassword = insertString (strPassword, pwdPassword.getPassword());
            
            if (table.equals("MANAGER")) {
                
            sqlResult = sqlCommand.executeQuery("SELECT username, password, ishired FROM TBL" + table + " WHERE username = '" + txtfUsername.getText() + "' OR password = '" + strPassword + "'");
            
            }
             else if (table.equals("OWNER")) {
               
                sqlResult = sqlCommand.executeQuery ("SELECT USERNAME, PASSWORD FROM " + table);
                
                if (sqlResult.isBeforeFirst()) {
                
                sqlResult.next ();
                
                String userOwner = sqlResult.getString ("USERNAME");
                String passOwner = sqlResult.getString ("PASSWORD");
                
                String valUser = txtfUsername.getText();
               
                char []password = pwdPassword.getPassword();
                
                String valPass = "";
                valPass = insertString (valPass, password);
                
                if (userOwner.equals(txtfUsername.getText()) && passOwner.equals(valPass)) {
                
                OwnerWindow ow = new OwnerWindow ();
                
                ow.pack ();
                ow.setLocationRelativeTo (null);
                ow.setVisible (true);
                        
                dispose ();
                 
                } else {
                    
                     if (userOwner.equals(valUser)) {
                            if ( (!passOwner.equals(valPass) ) ) {
                                lblMessage.setText("password must be wrong");
                            }
                     } else {
                                lblMessage.setText ("username is invalid");
                             }    
                }
                } else {
                    lblMessage.setText("");
                    JOptionPane.showMessageDialog (this, "owner's account not found please restart the application and complete the setup", "login", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
             sqlResult = sqlCommand.executeQuery("SELECT username, password, hired FROM TBL" + table + " WHERE username = '" + txtfUsername.getText() + "' OR password = '" + strPassword + "'");   
            }
            Account dbManager = new Account ();
            Account valManager = new Account ();
            
            valManager.username = txtfUsername.getText ();
            valManager.password = strPassword;
            
            if (sqlResult.isBeforeFirst()) {
          
            for (;sqlResult.next();) {
                
                 dbManager.username = sqlResult.getString("username");
                 dbManager.password = sqlResult.getString("password"); 
                 
                 if (!table.equals("MANAGER")) 
                    dbManager.hired = sqlResult.getBoolean("HIRED");
             
                 if (!checkAccount (dbManager, valManager)) {
                     break;
                 } else {
                       if (cbUser.getSelectedItem().toString().equals("Manager")) {
                         
                           if (sqlResult.getBoolean ("ishired")) {
                           
                           currentUser = valManager.username;
                           currentTypeUser = cbUser.getSelectedItem().toString().toUpperCase();
                            
                           updateLog("user has logged in", dateFormat.format(currentDate), timeFormat.format(currentDate), currentUser, currentTypeUser);
                           
                           setVisible (false);
                           dispose ();
                           
                           ManagerWindow managerWindow = new ManagerWindow ();
                           
                           managerWindow.setLocationRelativeTo(null);
                           managerWindow.pack ();
                           managerWindow.setVisible (true);
                           } else {
                               lblMessage.setText("");
                               JOptionPane.showMessageDialog (this, "you need to activate your account from your owner", "activating account", JOptionPane.INFORMATION_MESSAGE);
                           }
                            
                       } else {
                           
                           if (dbManager.hired) {
                        
                           currentUser = valManager.username;
                           currentTypeUser = cbUser.getSelectedItem().toString().toUpperCase();
                            
                           updateLog("user has logged in", dateFormat.format(currentDate), timeFormat.format(currentDate), currentUser, currentTypeUser);
                           
                           setVisible(false);
                           dispose ();
                           
                           EmployeeWindow employeeWindow = new EmployeeWindow ();
                           
                           employeeWindow.setLocationRelativeTo (null);
                           employeeWindow.pack ();
                           employeeWindow.setVisible(true);
                           } else {
                               lblMessage.setText("");
                               JOptionPane.showMessageDialog(this, "your account has not been activated by your manager or owner please activate your account and try again", "activate your account", JOptionPane.INFORMATION_MESSAGE);
                           }
                       }
                 }
            } 
            } else {
                if ( (valManager.username.isEmpty() && valManager.password.isEmpty()) )
                    lblMessage.setText("enter your username and password");
                else if (valManager.password.isEmpty())
                    lblMessage.setText("enter your password");
                else if (valManager.username.isEmpty())
                    lblMessage.setText("enter your username");
                else
                    lblMessage.setText("username and password wrong");
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                    if (conn != null)
                        conn.close ();
                    
                    if (sqlCommand != null)
                        sqlCommand.close ();
                    
                    if (sqlResult != null)
                        sqlResult.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }          
    }//GEN-LAST:event_pwdPasswordActionPerformed

    private void lblHPOSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHPOSMouseEntered

        lblHPOS.setForeground (new Color (171,197,54));
    }//GEN-LAST:event_lblHPOSMouseEntered

    private void lblHPOSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHPOSMouseExited

        lblHPOS.setForeground (new Color (235, 235, 235));
    }//GEN-LAST:event_lblHPOSMouseExited

    private void lblPosIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPosIconMouseEntered

        lblPosIcon.setIcon ( new ImageIcon (  new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\posIconGreen.png").getImage().getScaledInstance(70, 62, Image.SCALE_DEFAULT)) );
    }//GEN-LAST:event_lblPosIconMouseEntered

    private void lblPosIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPosIconMouseExited

         lblPosIcon.setIcon ( new ImageIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\posIconWhite.png").getImage().getScaledInstance(70, 62, Image.SCALE_DEFAULT)) );
    }//GEN-LAST:event_lblPosIconMouseExited

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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel3;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose3;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnCreateAccount;
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnNext;
    private javax.swing.JComboBox<String> cbUser;
    private javax.swing.JComboBox<String> cbUser2;
    private javax.swing.JDialog dgForgotPassword;
    private javax.swing.JDialog dgForgotPassword2;
    private javax.swing.JButton exitButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblConfirmMessage2;
    private javax.swing.JLabel lblConfirmMessage3;
    private javax.swing.JLabel lblConfirmMessage4;
    private javax.swing.JLabel lblCreate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFor;
    private javax.swing.JLabel lblForgotIt;
    private javax.swing.JLabel lblForgotMessage;
    private javax.swing.JLabel lblForgotMessage2;
    private javax.swing.JLabel lblHPOS;
    private javax.swing.JLabel lblLoginFor;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPosIcon;
    private javax.swing.JLabel lblResetMessage;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JTextField txtfCode;
    private javax.swing.JTextField txtfNewPassword;
    private javax.swing.JTextField txtfUsername;
    private javax.swing.JTextField txtfUsername2;
    // End of variables declaration//GEN-END:variables
}
