/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSoft.UI;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author Gina
 */

public class LogPanel extends javax.swing.JPanel {

    String column;
    String sort;
    
    /**
     * Creates new form LogPanel
     */
    
    public LogPanel() {
        initComponents();
        
        setBackground(new Color (51, 51, 51));
        
        cbColumn.setEditor (new ComboBoxUI ());
        cbColumn.setEditable (true);
        
        cbSortBy.setEditor (new ComboBoxUI ());
        cbSortBy.setEditable (true);
        
        
        column = this.cbColumn.getSelectedItem().toString ();
        sort = this.cbSortBy.getSelectedItem().toString ();
        
        updateLogTable ();
        updateLogTableGroupBy ();
    }
    
    private void updateLogTableGroupBy () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        DefaultTableModel tableModel = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        tableModel.addColumn("Description");
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Username");
        tableModel.addColumn("Type");
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLLOG ORDER BY " + column + " " + 
                                                  ((sort.equals("Ascending")) ? "ASC" : "DESC") );
            
            for (; sqlResult.next() ;) {
                tableModel.addRow(new String[] {sqlResult.getString("DESCRIPTION"),
                                                sqlResult.getString("DATE"),
                                                sqlResult.getString("TIME"),
                                                sqlResult.getString("USERNAME"),
                                                sqlResult.getString("TYPE")});         
            }
            
            tblLog.setModel (tableModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(LogPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateLogTable () {
        
       DefaultTableModel tableModel = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
       tableModel.addColumn("Description");
       tableModel.addColumn("Date");
       tableModel.addColumn("Time");
       tableModel.addColumn("Username");
       tableModel.addColumn("Type");
       
       Connection conn = null;
       Statement sqlStatement = null;
       ResultSet sqlResult = null;
       
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLLOG");
            
            sqlResult.last();
            
            for (; sqlResult.previous() ;) {
                
                tableModel.addRow(new String [] {sqlResult.getString("DESCRIPTION"),
                                                 sqlResult.getString("DATE"),
                                                 sqlResult.getString("TIME"),
                                                 sqlResult.getString("USERNAME"),
                                                 sqlResult.getString("TYPE")}); 
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LogPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       tblLog.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser2 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        btnClearLog = new javax.swing.JButton();
        btnSaveLog = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbColumn = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbSortBy = new javax.swing.JComboBox<>();

        jFileChooser2.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jFileChooser2.setDialogTitle("save log");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(35, 35, 35));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Logs");

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

        tblLog.setBackground(new java.awt.Color(35, 35, 35));
        tblLog.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tblLog.setForeground(new java.awt.Color(235, 235, 235));
        tblLog.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLog.setGridColor(new java.awt.Color(80, 137, 182));
        jScrollPane5.setViewportView(tblLog);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnClearLog.setBackground(new java.awt.Color(35, 35, 35));
        btnClearLog.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnClearLog.setForeground(new java.awt.Color(235, 235, 235));
        btnClearLog.setText("Clear Log");
        btnClearLog.setToolTipText("Clear the log");
        btnClearLog.setContentAreaFilled(false);
        btnClearLog.setOpaque(true);
        btnClearLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearLogMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClearLogMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClearLogMousePressed(evt);
            }
        });
        btnClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLogActionPerformed(evt);
            }
        });

        btnSaveLog.setBackground(new java.awt.Color(35, 35, 35));
        btnSaveLog.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSaveLog.setForeground(new java.awt.Color(235, 235, 235));
        btnSaveLog.setText("Save Log");
        btnSaveLog.setToolTipText("save the log");
        btnSaveLog.setContentAreaFilled(false);
        btnSaveLog.setOpaque(true);
        btnSaveLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveLogMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveLogMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveLogMousePressed(evt);
            }
        });
        btnSaveLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveLogActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Search");

        txtfSearch.setBackground(new java.awt.Color(35, 35, 35));
        txtfSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtfSearch.setForeground(new java.awt.Color(235, 235, 235));
        txtfSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnSearch.setBackground(new java.awt.Color(51, 51, 51));
        btnSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(235, 235, 235));
        btnSearch.setText("search");
        btnSearch.setToolTipText("");
        btnSearch.setContentAreaFilled(false);
        btnSearch.setOpaque(true);
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSearchMousePressed(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(txtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtfSearch))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(35, 35, 35));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Group by");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 235, 235));
        jLabel4.setText("by Column");

        cbColumn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Description", "Date", "Time", "Username", "Type" }));
        cbColumn.setSelectedIndex(1);
        cbColumn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbColumn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbColumnItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("Sort by");

        cbSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbSortBy.setSelectedIndex(1);
        cbSortBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSortByItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbColumn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSortBy, 0, 128, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbColumn)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClearLog, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSaveLog, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearLog, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveLog, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLogActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        Statement sqlStatement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlStatement.execute("DELETE FROM TBLLOG");
            
            updateLogTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(LogPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnClearLogActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        DefaultTableModel tableModel = new DefaultTableModel ();
        
        tableModel.addColumn("Description");
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Username");
        tableModel.addColumn("Type");
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT DESCRIPTION, DATE, TIME, USERNAME, TYPE FROM TBLLOG " +
                                                  "WHERE DESCRIPTION LIKE '%" + txtfSearch.getText() + "%' OR " +
                                                  "DATE LIKE '%" + txtfSearch.getText() + "%' OR " +
                                                  "TIME LIKE '%" + txtfSearch.getText() + "%' OR " +
                                                  "USERNAME LIKE '%" + txtfSearch.getText() + "%'");
            
            for (; sqlResult.next() ;) {
               tableModel.addRow(new String [] {sqlResult.getString("DESCRIPTION"),
                                                sqlResult.getString("DATE"),
                                                sqlResult.getString("TIME"),
                                                sqlResult.getString("USERNAME"),
                                                sqlResult.getString("TYPE")}); 
            } 
            
            tblLog.setModel(tableModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(LogPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbColumnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbColumnItemStateChanged
        // TODO add your handling code here:
        column = cbColumn.getSelectedItem().toString();
        updateLogTableGroupBy ();
    }//GEN-LAST:event_cbColumnItemStateChanged

    private void cbSortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSortByItemStateChanged
        // TODO add your handling code here:
        sort = cbSortBy.getSelectedItem().toString();
        updateLogTableGroupBy ();
    }//GEN-LAST:event_cbSortByItemStateChanged

    private void btnSaveLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveLogActionPerformed
            
        FileWriter writeFile = null;
        
        jFileChooser2.setSelectedFile(new File("LOG.txt"));
        jFileChooser2.setCurrentDirectory(new File ("C:\\Users\\Gina\\Documents"));
        
        if (jFileChooser2.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                
              
                
                if (jFileChooser2.getSelectedFile().equals("log.txt")) {
                    writeFile = new FileWriter (jFileChooser2.getCurrentDirectory() + "\\" + jFileChooser2.getSelectedFile());
                    System.out.println (jFileChooser2.getCurrentDirectory() + "\\" + jFileChooser2.getSelectedFile());
                }
                else {
                    writeFile = new FileWriter (jFileChooser2.getSelectedFile());
                    System.out.println (jFileChooser2.getSelectedFile() + "else");
                }
                
                DefaultTableModel getModel = (DefaultTableModel) tblLog.getModel();
               
                Date getCurrentDate = new Date ();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
                
                writeFile.write ("LOG FILE SAVED " + dateFormat.format(getCurrentDate) + " " + timeFormat.format(getCurrentDate) + System.lineSeparator());
                
                writeFile.write(System.lineSeparator());
                
                writeFile.write(String.format("%20s %20s %20s %32s %20s",
                                             getModel.getColumnName(0), getModel.getColumnName(1),
                                             getModel.getColumnName(2), getModel.getColumnName(3),
                                             getModel.getColumnName(4)));
                
                writeFile.write(System.lineSeparator());
                writeFile.write(System.lineSeparator());
                
                
                for (int rowCount = 0; rowCount != getModel.getRowCount(); ++rowCount) {
                    for (int colCount = 0; colCount != getModel.getColumnCount(); ++colCount) {
                        writeFile.write(String.format("%20s", getModel.getValueAt(rowCount, colCount)));
                    }
                    
                    writeFile.write(System.lineSeparator());
                }
                
                        
              
            } catch (IOException ex) {
                Logger.getLogger(LogPanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writeFile != null)
                    try {
                        writeFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(LogPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }//GEN-LAST:event_btnSaveLogActionPerformed

    private void btnClearLogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearLogMouseEntered
        // TODO add your handling code here:
        btnClearLog.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnClearLogMouseEntered

    private void btnClearLogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearLogMouseExited
        // TODO add your handling code here:
        btnClearLog.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnClearLogMouseExited

    private void btnClearLogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearLogMousePressed
        // TODO add your handling code here:
        btnClearLog.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnClearLogMousePressed

    private void btnSaveLogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveLogMouseEntered
        // TODO add your handling code here:
        btnSaveLog.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnSaveLogMouseEntered

    private void btnSaveLogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveLogMouseExited
        // TODO add your handling code here:
        btnSaveLog.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnSaveLogMouseExited

    private void btnSaveLogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveLogMousePressed
        // TODO add your handling code here:
        btnSaveLog.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSaveLogMousePressed

    private void btnSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseEntered
        btnSearch.setBackground (new Color (74, 74, 74) );
    }//GEN-LAST:event_btnSearchMouseEntered

    private void btnSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseExited
        // TODO add your handling code here:
        btnSearch.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSearchMouseExited

    private void btnSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMousePressed
        // TODO add your handling code here:
        btnSearch.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSearchMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearLog;
    private javax.swing.JButton btnSaveLog;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbColumn;
    private javax.swing.JComboBox<String> cbSortBy;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblLog;
    private javax.swing.JTextField txtfSearch;
    // End of variables declaration//GEN-END:variables
}
