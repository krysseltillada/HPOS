/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSoft.UI;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gina
 */
public class InventoryPanel extends javax.swing.JPanel {
    Point MouseCoordinates;
    Point MouseCoordinates2;
    Point MouseCoordinates3;
    Point MouseCoordinates4;
    
    File getFileLocation;
    String imageUserLocation = "";
    String prevProductNumber = "";
    
    String prevProductName = "";
    String prevProductDescription = "";
    String prevProductQuantity = "";
    String prevProductPrice = "";
    String prevProductNumber1 = "";
    String prevImageLocation = "";
    
    String Column = "";
    String sortBy = "";
    
    int showNumRows = 0;
    
    /**
     * Creates new form InventoryPanel
     */
    public InventoryPanel() {
       
        
        initComponents();
        
        cbColumn.setEditor (new ComboBoxUI ());
        cbColumn.setEditable (true);
        
        cbSortBy.setEditor (new ComboBoxUI ());
        cbSortBy.setEditable (true);
        
        cbNumRows.setEditor (new ComboBoxUI ());
        cbNumRows.setEditable (true);
        
        Column = cbColumn.getSelectedItem().toString();
        sortBy = cbSortBy.getSelectedItem().toString();
        
        dgAddProduct.getContentPane().setBackground (new Color (35, 35, 35));
        dgModifyProduct.getContentPane().setBackground (new Color (35, 35, 35));
        
        dgAddProduct.setUndecorated (true); 
        
        dgModifyProduct.setUndecorated (true);
        
        imageAddDialog.getContentPane().setBackground(new Color (35, 35, 35));
       
        imageAddDialog.setUndecorated(true);
        
        imageModifyDialog.getContentPane().setBackground (new Color (35, 35, 35));
        
        imageModifyDialog.setUndecorated (true);
        
        
        if (cbNumRows.getSelectedItem().toString().equals("all")) {
            showNumRows = 0;
        } else {
         showNumRows = Integer.parseInt(cbNumRows.getSelectedItem().toString());
        }
        
        updateTableInventory();
        updateTableGroupBy();
        
    }
    
    public void updateTableInventory () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        DefaultTableModel tableModel = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        tableModel.addColumn("product number");
        tableModel.addColumn("product name");
        tableModel.addColumn("product quantity");
        tableModel.addColumn("product description");
        tableModel.addColumn("product price");
        tableModel.addColumn("product image");
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement ();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLINVENTORY");
            
            for (; sqlResult.next() ;) {
                tableModel.addRow(new String [] {sqlResult.getString("PRODUCTNUMBER"),
                                                 sqlResult.getString("PRODUCTNAME"),
                                                 sqlResult.getString("PRODUCTQUANTITY"),
                                                 sqlResult.getString("PRODUCTDESCRIPTION"),
                                                 sqlResult.getString("PRODUCTPRICE")});
                
                  ImageIcon image = null;
                         Blob photo = sqlResult.getBlob("PRODUCTIMAGE");
                         ObjectInputStream ois = null;
                        try {
                            if (photo != null) {
                               ois = new ObjectInputStream(photo.getBinaryStream());
                            
                               try {
                                          
                                   
                                image = new ImageIcon( ((ImageIcon) ois.readObject()).getImage().getScaledInstance(148, 101, Image.SCALE_DEFAULT) );
                               
                                 } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                File getFileLocation = new File("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                
                                //image = new ImageIcon("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                image = new ImageIcon ( new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(148 ,101, Image.SCALE_DEFAULT) );
                                        
                               
                            }
                           
                        } catch (IOException ex) {
                            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        
                        tableModel.setValueAt(image, tableModel.getRowCount() - 1, tableModel.getColumnCount() - 1);
             
                    
            }
            
            System.out.println ("updated");
            
            tblInventory.setModel(tableModel);
            
            tblInventory.getColumnModel().getColumn(5).setCellRenderer(tblInventory.getDefaultRenderer(ImageIcon.class));
            
            tblInventory.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
            tblInventory.getTableHeader().setForeground(new Color(0,204,204));
            tblInventory.getTableHeader().setBackground(Color.BLACK);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null)
                    conn.close ();
                
                if (sqlStatement != null)
                    sqlStatement.close ();
                
                if (sqlResult != null)
                    sqlResult.close ();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        dgAddProduct = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblProductAddImage = new javax.swing.JLabel();
        txtfImageLocation = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taProductDescription = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtfProductName = new javax.swing.JTextField();
        txtfProductQuantity = new javax.swing.JTextField();
        txtfProductPrice = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtfProductNumber = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        btnClose3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        imageAddDialog = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        lblAddImage = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtfImageLocation2 = new javax.swing.JTextField();
        btnCancel1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnClose = new javax.swing.JButton();
        imageFileChooser = new javax.swing.JFileChooser();
        dgModifyProduct = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblProductModifyImage1 = new javax.swing.JLabel();
        txtfImageLocation1 = new javax.swing.JTextField();
        btnUpload1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taProductDescription1 = new javax.swing.JTextArea();
        btnModify = new javax.swing.JButton();
        btnCancel2 = new javax.swing.JButton();
        txtfProductNumber1 = new javax.swing.JTextField();
        txtfProductName1 = new javax.swing.JTextField();
        txtfProductQuantity1 = new javax.swing.JTextField();
        txtfProductPrice1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        btnClose4 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        imageModifyDialog = new javax.swing.JDialog();
        jLabel20 = new javax.swing.JLabel();
        lblModifyImage = new javax.swing.JLabel();
        btnModifyBrowse = new javax.swing.JButton();
        btnModifySave = new javax.swing.JButton();
        txtfModifyImageLocation = new javax.swing.JTextField();
        btnModifyCancel = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnClose2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnModifyProduct = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtfUserSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbColumn = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbSortBy = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbNumRows = new javax.swing.JComboBox<>();

        dgAddProduct.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dgAddProductMouseDragged(evt);
            }
        });
        dgAddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgAddProductMousePressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(235, 235, 235));
        jLabel7.setText("Add Product");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(35, 35, 35));

        lblProductAddImage.setToolTipText("");

        txtfImageLocation.setEditable(false);
        txtfImageLocation.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfImageLocation.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnUpload.setBackground(new java.awt.Color(51, 51, 51));
        btnUpload.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnUpload.setForeground(new java.awt.Color(235, 235, 235));
        btnUpload.setText("Upload");
        btnUpload.setContentAreaFilled(false);
        btnUpload.setFocusable(false);
        btnUpload.setOpaque(true);
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

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(235, 235, 235));
        jLabel9.setText("Product Name ");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(235, 235, 235));
        jLabel10.setText("Product Quantity ");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(235, 235, 235));
        jLabel11.setText("Product Price");

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(235, 235, 235));
        jLabel13.setText("Description");

        taProductDescription.setBackground(new java.awt.Color(35, 35, 35));
        taProductDescription.setColumns(20);
        taProductDescription.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        taProductDescription.setForeground(new java.awt.Color(235, 235, 235));
        taProductDescription.setRows(5);
        taProductDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        jScrollPane2.setViewportView(taProductDescription);

        btnAdd.setBackground(new java.awt.Color(51, 51, 51));
        btnAdd.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(235, 235, 235));
        btnAdd.setText("Add");
        btnAdd.setContentAreaFilled(false);
        btnAdd.setFocusable(false);
        btnAdd.setOpaque(true);
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        txtfProductName.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductName.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductName.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfProductQuantity.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductQuantity.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductQuantity.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductQuantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfProductPrice.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductPrice.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductPrice.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(235, 235, 235));
        jLabel12.setText("Product Number");

        txtfProductNumber.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductNumber.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductNumber.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblProductAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfProductName)
                            .addComponent(txtfProductNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtfProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtfProductPrice)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtfImageLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtfProductNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtfProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtfProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblProductAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpload)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtfImageLocation)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(35, 35, 35));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        jSeparator3.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator3.setForeground(new java.awt.Color(35, 35, 35));

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

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(235, 235, 235));
        jLabel21.setText("Add Product");

        javax.swing.GroupLayout dgAddProductLayout = new javax.swing.GroupLayout(dgAddProduct.getContentPane());
        dgAddProduct.getContentPane().setLayout(dgAddProductLayout);
        dgAddProductLayout.setHorizontalGroup(
            dgAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(dgAddProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dgAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dgAddProductLayout.createSequentialGroup()
                        .addGroup(dgAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(dgAddProductLayout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgAddProductLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(238, 238, 238)
                        .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        dgAddProductLayout.setVerticalGroup(
            dgAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgAddProductLayout.createSequentialGroup()
                .addGroup(dgAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dgAddProductLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dgAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imageAddDialog.setBackground(new java.awt.Color(35, 35, 35));
        imageAddDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                imageAddDialogMouseDragged(evt);
            }
        });
        imageAddDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageAddDialogMousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(235, 235, 235));
        jLabel14.setText("Upload an image");

        lblAddImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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

        btnSave.setBackground(new java.awt.Color(51, 51, 51));
        btnSave.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSave.setForeground(new java.awt.Color(235, 235, 235));
        btnSave.setText("Save");
        btnSave.setContentAreaFilled(false);
        btnSave.setFocusable(false);
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

        txtfImageLocation2.setEditable(false);
        txtfImageLocation2.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfImageLocation2.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        txtfImageLocation2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnCancel1.setBackground(new java.awt.Color(51, 51, 51));
        btnCancel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnCancel1.setForeground(new java.awt.Color(235, 235, 235));
        btnCancel1.setText("cancel");
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

        javax.swing.GroupLayout imageAddDialogLayout = new javax.swing.GroupLayout(imageAddDialog.getContentPane());
        imageAddDialog.getContentPane().setLayout(imageAddDialogLayout);
        imageAddDialogLayout.setHorizontalGroup(
            imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(imageAddDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imageAddDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(imageAddDialogLayout.createSequentialGroup()
                        .addComponent(txtfImageLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(imageAddDialogLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(imageAddDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imageAddDialogLayout.setVerticalGroup(
            imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageAddDialogLayout.createSequentialGroup()
                .addGroup(imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imageAddDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowse)
                    .addComponent(txtfImageLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(imageAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel1))
                .addContainerGap())
        );

        dgModifyProduct.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dgModifyProductMouseDragged(evt);
            }
        });
        dgModifyProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgModifyProductMousePressed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(35, 35, 35));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(235, 235, 235));
        jLabel8.setText("Modify Product");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(35, 35, 35));

        lblProductModifyImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/220px-Ivan_III_of_Russia_3.jpg"))); // NOI18N
        lblProductModifyImage1.setText("jLabel8");

        txtfImageLocation1.setEditable(false);
        txtfImageLocation1.setBackground(new java.awt.Color(35, 35, 35));
        txtfImageLocation1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfImageLocation1.setForeground(new java.awt.Color(235, 235, 235));
        txtfImageLocation1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnUpload1.setBackground(new java.awt.Color(51, 51, 51));
        btnUpload1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnUpload1.setForeground(new java.awt.Color(235, 235, 235));
        btnUpload1.setText("Upload");
        btnUpload1.setContentAreaFilled(false);
        btnUpload1.setFocusable(false);
        btnUpload1.setOpaque(true);
        btnUpload1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpload1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpload1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpload1MousePressed(evt);
            }
        });
        btnUpload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpload1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(235, 235, 235));
        jLabel15.setText("Product Name ");

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(235, 235, 235));
        jLabel16.setText("Product Quantity ");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(235, 235, 235));
        jLabel17.setText("Product Price");

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 21)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(235, 235, 235));
        jLabel18.setText("Product Number");

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(235, 235, 235));
        jLabel19.setText("Description");

        taProductDescription1.setBackground(new java.awt.Color(35, 35, 35));
        taProductDescription1.setColumns(20);
        taProductDescription1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        taProductDescription1.setForeground(new java.awt.Color(235, 235, 235));
        taProductDescription1.setRows(5);
        taProductDescription1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));
        jScrollPane3.setViewportView(taProductDescription1);

        btnModify.setBackground(new java.awt.Color(51, 51, 51));
        btnModify.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnModify.setForeground(new java.awt.Color(235, 235, 235));
        btnModify.setText("Modify");
        btnModify.setContentAreaFilled(false);
        btnModify.setFocusable(false);
        btnModify.setOpaque(true);
        btnModify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModifyMousePressed(evt);
            }
        });
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

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

        txtfProductNumber1.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductNumber1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductNumber1.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductNumber1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfProductName1.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductName1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductName1.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductName1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfProductQuantity1.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductQuantity1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductQuantity1.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductQuantity1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        txtfProductPrice1.setBackground(new java.awt.Color(35, 35, 35));
        txtfProductPrice1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfProductPrice1.setForeground(new java.awt.Color(235, 235, 235));
        txtfProductPrice1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblProductModifyImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfProductPrice1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(txtfProductQuantity1)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfProductNumber1)
                                    .addComponent(txtfProductName1)))))
                    .addComponent(jLabel19)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtfImageLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtfProductNumber1)
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfProductName1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtfProductQuantity1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(txtfProductPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblProductModifyImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpload1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfImageLocation1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModify)
                    .addComponent(btnCancel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(35, 35, 35));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));

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

        jSeparator4.setBackground(new java.awt.Color(80, 137, 182));
        jSeparator4.setForeground(new java.awt.Color(51, 51, 51));

        btnClose4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HSoft/UI/CloseIconNormal.png"))); // NOI18N
        btnClose4.setContentAreaFilled(false);
        btnClose4.setFocusable(false);
        btnClose4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClose4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClose4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClose4MousePressed(evt);
            }
        });
        btnClose4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose4ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(235, 235, 235));
        jLabel22.setText("Modify Product");

        javax.swing.GroupLayout dgModifyProductLayout = new javax.swing.GroupLayout(dgModifyProduct.getContentPane());
        dgModifyProduct.getContentPane().setLayout(dgModifyProductLayout);
        dgModifyProductLayout.setHorizontalGroup(
            dgModifyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgModifyProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dgModifyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dgModifyProductLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgModifyProductLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(230, 230, 230)
                .addComponent(btnClose4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator4)
        );
        dgModifyProductLayout.setVerticalGroup(
            dgModifyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgModifyProductLayout.createSequentialGroup()
                .addGroup(dgModifyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dgModifyProductLayout.createSequentialGroup()
                        .addComponent(btnClose4)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dgModifyProductLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dgModifyProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imageModifyDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                imageModifyDialogMouseDragged(evt);
            }
        });
        imageModifyDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageModifyDialogMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(235, 235, 235));
        jLabel20.setText("Upload an image");

        btnModifyBrowse.setBackground(new java.awt.Color(51, 51, 51));
        btnModifyBrowse.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnModifyBrowse.setForeground(new java.awt.Color(235, 235, 235));
        btnModifyBrowse.setText("Browse");
        btnModifyBrowse.setContentAreaFilled(false);
        btnModifyBrowse.setFocusable(false);
        btnModifyBrowse.setOpaque(true);
        btnModifyBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifyBrowseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifyBrowseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModifyBrowseMousePressed(evt);
            }
        });
        btnModifyBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyBrowseActionPerformed(evt);
            }
        });

        btnModifySave.setBackground(new java.awt.Color(51, 51, 51));
        btnModifySave.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnModifySave.setForeground(new java.awt.Color(235, 235, 235));
        btnModifySave.setText("Save");
        btnModifySave.setContentAreaFilled(false);
        btnModifySave.setFocusable(false);
        btnModifySave.setOpaque(true);
        btnModifySave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifySaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifySaveMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModifySaveMousePressed(evt);
            }
        });
        btnModifySave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifySaveActionPerformed(evt);
            }
        });

        txtfModifyImageLocation.setEditable(false);
        txtfModifyImageLocation.setBackground(new java.awt.Color(35, 35, 35));
        txtfModifyImageLocation.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfModifyImageLocation.setForeground(new java.awt.Color(235, 235, 235));
        txtfModifyImageLocation.setText(".jpg, .png, .gif only");
        txtfModifyImageLocation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnModifyCancel.setBackground(new java.awt.Color(51, 51, 51));
        btnModifyCancel.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnModifyCancel.setForeground(new java.awt.Color(235, 235, 235));
        btnModifyCancel.setText("Cancel");
        btnModifyCancel.setContentAreaFilled(false);
        btnModifyCancel.setFocusable(false);
        btnModifyCancel.setOpaque(true);
        btnModifyCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifyCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifyCancelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModifyCancelMousePressed(evt);
            }
        });
        btnModifyCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyCancelActionPerformed(evt);
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

        javax.swing.GroupLayout imageModifyDialogLayout = new javax.swing.GroupLayout(imageModifyDialog.getContentPane());
        imageModifyDialog.getContentPane().setLayout(imageModifyDialogLayout);
        imageModifyDialogLayout.setHorizontalGroup(
            imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageModifyDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imageModifyDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageModifyDialogLayout.createSequentialGroup()
                                .addComponent(txtfModifyImageLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(imageModifyDialogLayout.createSequentialGroup()
                                .addComponent(btnModifySave, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))
                        .addGroup(imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnModifyBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModifyCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(imageModifyDialogLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(imageModifyDialogLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblModifyImage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        imageModifyDialogLayout.setVerticalGroup(
            imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageModifyDialogLayout.createSequentialGroup()
                .addGroup(imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imageModifyDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20))
                    .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblModifyImage, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(imageModifyDialogLayout.createSequentialGroup()
                        .addComponent(btnModifyBrowse)
                        .addGap(7, 7, 7))
                    .addGroup(imageModifyDialogLayout.createSequentialGroup()
                        .addComponent(txtfModifyImageLocation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(imageModifyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModifySave)
                    .addComponent(btnModifyCancel))
                .addContainerGap())
        );

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList1);

        setBackground(new java.awt.Color(51, 51, 51));

        btnModifyProduct.setBackground(new java.awt.Color(35, 35, 35));
        btnModifyProduct.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnModifyProduct.setForeground(new java.awt.Color(235, 235, 235));
        btnModifyProduct.setText("Modify Product");
        btnModifyProduct.setContentAreaFilled(false);
        btnModifyProduct.setFocusable(false);
        btnModifyProduct.setOpaque(true);
        btnModifyProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModifyProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModifyProductMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModifyProductMousePressed(evt);
            }
        });
        btnModifyProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyProductActionPerformed(evt);
            }
        });

        btnDeleteAll.setBackground(new java.awt.Color(35, 35, 35));
        btnDeleteAll.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDeleteAll.setForeground(new java.awt.Color(235, 235, 235));
        btnDeleteAll.setText("Delete All");
        btnDeleteAll.setContentAreaFilled(false);
        btnDeleteAll.setFocusable(false);
        btnDeleteAll.setOpaque(true);
        btnDeleteAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteAllMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteAllMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteAllMousePressed(evt);
            }
        });
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllActionPerformed(evt);
            }
        });

        btnAddProduct.setBackground(new java.awt.Color(35, 35, 35));
        btnAddProduct.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(235, 235, 235));
        btnAddProduct.setText("Add Product");
        btnAddProduct.setContentAreaFilled(false);
        btnAddProduct.setFocusable(false);
        btnAddProduct.setOpaque(true);
        btnAddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddProductMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddProductMousePressed(evt);
            }
        });
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnDeleteProduct.setBackground(new java.awt.Color(35, 35, 35));
        btnDeleteProduct.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDeleteProduct.setForeground(new java.awt.Color(235, 235, 235));
        btnDeleteProduct.setText("Delete Product");
        btnDeleteProduct.setContentAreaFilled(false);
        btnDeleteProduct.setFocusable(false);
        btnDeleteProduct.setOpaque(true);
        btnDeleteProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteProductMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteProductMousePressed(evt);
            }
        });
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(35, 35, 35));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Inventory");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(35, 35, 35));

        tblInventory.setBackground(new java.awt.Color(35, 35, 35));
        tblInventory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        tblInventory.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tblInventory.setForeground(new java.awt.Color(235, 235, 235));
        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventory.setGridColor(new java.awt.Color(80, 137, 182));
        tblInventory.setName(""); // NOI18N
        tblInventory.setRowHeight(100);
        tblInventory.setRowMargin(0);
        tblInventory.setSelectionForeground(new java.awt.Color(255, 51, 0));
        tblInventory.setShowHorizontalLines(false);
        tblInventory.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblInventoryInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventory);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(35, 35, 35));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Search");

        txtfUserSearch.setBackground(new java.awt.Color(35, 35, 35));
        txtfUserSearch.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtfUserSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfUserSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnSearch.setBackground(new java.awt.Color(51, 51, 51));
        btnSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(235, 235, 235));
        btnSearch.setText("Search");
        btnSearch.setContentAreaFilled(false);
        btnSearch.setFocusable(false);
        btnSearch.setOpaque(true);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfUserSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfUserSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(35, 35, 35));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Group by");

        cbColumn.setBackground(new java.awt.Color(35, 35, 35));
        cbColumn.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        cbColumn.setForeground(new java.awt.Color(235, 235, 235));
        cbColumn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product No", "Product Name", "Product Quantity", "Product Price" }));
        cbColumn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbColumn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbColumnItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 235, 235));
        jLabel4.setText("Column");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("Sort by");

        cbSortBy.setBackground(new java.awt.Color(35, 35, 35));
        cbSortBy.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        cbSortBy.setForeground(new java.awt.Color(235, 235, 235));
        cbSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbSortBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSortByItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 235, 235));
        jLabel6.setText("no of rows");

        cbNumRows.setBackground(new java.awt.Color(35, 35, 35));
        cbNumRows.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        cbNumRows.setForeground(new java.awt.Color(235, 235, 235));
        cbNumRows.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all", "1", "2", "3", "4", "5", "6", "7", "8", "9", " " }));
        cbNumRows.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbNumRows.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNumRowsItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbSortBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbColumn, 0, 134, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNumRows, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbNumRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModifyProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(btnDeleteProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModifyProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblInventoryInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblInventoryInputMethodTextChanged
        // TODO add your handling code here:
        updateTableInventory();
    }//GEN-LAST:event_tblInventoryInputMethodTextChanged

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        // TODO add your handling code here:
        
        dgAddProduct.pack();
        dgAddProduct.setLocationRelativeTo(null);
        dgAddProduct.setVisible(true);
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        txtfProductNumber.setText("");
        txtfProductName.setText("");
        txtfProductQuantity.setText("");
        txtfProductPrice.setText("");
        taProductDescription.setText("");
        txtfImageLocation.setText("");
        
        
        lblProductAddImage.setIcon(null);
                    
        
        dgAddProduct.setVisible(false);
        dgAddProduct.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        imageAddDialog.setLocationRelativeTo(null);
        imageAddDialog.pack();
        imageAddDialog.setVisible(true);
        
    }//GEN-LAST:event_btnUploadActionPerformed

    boolean checkImageType (String getImageType, String []imageTypes) {
          for (int fileCount = 0; fileCount != imageTypes.length; ++fileCount) {
              if (imageTypes[fileCount].equals(getImageType))
                  return true;
          }
          
          return false ;
    }
    
    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        String []imageTypes = new String[]{".jpg", ".png", ".gif"};
        
    

        if (imageFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            getFileLocation = imageFileChooser.getSelectedFile();
            

            String getImageType = getFileLocation.getName();
            getImageType = getImageType.substring(getImageType.indexOf("."));

            if (checkImageType(getImageType, imageTypes)) {

                lblAddImage.setIcon( new ImageIcon(new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblAddImage.getWidth(), lblAddImage.getHeight(), Image.SCALE_DEFAULT)));
                txtfImageLocation2.setText(getFileLocation.getName());

            } else {
                        
                txtfImageLocation2.setText("");
                JOptionPane.showMessageDialog(this, "invalid file type", "upload", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "upload an image", "upload ", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (!txtfImageLocation2.getText().isEmpty()) {
           
            try {
   
            System.out.println ("image add");
            
            lblProductAddImage.setIcon(new ImageIcon (new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblProductAddImage.getWidth(), lblProductAddImage.getHeight(), Image.SCALE_DEFAULT)));
            
            txtfImageLocation.setText(txtfImageLocation2.getText());
            imageUserLocation = getFileLocation.getAbsolutePath();
        
            imageAddDialog.dispose();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(imageAddDialog, "image location not found", "upload image", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "you must upload an image", "upload image", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        imageUserLocation = "";
        
        lblAddImage.setIcon (null);
        
        imageAddDialog.setVisible(false);
        imageAddDialog.dispose();
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private boolean checkDigits (String digits) {
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
    
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        
        if ( (!txtfProductName.getText().isEmpty() ) &&
             (!taProductDescription.getText().isEmpty()) &&
                checkDigits(txtfProductNumber.getText()) &&
                checkDigits(txtfProductQuantity.getText()) &&
             (!txtfProductPrice.getText().isEmpty()) &&
              (!imageUserLocation.isEmpty())  ) {
            
            double price;
            
            try {
               price = Double.parseDouble(txtfProductPrice.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dgAddProduct, "entered wrong data in price", "Add Product", JOptionPane.ERROR_MESSAGE);
            }
            
        Connection conn = null;
        PreparedStatement sqlStatement1 = null;
        Statement sqlStatement2 = null;
        ResultSet sqlResult = null;
        
            try {
                conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                sqlResult = sqlStatement2.executeQuery("SELECT * FROM TBLINVENTORY WHERE PRODUCTNUMBER = '" + txtfProductNumber.getText() + "' OR PRODUCTNAME = '" + txtfProductName.getText() + "'" );
                
                if (sqlResult.isBeforeFirst()) {
                    for ( ;sqlResult.next(); ) {
                        
                    if (sqlResult.getString("PRODUCTNUMBER").equals(txtfProductNumber.getText())) {
                        JOptionPane.showMessageDialog (dgAddProduct, "product no " + sqlResult.getString("PRODUCTNUMBER") + " exists", "Add Product", JOptionPane.ERROR_MESSAGE);
                    }  else if (sqlResult.getString ("PRODUCTNAME").equals(txtfProductName.getText())) {
                        JOptionPane.showMessageDialog (dgAddProduct, "product name " + sqlResult.getString("PRODUCTNAME") + " exists", "Add Product", JOptionPane.ERROR_MESSAGE );
                    } else {
                        
                    JOptionPane.showMessageDialog (dgAddProduct, "product no " + sqlResult.getString("PRODUCTNUMBER") + " " + sqlResult.getString("PRODUCTNAME"), "Add Product", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    ImageIcon getImage = new ImageIcon (imageUserLocation);
                    
                    Blob imageBlob =  conn.createBlob();
                    
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(imageBlob.setBinaryStream(1));
                        oos.writeObject(getImage);
                        oos.close();
                        
                         
                        sqlStatement1 = conn.prepareStatement("INSERT INTO TBLINVENTORY (PRODUCTNUMBER, PRODUCTNAME, PRODUCTQUANTITY, PRODUCTPRICE, PRODUCTDESCRIPTION, PRODUCTIMAGE, IMAGELOCATION) " +
                                                          "VALUES (?, ?, ?, ?, ?, ?, ?)");
                    
                        sqlStatement1.setString(1,  txtfProductNumber.getText());
                        sqlStatement1.setString(2,  txtfProductName.getText());
                        sqlStatement1.setInt(3,  Integer.parseInt(txtfProductQuantity.getText()));
                        sqlStatement1.setDouble(4, Double.parseDouble(txtfProductPrice.getText()));
                        sqlStatement1.setString(5, taProductDescription.getText());
                        sqlStatement1.setBlob(6, imageBlob);
                        sqlStatement1.setString(7, imageUserLocation);
                    
                        sqlStatement1.execute();
                        
                        JOptionPane.showMessageDialog(dgAddProduct, "product " + txtfProductName.getText() + " added ", "Add Product", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        System.out.println ("done");
                    
                        updateTableInventory ();
                    
                        txtfProductNumber.setText("");
                        txtfProductName.setText("");
                        txtfProductQuantity.setText("");
                        txtfProductPrice.setText("");
                        taProductDescription.setText("");
                        txtfImageLocation.setText("");
                        lblAddImage.setIcon(null);
                        txtfImageLocation2.setText(".jpg, .png, .gif only");
                    
                        lblProductAddImage.setIcon(new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\HSoft\\UI\\Unknown_License.png"));
                    
                        dgAddProduct.setVisible(false);
                        dgAddProduct.dispose();
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog (dgAddProduct, "invalid image", "Add Product", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                   
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (conn != null)
                        conn.close ();
                    
                    if (sqlStatement1 != null)
                        sqlStatement1.close();
                    
                    if (sqlStatement2 != null)
                        sqlStatement2.close();
                    
                    if (sqlResult != null)
                        sqlResult.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        } else {
           if (txtfProductName.getText().isEmpty()  &&
               taProductDescription.getText().isEmpty() &&
               checkDigits(txtfProductNumber.getText()) &&
               checkDigits(txtfProductQuantity.getText()) &&
               txtfProductPrice.getText().isEmpty()) {
               JOptionPane.showMessageDialog (dgAddProduct, "must fill in the required fields", "Add Product", JOptionPane.ERROR_MESSAGE);
           } else if (txtfProductName.getText().isEmpty()) {
               JOptionPane.showMessageDialog (dgAddProduct, "enter the product name", "Add Product", JOptionPane.ERROR_MESSAGE);
           } else if (txtfProductNumber.getText().isEmpty()) {
               JOptionPane.showMessageDialog (dgAddProduct, "enter the product number", "Add Product", JOptionPane.ERROR_MESSAGE);
           } else if (!checkDigits(txtfProductNumber.getText())) {
               JOptionPane.showMessageDialog (dgAddProduct, "digits only when entering a product number", "Add Product", JOptionPane.ERROR_MESSAGE);
           } else if (!checkDigits(txtfProductQuantity.getText())) {
               JOptionPane.showMessageDialog (dgAddProduct, "digits only when entering a product quantity", "Add Product", JOptionPane.ERROR_MESSAGE);
           } else if (imageUserLocation.isEmpty()) {
               JOptionPane.showMessageDialog (dgAddProduct, "upload an image first", "Add Product", JOptionPane.ERROR_MESSAGE);
           }
           else {
               JOptionPane.showMessageDialog (dgAddProduct, "enter the product description", "Add Product", JOptionPane.ERROR_MESSAGE);
           }
                
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnModifyProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyProductActionPerformed
        // TODO add your handling code here:
        
        int getIndexRow = tblInventory.getSelectedRow();  
        
        if ( getIndexRow >= 0 && getIndexRow <= tblInventory.getRowCount()) {
            
            dgModifyProduct.pack();
            dgModifyProduct.setLocationRelativeTo(null);
            dgModifyProduct.setVisible(true);
            
            Connection conn = null;
            Statement sqlStatement = null;
            ResultSet sqlResult = null;
            
            String getProductNumber = (String)( tblInventory.getModel().getValueAt(getIndexRow, 0));
            
            prevProductNumber = getProductNumber;
            
            try {
                conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement = conn.createStatement();
                sqlResult = sqlStatement.executeQuery("SELECT PRODUCTNUMBER, PRODUCTNAME, PRODUCTQUANTITY, PRODUCTPRICE, PRODUCTIMAGE, PRODUCTDESCRIPTION, IMAGELOCATION " +
                                                      "FROM TBLINVENTORY WHERE PRODUCTNUMBER = '" + getProductNumber + "'");
                
                for (; sqlResult.next() ;) {
                    
                    
                    
                    txtfProductNumber1.setText(sqlResult.getString ("PRODUCTNUMBER"));
                    txtfProductName1.setText(sqlResult.getString ("PRODUCTNAME"));
                    txtfProductQuantity1.setText(sqlResult.getString ("PRODUCTQUANTITY"));
                    txtfProductPrice1.setText(sqlResult.getString("PRODUCTPRICE"));
                    taProductDescription1.setText(sqlResult.getString("PRODUCTDESCRIPTION"));
                    
                    
                    File getFileName = new File (sqlResult.getString("IMAGELOCATION"));
                    
                    txtfImageLocation1.setText(getFileName.getName());
                    
                    prevProductNumber1 = txtfProductNumber1.getText();
                    prevProductName = txtfProductName1.getText();
                    prevProductPrice = txtfProductPrice1.getText();
                    prevProductQuantity = txtfProductQuantity1.getText();
                    prevProductDescription = taProductDescription1.getText();
                    prevImageLocation = txtfImageLocation1.getText();
                    
                    imageUserLocation = getFileName.getAbsolutePath();
                    
                   
                    Blob photo = sqlResult.getBlob("PRODUCTIMAGE");

                    ObjectInputStream ois = null;
                     
                    try {
                        if (photo != null) {
                        
                        ois = new ObjectInputStream (photo.getBinaryStream());
                            try {
                            ImageIcon  image = new ImageIcon(((ImageIcon) (ois.readObject())).getImage().getScaledInstance(lblProductModifyImage1.getWidth(),
                                                                                                      lblProductModifyImage1.getHeight(),
                                                                                                      Image.SCALE_DEFAULT));
                                
                            
                            
                            lblProductModifyImage1.setIcon (image);
                             
                              
                                
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        } else { 
                           ImageIcon  image = new ImageIcon ("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                               lblProductModifyImage1.setIcon (image);
                          
                               
                        }
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (conn != null)
                        conn.close ();
                    
                    if (sqlStatement != null)
                        sqlStatement.close ();
                    
                    if (sqlResult != null)
                        sqlResult.close ();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Select a product first", "modify product", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnModifyProductActionPerformed

    private void btnUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload1ActionPerformed
        // TODO add your handling code here:
        imageModifyDialog.pack();
        imageModifyDialog.setLocationRelativeTo(null);
        imageModifyDialog.setVisible(true);
        
    }//GEN-LAST:event_btnUpload1ActionPerformed

    private boolean checkIfDataChange () {
        if ( (txtfProductName1.getText().equals(prevProductName)) &&
             (txtfProductNumber1.getText().equals(prevProductNumber1)) &&
             (txtfProductPrice1.getText().equals(prevProductPrice)) &&
              (txtfProductQuantity1.getText().equals(prevProductQuantity)) &&
                (taProductDescription1.getText().equals(prevProductDescription)) &&
                (txtfImageLocation1.getText().equals(prevImageLocation) )  )    {
        
            
            return false; 
        }
        
        System.out.println (txtfProductName1.getText() + " " + prevProductName);
   
        
        return true;
    }
    
    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        // TODO add your handling code here:
        if (checkIfDataChange()) {
        
        if ( (!txtfProductName1.getText().isEmpty() ) &&
             (!taProductDescription1.getText().isEmpty()) &&
                checkDigits(txtfProductNumber1.getText()) &&
                checkDigits(txtfProductQuantity1.getText()) &&
             (!txtfProductPrice1.getText().isEmpty()) &&
              (!imageUserLocation.isEmpty())  ) {
              
            Connection conn = null;
            PreparedStatement sqlStatement = null;
            ResultSet sqlResult = null;
            
            try {
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement = conn.prepareStatement("UPDATE TBLINVENTORY SET " +
                                                     "PRODUCTNUMBER = ?, PRODUCTNAME = ?, PRODUCTQUANTITY = ?, PRODUCTPRICE = ?," +
                                                     "PRODUCTIMAGE = ?, PRODUCTDESCRIPTION = ? WHERE PRODUCTNUMBER = '" + prevProductNumber + "'");
              
                ImageIcon image = new ImageIcon (imageUserLocation);
                
               
                Blob imageBlob = conn.createBlob();
                
                try {
                    
                    if (image != null) {
                    
                    ObjectOutputStream oos = new ObjectOutputStream(imageBlob.setBinaryStream(1));
                    oos.writeObject(image);
                    oos.close();
                    
                    sqlStatement.setString(1, txtfProductNumber1.getText());
                    sqlStatement.setString(2, txtfProductName1.getText());
                    sqlStatement.setInt(3, Integer.parseInt (txtfProductQuantity1.getText()) );
                    sqlStatement.setDouble(4, Double.parseDouble (txtfProductPrice1.getText()) );
                    sqlStatement.setBlob (5, imageBlob);
                    sqlStatement.setString(6, taProductDescription1.getText());
                    
                    sqlStatement.execute();
                    
                    updateTableInventory();
                    
                    JOptionPane.showMessageDialog(dgModifyProduct, "Product has been modified", "modify product", JOptionPane.INFORMATION_MESSAGE);
                    
                    dgModifyProduct.setVisible(false);
                    dgModifyProduct.dispose();
                    
                    } else {
                        JOptionPane.showMessageDialog (dgModifyProduct, "image location not found", "modify product", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }     
        else {
           if (txtfProductName1.getText().isEmpty()  &&
               taProductDescription1.getText().isEmpty() &&
               checkDigits(txtfProductNumber1.getText()) &&
               checkDigits(txtfProductQuantity1.getText()) &&
               txtfProductPrice1.getText().isEmpty()) {
               JOptionPane.showMessageDialog (dgModifyProduct, "must fill in the required fields", "Modify Product", JOptionPane.ERROR_MESSAGE);
           } else if (txtfProductName1.getText().isEmpty()) {
               JOptionPane.showMessageDialog (dgModifyProduct, "enter the product name", "Modify Product", JOptionPane.ERROR_MESSAGE);
           } else if (txtfProductNumber1.getText().isEmpty()) {
               JOptionPane.showMessageDialog (dgModifyProduct, "enter the product number", "Modify Product", JOptionPane.ERROR_MESSAGE);
           } else if (!checkDigits(txtfProductNumber1.getText())) {
               JOptionPane.showMessageDialog (dgModifyProduct, "digits only when entering a product number", "Modify Product", JOptionPane.ERROR_MESSAGE);
           } else if (!checkDigits(txtfProductQuantity1.getText())) {
               JOptionPane.showMessageDialog (dgModifyProduct, "digits only when entering a product quantity", "Modify Product", JOptionPane.ERROR_MESSAGE);
           } else if (imageUserLocation.isEmpty()) {
               JOptionPane.showMessageDialog (dgModifyProduct, "upload an image first", "Modify Product", JOptionPane.ERROR_MESSAGE);
           }
           else {
               JOptionPane.showMessageDialog (dgModifyProduct, "enter the product description", "Modify Product", JOptionPane.ERROR_MESSAGE);
           }
                
        }
        } else {
            JOptionPane.showMessageDialog (dgModifyProduct, "you didn't changed anything", "Modify Product", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
        // TODO add your handling code here:
        imageUserLocation = "";
        
        dgModifyProduct.setVisible (false);
        dgModifyProduct.dispose ();
    }//GEN-LAST:event_btnCancel2ActionPerformed

    private void btnModifyBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyBrowseActionPerformed
        // TODO add your handling code here:
        String []imageTypes = {".jpg", ".png", ".gif"};
        
        if (imageFileChooser.showOpenDialog(dgModifyProduct) == JFileChooser.APPROVE_OPTION) {
            getFileLocation = imageFileChooser.getSelectedFile();
            
            String getImageType = getFileLocation.getName().substring(getFileLocation.getName().indexOf("."));
            
            if (checkImageType(getImageType, imageTypes)) {
                lblModifyImage.setIcon( new ImageIcon (new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(lblModifyImage.getWidth(), 
                                                                                                                                  lblModifyImage.getHeight(),
                                                                                                                                  Image.SCALE_DEFAULT)));
                txtfModifyImageLocation.setText(getFileLocation.getName());
                
            } else {
                txtfModifyImageLocation.setText("");
                JOptionPane.showMessageDialog(imageModifyDialog, "invalid image type", "Modify Product", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog (imageModifyDialog, "upload an image", "Modify Product", JOptionPane.ERROR_MESSAGE);
        }
        
        
       
    }//GEN-LAST:event_btnModifyBrowseActionPerformed

    private void btnModifySaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifySaveActionPerformed
        // TODO add your handling code here:
        if (!txtfModifyImageLocation.getText().isEmpty()) {
            
            try {
                
                txtfImageLocation1.setText(txtfModifyImageLocation.getText());
                imageUserLocation = getFileLocation.getAbsolutePath();
                
                lblProductModifyImage1.setIcon( new ImageIcon(  new ImageIcon(imageUserLocation).getImage().getScaledInstance(lblProductModifyImage1.getWidth(),
                                                                                                             lblProductModifyImage1.getHeight(),
                                                                                                             Image.SCALE_DEFAULT))); 
                       
               imageModifyDialog.setVisible(false);
               imageModifyDialog.dispose ();
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog (imageModifyDialog, "image location not found", "Modify Product", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(imageModifyDialog, "upload an image", "Modify Product", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifySaveActionPerformed

    private void btnModifyCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyCancelActionPerformed
        // TODO add your handling code here:
        txtfImageLocation2.setText(".jpg, .png, .gif only");
        imageUserLocation = "";
        imageModifyDialog.dispose(); 
        lblModifyImage.setIcon(null);
    }//GEN-LAST:event_btnModifyCancelActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        // TODO add your handling code here:
        int getIndex = tblInventory.getSelectedRow();
        
        if (getIndex <= tblInventory.getRowCount() && getIndex >= 0) {
        
        String productNumber = tblInventory.getValueAt(getIndex, 0).toString();
        
        Connection conn = null;
        Statement sqlStatement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement ();
            
            sqlStatement.execute("DELETE FROM TBLINVENTORY WHERE PRODUCTNUMBER = '" + productNumber + "'");
            
            updateTableInventory();
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else {
            JOptionPane.showMessageDialog(this, "Select a product first", "Delete Product", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        // TODO add your handling code here:
        if (tblInventory.getRowCount() > 0) {
             Connection conn = null;
        Statement sqlStatement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement ();
            
            sqlStatement.execute("DELETE FROM TBLINVENTORY");
            
            
            updateTableInventory();
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else {
            JOptionPane.showMessageDialog (this, "you have no product to delete", "delete all", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement ();
            sqlResult = sqlStatement.executeQuery("SELECT PRODUCTNUMBER, PRODUCTNAME, PRODUCTQUANTITY, PRODUCTDESCRIPTION, PRODUCTPRICE, PRODUCTIMAGE FROM TBLINVENTORY WHERE PRODUCTNAME LIKE '%" + txtfUserSearch.getText() + "%' OR PRODUCTNUMBER LIKE '%" + txtfUserSearch.getText() + "%'");
         
            DefaultTableModel tableModel = new DefaultTableModel () {
                public boolean isCellEditable (int row, int column) {
                    return false;
                }
             };
            
            tableModel.addColumn("product number");
            tableModel.addColumn("product name");
            tableModel.addColumn("product quantity");
            tableModel.addColumn("product description");
            tableModel.addColumn("product price");
            tableModel.addColumn("product image");
            
            for (; sqlResult.next() ;) {
                tableModel.addRow(new String [] {sqlResult.getString("PRODUCTNUMBER"),
                                                 sqlResult.getString("PRODUCTNAME"),
                                                 sqlResult.getString("PRODUCTQUANTITY"),
                                                 sqlResult.getString("PRODUCTDESCRIPTION"),
                                                 sqlResult.getString("PRODUCTPRICE")});
                
                  ImageIcon image = null;
                         Blob photo = sqlResult.getBlob("PRODUCTIMAGE");
                         ObjectInputStream ois = null;
                        try {
                            if (photo != null) {
                               ois = new ObjectInputStream(photo.getBinaryStream());
                            
                               try {
                                    
                                image = (ImageIcon) ois.readObject();
                               
                                 
                                 } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                File getFileLocation = new File("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                
                                //image = new ImageIcon("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                image = new ImageIcon ( new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(tblInventory.getColumnModel().getColumn(0).getWidth()
                                                                                                                                     ,tblInventory.getRowHeight(), Image.SCALE_DEFAULT) );
                                        
                               
                            }
                           
                        } catch (IOException ex) {
                            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        
                        tableModel.setValueAt(image, tableModel.getRowCount() - 1, tableModel.getColumnCount() - 1);
            
            }
            
            System.out.println ("search");
            
            tblInventory.setModel(tableModel);
            
            tblInventory.getColumnModel().getColumn(5).setCellRenderer(tblInventory.getDefaultRenderer(ImageIcon.class));
            
         
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnSearchActionPerformed

    public void updateTableGroupBy () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        String sort = (cbSortBy.getSelectedItem().toString().equals("Ascending")) ? "ASC" : "DESC";
      
     
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
  
            if (Column.equals("Product Name")) {
                Column = "PRODUCTNAME";
            } else if (Column.equals("Product No")) {
                Column = "PRODUCTNUMBER";
            } else if (Column.equals("Product Quantity")) {
                Column = "PRODUCTQUANTITY";
            } else if (Column.equals("Product Price")) {
                Column = "PRODUCTPRICE";
            }
            
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLINVENTORY ORDER BY " + Column + " " + sort);
            
            
            DefaultTableModel tableModel = new DefaultTableModel () {
                public boolean isCellEditable (int row, int column) {
                    return false;
                }
            };
            
            tableModel.addColumn("product number");
            tableModel.addColumn("product name");
            tableModel.addColumn("product quantity");
            tableModel.addColumn("product description");
            tableModel.addColumn("product price");
            tableModel.addColumn("product image");
            
            if (showNumRows != 0) {
            
            for (int i = 0; i != showNumRows; ++i) {
    
                sqlResult.next();
                
                try {
                
                tableModel.addRow(new String []{sqlResult.getString("PRODUCTNUMBER"),
                                             sqlResult.getString("PRODUCTNAME"),
                                             sqlResult.getString("PRODUCTQUANTITY"),
                                             sqlResult.getString("PRODUCTDESCRIPTION"),
                                             sqlResult.getString("PRODUCTPRICE")});
                
                
                
                   ImageIcon image = null;
                         Blob photo = sqlResult.getBlob("PRODUCTIMAGE");
                         ObjectInputStream ois = null;
                        try {
                            if (photo != null) {
                               ois = new ObjectInputStream(photo.getBinaryStream());
                            
                               try {
                                    
                                image = (ImageIcon) ois.readObject();
                               
                                 
                                 } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                File getFileLocation = new File("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                
                                //image = new ImageIcon("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                image = new ImageIcon ( new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(148 ,101, Image.SCALE_DEFAULT) );
                                        
                               
                            }
                           
                        } catch (IOException ex) {
                            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         tableModel.setValueAt(image, tableModel.getRowCount() - 1, tableModel.getColumnCount() - 1);
                } catch (Exception e) {
                    break;
                }
                      
                
            }
            
            } else {
                 for (; sqlResult.next() ;) {
    
                
                
                try {
                
                tableModel.addRow(new String []{sqlResult.getString("PRODUCTNUMBER"),
                                             sqlResult.getString("PRODUCTNAME"),
                                             sqlResult.getString("PRODUCTQUANTITY"),
                                             sqlResult.getString("PRODUCTDESCRIPTION"),
                                             sqlResult.getString("PRODUCTPRICE")});
                
                
                
                   ImageIcon image = null;
                         Blob photo = sqlResult.getBlob("PRODUCTIMAGE");
                         ObjectInputStream ois = null;
                        try {
                            if (photo != null) {
                               ois = new ObjectInputStream(photo.getBinaryStream());
                            
                               try {
                                    
                                image = (ImageIcon) ois.readObject();
                               
                                 
                                 } catch (ClassNotFoundException ex) {
                                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else {
                                File getFileLocation = new File("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                
                                //image = new ImageIcon("C:\\Users\\Gina\\Downloads\\heavybreathing.jpg");
                                image = new ImageIcon ( new ImageIcon(getFileLocation.getAbsolutePath()).getImage().getScaledInstance(148 ,101, Image.SCALE_DEFAULT) );
                                        
                               
                            }
                           
                        } catch (IOException ex) {
                            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         tableModel.setValueAt(image, tableModel.getRowCount() - 1, tableModel.getColumnCount() - 1);
                } catch (Exception e) {
                    break;
                }
                      
                
            }
                
            }
            
            tblInventory.setModel(tableModel);
            
            tblInventory.getColumnModel().getColumn(5).setCellRenderer(tblInventory.getDefaultRenderer(ImageIcon.class));
            
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    private void cbColumnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbColumnItemStateChanged
        // TODO add your handling code here:
        Column = cbColumn.getSelectedItem().toString();
        updateTableGroupBy();
    }//GEN-LAST:event_cbColumnItemStateChanged

    private void cbSortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSortByItemStateChanged
        // TODO add your handling code here:
        sortBy = cbSortBy.getSelectedItem().toString();
         updateTableGroupBy();
    }//GEN-LAST:event_cbSortByItemStateChanged

    private void cbNumRowsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNumRowsItemStateChanged
        // TODO add your handling code here:
       
        if (cbNumRows.getSelectedItem().toString().equals("all")) {
            showNumRows = 0;
        } else {
           showNumRows = Integer.parseInt(cbNumRows.getSelectedItem().toString());
        }
       
         updateTableGroupBy();
    }//GEN-LAST:event_cbNumRowsItemStateChanged

    private void btnAddProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddProductMouseEntered
        // TODO add your handling code here:
        btnAddProduct.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnAddProductMouseEntered

    private void btnAddProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddProductMouseExited
        // TODO add your handling code here:
        btnAddProduct.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnAddProductMouseExited

    private void btnAddProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddProductMousePressed
        // TODO add your handling code here:
        btnAddProduct.setBackground (new Color (84, 84, 84));
    }//GEN-LAST:event_btnAddProductMousePressed

    private void btnModifyProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyProductMouseEntered
        // TODO add your handling code here:
        btnModifyProduct.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnModifyProductMouseEntered

    private void btnModifyProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyProductMouseExited
        // TODO add your handling code here:
        btnModifyProduct.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnModifyProductMouseExited

    private void btnModifyProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyProductMousePressed
        // TODO add your handling code here:
        btnModifyProduct.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnModifyProductMousePressed

    private void btnDeleteProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteProductMouseEntered
        // TODO add your handling code here:
        btnDeleteProduct.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeleteProductMouseEntered

    private void btnDeleteProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteProductMouseExited
        // TODO add your handling code here:
        btnDeleteProduct.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnDeleteProductMouseExited

    private void btnDeleteProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteProductMousePressed
        // TODO add your handling code here:
        btnDeleteProduct.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeleteProductMousePressed

    private void btnDeleteAllMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMouseEntered
        // TODO add your handling code here:
        btnDeleteAll.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeleteAllMouseEntered

    private void btnDeleteAllMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMouseExited
        // TODO add your handling code here:
        btnDeleteAll.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnDeleteAllMouseExited

    private void btnDeleteAllMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMousePressed
        // TODO add your handling code here:
        btnDeleteAll.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeleteAllMousePressed

    private void btnUploadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMouseEntered
        // TODO add your handling code here:
        btnUpload.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnUploadMouseEntered

    private void btnUploadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMouseExited
        // TODO add your handling code here:
        btnUpload.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnUploadMouseExited

    private void btnUploadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMousePressed
        // TODO add your handling code here:
        btnUpload.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnUploadMousePressed

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        // TODO add your handling code here:
        btnAdd.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        // TODO add your handling code here:
        btnAdd.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        // TODO add your handling code here:
        btnAdd.setBackground ( new Color (89, 89, 89));
    }//GEN-LAST:event_btnAddMousePressed

    private void imageAddDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAddDialogMousePressed
        // TODO add your handling code here:
        MouseCoordinates = evt.getPoint();
    }//GEN-LAST:event_imageAddDialogMousePressed

    private void imageAddDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAddDialogMouseDragged
        // TODO add your handling code here:
          Point currentCoords = evt.getLocationOnScreen();
                imageAddDialog.setLocation(currentCoords.x - MouseCoordinates.x, currentCoords.y - MouseCoordinates.y);
    }//GEN-LAST:event_imageAddDialogMouseDragged

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here
        imageAddDialog.setVisible (false);
        imageAddDialog.dispose ();
    }//GEN-LAST:event_btnCloseActionPerformed

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
        btnCancel1.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnCancel1MouseExited

    private void btnCancel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MousePressed
        // TODO add your handling code here:
        btnCancel1.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnCancel1MousePressed

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        btnSave.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        // TODO add your handling code here:
        btnSave.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
        btnSave.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnUpload1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpload1MouseEntered
        // TODO add your handling code here:
        btnUpload1.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnUpload1MouseEntered

    private void btnUpload1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpload1MouseExited
        // TODO add your handling code here:
        btnUpload1.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnUpload1MouseExited

    private void btnUpload1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpload1MousePressed
        // TODO add your handling code here:
        btnUpload1.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnUpload1MousePressed

    private void btnModifyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyMouseEntered
        // TODO add your handling code here:
        btnModify.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnModifyMouseEntered

    private void btnModifyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyMouseExited
        // TODO add your handling code here:
        btnModify.setBackground (new Color (51, 51, 51)); 
    }//GEN-LAST:event_btnModifyMouseExited

    private void btnModifyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyMousePressed
        // TODO add your handling code here:
        btnModify.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnModifyMousePressed

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

    private void imageModifyDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageModifyDialogMousePressed
        // TODO add your handling code here:
        MouseCoordinates2 = evt.getPoint();
    }//GEN-LAST:event_imageModifyDialogMousePressed

    private void imageModifyDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageModifyDialogMouseDragged
        // TODO add your handling code here:
         Point currentCoords = evt.getLocationOnScreen();
                imageModifyDialog.setLocation(currentCoords.x - MouseCoordinates2.x, currentCoords.y - MouseCoordinates2.y);
    }//GEN-LAST:event_imageModifyDialogMouseDragged

    private void btnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose2ActionPerformed
        // TODO add your handling code here:
        imageModifyDialog.setVisible(false);
        imageModifyDialog.dispose ();
    }//GEN-LAST:event_btnClose2ActionPerformed

    private void btnModifyBrowseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyBrowseMouseEntered
        // TODO add your handling code here:
        btnModifyBrowse.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnModifyBrowseMouseEntered

    private void btnModifyBrowseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyBrowseMouseExited
        // TODO add your handling code here:
        btnModifyBrowse.setBackground (new Color (51, 51, 51)); 
    }//GEN-LAST:event_btnModifyBrowseMouseExited

    private void btnModifyBrowseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyBrowseMousePressed
        // TODO add your handling code here:
        btnModifyBrowse.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnModifyBrowseMousePressed

    private void btnModifyCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyCancelMouseEntered
        // TODO add your handling code here:
        btnModifyCancel.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnModifyCancelMouseEntered

    private void btnModifyCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyCancelMouseExited
        // TODO add your handling code here:
        btnModifyCancel.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnModifyCancelMouseExited

    private void btnModifyCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifyCancelMousePressed
        // TODO add your handling code here:
        btnModifyCancel.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnModifyCancelMousePressed

    private void btnModifySaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifySaveMouseEntered
        // TODO add your handling code here:
        btnModifySave.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnModifySaveMouseEntered

    private void btnModifySaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifySaveMouseExited
        // TODO add your handling code here:
        btnModifySave.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnModifySaveMouseExited

    private void btnModifySaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifySaveMousePressed
        // TODO add your handling code here:
        btnModifySave.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnModifySaveMousePressed

    private void dgAddProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgAddProductMousePressed
        // TODO add your handling code here:
         MouseCoordinates3 = evt.getPoint();
    }//GEN-LAST:event_dgAddProductMousePressed

    private void dgAddProductMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgAddProductMouseDragged
        // TODO add your handling code here:
          Point currentCoords = evt.getLocationOnScreen();
                dgAddProduct.setLocation(currentCoords.x - MouseCoordinates3.x, currentCoords.y - MouseCoordinates3.y);
    }//GEN-LAST:event_dgAddProductMouseDragged

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed
        // TODO add your handling code here:
        btnCancel.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnCancelMousePressed

    private void btnClose3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MouseEntered
        // TODO add your handling code here:
        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnClose3MouseEntered

    private void btnClose3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MouseExited
        // TODO add your handling code here:
        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnClose3MouseExited

    private void btnClose3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose3MousePressed
        // TODO add your handling code here:
        btnClose3.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnClose3MousePressed

    private void btnClose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose3ActionPerformed
        // TODO add your handling code here:
        txtfProductNumber.setText("");
        txtfProductName.setText("");
        txtfProductQuantity.setText("");
        txtfProductPrice.setText("");
        taProductDescription.setText("");
        txtfImageLocation.setText("");
        
        lblProductAddImage.setIcon(null);
        
        dgAddProduct.setVisible (false);
        dgAddProduct.dispose ();
    }//GEN-LAST:event_btnClose3ActionPerformed

    private void btnClose4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose4MouseEntered
        // TODO add your handling code here:
        btnClose4.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconFocused.png"));
    }//GEN-LAST:event_btnClose4MouseEntered

    private void btnClose4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose4MouseExited
        // TODO add your handling code here:
         btnClose4.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconNormal.png"));
    }//GEN-LAST:event_btnClose4MouseExited

    private void btnClose4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose4MousePressed
        // TODO add your handling code here:
         btnClose4.setIcon (new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\CloseIconPressed.png"));
    }//GEN-LAST:event_btnClose4MousePressed

    private void dgModifyProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgModifyProductMousePressed
        // TODO add your handling code here:
         MouseCoordinates4 = evt.getPoint();
    }//GEN-LAST:event_dgModifyProductMousePressed

    private void dgModifyProductMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgModifyProductMouseDragged
        // TODO add your handling code here:
         Point currentCoords = evt.getLocationOnScreen();
                dgModifyProduct.setLocation(currentCoords.x - MouseCoordinates4.x, currentCoords.y - MouseCoordinates4.y);
    }//GEN-LAST:event_dgModifyProductMouseDragged

    private void btnClose4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose4ActionPerformed
        // TODO add your handling code here:
        imageUserLocation = "";
        
        dgModifyProduct.setVisible (false);
        dgModifyProduct.dispose ();
    }//GEN-LAST:event_btnClose4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnClose2;
    private javax.swing.JButton btnClose3;
    private javax.swing.JButton btnClose4;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnModifyBrowse;
    private javax.swing.JButton btnModifyCancel;
    private javax.swing.JButton btnModifyProduct;
    private javax.swing.JButton btnModifySave;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnUpload1;
    private javax.swing.JComboBox<String> cbColumn;
    private javax.swing.JComboBox<String> cbNumRows;
    private javax.swing.JComboBox<String> cbSortBy;
    private javax.swing.JDialog dgAddProduct;
    private javax.swing.JDialog dgModifyProduct;
    private javax.swing.JDialog imageAddDialog;
    private javax.swing.JFileChooser imageFileChooser;
    private javax.swing.JDialog imageModifyDialog;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblAddImage;
    private javax.swing.JLabel lblModifyImage;
    private javax.swing.JLabel lblProductAddImage;
    private javax.swing.JLabel lblProductModifyImage1;
    private javax.swing.JTextArea taProductDescription;
    private javax.swing.JTextArea taProductDescription1;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTextField txtfImageLocation;
    private javax.swing.JTextField txtfImageLocation1;
    private javax.swing.JTextField txtfImageLocation2;
    private javax.swing.JTextField txtfModifyImageLocation;
    private javax.swing.JTextField txtfProductName;
    private javax.swing.JTextField txtfProductName1;
    private javax.swing.JTextField txtfProductNumber;
    private javax.swing.JTextField txtfProductNumber1;
    private javax.swing.JTextField txtfProductPrice;
    private javax.swing.JTextField txtfProductPrice1;
    private javax.swing.JTextField txtfProductQuantity;
    private javax.swing.JTextField txtfProductQuantity1;
    private javax.swing.JTextField txtfUserSearch;
    // End of variables declaration//GEN-END:variables
}
