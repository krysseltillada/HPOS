package HSoft.UI;

import HSoft.User.*;
import java.awt.Color;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.table.DefaultTableModel;

public class HomePanel extends javax.swing.JPanel {
    Point MouseCoordinates;
    Point MouseCoordinates2;
    Point MouseCoordinates3;
    
    Inventory getInventory = new Inventory ();
    
    Set <Integer> productNumberSet = new HashSet <Integer> () ; 
    HashMap <Integer, Inventory> itemQuantity = new HashMap <Integer, Inventory> ();
        
    Vector vInventory = new Vector ();
    
    int Discount = 0;
    int Tax = 0;
    
    int getIndex = 0;
  
    String columnBy = "";
    String sortBy = "";
    int numRows = 0;
    
    ImageIcon Selectimage = null;
    
    DefaultListModel listItem = new DefaultListModel();
          
    boolean ifPaid = false;
    boolean ifNext = false;
   
    private void spinnerSetEditable (JSpinner getSpinner, boolean ifEditable) {
         DefaultEditor gEditor = (DefaultEditor)getSpinner.getEditor();
        
        gEditor.getTextField().setEditable(ifEditable);
        
        getSpinner.setEditor(gEditor);
    }
    
    private String removeWhiteSpace (String word) {
        String removedSpaces = "";
        
        for (int letterCount = 0; letterCount != word.length(); ++letterCount) {
            if (!Character.isWhitespace(word.charAt(letterCount))) 
                removedSpaces += word.charAt(letterCount);
        }
        
        return removedSpaces;
    }
    
    public HomePanel() {
        init ();
    }
    
    private void init () {
        initComponents();
         
        cbColumnBy.setEditor(new ComboBoxUI ());
        cbColumnBy.setEditable (true);
        
        cbSortBy.setEditor (new ComboBoxUI ());
        cbSortBy.setEditable (true);
        
        cbNumRows.setEditor (new ComboBoxUI ());
        cbNumRows.setEditable (true);
            
        spinnerSetEditable (spExchangeTo, false);
        spinnerSetEditable (spExchangeFrom, false);
       
        columnBy = cbColumnBy.getSelectedItem().toString();
        sortBy = cbSortBy.getSelectedItem().toString();
        numRows = (cbNumRows.getSelectedItem().toString().equals("all")) ? 0 : Integer.parseInt(cbNumRows.getSelectedItem().toString());
        
        if (columnBy.equals("Product No"))
            columnBy = "PRODUCTNUMBER";
        
        dgBrowseProduct.getContentPane().setBackground(new Color (35, 35, 35));
        dgBrowseProduct.setUndecorated (true);
        dgBrowseProduct.setResizable (false);
        
        dgReceipt.setUndecorated (true); 
        dgReceipt.getContentPane().setBackground (new Color (35, 35, 35));   
    
        payoutDialog.setUndecorated (true);
        payoutDialog.getContentPane().setBackground (new Color (35, 35, 35));
        payoutDialog.setResizable (false);
        
        btnBrowse.setOpaque(true);
        
        btnSelect.setOpaque (true);
        btnSelectCancel.setOpaque (true);
        
        setBackground (new Color(51, 51, 51) );
    }
    
    private boolean checkDigits (String digits) {
        int count = 0;
        
        if (digits.isEmpty())
            return false;
        
        for (int letterCount = 0; letterCount != digits.length(); ++letterCount) {
            if (!Character.isDigit (digits.charAt(letterCount))) {
                if (digits.charAt(letterCount) == '.') {
                     count++;
                } else {
                    return false;
                }
                    
            }
            
        }
        
        return count <= 1;
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        payoutDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtfUserAmountTextBox = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        spExchangeFrom = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        spExchangeTo = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        btnConvert = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        btnClose3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        dgBrowseProduct = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductSelect = new javax.swing.JTable();
        btnSelect = new javax.swing.JButton();
        btnSelectCancel = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        lblProductSelectImage = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbColumnBy = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbSortBy = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbNumRows = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtfProductSearch = new javax.swing.JTextField();
        btnSelectSearch = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        dgReceipt = new javax.swing.JDialog();
        btnClose2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        taReceipt = new javax.swing.JTextArea();
        btnOk = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblProductImage = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        lblProductQuantity = new javax.swing.JLabel();
        lblProductNo = new javax.swing.JLabel();
        lblProductPrice = new javax.swing.JLabel();
        lblProductMeasureUnitAndNumber = new javax.swing.JLabel();
        lblQuantityValue = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMaxQuantity = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtfUserTextBox = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnFour = new javax.swing.JButton();
        btnFive = new javax.swing.JButton();
        btnSeven = new javax.swing.JButton();
        btnEight = new javax.swing.JButton();
        btnNine = new javax.swing.JButton();
        btnSix = new javax.swing.JButton();
        btnOne = new javax.swing.JButton();
        btnTwo = new javax.swing.JButton();
        btnThree = new javax.swing.JButton();
        btnZero = new javax.swing.JButton();
        btnBackSpace = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnClearAll = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnPay = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblAmount = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblTax = new javax.swing.JLabel();
        lblMoney = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        btnDiscount = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnQuantity = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btnAddItem = new javax.swing.JButton();
        btnRemoveItem = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btnTax = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();

        payoutDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                payoutDialogMouseDragged(evt);
            }
        });
        payoutDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                payoutDialogMousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(35, 35, 35));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("Payout");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(35, 35, 35));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 235, 235));
        jLabel6.setText("Amount payed: ");

        txtfUserAmountTextBox.setBackground(new java.awt.Color(35, 35, 35));
        txtfUserAmountTextBox.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtfUserAmountTextBox.setForeground(new java.awt.Color(235, 235, 235));
        txtfUserAmountTextBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfUserAmountTextBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        txtfUserAmountTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfUserAmountTextBoxActionPerformed(evt);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(51, 51, 51));
        btnConfirm.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(235, 235, 235));
        btnConfirm.setText("Confirm");
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setFocusable(false);
        btnConfirm.setOpaque(true);
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

        btnCancel.setBackground(new java.awt.Color(51, 51, 51));
        btnCancel.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(235, 235, 235));
        btnCancel.setText("Cancel");
        btnCancel.setContentAreaFilled(false);
        btnCancel.setFocusable(false);
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

        spExchangeFrom.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        spExchangeFrom.setModel(new javax.swing.SpinnerListModel(new String[] {"US Dollar", "Euro Pound", "Singapore Dollar", "Canadian Dollar", "British Pound"}));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(235, 235, 235));
        jLabel7.setText("From");

        spExchangeTo.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        spExchangeTo.setModel(new javax.swing.SpinnerListModel(new String[] {"Euro Pound", "US Dollar", "Singapore Dollar", "Canadian Dollar", "British Pound"}));
        spExchangeTo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(235, 235, 235));
        jLabel8.setText("To");

        btnConvert.setBackground(new java.awt.Color(51, 51, 51));
        btnConvert.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnConvert.setForeground(new java.awt.Color(235, 235, 235));
        btnConvert.setText("Convert");
        btnConvert.setContentAreaFilled(false);
        btnConvert.setFocusable(false);
        btnConvert.setOpaque(true);
        btnConvert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConvertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConvertMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnConvertMousePressed(evt);
            }
        });
        btnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfUserAmountTextBox)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spExchangeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spExchangeTo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConvert)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfUserAmountTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spExchangeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConvert)
                    .addComponent(jLabel8)
                    .addComponent(spExchangeTo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        jSeparator4.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator4.setForeground(new java.awt.Color(51, 51, 51));

        btnClose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
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

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(235, 235, 235));
        jLabel18.setText("Payout");

        javax.swing.GroupLayout payoutDialogLayout = new javax.swing.GroupLayout(payoutDialog.getContentPane());
        payoutDialog.getContentPane().setLayout(payoutDialogLayout);
        payoutDialogLayout.setHorizontalGroup(
            payoutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payoutDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payoutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(payoutDialogLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(payoutDialogLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jSeparator4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payoutDialogLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(143, 143, 143)
                .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        payoutDialogLayout.setVerticalGroup(
            payoutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payoutDialogLayout.createSequentialGroup()
                .addGroup(payoutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dgBrowseProduct.setBackground(new java.awt.Color(0, 0, 0));
        dgBrowseProduct.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dgBrowseProductMouseDragged(evt);
            }
        });
        dgBrowseProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgBrowseProductMousePressed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(35, 35, 35));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        tblProductSelect.setBackground(new java.awt.Color(51, 51, 51));
        tblProductSelect.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tblProductSelect.setForeground(new java.awt.Color(235, 235, 235));
        tblProductSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductSelect.setGridColor(new java.awt.Color(80, 137, 182));
        tblProductSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblProductSelectMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductSelect);

        btnSelect.setBackground(new java.awt.Color(51, 51, 51));
        btnSelect.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSelect.setForeground(new java.awt.Color(235, 235, 235));
        btnSelect.setText("Select");
        btnSelect.setContentAreaFilled(false);
        btnSelect.setFocusable(false);
        btnSelect.setOpaque(true);
        btnSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelectMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSelectMousePressed(evt);
            }
        });
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

        btnSelectCancel.setBackground(new java.awt.Color(51, 51, 51));
        btnSelectCancel.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSelectCancel.setForeground(new java.awt.Color(235, 235, 235));
        btnSelectCancel.setText("Cancel");
        btnSelectCancel.setContentAreaFilled(false);
        btnSelectCancel.setFocusable(false);
        btnSelectCancel.setOpaque(true);
        btnSelectCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelectCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelectCancelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSelectCancelMousePressed(evt);
            }
        });
        btnSelectCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectCancelActionPerformed(evt);
            }
        });

        lblProductSelectImage.setBackground(new java.awt.Color(35, 35, 35));
        lblProductSelectImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        lblProductSelectImage.setOpaque(true);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProductSelectImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProductSelectImage, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Group by");

        cbColumnBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product No", "Product Name", "Product Quantity", "Product Price" }));
        cbColumnBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbColumnBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbColumnByItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(235, 235, 235));
        jLabel11.setText("Column by");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(235, 235, 235));
        jLabel12.setText("Sort by");

        cbSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbSortBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSortByItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(235, 235, 235));
        jLabel13.setText("no of rows");

        cbNumRows.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all", "1", "2", "3", "4", "5", "6", "7", "8", "9", " " }));
        cbNumRows.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbNumRows.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNumRowsItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelectCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbSortBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbColumnBy, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNumRows, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbColumnBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbNumRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSelect)
                            .addComponent(btnSelectCancel)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(35, 35, 35));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(235, 235, 235));
        jLabel9.setText("Select Product");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(35, 35, 35));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        jLabel14.setBackground(new java.awt.Color(35, 35, 35));
        jLabel14.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(235, 235, 235));
        jLabel14.setText("Search");

        txtfProductSearch.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductSearch.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnSelectSearch.setBackground(new java.awt.Color(51, 51, 51));
        btnSelectSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSelectSearch.setForeground(new java.awt.Color(235, 235, 235));
        btnSelectSearch.setText("Search");
        btnSelectSearch.setContentAreaFilled(false);
        btnSelectSearch.setFocusable(false);
        btnSelectSearch.setOpaque(true);
        btnSelectSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelectSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelectSearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSelectSearchMousePressed(evt);
            }
        });
        btnSelectSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSelectSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(txtfProductSearch, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

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

        jSeparator2.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(235, 235, 235));
        jLabel17.setText("Browse Product");

        javax.swing.GroupLayout dgBrowseProductLayout = new javax.swing.GroupLayout(dgBrowseProduct.getContentPane());
        dgBrowseProduct.getContentPane().setLayout(dgBrowseProductLayout);
        dgBrowseProductLayout.setHorizontalGroup(
            dgBrowseProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgBrowseProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dgBrowseProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dgBrowseProductLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgBrowseProductLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(358, 358, 358)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        dgBrowseProductLayout.setVerticalGroup(
            dgBrowseProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgBrowseProductLayout.createSequentialGroup()
                .addGroup(dgBrowseProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dgBrowseProductLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dgBrowseProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dgReceipt.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dgReceiptMouseDragged(evt);
            }
        });
        dgReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgReceiptMousePressed(evt);
            }
        });

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

        jSeparator3.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator3.setForeground(new java.awt.Color(35, 35, 35));

        taReceipt.setBackground(new java.awt.Color(35, 35, 35));
        taReceipt.setColumns(20);
        taReceipt.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        taReceipt.setForeground(new java.awt.Color(235, 235, 235));
        taReceipt.setRows(5);
        taReceipt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        jScrollPane3.setViewportView(taReceipt);

        btnOk.setBackground(new java.awt.Color(51, 51, 51));
        btnOk.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnOk.setForeground(new java.awt.Color(235, 235, 235));
        btnOk.setText("Ok");
        btnOk.setContentAreaFilled(false);
        btnOk.setOpaque(true);
        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOkMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOkMousePressed(evt);
            }
        });
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(235, 235, 235));
        jLabel16.setText("Receipt");

        javax.swing.GroupLayout dgReceiptLayout = new javax.swing.GroupLayout(dgReceipt.getContentPane());
        dgReceipt.getContentPane().setLayout(dgReceiptLayout);
        dgReceiptLayout.setHorizontalGroup(
            dgReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(dgReceiptLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dgReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgReceiptLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dgReceiptLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgReceiptLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        dgReceiptLayout.setVerticalGroup(
            dgReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgReceiptLayout.createSequentialGroup()
                .addGroup(dgReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dgReceiptLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        lblProductImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        lblProductName.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblProductName.setForeground(new java.awt.Color(235, 235, 235));

        lblProductQuantity.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblProductQuantity.setForeground(new java.awt.Color(235, 235, 235));
        lblProductQuantity.setText("x");

        lblProductNo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblProductNo.setForeground(new java.awt.Color(235, 235, 235));
        lblProductNo.setText("Product No:");

        lblProductPrice.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblProductPrice.setForeground(new java.awt.Color(235, 235, 235));
        lblProductPrice.setText("Price: ");

        lblProductMeasureUnitAndNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblQuantityValue.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lblQuantityValue.setForeground(new java.awt.Color(235, 235, 235));
        lblQuantityValue.setText("0");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(235, 235, 235));
        jLabel15.setText("max quantity");

        lblMaxQuantity.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblMaxQuantity.setForeground(new java.awt.Color(235, 235, 235));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(lblProductMeasureUnitAndNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblProductQuantity)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblQuantityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblMaxQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblProductPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(73, 73, 73)))
                        .addGap(42, 42, 42))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaxQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblProductMeasureUnitAndNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantityValue))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblProductNo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProductPrice)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(35, 35, 35));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        txtfUserTextBox.setBackground(new java.awt.Color(35, 35, 35));
        txtfUserTextBox.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtfUserTextBox.setForeground(new java.awt.Color(235, 235, 235));
        txtfUserTextBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfUserTextBox.setToolTipText("enter the product no. etc");
        txtfUserTextBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        txtfUserTextBox.setFocusable(false);

        btnBrowse.setBackground(new java.awt.Color(51, 51, 51));
        btnBrowse.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnBrowse.setForeground(new java.awt.Color(235, 235, 235));
        btnBrowse.setText("Browse");
        btnBrowse.setToolTipText("Browse for products");
        btnBrowse.setContentAreaFilled(false);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtfUserTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtfUserTextBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(new java.awt.Color(35, 35, 35));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        btnFour.setBackground(new java.awt.Color(51, 51, 51));
        btnFour.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnFour.setForeground(new java.awt.Color(235, 235, 235));
        btnFour.setText("4");
        btnFour.setContentAreaFilled(false);
        btnFour.setFocusable(false);
        btnFour.setOpaque(true);
        btnFour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFourMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFourMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFourMousePressed(evt);
            }
        });
        btnFour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFourActionPerformed(evt);
            }
        });

        btnFive.setBackground(new java.awt.Color(51, 51, 51));
        btnFive.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnFive.setForeground(new java.awt.Color(235, 235, 235));
        btnFive.setText("5");
        btnFive.setContentAreaFilled(false);
        btnFive.setFocusable(false);
        btnFive.setOpaque(true);
        btnFive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFiveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFiveMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFiveMousePressed(evt);
            }
        });
        btnFive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiveActionPerformed(evt);
            }
        });

        btnSeven.setBackground(new java.awt.Color(51, 51, 51));
        btnSeven.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnSeven.setForeground(new java.awt.Color(235, 235, 235));
        btnSeven.setText("7");
        btnSeven.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSeven.setContentAreaFilled(false);
        btnSeven.setFocusable(false);
        btnSeven.setOpaque(true);
        btnSeven.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSevenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSevenMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSevenMousePressed(evt);
            }
        });
        btnSeven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSevenActionPerformed(evt);
            }
        });

        btnEight.setBackground(new java.awt.Color(51, 51, 51));
        btnEight.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEight.setForeground(new java.awt.Color(235, 235, 235));
        btnEight.setText("8");
        btnEight.setContentAreaFilled(false);
        btnEight.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEight.setFocusable(false);
        btnEight.setOpaque(true);
        btnEight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEightMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEightMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEightMousePressed(evt);
            }
        });
        btnEight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEightActionPerformed(evt);
            }
        });

        btnNine.setBackground(new java.awt.Color(51, 51, 51));
        btnNine.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNine.setForeground(new java.awt.Color(235, 235, 235));
        btnNine.setText("9");
        btnNine.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNine.setContentAreaFilled(false);
        btnNine.setFocusable(false);
        btnNine.setOpaque(true);
        btnNine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNineMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNineMousePressed(evt);
            }
        });
        btnNine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNineActionPerformed(evt);
            }
        });

        btnSix.setBackground(new java.awt.Color(51, 51, 51));
        btnSix.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnSix.setForeground(new java.awt.Color(235, 235, 235));
        btnSix.setText("6");
        btnSix.setContentAreaFilled(false);
        btnSix.setFocusable(false);
        btnSix.setOpaque(true);
        btnSix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSixMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSixMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSixMousePressed(evt);
            }
        });
        btnSix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSixActionPerformed(evt);
            }
        });

        btnOne.setBackground(new java.awt.Color(51, 51, 51));
        btnOne.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnOne.setForeground(new java.awt.Color(235, 235, 235));
        btnOne.setText("1");
        btnOne.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnOne.setContentAreaFilled(false);
        btnOne.setFocusable(false);
        btnOne.setOpaque(true);
        btnOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOneMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOneMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOneMousePressed(evt);
            }
        });
        btnOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneActionPerformed(evt);
            }
        });

        btnTwo.setBackground(new java.awt.Color(51, 51, 51));
        btnTwo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnTwo.setForeground(new java.awt.Color(235, 235, 235));
        btnTwo.setText("2");
        btnTwo.setContentAreaFilled(false);
        btnTwo.setFocusable(false);
        btnTwo.setOpaque(true);
        btnTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTwoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTwoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTwoMousePressed(evt);
            }
        });
        btnTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwoActionPerformed(evt);
            }
        });

        btnThree.setBackground(new java.awt.Color(51, 51, 51));
        btnThree.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnThree.setForeground(new java.awt.Color(235, 235, 235));
        btnThree.setText("3");
        btnThree.setContentAreaFilled(false);
        btnThree.setFocusable(false);
        btnThree.setOpaque(true);
        btnThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThreeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThreeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThreeMousePressed(evt);
            }
        });
        btnThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThreeActionPerformed(evt);
            }
        });

        btnZero.setBackground(new java.awt.Color(51, 51, 51));
        btnZero.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnZero.setForeground(new java.awt.Color(235, 235, 235));
        btnZero.setText("0");
        btnZero.setContentAreaFilled(false);
        btnZero.setFocusable(false);
        btnZero.setOpaque(true);
        btnZero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnZeroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnZeroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnZeroMousePressed(evt);
            }
        });
        btnZero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZeroActionPerformed(evt);
            }
        });

        btnBackSpace.setBackground(new java.awt.Color(51, 51, 51));
        btnBackSpace.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnBackSpace.setForeground(new java.awt.Color(235, 235, 235));
        btnBackSpace.setText("<-");
        btnBackSpace.setContentAreaFilled(false);
        btnBackSpace.setFocusable(false);
        btnBackSpace.setOpaque(true);
        btnBackSpace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackSpaceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackSpaceMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackSpaceMousePressed(evt);
            }
        });
        btnBackSpace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackSpaceActionPerformed(evt);
            }
        });

        btnFind.setBackground(new java.awt.Color(51, 51, 51));
        btnFind.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnFind.setForeground(new java.awt.Color(235, 235, 235));
        btnFind.setText("find");
        btnFind.setContentAreaFilled(false);
        btnFind.setFocusable(false);
        btnFind.setOpaque(true);
        btnFind.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFindMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFindMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFindMousePressed(evt);
            }
        });
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(51, 51, 51));
        btnClear.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(235, 235, 235));
        btnClear.setText("c");
        btnClear.setContentAreaFilled(false);
        btnClear.setFocusable(false);
        btnClear.setOpaque(true);
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClearMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClearMousePressed(evt);
            }
        });
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnOne, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnSeven, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnFour, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEight, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnZero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSix, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(btnThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBackSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBackSpace, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(btnEight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSeven, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFive, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(btnSix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThree, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(btnTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnZero, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(35, 35, 35));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        itemList.setBackground(new java.awt.Color(35, 35, 35));
        itemList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        itemList.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        itemList.setForeground(new java.awt.Color(235, 235, 235));
        jScrollPane1.setViewportView(itemList);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Product Name");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Product No");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Price");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 235, 235));
        jLabel4.setText("Quantity");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        btnClearAll.setBackground(new java.awt.Color(35, 35, 35));
        btnClearAll.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnClearAll.setForeground(new java.awt.Color(235, 235, 235));
        btnClearAll.setText("Clear All");
        btnClearAll.setToolTipText("clear all items");
        btnClearAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnClearAll.setContentAreaFilled(false);
        btnClearAll.setFocusable(false);
        btnClearAll.setOpaque(true);
        btnClearAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearAllMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClearAllMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClearAllMousePressed(evt);
            }
        });
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(btnClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClearAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 153, 0));

        btnPay.setBackground(new java.awt.Color(35, 35, 35));
        btnPay.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnPay.setForeground(new java.awt.Color(235, 235, 235));
        btnPay.setText("Pay");
        btnPay.setToolTipText("pay all the items");
        btnPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnPay.setContentAreaFilled(false);
        btnPay.setFocusable(false);
        btnPay.setOpaque(true);
        btnPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPayMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPayMousePressed(evt);
            }
        });
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(35, 35, 35));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));

        lblAmount.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblAmount.setForeground(new java.awt.Color(235, 235, 235));
        lblAmount.setText("Amount: 0.00");

        lblChange.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblChange.setForeground(new java.awt.Color(235, 235, 235));
        lblChange.setText("Change: 0.00");

        lblTotal.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(235, 235, 235));
        lblTotal.setText("Total: 0.00 ");

        lblDiscount.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblDiscount.setForeground(new java.awt.Color(235, 235, 235));
        lblDiscount.setText("Discount: 0% ");

        lblTax.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblTax.setForeground(new java.awt.Color(235, 235, 235));
        lblTax.setText("Tax: 0%");

        lblMoney.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblMoney.setForeground(new java.awt.Color(235, 235, 235));
        lblMoney.setText("Money: ");

        jSeparator1.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTax, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(lblChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTax)
                    .addComponent(lblAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoney)
                    .addComponent(lblChange))
                .addContainerGap())
        );

        btnDiscount.setBackground(new java.awt.Color(35, 35, 35));
        btnDiscount.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnDiscount.setForeground(new java.awt.Color(235, 235, 235));
        btnDiscount.setText("Discount");
        btnDiscount.setToolTipText("add a discount for your product");
        btnDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnDiscount.setContentAreaFilled(false);
        btnDiscount.setFocusable(false);
        btnDiscount.setOpaque(true);
        btnDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDiscountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDiscountMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDiscountMousePressed(evt);
            }
        });
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        btnQuantity.setBackground(new java.awt.Color(35, 35, 35));
        btnQuantity.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnQuantity.setForeground(new java.awt.Color(235, 235, 235));
        btnQuantity.setText("Quantity");
        btnQuantity.setToolTipText("add a quantity for your product");
        btnQuantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnQuantity.setContentAreaFilled(false);
        btnQuantity.setFocusable(false);
        btnQuantity.setOpaque(true);
        btnQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuantityMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuantityMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQuantityMousePressed(evt);
            }
        });
        btnQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnAddItem.setBackground(new java.awt.Color(35, 35, 35));
        btnAddItem.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAddItem.setForeground(new java.awt.Color(235, 235, 235));
        btnAddItem.setText("Add Item");
        btnAddItem.setToolTipText("add an item");
        btnAddItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnAddItem.setContentAreaFilled(false);
        btnAddItem.setFocusable(false);
        btnAddItem.setOpaque(true);
        btnAddItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddItemMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddItemMousePressed(evt);
            }
        });
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddItem, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
        );

        btnRemoveItem.setBackground(new java.awt.Color(35, 35, 35));
        btnRemoveItem.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnRemoveItem.setForeground(new java.awt.Color(235, 235, 235));
        btnRemoveItem.setText("Remove Item");
        btnRemoveItem.setToolTipText("remove an item");
        btnRemoveItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnRemoveItem.setContentAreaFilled(false);
        btnRemoveItem.setFocusable(false);
        btnRemoveItem.setOpaque(true);
        btnRemoveItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoveItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoveItemMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRemoveItemMousePressed(evt);
            }
        });
        btnRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemActionPerformed(evt);
            }
        });

        btnTax.setBackground(new java.awt.Color(35, 35, 35));
        btnTax.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnTax.setForeground(new java.awt.Color(235, 235, 235));
        btnTax.setText("Tax");
        btnTax.setToolTipText("add a tax for all of your product");
        btnTax.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnTax.setContentAreaFilled(false);
        btnTax.setFocusable(false);
        btnTax.setOpaque(true);
        btnTax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaxMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTaxMousePressed(evt);
            }
        });
        btnTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(btnTax, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        btnNext.setBackground(new java.awt.Color(35, 35, 35));
        btnNext.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNext.setForeground(new java.awt.Color(235, 235, 235));
        btnNext.setText("Next");
        btnNext.setToolTipText("next counter ");
        btnNext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnNext.setContentAreaFilled(false);
        btnNext.setFocusable(false);
        btnNext.setOpaque(true);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveItem, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRemoveItem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFourActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "4");
    }//GEN-LAST:event_btnFourActionPerformed

    private void btnFiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiveActionPerformed
 
        txtfUserTextBox.setText(txtfUserTextBox.getText() + "5");
    }//GEN-LAST:event_btnFiveActionPerformed

    private void btnSevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSevenActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "7");
    }//GEN-LAST:event_btnSevenActionPerformed

    private void btnEightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEightActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "8");
    }//GEN-LAST:event_btnEightActionPerformed

    private void btnNineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNineActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "9");
    }//GEN-LAST:event_btnNineActionPerformed

    private void btnSixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSixActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "6");
    }//GEN-LAST:event_btnSixActionPerformed

    private void btnOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOneActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "1");
    }//GEN-LAST:event_btnOneActionPerformed

    private void btnTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTwoActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "2");
    }//GEN-LAST:event_btnTwoActionPerformed

    private void btnThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThreeActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "3");
    }//GEN-LAST:event_btnThreeActionPerformed

    private void btnZeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZeroActionPerformed

        txtfUserTextBox.setText(txtfUserTextBox.getText() + "0");
    }//GEN-LAST:event_btnZeroActionPerformed

    private void btnBackSpaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackSpaceActionPerformed

        if (!txtfUserTextBox.getText().isEmpty())
            txtfUserTextBox.setText (txtfUserTextBox.getText().substring(0, (txtfUserTextBox.getText().length() - 1)));
    }//GEN-LAST:event_btnBackSpaceActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String getUserTextString = txtfUserTextBox.getText ();

        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;

        if (checkDigits(getUserTextString)) {

            try {

                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                sqlResult = sqlStatement.executeQuery("SELECT ProductNumber, ProductName, ProductQuantity, ProductDescription, ProductMeasureUnit, ProductMeasureNumber, "
                    + " ProductDiscount, ProductPrice, ProductImage FROM TBLINVENTORY WHERE ProductNumber = '" + getUserTextString + "'");

                if (sqlResult.isBeforeFirst()) {

                    for (;sqlResult.next();) {
                        getInventory.productNumber = sqlResult.getString("productNumber");
                        getInventory.productName =  sqlResult.getString("ProductName");
                        getInventory.productDescription = sqlResult.getString("ProductDescription");
                        getInventory.productPrice = sqlResult.getDouble("ProductPrice");
                        getInventory.productQuantity = 1;
                      
                        lblProductImage.setToolTipText(getInventory.productDescription);
                        lblMaxQuantity.setText(sqlResult.getString("ProductQuantity"));
                        
                        ImageIcon image = null;
                        Blob photo = sqlResult.getBlob("ProductImage");
                        ObjectInputStream ois = null;
                        
                        try {
                            if (photo != null) {
                                
                                ois = new ObjectInputStream(photo.getBinaryStream());
                            
                               try {
                                    image = new ImageIcon(((ImageIcon) ois.readObject()).getImage().getScaledInstance(lblProductImage.getWidth(), lblProductImage.getHeight(), Image.SCALE_DEFAULT));
                                
                                 } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                File getFileLocation = new File("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                
                                image = new ImageIcon(new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblProductImage.getWidth(), lblProductImage.getHeight(), Image.SCALE_DEFAULT));
                            }
                           
                        } catch (IOException ex) {
                            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        lblProductImage.setIcon(image);
                    }
                    
                    lblProductName.setText(getInventory.productName);
                    lblProductPrice.setText("Price: " + Double.toString(getInventory.productPrice));
                    lblProductNo.setText("Product No: " + getInventory.productNumber);
                    lblQuantityValue.setText("1");
                    
                    txtfUserTextBox.setText("");
                    txtfUserTextBox.setRequestFocusEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Product number not found", "not found", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (conn != null)
                    conn.close ();

                    if (sqlStatement != null)
                    sqlStatement.close ();

                    if (sqlResult != null)
                    sqlResult.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "digits only", "enter", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        txtfUserTextBox.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed

        if (!ifPaid) {
        
        if (  (!getInventory.productNumber.isEmpty()) && (! (getInventory.productPrice <= 0) ) && (! (getInventory.productQuantity <= 0) && (!getInventory.productName.isEmpty() ) )  ) {
          
        Inventory vGetInventory = new Inventory ();
        
        vGetInventory.productNumber = getInventory.productNumber;
        vGetInventory.productPrice = getInventory.productPrice;
        vGetInventory.productQuantity = getInventory.productQuantity; 
        vGetInventory.productName = getInventory.productName;
    
        if (vGetInventory.productQuantity <= 0) 
            vGetInventory.productQuantity = 1;
            
        vInventory.add(vGetInventory);
        
        String formattedStr = String.format("%8s %23s %13.2f %11d", getInventory.productNumber, getInventory.productName, getInventory.productPrice, getInventory.productQuantity);
        
        listItem.addElement(formattedStr);
        
        itemList.setModel(listItem);
        
        double salePrice = 0;
        
        if (!listItem.isEmpty()) {
                
        double total = 0;
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
        
        double discount = total * ((float)(Discount / 100.0f) ); 
        salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
        salePrice += Pricetax;
      
        }
        
        lblTotal.setText("Total: " + String.format("%1.2f", salePrice));
           
        } else {
            if ( getInventory.productNumber.isEmpty() && (getInventory.productPrice <= 0) && (getInventory.productQuantity <= 0) ){
                JOptionPane.showMessageDialog(this, "enter the product number, product price, product quantity", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (getInventory.productNumber.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "enter the product number", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (getInventory.productPrice <= 0) {
                 JOptionPane.showMessageDialog(this, "enter the product price", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (getInventory.productName.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "enter the product Name", "enter", JOptionPane.ERROR_MESSAGE);
            } else {
                 JOptionPane.showMessageDialog(this, "enter product quantity", "enter", JOptionPane.ERROR_MESSAGE);
            }        
        }
        } else {
            JOptionPane.showMessageDialog(this, "next counter", "add item", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuantityActionPerformed

        if (!txtfUserTextBox.getText().isEmpty()) {
        
        if (!ifPaid) {
        
        if (checkDigitsD (txtfUserTextBox.getText())) {
        
        lblQuantityValue.setText(txtfUserTextBox.getText().trim());
        getInventory.productQuantity = Integer.parseInt(lblQuantityValue.getText());
        
        } else {
            JOptionPane.showMessageDialog(this, "digits only", "enter", JOptionPane.ERROR_MESSAGE);
        }
        } else {
            JOptionPane.showMessageDialog(this, "next counter", "quantity", JOptionPane.INFORMATION_MESSAGE);
        }
        } else {
            JOptionPane.showMessageDialog(this, "add a quantity", "quantity", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnQuantityActionPerformed

    private void btnRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemActionPerformed

        if (!ifPaid) {
        
        if( (!vInventory.isEmpty()) && (!listItem.isEmpty()) ) {
            
        if (itemList.isSelectedIndex(itemList.getSelectedIndex())) {
        
        vInventory.removeElementAt(itemList.getSelectedIndex());
        listItem.removeElementAt(itemList.getSelectedIndex());
        itemList.setModel(listItem);
        
        double salePrice = 0;
        
        if (!listItem.isEmpty()) {
                
        double total = 0;
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
        
        double discount = total * ((float)(Discount / 100.0f) ); 
        
        salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
    
        salePrice += Pricetax;
        }
        
        lblTotal.setText("Total: " + String.format("%1.2f", salePrice));
        
        } else {
            
        vInventory.removeElementAt(listItem.getSize() - 1);
        listItem.removeElementAt(listItem.getSize() - 1);
        itemList.setModel(listItem);
        
        double salePrice = 0;
        
        if (!listItem.isEmpty()) {
                
        double total = 0;
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
        
        double discount = total * ((float)(Discount / 100.0f) ); 
        
        salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
        
        salePrice += Pricetax;
        
        }
        lblTotal.setText("Total: " + String.format("%1.2f", salePrice));
        }
        
        } else {
            JOptionPane.showMessageDialog(this, "add an item first", "remove item", JOptionPane.ERROR_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog(this, "next counter", "remove item", JOptionPane.INFORMATION_MESSAGE);
        }
    
    }//GEN-LAST:event_btnRemoveItemActionPerformed

    private boolean checkDigitsD (String digits) {
        int count = 0;
        
        if (digits.isEmpty())
            return false;
        
        for (int letterCount = 0; letterCount != digits.length(); ++letterCount) {
            if (!Character.isDigit (digits.charAt(letterCount))) {
                if (digits.charAt(letterCount) == '.') {
                     return false;
                } else {
                    return false;
                }
                    
            }
            
        }
        
        return true;
    } 
    
    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed

        if (!txtfUserTextBox.getText().isEmpty()) {
        
        if (Integer.parseInt (txtfUserTextBox.getText()) <= 100) {
        
        if (!ifPaid) {
        
        if (checkDigitsD (txtfUserTextBox.getText())) {
        
        Discount = Integer.parseInt(txtfUserTextBox.getText());
        lblDiscount.setText("Discount: " + txtfUserTextBox.getText() + "%");
       
        if (!listItem.isEmpty()) {
                
        double total = 0;
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
        
        double discount = total * ((float)(Discount / 100.0f) ); 
     
        double salePrice = total - discount;  
        
        double Pricetax = total * ((float) (Tax / 100.0f));
        salePrice += Pricetax;
        
        lblTotal.setText ("Total: " + String.format("%1.2f",salePrice));
        
        }
        
        } else {
            JOptionPane.showMessageDialog(this, "digits only", "enter",  JOptionPane.ERROR_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog (this, "next counter", "Discount", JOptionPane.INFORMATION_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog (this, "discount only to 1 to 100", "Discount", JOptionPane.ERROR_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog (this, "add a discount", "Discount", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDiscountActionPerformed

    private void btnTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaxActionPerformed

        if (!txtfUserTextBox.getText().isEmpty()) {
        
        if (Integer.parseInt(txtfUserTextBox.getText()) <= 100) {
        
        if (!ifPaid) {
        
        if (checkDigitsD (txtfUserTextBox.getText())) {
        
        Tax = Integer.parseInt(txtfUserTextBox.getText());
        lblTax.setText("Tax: " + txtfUserTextBox.getText() + "%");
        
         if (!listItem.isEmpty()) {
                
        double total = 0;
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
       
        double discount = total * ((float)(Discount / 100.0f) ); 

        double salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
        salePrice += Pricetax;
      
        lblTotal.setText ("Total: " + String.format("%1.2f",salePrice));
        
        }
         
        } else {
            JOptionPane.showMessageDialog(this, "digits only", "enter", JOptionPane.ERROR_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog(this, "next counter", "tax", JOptionPane.INFORMATION_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog (this, "tax only to 1 to 100", "tax", JOptionPane.ERROR_MESSAGE);
        }
        
        } else {
            JOptionPane.showMessageDialog (this, "add a tax", "tax", JOptionPane.INFORMATION_MESSAGE); 
        }
    }//GEN-LAST:event_btnTaxActionPerformed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
   
       if (!ifPaid) {
        
       this.lblProductName.setText("");
       this.lblProductPrice.setText("Price: ");
       this.lblQuantityValue.setText("");
       this.lblProductNo.setText ("Product No");
       lblMaxQuantity.setText("");
       
       lblProductImage.setIcon(null);
       lblProductImage.setToolTipText("");
       
       getInventory.productName = "";
       getInventory.productNumber = "";
       getInventory.productPrice = 0;
       getInventory.productDiscount = 0;
       getInventory.productQuantity = 0;
       
       listItem.removeAllElements();
       vInventory.removeAllElements();
     
       productNumberSet.clear();
       
        double salePrice = 0;
        
        if (!listItem.isEmpty()) {
                
        double total = 0;
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
       
        double discount = total * ((float)(Discount / 100.0f) ); 
    
        salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
       
        salePrice += Pricetax;
      
        }
        
       lblTotal.setText("Total: " + String.format("%1.2f", salePrice));
    
       itemList.setModel(listItem);
       
       } else {
           JOptionPane.showMessageDialog(this, "next item", "clear all", JOptionPane.INFORMATION_MESSAGE);
       }
      
    }//GEN-LAST:event_btnClearAllActionPerformed

    private String checkInventory (Integer productCode, int totalQuantity) {
        Connection conn = null;
        Statement sqlStatement = null; 
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT PRODUCTQUANTITY, PRODUCTNAME FROM TBLINVENTORY WHERE PRODUCTNUMBER = '" + productCode.toString() + "'");
            
            for (;sqlResult.next();) {
                int quantityFromInventory = sqlResult.getInt("PRODUCTQUANTITY");
                
                if (quantityFromInventory  < 0) {
                      return "product " + sqlResult.getString("PRODUCTNAME") + " out of stock \n";
                      
                
                } else {
                     if (quantityFromInventory < totalQuantity)
                      return "the only stocks in product" + sqlResult.getString("PRODUCTNAME") + " is " + quantityFromInventory + " but total quantity is: " + totalQuantity + "\n";
                      else {
                      return "";
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
                try {
                    if (conn != null)
                    conn.close ();

                    if (sqlStatement != null)
                    sqlStatement.close ();

                    if (sqlResult != null)
                    sqlResult.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return "";
        
    }
    
    private void updateInventory (Integer productCode, int totalQuantity) {
        Connection conn = null;
        Statement sqlStatement = null; 
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT PRODUCTQUANTITY, PRODUCTNAME FROM TBLINVENTORY WHERE PRODUCTNUMBER = '" + productCode.toString() + "'");
            
            sqlResult.next();
            
            int quantityFromInventory = sqlResult.getInt("PRODUCTQUANTITY");
                
            int updatedQuantity = quantityFromInventory - totalQuantity;
          
            sqlStatement.executeUpdate("UPDATE TBLINVENTORY SET PRODUCTQUANTITY = " + updatedQuantity + " WHERE PRODUCTNUMBER = '" + productCode + "'");
           
        } catch (SQLException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
                try {
                    if (conn != null) {
                     conn.close ();
                    }

                    if (sqlStatement != null) {
                     sqlStatement.close ();
                    }

                    if (sqlResult != null) {
                       sqlResult.close ();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
       if (!ifPaid) { 
     
        if (  (!getInventory.productNumber.isEmpty()) && (! (getInventory.productPrice <= 0) ) && (! (getInventory.productQuantity <= 0) && (!getInventory.productName.isEmpty() && (!listItem.isEmpty()) ) )  ) {
            
       for (int itemCount = 0; itemCount != vInventory.size(); ++itemCount)
           productNumberSet.add( Integer.parseInt(((Inventory) (vInventory.elementAt(itemCount))).productNumber) );
       
       for (Integer pNumber : productNumberSet)
             itemQuantity.put(pNumber, new Inventory());
       
       for (int itemCount = 0; itemCount != vInventory.size(); ++itemCount) {
          Inventory getI = itemQuantity.get( Integer.parseInt(((Inventory)  (vInventory.elementAt(itemCount))).productNumber) );
          getI.productQuantity += ((Inventory)(vInventory.elementAt(itemCount))).productQuantity;
       }
     
       for (Integer pNumber : productNumberSet) {
            Inventory getI = itemQuantity.get(pNumber );
       }
       
       String logs = "";
       
       for (Integer pNumber : productNumberSet) {
           Inventory getI = itemQuantity.get(pNumber);
           logs += checkInventory(pNumber, getI.productQuantity);
       }
       
       if (!logs.isEmpty()) {
           JOptionPane.showMessageDialog(this, logs, "out of stocks", JOptionPane.ERROR_MESSAGE);
       } else {
           payoutDialog.pack();
           payoutDialog.setLocationRelativeTo(null);
           payoutDialog.setVisible(true);
       }
       
       }  else {
            if ( getInventory.productNumber.isEmpty() && (getInventory.productPrice <= 0) && (getInventory.productQuantity <= 0) ){
                JOptionPane.showMessageDialog(this, "enter the product number, product price, product quantity", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (getInventory.productNumber.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "enter the product number", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (getInventory.productPrice <= 0) {
                 JOptionPane.showMessageDialog(this, "enter the product price", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (getInventory.productName.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "enter the product Name", "enter", JOptionPane.ERROR_MESSAGE);
            } else if (listItem.isEmpty()){
                 JOptionPane.showMessageDialog(this, "you need to add an item", "enter", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "enter the product quantity", "enter", JOptionPane.ERROR_MESSAGE);
            }                 
        }
        
       } else {
           JOptionPane.showMessageDialog (this, "next counter", "payout", JOptionPane.INFORMATION_MESSAGE);
       }
         
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertActionPerformed
 
        HashMap  <String, Double> currencyRates = new HashMap <String, Double> ();
        
        final double USDollarRate = 1.00;
        final double EuroPoundsRate = 0.907605;
        final double SingaporeDollarRate = 1.353404;
        final double CanadianDollarRate = 1.302238;
        final double BritishPoundRate = 0.764354;
        
        currencyRates.put("US Dollar", new Double(USDollarRate));
        currencyRates.put("Euro Pound", new Double(EuroPoundsRate));
        currencyRates.put("Singapore Dollar", new Double(SingaporeDollarRate));
        currencyRates.put("Canadian Dollar", new Double(CanadianDollarRate));
        currencyRates.put("British Pound", new Double(BritishPoundRate));
      
        double convertedAmount = ((Double.parseDouble(txtfUserAmountTextBox.getText())) * currencyRates.get(((String) spExchangeFrom.getValue()) )) / currencyRates.get(  ((String) spExchangeTo.getValue()  ) );

        txtfUserAmountTextBox.setText(String.format("%1.6s", Double.toString(convertedAmount)) );   
    }//GEN-LAST:event_btnConvertActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed

        this.txtfUserAmountTextBox.setText("");
        
        payoutDialog.setVisible(false);
        payoutDialog.dispose();
        
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed

       double salePrice = 0;
       
        if (!listItem.isEmpty()) {
                
        double total = 0;
        if (checkDigits(txtfUserAmountTextBox.getText()) ) {
        
        for (Integer pNumber : productNumberSet) {
            Inventory getI = itemQuantity.get(pNumber);
            updateInventory(pNumber, getI.productQuantity);
       }
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
        
        double discount = total * ((float)(Discount / 100.0f) ); 
      
        salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
        
        salePrice += Pricetax;
      
        double amountPaid = Double.parseDouble(txtfUserAmountTextBox.getText());
        
        if (salePrice <= amountPaid) {
            
        double change = amountPaid - salePrice;
        
        payoutDialog.setVisible(false);
        payoutDialog.dispose();
        
        if (ManagerWindow.lblShopName != null) {
        
        taReceipt.append(String.format("%50s",ManagerWindow.lblShopName.getText()) + "\n");
        taReceipt.append(String.format ("%50s", ManagerWindow.lblDescription.getText()) + "\n");
        taReceipt.append(String.format ("%50s", ManagerWindow.lblAddress.getText()) + "\n");
        taReceipt.append("                                      " + ManagerWindow.lblDate.getText() + "\n");
        taReceipt.append("                                      " + ManagerWindow.lblTime.getText() + "\n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        taReceipt.append("           Product No   Product Name    Product Price \n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        
        } else {
        taReceipt.append(String.format("%50s",EmployeeWindow.lblShopName.getText()) + "\n");
        taReceipt.append(String.format ("%50s", EmployeeWindow.lblDescription.getText()) + "\n");
        taReceipt.append(String.format ("%50s", EmployeeWindow.lblAddress.getText()) + "\n");
        taReceipt.append("                                      " + EmployeeWindow.lblDate.getText() + "\n");
        taReceipt.append("                                      " + EmployeeWindow.lblTime.getText() + "\n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        taReceipt.append("           Product No   Product Name    Product Price \n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        }
        
        for (int productCount = 0; productCount != this.vInventory.size(); ++productCount) {
            Inventory getProductInfo = (Inventory)vInventory.get(productCount);
            taReceipt.append (String.format ("%20s %20s %20.6s", getProductInfo.productNumber, getProductInfo.productName, Double.toString(getProductInfo.productPrice)) +  "\n");
        }
        
        taReceipt.append ("   ------------------------------------------------------------------------\n");
        taReceipt.append ("                             total price: " + String.format ("%1.6s", Double.toString(salePrice)) + "\n");
        taReceipt.append ("                             change: " + String.format ("%1.6s", Double.toString(change)) + "\n");
        taReceipt.append ("                             tax: " + Integer.toString (Tax) + "%\n");
        taReceipt.append ("                             discount: " + Integer.toString (Discount) + "%\n");
        taReceipt.append ("                             Money: " + spExchangeFrom.getValue().toString());
        
        
        dgReceipt.pack ();
        dgReceipt.setVisible(true);
        
        lblAmount.setText("Amount: " + String.format("%1.6s", Double.toString(amountPaid)));
        lblChange.setText("Change: " + String.format("%1.6s",Double.toString(change)) );
        lblMoney.setText("Money: " + spExchangeFrom.getValue().toString());
        
        productNumberSet.clear();
        itemQuantity.clear();
        vInventory.clear();
        listItem.clear();
        
        lblProductNo.setText("Product No: ");
        lblProductName.setText("");
        lblProductPrice.setText("Price: ");
        lblQuantityValue.setText("");
        txtfUserTextBox.setText("");
        this.lblMaxQuantity.setText("");
        this.txtfUserAmountTextBox.setText("");
        
        lblProductImage.setIcon(null);
        lblProductImage.setToolTipText("");
        
        getInventory.productNumber = "";
        getInventory.productPrice = 0.0;
        getInventory.productQuantity = 0;
       
        if (!ifPaid)
           ifPaid = true;
        
        if (!ifNext)
            ifNext = true;
        
        itemList.setModel(listItem);
       
        } else {
            JOptionPane.showMessageDialog(this, "insufficient amount", "payout", JOptionPane.ERROR_MESSAGE);
        }
        
        } else {
                JOptionPane.showMessageDialog (this, "digits only", "payout", JOptionPane.ERROR_MESSAGE);
        }
        } 
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

        if (ifPaid) {
        
        lblTotal.setText("Total: ");
        lblAmount.setText("Amount: ");
        lblChange.setText("Change: ");
        lblMaxQuantity.setText("");
        
        ifPaid = false;
        } else {
            JOptionPane.showMessageDialog(this, "you didnt pay yet", "payout", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void updateSelectInventoryTable () {
        DefaultTableModel tableInventory = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
           }
        };
        
        tableInventory.addColumn("product number");
        tableInventory.addColumn("product name");
        tableInventory.addColumn("product quantity");
        tableInventory.addColumn("product description");
        tableInventory.addColumn("product price");
        
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLINVENTORY ORDER BY " + removeWhiteSpace(columnBy).toUpperCase() + " " + ((sortBy.equals("Ascending") ? "ASC" : "DESC")));
            
            if (numRows != 0) {
                for (int rowCount = 0; rowCount != numRows; ++rowCount) {
                    sqlResult.next ();
                    
                       
                    try {
                    
                    tableInventory.addRow (new String[] {sqlResult.getString ("PRODUCTNUMBER"),
                                                         sqlResult.getString ("PRODUCTNAME"),
                                                         sqlResult.getString ("PRODUCTQUANTITY"),
                                                         sqlResult.getString ("PRODUCTDESCRIPTION"),
                                                         sqlResult.getString ("PRODUCTPRICE")});
                    
                    } catch (Exception e) {
                        break;
                    }
                }
                
                
            } else {
                for (; sqlResult.next ();) {
                    
                    try {
                    
                    tableInventory.addRow (new String[] {sqlResult.getString ("PRODUCTNUMBER"),
                                                         sqlResult.getString ("PRODUCTNAME"),
                                                         sqlResult.getString ("PRODUCTQUANTITY"),
                                                         sqlResult.getString ("PRODUCTDESCRIPTION"),
                                                         sqlResult.getString ("PRODUCTPRICE")});
                    
                    } catch (Exception e) {
                        break;
                    }
                    
                }
            }
            
            tblProductSelect.setModel(tableInventory);
            
        } catch (SQLException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed

        updateSelectInventoryTable ();
       
        dgBrowseProduct.setLocationRelativeTo(null);
        dgBrowseProduct.pack();
        dgBrowseProduct.setVisible(true);
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnSelectCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectCancelActionPerformed

        dgBrowseProduct.setVisible(false);
        dgBrowseProduct.dispose();
    }//GEN-LAST:event_btnSelectCancelActionPerformed

    private void cbColumnByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbColumnByItemStateChanged

        columnBy = cbColumnBy.getSelectedItem().toString();
        
        if (columnBy.equals("Product No"))
            columnBy = "PRODUCTNUMBER";
        
        updateSelectInventoryTable ();
    }//GEN-LAST:event_cbColumnByItemStateChanged

    private void cbSortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSortByItemStateChanged

        sortBy = cbSortBy.getSelectedItem().toString();
        updateSelectInventoryTable();
    }//GEN-LAST:event_cbSortByItemStateChanged

    private void cbNumRowsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNumRowsItemStateChanged

        numRows = (cbNumRows.getSelectedItem().toString().equals("all")) ? 0 : Integer.parseInt(cbNumRows.getSelectedItem().toString());
        updateSelectInventoryTable();
    }//GEN-LAST:event_cbNumRowsItemStateChanged

    private void btnSelectSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectSearchActionPerformed

        DefaultTableModel searchModel = new DefaultTableModel () {
           public boolean isCellEditable (int row, int column) {
                return false;
           }
        };
         
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        searchModel.addColumn("product number");
        searchModel.addColumn("product name");
        searchModel.addColumn("product quantity");
        searchModel.addColumn("product description");
        searchModel.addColumn("product price");
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT PRODUCTNUMBER, PRODUCTNAME, PRODUCTQUANTITY, PRODUCTDESCRIPTION, PRODUCTPRICE, PRODUCTIMAGE FROM TBLINVENTORY WHERE PRODUCTNAME LIKE '%" + txtfProductSearch.getText() + "%' OR PRODUCTNUMBER LIKE '%" + txtfProductSearch.getText() + "%'");
            
            for (; sqlResult.next() ;) {
                searchModel.addRow(new String [] {sqlResult.getString("PRODUCTNUMBER"),
                                                    sqlResult.getString("PRODUCTNAME"),
                                                    sqlResult.getString("PRODUCTQUANTITY"),
                                                    sqlResult.getString("PRODUCTDESCRIPTION"),
                                                    sqlResult.getString("PRODUCTPRICE")});
            }
            
            tblProductSelect.setModel (searchModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSelectSearchActionPerformed

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed

            getInventory.productNumber = tblProductSelect.getModel().getValueAt(getIndex, 0).toString();
            getInventory.productName = tblProductSelect.getModel().getValueAt(getIndex, 1).toString();
            getInventory.productQuantity = 1;
            getInventory.productDescription = tblProductSelect.getModel().getValueAt(getIndex, 3).toString();
            getInventory.productPrice = Double.parseDouble(tblProductSelect.getModel().getValueAt(getIndex, 4).toString());
            
            lblProductName.setText(getInventory.productName);
            lblProductPrice.setText("Price: " + Double.toString(getInventory.productPrice));
            lblProductNo.setText("Product No: " + getInventory.productNumber);
            lblQuantityValue.setText("1");
            
            lblProductImage.setIcon(new ImageIcon (Selectimage.getImage().getScaledInstance(125, 124, Image.SCALE_DEFAULT)));
            lblMaxQuantity.setText(tblProductSelect.getModel().getValueAt(getIndex, 2).toString());
            
            dgBrowseProduct.setVisible(false);
            dgBrowseProduct.dispose();
    }//GEN-LAST:event_btnSelectActionPerformed

    private void tblProductSelectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductSelectMousePressed

        getIndex = tblProductSelect.getSelectedRow ();
        
        System.out.println ("pressed" + getIndex);
       
        if (getIndex != -1) {
            
            String productName = tblProductSelect.getModel().getValueAt(getIndex, 1).toString();
        
            Connection conn = null;
            Statement sqlStatement = null;
            ResultSet sqlResult = null;
            
            try {
                conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement = conn.createStatement();
                sqlResult = sqlStatement.executeQuery("SELECT PRODUCTIMAGE FROM TBLINVENTORY WHERE PRODUCTNAME = '" + productName + "'");
                
                for (; sqlResult.next() ;) {
                    
                        Blob imageBlob = conn.createBlob();
                        
                        imageBlob = sqlResult.getBlob("PRODUCTIMAGE");
                        
                    try {
                        ObjectInputStream blobToImage = new ObjectInputStream (imageBlob.getBinaryStream());
                            try {
                                Selectimage = new ImageIcon (  ((ImageIcon)  blobToImage.readObject()).getImage().getScaledInstance(170, 152, Image.SCALE_DEFAULT) );
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    } catch (IOException ex) {
                        Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    lblProductSelectImage.setIcon(Selectimage);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            JOptionPane.showMessageDialog (this, "select a product first", "select product",  JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblProductSelectMousePressed

    private void btnSevenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSevenMouseEntered

        btnSeven.setBackground(new Color (74,74,74));
    }//GEN-LAST:event_btnSevenMouseEntered

    private void btnSevenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSevenMouseExited

        btnSeven.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSevenMouseExited

    private void btnSevenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSevenMousePressed

        btnSeven.setBackground (new Color (89,89,89));
    }//GEN-LAST:event_btnSevenMousePressed

    private void btnEightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEightMouseEntered

        btnEight.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnEightMouseEntered

    private void btnEightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEightMouseExited

        btnEight.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnEightMouseExited

    private void btnEightMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEightMousePressed

        btnEight.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnEightMousePressed

    private void btnNineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNineMouseEntered

        btnNine.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnNineMouseEntered

    private void btnNineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNineMouseExited

        btnNine.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnNineMouseExited

    private void btnNineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNineMousePressed

        btnNine.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnNineMousePressed

    private void btnBackSpaceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackSpaceMouseEntered

        btnBackSpace.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnBackSpaceMouseEntered

    private void btnBackSpaceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackSpaceMouseExited

        btnBackSpace.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnBackSpaceMouseExited

    private void btnBackSpaceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackSpaceMousePressed

        btnBackSpace.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnBackSpaceMousePressed

    private void btnFourMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFourMouseEntered

        btnFour.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnFourMouseEntered

    private void btnFourMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFourMouseExited

        btnFour.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnFourMouseExited

    private void btnFourMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFourMousePressed

        btnFour.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnFourMousePressed

    private void btnFiveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFiveMouseEntered

        btnFive.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnFiveMouseEntered

    private void btnFiveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFiveMouseExited

        btnFive.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnFiveMouseExited

    private void btnFiveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFiveMousePressed

        btnFive.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnFiveMousePressed

    private void btnSixMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSixMouseEntered

        btnSix.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSixMouseEntered

    private void btnSixMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSixMouseExited

        btnSix.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSixMouseExited

    private void btnSixMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSixMousePressed

        btnSix.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSixMousePressed

    private void btnFindMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindMouseEntered

        btnFind.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnFindMouseEntered

    private void btnFindMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindMouseExited

        btnFind.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnFindMouseExited

    private void btnFindMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindMousePressed

        btnFind.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnFindMousePressed

    private void btnOneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOneMouseEntered

        btnOne.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnOneMouseEntered

    private void btnOneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOneMouseExited

        btnOne.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnOneMouseExited

    private void btnOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOneMousePressed

        btnOne.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnOneMousePressed

    private void btnTwoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTwoMouseEntered

        btnTwo.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnTwoMouseEntered

    private void btnTwoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTwoMouseExited

        btnTwo.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnTwoMouseExited

    private void btnTwoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTwoMousePressed

        btnTwo.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnTwoMousePressed

    private void btnThreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThreeMouseEntered

        btnThree.setBackground (new Color (74, 74, 74));
        
    }//GEN-LAST:event_btnThreeMouseEntered

    private void btnThreeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThreeMouseExited

        btnThree.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnThreeMouseExited

    private void btnThreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThreeMousePressed
        // TODO add your handling code here:
        btnThree.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnThreeMousePressed

    private void btnZeroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZeroMouseEntered

        btnZero.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnZeroMouseEntered

    private void btnZeroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZeroMouseExited

        btnZero.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnZeroMouseExited

    private void btnZeroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZeroMousePressed

        btnZero.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnZeroMousePressed

    private void btnClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseEntered

        btnClear.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnClearMouseEntered

    private void btnClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseExited

        btnClear.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnClearMouseExited

    private void btnClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMousePressed
 
        btnClear.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnClearMousePressed

    private void btnDiscountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiscountMouseEntered

        btnDiscount.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDiscountMouseEntered

    private void btnDiscountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiscountMouseExited

        btnDiscount.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnDiscountMouseExited

    private void btnDiscountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDiscountMousePressed

        btnDiscount.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDiscountMousePressed

    private void btnQuantityMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuantityMouseEntered

        btnQuantity.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnQuantityMouseEntered

    private void btnQuantityMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuantityMouseExited

        btnQuantity.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnQuantityMouseExited

    private void btnQuantityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuantityMousePressed

        btnQuantity.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnQuantityMousePressed

    private void btnAddItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddItemMouseEntered

        btnAddItem.setBackground (new Color (74, 74, 74)); 
    }//GEN-LAST:event_btnAddItemMouseEntered

    private void btnAddItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddItemMouseExited

        btnAddItem.setBackground (new Color (35, 35, 35)); 
    }//GEN-LAST:event_btnAddItemMouseExited

    private void btnAddItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddItemMousePressed

        btnAddItem.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnAddItemMousePressed

    private void btnRemoveItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveItemMouseEntered

        btnRemoveItem.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnRemoveItemMouseEntered

    private void btnRemoveItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveItemMouseExited

        btnRemoveItem.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnRemoveItemMouseExited

    private void btnRemoveItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveItemMousePressed

        btnRemoveItem.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnRemoveItemMousePressed

    private void btnTaxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaxMouseEntered

        btnTax.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnTaxMouseEntered

    private void btnTaxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaxMouseExited

        btnTax.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnTaxMouseExited

    private void btnTaxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaxMousePressed

        btnTax.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnTaxMousePressed

    private void btnClearAllMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearAllMouseEntered
   
        btnClearAll.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnClearAllMouseEntered

    private void btnClearAllMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearAllMouseExited
      
        btnClearAll.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnClearAllMouseExited

    private void btnClearAllMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearAllMousePressed
      
        btnClearAll.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnClearAllMousePressed

    private void btnPayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayMouseEntered
     
        btnPay.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnPayMouseEntered

    private void btnPayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayMouseExited
    
        btnPay.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnPayMouseExited

    private void btnPayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayMousePressed
     
        btnPay.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnPayMousePressed

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered
        
        btnNext.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited
     
        btnNext.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnNextMouseExited

    private void btnNextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMousePressed
     
        btnNext.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnNextMousePressed

    private void btnBrowseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseEntered
     
        btnBrowse.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnBrowseMouseEntered

    private void btnBrowseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseExited
    
        btnBrowse.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnBrowseMouseExited

    private void btnBrowseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMousePressed
      
        btnBrowse.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnBrowseMousePressed

    private void btnSelectSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectSearchMouseEntered
    
        btnSelectSearch.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSelectSearchMouseEntered

    private void btnSelectSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectSearchMouseExited
   
        btnSelectSearch.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSelectSearchMouseExited

    private void btnSelectSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectSearchMousePressed
      
        btnSelectSearch.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSelectSearchMousePressed

    private void btnSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseEntered
       
        btnSelect.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSelectMouseEntered

    private void btnSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseExited
       
        btnSelect.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSelectMouseExited

    private void btnSelectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMousePressed
      
        btnSelect.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSelectMousePressed

    private void btnSelectCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectCancelMouseEntered
       
        btnSelectCancel.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSelectCancelMouseEntered

    private void btnSelectCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectCancelMouseExited
     
        btnSelectCancel.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSelectCancelMouseExited

    private void btnSelectCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectCancelMousePressed
    
        btnSelectCancel.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSelectCancelMousePressed

    private void dgBrowseProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgBrowseProductMousePressed
       
        MouseCoordinates = evt.getPoint();
    }//GEN-LAST:event_dgBrowseProductMousePressed

    private void dgBrowseProductMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgBrowseProductMouseDragged
  
        Point currentCoords = evt.getLocationOnScreen();
              dgBrowseProduct.setLocation(currentCoords.x - MouseCoordinates.x, currentCoords.y - MouseCoordinates.y);
    }//GEN-LAST:event_dgBrowseProductMouseDragged

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
 
        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
 
        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
   
        btnClose.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
    
        dgBrowseProduct.setVisible (false);
        dgBrowseProduct.dispose ();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void dgReceiptMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgReceiptMouseDragged
   
          Point currentCoords = evt.getLocationOnScreen();
                dgReceipt.setLocation(currentCoords.x - MouseCoordinates2.x, currentCoords.y - MouseCoordinates2.y);
    }//GEN-LAST:event_dgReceiptMouseDragged

    private void dgReceiptMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgReceiptMousePressed
     
         MouseCoordinates2 = evt.getPoint();
    }//GEN-LAST:event_dgReceiptMousePressed

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
    
        dgReceipt.setVisible(false);
        dgReceipt.dispose ();
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

    private void btnOkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseEntered
      
        btnOk.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnOkMouseEntered

    private void btnOkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseExited
       
        btnOk.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnOkMouseExited

    private void btnOkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMousePressed
      
        btnOk.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnOkMousePressed

    private void btnClose3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MouseEntered
      
        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnClose3MouseEntered

    private void btnClose3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MouseExited
      
        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnClose3MouseExited

    private void btnClose3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MousePressed
      
        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnClose3MousePressed

    private void btnClose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose3ActionPerformed
   
        payoutDialog.setVisible(false);
        payoutDialog.dispose();
    }//GEN-LAST:event_btnClose3ActionPerformed

    private void payoutDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payoutDialogMousePressed
     
         MouseCoordinates3 = evt.getPoint();
    }//GEN-LAST:event_payoutDialogMousePressed

    private void payoutDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payoutDialogMouseDragged
    
           Point currentCoords = evt.getLocationOnScreen();
              payoutDialog.setLocation(currentCoords.x - MouseCoordinates3.x, currentCoords.y - MouseCoordinates3.y);
    }//GEN-LAST:event_payoutDialogMouseDragged

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
     
        dgReceipt.setVisible(false);
        dgReceipt.dispose ();
    }//GEN-LAST:event_btnOkActionPerformed

    private void txtfUserAmountTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfUserAmountTextBoxActionPerformed
       
       double salePrice = 0;
       
        if (!listItem.isEmpty()) {
                
        double total = 0;
        if (checkDigits(txtfUserAmountTextBox.getText()) ) {
        
        for (Integer pNumber : productNumberSet) {  
           Inventory getI = itemQuantity.get(pNumber);
           updateInventory(pNumber, getI.productQuantity);
        }
        
        for (int i = 0; i != vInventory.size(); ++i) {
            Inventory in = (Inventory) (vInventory.get(i));
            total += in.productPrice * in.productQuantity;
        }
        
        double discount = total * ((float)(Discount / 100.0f) ); 
      
        salePrice = total - discount;   
        
        double Pricetax = total * ((float) (Tax / 100.0f));
        
        salePrice += Pricetax;
      
        double amountPaid = Double.parseDouble(txtfUserAmountTextBox.getText());
        
        if (salePrice <= amountPaid) {
            
        double change = amountPaid - salePrice;
        
        payoutDialog.setVisible(false);
        payoutDialog.dispose();
        
        if (ManagerWindow.lblShopName != null) {
        
        taReceipt.append(String.format("%50s",ManagerWindow.lblShopName.getText()) + "\n");
        taReceipt.append(String.format ("%50s", ManagerWindow.lblDescription.getText()) + "\n");
        taReceipt.append(String.format ("%50s", ManagerWindow.lblAddress.getText()) + "\n");
        taReceipt.append("                                      " + ManagerWindow.lblDate.getText() + "\n");
        taReceipt.append("                                      " + ManagerWindow.lblTime.getText() + "\n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        taReceipt.append("           Product No   Product Name    Product Price \n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        
        } else {
        taReceipt.append(String.format("%50s",EmployeeWindow.lblShopName.getText()) + "\n");
        taReceipt.append(String.format ("%50s", EmployeeWindow.lblDescription.getText()) + "\n");
        taReceipt.append(String.format ("%50s", EmployeeWindow.lblAddress.getText()) + "\n");
        taReceipt.append("                                      " + EmployeeWindow.lblDate.getText() + "\n");
        taReceipt.append("                                      " + EmployeeWindow.lblTime.getText() + "\n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        taReceipt.append("           Product No   Product Name    Product Price \n");
        taReceipt.append("   ------------------------------------------------------------------------\n");
        }
        
        for (int productCount = 0; productCount != this.vInventory.size(); ++productCount) {
            Inventory getProductInfo = (Inventory)vInventory.get(productCount);
            taReceipt.append (String.format ("%20s %20s %20.6s", getProductInfo.productNumber, getProductInfo.productName, Double.toString(getProductInfo.productPrice)) +  "\n");
        }
        
        taReceipt.append ("   ------------------------------------------------------------------------\n");
        taReceipt.append ("                             total price: " + String.format ("%1.6s", Double.toString(salePrice)) + "\n");
        taReceipt.append ("                             change: " + String.format ("%1.6s", Double.toString(change)) + "\n");
        taReceipt.append ("                             tax: " + Integer.toString (Tax) + "%\n");
        taReceipt.append ("                             discount: " + Integer.toString (Discount) + "%\n");
        taReceipt.append ("                             Money: " + spExchangeFrom.getValue().toString());
        
        
        dgReceipt.pack ();
        dgReceipt.setVisible(true);
        
        lblAmount.setText("Amount: " + String.format("%1.6s", Double.toString(amountPaid)));
        lblChange.setText("Change: " + String.format("%1.6s",Double.toString(change)) );
        lblMoney.setText("Money: " + spExchangeFrom.getValue().toString());
        
        productNumberSet.clear();
        itemQuantity.clear();
        vInventory.clear();
        listItem.clear();
        
        lblProductNo.setText("Product No: ");
        lblProductName.setText("");
        lblProductPrice.setText("Price: ");
        lblQuantityValue.setText("");
        txtfUserTextBox.setText("");
        this.lblMaxQuantity.setText("");
        
        lblProductImage.setIcon(null);
        lblProductImage.setToolTipText("");
        
        getInventory.productNumber = "";
        getInventory.productPrice = 0.0;
        getInventory.productQuantity = 0;
       
        if (!ifPaid)
           ifPaid = true;
        
        if (!ifNext)
            ifNext = true;
        
        itemList.setModel(listItem);
       
        } else {
            JOptionPane.showMessageDialog(this, "insufficient amount", "payout", JOptionPane.ERROR_MESSAGE);
        }
        } else {
                JOptionPane.showMessageDialog (this, "digits only", "payout", JOptionPane.ERROR_MESSAGE);
        }
        } 
    }//GEN-LAST:event_txtfUserAmountTextBoxActionPerformed

    private void btnConvertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConvertMouseEntered

        btnConvert.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnConvertMouseEntered

    private void btnConvertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConvertMouseExited

        btnConvert.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnConvertMouseExited

    private void btnConvertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConvertMousePressed
  
        btnConvert.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnConvertMousePressed

    private void btnConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseEntered
     
        btnConfirm.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnConfirmMouseEntered

    private void btnConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseExited
    
        btnConfirm.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnConfirmMouseExited

    private void btnConfirmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMousePressed
     
        btnConfirm.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnConfirmMousePressed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
    
        btnCancel.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed
    
        btnCancel.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnCancelMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnBackSpace;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnClose3;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnConvert;
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnEight;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFive;
    private javax.swing.JButton btnFour;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNine;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnOne;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnQuantity;
    private javax.swing.JButton btnRemoveItem;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton btnSelectCancel;
    private javax.swing.JButton btnSelectSearch;
    private javax.swing.JButton btnSeven;
    private javax.swing.JButton btnSix;
    private javax.swing.JButton btnTax;
    private javax.swing.JButton btnThree;
    private javax.swing.JButton btnTwo;
    private javax.swing.JButton btnZero;
    private javax.swing.JComboBox<String> cbColumnBy;
    private javax.swing.JComboBox<String> cbNumRows;
    private javax.swing.JComboBox<String> cbSortBy;
    private javax.swing.JDialog dgBrowseProduct;
    private javax.swing.JDialog dgReceipt;
    private javax.swing.JList<String> itemList;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblMaxQuantity;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblProductImage;
    private javax.swing.JLabel lblProductMeasureUnitAndNumber;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblProductNo;
    private javax.swing.JLabel lblProductPrice;
    private javax.swing.JLabel lblProductQuantity;
    private javax.swing.JLabel lblProductSelectImage;
    private javax.swing.JLabel lblQuantityValue;
    private javax.swing.JLabel lblTax;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JDialog payoutDialog;
    private javax.swing.JSpinner spExchangeFrom;
    private javax.swing.JSpinner spExchangeTo;
    private javax.swing.JTextArea taReceipt;
    private javax.swing.JTable tblProductSelect;
    private javax.swing.JTextField txtfProductSearch;
    private javax.swing.JTextField txtfUserAmountTextBox;
    private javax.swing.JTextField txtfUserTextBox;
    // End of variables declaration//GEN-END:variables
}
