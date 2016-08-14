/*
 * Copyright (C) 2016 Gina
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package HSoft.UI;

import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gina
 */
public class OwnerWindow extends javax.swing.JFrame {
    Point MouseCoordinates;
    
    
    /**
     * Creates new form OwnerWindow
     */
    public OwnerWindow() {
        setUndecorated (true);
        
        initComponents();
        
        getContentPane().setBackground(new Color (35, 35, 35));
        
        ImageIcon getIcon = new ImageIcon ("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\icons\\icons\\posIcon.png");
        
        setIconImage (getIcon.getImage());
        
        updateManager ();
        updateEmployee ();
    }
    
    private void updateManager () {
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        DefaultTableModel tableModel =  new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLMANAGER");
            
            tableModel.addColumn ("firstname");
            tableModel.addColumn ("lastname");
            tableModel.addColumn ("email");
            tableModel.addColumn ("address");
            tableModel.addColumn ("age");
            tableModel.addColumn ("gender");
            tableModel.addColumn ("ishired");
            
            for (;sqlResult.next();) {
                tableModel.addRow(new String [] {sqlResult.getString ("FIRSTNAME"),
                                                 sqlResult.getString ("LASTNAME"),
                                                 sqlResult.getString ("EMAIL"),
                                                 sqlResult.getString ("ADDRESS"),
                                                 sqlResult.getString ("AGE"),
                                                 sqlResult.getString ("GENDER"),
                                                 (sqlResult.getString ("ISHIRED").equals("true")) ? "yes" : "no"});
            } 
            
            tblManager.setModel(tableModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    private void updateEmployee () {
          Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        DefaultTableModel tableModel = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLEMPLOYEE");
            
            tableModel.addColumn ("firstname");
            tableModel.addColumn ("lastname");
            tableModel.addColumn ("email");
            tableModel.addColumn ("address");
            tableModel.addColumn ("age");
            tableModel.addColumn ("gender");
            tableModel.addColumn ("ishired");
            
            for (;sqlResult.next();) {
                tableModel.addRow(new String [] {sqlResult.getString ("FIRSTNAME"),
                                                 sqlResult.getString ("LASTNAME"),
                                                 sqlResult.getString ("EMAIL"),
                                                 sqlResult.getString ("ADDRESS"),
                                                 sqlResult.getString ("AGE"),
                                                 sqlResult.getString ("GENDER"),
                                                 (sqlResult.getString ("HIRED").equals("true")) ? "yes" : "no"});
            } 
            
            tblEmployee.setModel(tableModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel2 = new javax.swing.JLabel();
        tbPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblManager = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnActivate = new javax.swing.JButton();
        btnDeactivate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnClose = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HPos");
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

        tblManager.setBackground(new java.awt.Color(35, 35, 35));
        tblManager.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tblManager.setForeground(new java.awt.Color(235, 235, 235));
        tblManager.setModel(new javax.swing.table.DefaultTableModel(
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
        tblManager.setGridColor(new java.awt.Color(80, 137, 182));
        jScrollPane1.setViewportView(tblManager);

        tbPane.addTab("Manager", jScrollPane1);

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblEmployee);

        tbPane.addTab("Employee", jScrollPane2);

        btnActivate.setBackground(new java.awt.Color(51, 51, 51));
        btnActivate.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnActivate.setForeground(new java.awt.Color(235, 235, 235));
        btnActivate.setText("Activate");
        btnActivate.setContentAreaFilled(false);
        btnActivate.setFocusable(false);
        btnActivate.setOpaque(true);
        btnActivate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActivateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActivateMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnActivateMousePressed(evt);
            }
        });
        btnActivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivateActionPerformed(evt);
            }
        });

        btnDeactivate.setBackground(new java.awt.Color(51, 51, 51));
        btnDeactivate.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDeactivate.setForeground(new java.awt.Color(235, 235, 235));
        btnDeactivate.setText("Deactivate");
        btnDeactivate.setContentAreaFilled(false);
        btnDeactivate.setFocusable(false);
        btnDeactivate.setOpaque(true);
        btnDeactivate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeactivateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeactivateMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeactivateMousePressed(evt);
            }
        });
        btnDeactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeactivateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(51, 51, 51));
        btnDelete.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(235, 235, 235));
        btnDelete.setText("Delete");
        btnDelete.setContentAreaFilled(false);
        btnDelete.setFocusable(false);
        btnDelete.setOpaque(true);
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnDeleteAll.setBackground(new java.awt.Color(51, 51, 51));
        btnDeleteAll.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDeleteAll.setForeground(new java.awt.Color(235, 235, 235));
        btnDeleteAll.setText("DeleteAll");
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

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 235, 235));
        jLabel1.setText("Manage your people");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbPane, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeactivate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnActivate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnDeleteAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tbPane, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnActivate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeactivate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteAll)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivateActionPerformed
        // TODO add your handling code here:
       

           
           
          if (tbPane.getSelectedIndex() == 0) {
            int getIndex = tblManager.getSelectedRow();
            
            if (getIndex != -1) {
         
            String firstname = (String) tblManager.getModel().getValueAt(getIndex, 0);
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("UPDATE TBLMANAGER SET ISHIRED = ? WHERE FIRSTNAME = ?");
                
                sqlCmd.setBoolean (1 , true);
                sqlCmd.setString (2, firstname);
                
                sqlCmd.execute ();
                
                updateManager ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } else {
                JOptionPane.showMessageDialog (this, "select a row first", "deactivate", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } else {
             int getIndex = tblEmployee.getSelectedRow();
             
              if (getIndex != -1) {
            
            String firstname = (String) tblEmployee.getModel().getValueAt(getIndex, 0);
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("UPDATE TBLEMPLOYEE SET HIRED = ? WHERE FIRSTNAME = ?");
                
                sqlCmd.setBoolean (1 , true);
                sqlCmd.setString (2, firstname);
                
                sqlCmd.execute ();
                
                updateEmployee ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } else {
                JOptionPane.showMessageDialog (this, "select a row first", "deactivate", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnActivateActionPerformed

    private void btnDeactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeactivateActionPerformed
        
           
          if (tbPane.getSelectedIndex() == 0) {
            int getIndex = tblManager.getSelectedRow();
            
            if (getIndex != -1) {
         
            String firstname = (String) tblManager.getModel().getValueAt(getIndex, 0);
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("UPDATE TBLMANAGER SET ISHIRED = ? WHERE FIRSTNAME = ?");
                
                sqlCmd.setBoolean (1 , false);
                sqlCmd.setString (2, firstname);
                
                sqlCmd.execute ();
                
                updateManager ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } else {
                JOptionPane.showMessageDialog (this, "select a row first", "deactivate", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } else {
             int getIndex = tblEmployee.getSelectedRow();
             
              if (getIndex != -1) {
            
            String firstname = (String) tblEmployee.getModel().getValueAt(getIndex, 0);
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("UPDATE TBLEMPLOYEE SET HIRED = ? WHERE FIRSTNAME = ?");
                
                sqlCmd.setBoolean (1 , false);
                sqlCmd.setString (2, firstname);
                
                sqlCmd.execute ();
                
                updateEmployee ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } else {
                JOptionPane.showMessageDialog (this, "select a row first", "deactivate", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeactivateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
          if (tbPane.getSelectedIndex() == 0) {
            int getIndex = tblManager.getSelectedRow();
            
            if (getIndex != -1) {
         
            String firstname = (String) tblManager.getModel().getValueAt(getIndex, 0);
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("DELETE FROM TBLMANAGER WHERE FIRSTNAME = ?");
                
            
                sqlCmd.setString (1, firstname);
                
                sqlCmd.execute ();
                
                updateManager ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } else {
                JOptionPane.showMessageDialog (this, "select a row first", "deactivate", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } else {
             int getIndex = tblEmployee.getSelectedRow();
             
              if (getIndex != -1) {
            
            String firstname = (String) tblEmployee.getModel().getValueAt(getIndex, 0);
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("DELETE FROM TBLEMPLOYEE WHERE FIRSTNAME = ?");
                
                sqlCmd.setString (1, firstname);
                
                sqlCmd.execute ();
                
                updateEmployee ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } else {
                JOptionPane.showMessageDialog (this, "select a row first", "deactivate", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        // TODO add your handling code here:
         if (tbPane.getSelectedIndex() == 0) {
            
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("DELETE FROM TBLMANAGER");
            
                
                sqlCmd.execute ();
                
                updateManager ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
        } else {
            
            
            try {
                Connection conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                PreparedStatement sqlCmd = conn.prepareStatement("DELETE FROM TBLEMPLOYEE");
                
   
                sqlCmd.execute ();
                
                updateEmployee ();
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         
        }
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        MouseCoordinates = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        Point currentPosition = evt.getLocationOnScreen();
        
        this.setLocation(currentPosition.x - MouseCoordinates.x,  currentPosition.y - MouseCoordinates.y);
    }//GEN-LAST:event_formMouseDragged

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

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        int ifYes = JOptionPane.showConfirmDialog(this, "get back to the login screen?", "get back?", JOptionPane.YES_NO_CANCEL_OPTION);
 
        switch (ifYes) {
        
            case 0: {
            
                setVisible (false);
                dispose ();
      
        
                LoginUI login = new LoginUI ();
        
                login.pack ();
                login.setLocationRelativeTo (null);
                login.setVisible (true);
        
        break;
        } 
           case 1: {
                 
                setVisible (false);
                dispose ();
                System.exit (0);
                
                break;
        }
            
           default:
               break;
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnActivateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActivateMouseEntered
        // TODO add your handling code here:
        btnActivate.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnActivateMouseEntered

    private void btnActivateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActivateMouseExited
        // TODO add your handling code here:
        btnActivate.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnActivateMouseExited

    private void btnActivateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActivateMousePressed
        // TODO add your handling code here:
        btnActivate.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnActivateMousePressed

    private void btnDeactivateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeactivateMouseEntered
        // TODO add your handling code here:
        btnDeactivate.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeactivateMouseEntered

    private void btnDeactivateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeactivateMouseExited
        // TODO add your handling code here:
        btnDeactivate.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnDeactivateMouseExited

    private void btnDeactivateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeactivateMousePressed
        // TODO add your handling code here:
        btnDeactivate.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeactivateMousePressed

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
        btnDelete.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
        btnDelete.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        // TODO add your handling code here:
        btnDelete.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnDeleteAllMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMouseEntered
        // TODO add your handling code here:
        btnDeleteAll.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeleteAllMouseEntered

    private void btnDeleteAllMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMouseExited
        // TODO add your handling code here:
        btnDeleteAll.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnDeleteAllMouseExited

    private void btnDeleteAllMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllMousePressed
        // TODO add your handling code here:
        btnDeleteAll.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeleteAllMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(OwnerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OwnerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OwnerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OwnerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OwnerWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivate;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDeactivate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane tbPane;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTable tblManager;
    // End of variables declaration//GEN-END:variables
}
