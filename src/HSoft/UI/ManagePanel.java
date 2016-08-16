package HSoft.UI;

import HSoft.User.Employee;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ManagePanel extends javax.swing.JPanel {
    String Column = "";
    String sortBy = "";
    
    int showNumRows = 0;
     
    public ManagePanel() {
        init ();
    }
    
    private void init () {
        initComponents();
        
        cbColumn.setEditor(new ComboBoxUI ());
        cbColumn.setEditable (true);
        
        cbSortBy.setEditor (new ComboBoxUI ());
        cbSortBy.setEditable (true);
        
        cbNumRows.setEditor (new ComboBoxUI ());
        cbNumRows.setEditable (true);
       
        
       Column = removeWhiteSpace(cbColumn.getSelectedItem().toString()).toUpperCase();
       
       if (cbNumRows.getSelectedItem().toString().equals("all")) {
           showNumRows = 0;
       } else {
           showNumRows = Integer.parseInt(cbNumRows.getSelectedItem().toString());
       }
       
       sortBy = (cbSortBy.getSelectedItem().toString().equals("Ascending")) ? "ASC" : "DESC"; 
       
       updateTableEmployees();
       updateTableGroupBy();
       
    }
    
    public void updateTableEmployees () {
        DefaultTableModel tableModel = new DefaultTableModel () {
             public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Age");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Date Login");
        tableModel.addColumn("Time Login");
        tableModel.addColumn("Date Registered");
        tableModel.addColumn("Time Registered");
        tableModel.addColumn("is hired");
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlResult = sqlStatement.executeQuery("SELECT FIRSTNAME, LASTNAME, EMAIL, ADDRESS, DATEREGISTERED, " +
                                                  "DATELOGIN, TIMELOGIN, AGE, GENDER, TIMEREGISTERED, HIRED, USERIMAGE FROM TBLEMPLOYEE");  
            
            if (sqlResult.isBeforeFirst()) {
                
            int i = 0;
            
            for (;sqlResult.next();) {
                tableModel.addRow(new String []{sqlResult.getString("FIRSTNAME"),
                                                sqlResult.getString("LASTNAME"),
                                                sqlResult.getString("GENDER"),
                                                sqlResult.getString("AGE"),
                                                sqlResult.getString("EMAIL"),
                                                sqlResult.getString("Address"),
                                                sqlResult.getString("DATELOGIN"),
                                                sqlResult.getString("TIMELOGIN"),
                                                sqlResult.getString("DATEREGISTERED"),
                                                sqlResult.getString("TIMEREGISTERED"),
                                                (sqlResult.getString("HIRED") == "true") ? "yes" : "no"});
                
                if (i < 1) {
                    Blob imageBlob = sqlResult.getBlob("USERIMAGE"); 
                    ImageIcon image = null;
                    
                    try {
                        ObjectInputStream oos = new ObjectInputStream(imageBlob.getBinaryStream());
                        try {
                            image = (ImageIcon) oos.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    lblEmployeeImage.setIcon (new ImageIcon(image.getImage().getScaledInstance(188, 170, Image.SCALE_DEFAULT)));
                    ++i;
                }
                
           }
            
            Employee getEmployeeInfo = new Employee ();
            
            tblEmployees.setModel(tableModel);
            
            getEmployeeInfo.firstname = tblEmployees.getValueAt(0, 0).toString();
            getEmployeeInfo.lastname = tblEmployees.getValueAt(0, 1).toString();
            getEmployeeInfo.gender = tblEmployees.getValueAt(0, 2).toString ();
            getEmployeeInfo.age = Integer.parseInt(tblEmployees.getValueAt(0, 3).toString() );
            getEmployeeInfo.email = tblEmployees.getValueAt(0, 4).toString();
            getEmployeeInfo.address = tblEmployees.getValueAt(0, 5).toString();
            
            if (tblEmployees.getValueAt(0, 6) != null ||
                tblEmployees.getValueAt(0, 7) != null) {
                
            getEmployeeInfo.timeLogin = tblEmployees.getValueAt(0, 7).toString ();    
            getEmployeeInfo.dateLogin = tblEmployees.getValueAt(0, 6).toString(); 
            
            lblDTLogin.setText("Date/Time Login: " + getEmployeeInfo.dateLogin + "/" + getEmployeeInfo.timeLogin);
            } else {
                lblDTLogin.setText("Date/Time Login: " + "not login");
            }
           
            getEmployeeInfo.dateRegistered = tblEmployees.getValueAt(0, 8).toString();
            getEmployeeInfo.timeRegistered = tblEmployees.getValueAt(0, 9).toString();
            getEmployeeInfo.hired = Boolean.parseBoolean(tblEmployees.getValueAt(0, 10).toString());
            
            String fullName = getEmployeeInfo.firstname + " " + getEmployeeInfo.lastname;
            
            lblEmployeeName.setText("Name:         " + fullName);
            lblEmployeeGender.setText("Gender: " + getEmployeeInfo.gender);
            lblEmployeeAge.setText("Age: " + Integer.toString(getEmployeeInfo.age) );
            lblEmployeeAddress.setText("Address: " + getEmployeeInfo.address);
            lblEmployeeEmail.setText("Email: " + getEmployeeInfo.email);
           
            lblDTregistered.setText("Date/Time registered: " + getEmployeeInfo.dateRegistered + "/" + getEmployeeInfo.timeRegistered);
            lblEmployeeHired.setText("Hired: " + ((getEmployeeInfo.hired) ? "yes" : "no") );
            
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null)
                    conn.close ();
                
                if (sqlStatement != null)
                    sqlStatement.close();
                
                if (sqlResult != null)
                    sqlResult.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        tblEmployees.setModel (tableModel);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtfUserSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbColumn = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbSortBy = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbNumRows = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        btnHireEmployee = new javax.swing.JButton();
        btnUnhireEmployee = new javax.swing.JButton();
        btnDeleteEmployee = new javax.swing.JButton();
        btnDeleteAllEmployee = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblEmployeeImage = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblEmployeeName = new javax.swing.JLabel();
        lblEmployeeGender = new javax.swing.JLabel();
        lblEmployeeAge = new javax.swing.JLabel();
        lblEmployeeAddress = new javax.swing.JLabel();
        lblEmployeeEmail = new javax.swing.JLabel();
        lblDTLogin = new javax.swing.JLabel();
        lblDTregistered = new javax.swing.JLabel();
        lblEmployeeHired = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(35, 35, 35));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 235, 235));
        jLabel3.setText("Manage Employee");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(35, 35, 35));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 235, 235));
        jLabel2.setText("Search");

        txtfUserSearch.setBackground(new java.awt.Color(35, 35, 35));
        txtfUserSearch.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtfUserSearch.setForeground(new java.awt.Color(235, 235, 235));
        txtfUserSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfUserSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 137, 182)));

        btnSearch.setBackground(new java.awt.Color(51, 51, 51));
        btnSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(235, 235, 235));
        btnSearch.setText("Search");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfUserSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfUserSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(35, 35, 35));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 235, 235));
        jLabel4.setText("Group by");

        cbColumn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Name ", "Last Name", "Age", "Date Registered", "Time Registered", "Date Login", "Time Login", "Email" }));
        cbColumn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbColumn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbColumnItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(235, 235, 235));
        jLabel5.setText("Column");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 235, 235));
        jLabel6.setText("Sort by");

        cbSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbSortBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 35, 35)));
        cbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSortByItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(235, 235, 235));
        jLabel7.setText("no of rows");

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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNumRows, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbColumn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSortBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbNumRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));

        tblEmployees.setBackground(new java.awt.Color(35, 35, 35));
        tblEmployees.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
        tblEmployees.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tblEmployees.setForeground(new java.awt.Color(235, 235, 235));
        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEmployees.setGridColor(new java.awt.Color(80, 137, 182));
        tblEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployees);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnHireEmployee.setBackground(new java.awt.Color(35, 35, 35));
        btnHireEmployee.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnHireEmployee.setForeground(new java.awt.Color(235, 235, 235));
        btnHireEmployee.setText("hire employee");
        btnHireEmployee.setContentAreaFilled(false);
        btnHireEmployee.setOpaque(true);
        btnHireEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHireEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHireEmployeeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHireEmployeeMousePressed(evt);
            }
        });
        btnHireEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHireEmployeeActionPerformed(evt);
            }
        });

        btnUnhireEmployee.setBackground(new java.awt.Color(35, 35, 35));
        btnUnhireEmployee.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnUnhireEmployee.setForeground(new java.awt.Color(235, 235, 235));
        btnUnhireEmployee.setText("unhire employee");
        btnUnhireEmployee.setContentAreaFilled(false);
        btnUnhireEmployee.setOpaque(true);
        btnUnhireEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUnhireEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUnhireEmployeeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUnhireEmployeeMousePressed(evt);
            }
        });
        btnUnhireEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnhireEmployeeActionPerformed(evt);
            }
        });

        btnDeleteEmployee.setBackground(new java.awt.Color(35, 35, 35));
        btnDeleteEmployee.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDeleteEmployee.setForeground(new java.awt.Color(235, 235, 235));
        btnDeleteEmployee.setText("delete employee");
        btnDeleteEmployee.setContentAreaFilled(false);
        btnDeleteEmployee.setOpaque(true);
        btnDeleteEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteEmployeeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteEmployeeMousePressed(evt);
            }
        });
        btnDeleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEmployeeActionPerformed(evt);
            }
        });

        btnDeleteAllEmployee.setBackground(new java.awt.Color(35, 35, 35));
        btnDeleteAllEmployee.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnDeleteAllEmployee.setForeground(new java.awt.Color(235, 235, 235));
        btnDeleteAllEmployee.setText("delete all employees");
        btnDeleteAllEmployee.setContentAreaFilled(false);
        btnDeleteAllEmployee.setOpaque(true);
        btnDeleteAllEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteAllEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteAllEmployeeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteAllEmployeeMousePressed(evt);
            }
        });
        btnDeleteAllEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllEmployeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmployeeImage, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmployeeImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(35, 35, 35));

        lblEmployeeName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblEmployeeName.setForeground(new java.awt.Color(235, 235, 235));
        lblEmployeeName.setText("Name");

        lblEmployeeGender.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblEmployeeGender.setForeground(new java.awt.Color(235, 235, 235));
        lblEmployeeGender.setText("Gender");

        lblEmployeeAge.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblEmployeeAge.setForeground(new java.awt.Color(235, 235, 235));
        lblEmployeeAge.setText("Age");

        lblEmployeeAddress.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblEmployeeAddress.setForeground(new java.awt.Color(235, 235, 235));
        lblEmployeeAddress.setText("Address");

        lblEmployeeEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblEmployeeEmail.setForeground(new java.awt.Color(235, 235, 235));
        lblEmployeeEmail.setText("Email");

        lblDTLogin.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblDTLogin.setForeground(new java.awt.Color(235, 235, 235));
        lblDTLogin.setText("Date / Time Login");

        lblDTregistered.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblDTregistered.setForeground(new java.awt.Color(235, 235, 235));
        lblDTregistered.setText("Date / Time registered");

        lblEmployeeHired.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lblEmployeeHired.setForeground(new java.awt.Color(235, 235, 235));
        lblEmployeeHired.setText("Hired");

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(235, 235, 235));
        jLabel15.setText("Employees Information");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEmployeeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmployeeGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmployeeAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmployeeAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmployeeEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDTregistered, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                            .addComponent(lblEmployeeHired, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeEmail)
                    .addComponent(lblEmployeeName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDTLogin)
                    .addComponent(lblEmployeeGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDTregistered)
                    .addComponent(lblEmployeeAge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeAddress)
                    .addComponent(lblEmployeeHired))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHireEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUnhireEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteAllEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHireEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUnhireEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteAllEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeesMouseClicked
     
        int getIndex = tblEmployees.getSelectedRow();
        
        DefaultTableModel getModel = (DefaultTableModel) tblEmployees.getModel();
        
        Employee getEmployeeInfo = new Employee ();
        
        getEmployeeInfo.firstname = tblEmployees.getValueAt(getIndex, 0).toString();
        getEmployeeInfo.lastname = tblEmployees.getValueAt(getIndex, 1).toString();
        getEmployeeInfo.gender = tblEmployees.getValueAt(getIndex, 2).toString ();
        getEmployeeInfo.age = Integer.parseInt(tblEmployees.getValueAt(getIndex, 3).toString() );
        getEmployeeInfo.email = tblEmployees.getValueAt(getIndex, 4).toString();
        getEmployeeInfo.address = tblEmployees.getValueAt(getIndex, 5).toString();
        getEmployeeInfo.dateRegistered = tblEmployees.getValueAt(getIndex, 8).toString();
        getEmployeeInfo.timeRegistered = tblEmployees.getValueAt(getIndex, 9).toString();
        getEmployeeInfo.hired = Boolean.parseBoolean(tblEmployees.getValueAt(getIndex, 10).toString());
            
        if (tblEmployees.getValueAt(getIndex, 6) != null ||
            tblEmployees.getValueAt(getIndex, 7) != null) {
            getEmployeeInfo.timeLogin = tblEmployees.getValueAt(getIndex, 7).toString ();    
            getEmployeeInfo.dateLogin = tblEmployees.getValueAt(getIndex, 6).toString(); 
            
            lblDTLogin.setText("Date/Time Login: " + getEmployeeInfo.dateLogin + "/" + getEmployeeInfo.timeLogin);
        } else {
                 lblDTLogin.setText("Date/Time Login: " + "not login");
        }
        
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement ();
            sqlResult = sqlStatement.executeQuery("SELECT USERIMAGE FROM TBLEMPLOYEE WHERE EMAIL = '" + getEmployeeInfo.email + "'");
            
            for (; sqlResult.next() ;) {
                Blob imageBlob = sqlResult.getBlob("USERIMAGE"); 
                    ImageIcon image = null;
                    
                    try {
                        ObjectInputStream oos = new ObjectInputStream(imageBlob.getBinaryStream());
                        try {
                            image = (ImageIcon) oos.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                         lblEmployeeImage.setIcon (new ImageIcon(image.getImage().getScaledInstance(188, 170, Image.SCALE_DEFAULT)));
                    } catch (IOException ex) {
                        Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String fullName = getEmployeeInfo.firstname + " " + getEmployeeInfo.lastname;
            
        lblEmployeeName.setText("Name         " + fullName);
        lblEmployeeGender.setText("Gender " + getEmployeeInfo.gender);
        lblEmployeeAge.setText("Age " + Integer.toString(getEmployeeInfo.age) );
        lblEmployeeAddress.setText("Address " + getEmployeeInfo.address);
        lblEmployeeEmail.setText("Email " + getEmployeeInfo.email);
        lblDTregistered.setText("Date/Time registered " + getEmployeeInfo.dateRegistered + "/" + getEmployeeInfo.timeRegistered);
        lblEmployeeHired.setText("Hired " + ((getEmployeeInfo.hired) ? "yes" : "no") );
    }//GEN-LAST:event_tblEmployeesMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        DefaultTableModel tableModel = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Age");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Date Login");
        tableModel.addColumn("Time Login");
        tableModel.addColumn("Date Registered");
        tableModel.addColumn("Time Registered");
        tableModel.addColumn("is hired");
        
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLEMPLOYEE WHERE " +
                                                  "FIRSTNAME LIKE '%" + txtfUserSearch.getText() + "%' OR " +
                                                  "LASTNAME LIKE '%" + txtfUserSearch.getText() + "%' OR " +
                                                  "EMAIL LIKE '%" + txtfUserSearch.getText() + "%' OR " +
                                                  "DATEREGISTERED LIKE '%" + txtfUserSearch.getText() + "%' OR " +
                                                  "TIMEREGISTERED LIKE '%" + txtfUserSearch.getText() + "%' OR " +
                                                  "DATELOGIN LIKE '%" + txtfUserSearch.getText() + "%' OR " +
                                                  "TIMELOGIN LIKE '%" + txtfUserSearch.getText() + "%'");
                
            for (; sqlResult.next() ;) {
                tableModel.addRow(new String []{sqlResult.getString("FIRSTNAME"),
                                                sqlResult.getString("LASTNAME"),
                                                sqlResult.getString("GENDER"),
                                                sqlResult.getString("AGE"),
                                                sqlResult.getString("EMAIL"),
                                                sqlResult.getString("Address"),
                                                sqlResult.getString("DATELOGIN"),
                                                sqlResult.getString("TIMELOGIN"),
                                                sqlResult.getString("DATEREGISTERED"),
                                                sqlResult.getString("TIMEREGISTERED"),
                                                (sqlResult.getString("HIRED") == "true") ? "yes" : "no"});
            }
            
            tblEmployees.setModel (tableModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnSearchActionPerformed
    
    private void updateTableGroupBy () {
        DefaultTableModel tableModel = new DefaultTableModel () {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        
        String sort = (cbSortBy.getSelectedItem().toString().equals("Ascending")) ? "ASC" : "DESC";
        
        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet sqlResult = null;
        
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Age");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Date Login");
        tableModel.addColumn("Time Login");
        tableModel.addColumn("Date Registered");
        tableModel.addColumn("Time Registered");
        tableModel.addColumn("is hired");
       
        try {
            System.out.println ("gb");
            
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlResult = sqlStatement.executeQuery("SELECT * FROM TBLEMPLOYEE ORDER BY " + Column + " " + sort);
            
            if (showNumRows != 0) {
            
            for (int i = 0; i < showNumRows; ++i) {
          
                 sqlResult.next();
                
              try {
                  
                        tableModel.addRow(new String []{sqlResult.getString("FIRSTNAME"),
                                                sqlResult.getString("LASTNAME"),
                                                sqlResult.getString("GENDER"),
                                                sqlResult.getString("AGE"),
                                                sqlResult.getString("EMAIL"),
                                                sqlResult.getString("Address"),
                                                sqlResult.getString("DATELOGIN"),
                                                sqlResult.getString("TIMELOGIN"),
                                                sqlResult.getString("DATEREGISTERED"),
                                                sqlResult.getString("TIMEREGISTERED"),
                                                ("true".equals(sqlResult.getString("HIRED"))) ? "yes" : "no"});
                 
                       
              } catch (Exception e) {
                  break;
              }
                 
            }
            
            } else {
                  for (; sqlResult.next() ;) {
          
              try {
                  
                        tableModel.addRow(new String []{sqlResult.getString("FIRSTNAME"),
                                                sqlResult.getString("LASTNAME"),
                                                sqlResult.getString("GENDER"),
                                                sqlResult.getString("AGE"),
                                                sqlResult.getString("EMAIL"),
                                                sqlResult.getString("Address"),
                                                sqlResult.getString("DATELOGIN"),
                                                sqlResult.getString("TIMELOGIN"),
                                                sqlResult.getString("DATEREGISTERED"),
                                                sqlResult.getString("TIMEREGISTERED"),
                                                ("true".equals(sqlResult.getString("HIRED"))) ? "yes" : "no"});
                 
                       
              } catch (Exception e) {
                  break;
              }
                 
            }
                
            }
            
            tblEmployees.setModel (tableModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String removeWhiteSpace (String word) {
        String rwsWord = "";
        
        for (int i = 0; i != word.length(); ++i) {
            if (!Character.isWhitespace(word.charAt(i)))
                rwsWord += word.charAt(i);
        }
        
        return rwsWord;
    }
     
    
    private void cbColumnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbColumnItemStateChanged

        Column = removeWhiteSpace(cbColumn.getSelectedItem().toString()).toUpperCase();  
        this.updateTableGroupBy();
    }//GEN-LAST:event_cbColumnItemStateChanged

    private void cbSortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSortByItemStateChanged
    
        sortBy = (cbSortBy.getSelectedItem().toString().equals("Ascending") ) ? "ASC" : "DESC";
        
        this.updateTableGroupBy();
    }//GEN-LAST:event_cbSortByItemStateChanged

    private void cbNumRowsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNumRowsItemStateChanged
     
        if (cbNumRows.getSelectedItem().toString().equals("all")) {
            showNumRows = 0;
        } else {
            showNumRows = Integer.parseInt(cbNumRows.getSelectedItem().toString());
        }
     
       updateTableGroupBy();
    }//GEN-LAST:event_cbNumRowsItemStateChanged

    private void btnHireEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHireEmployeeActionPerformed
  
        int getIndex = tblEmployees.getSelectedRow();
        
        if (getIndex != -1) {
            String email = (String) tblEmployees.getModel().getValueAt(getIndex, 4);
            
            Connection conn = null;
            Statement sqlStatement = null;
            Statement sqlStatement2 = null;
            ResultSet sqlResult = null;
            
            try {
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement = conn.createStatement ();
                sqlStatement2 = conn.createStatement ();
                
                sqlResult = sqlStatement.executeQuery("SELECT HIRED FROM TBLEMPLOYEE WHERE EMAIL = '" + email + "'");
                
                for (; sqlResult.next(); ) {
                
                 if (sqlResult.getString("HIRED").equals("false")) {
                     sqlStatement2.executeUpdate("UPDATE TBLEMPLOYEE SET HIRED = true WHERE EMAIL = '" + email + "'");
                     JOptionPane.showMessageDialog(this, "employee hired", "employee hire", JOptionPane.INFORMATION_MESSAGE);
                 } else {
                     JOptionPane.showMessageDialog(this, "employee already hired", "hire employee", JOptionPane.INFORMATION_MESSAGE);
                 }
                 
                }
                
                updateTableEmployees();
                
            } catch (SQLException ex) {
                Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "select an employee first", "hire employee", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHireEmployeeActionPerformed

    private void btnDeleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEmployeeActionPerformed
         int getIndex = tblEmployees.getSelectedRow();
         
         if (getIndex != -1) {
             Connection conn = null;
             Statement sqlStatement = null;
             
             String getEmail = (String) tblEmployees.getModel().getValueAt(getIndex, 4);
       
             try {
                 conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
                 sqlStatement = conn.createStatement();
                 sqlStatement.execute("DELETE FROM TBLEMPLOYEE WHERE EMAIL = '" + getEmail + "'");
                 
                 updateTableEmployees();
                 
             } catch (SQLException ex) {
                 Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             
         } else {
            JOptionPane.showMessageDialog(this, "select an employee first", "hire employee", JOptionPane.ERROR_MESSAGE);
         }
    }//GEN-LAST:event_btnDeleteEmployeeActionPerformed

    private void btnUnhireEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnhireEmployeeActionPerformed
    
             int getIndex = tblEmployees.getSelectedRow();
        
        if (getIndex != -1) {
            String email = (String) tblEmployees.getModel().getValueAt(getIndex, 4);
            
            Connection conn = null;
            Statement sqlStatement = null;
            Statement sqlStatement2 = null;
            ResultSet sqlResult = null;
            
            try {
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Kryssel");
                sqlStatement = conn.createStatement ();
                sqlStatement2 = conn.createStatement ();
                
                sqlResult = sqlStatement.executeQuery("SELECT HIRED FROM TBLEMPLOYEE WHERE EMAIL = '" + email + "'");
                
                for (; sqlResult.next(); ) {
                
                 if (sqlResult.getString("HIRED").equals("true")) {
                     sqlStatement2.executeUpdate("UPDATE TBLEMPLOYEE SET HIRED = false WHERE EMAIL = '" + email + "'");
                     JOptionPane.showMessageDialog(this, "employee unhired", "unhire employee", JOptionPane.INFORMATION_MESSAGE);
                 } else {
                     JOptionPane.showMessageDialog(this, "employee already unhired", "hire employee", JOptionPane.INFORMATION_MESSAGE);
                 }
                 
                }
                
                updateTableEmployees();
                
            } catch (SQLException ex) {
                Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "select an employee first", "hire employee", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUnhireEmployeeActionPerformed

    private void btnDeleteAllEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllEmployeeActionPerformed
    
        Connection conn = null;
        Statement sqlStatement = null; 
        
        try {
            conn = DriverManager.getConnection ("jdbc:derby://localhost:1527/Kryssel");
            sqlStatement = conn.createStatement();
            sqlStatement.execute ("DELETE FROM TBLEMPLOYEE");
            
            updateTableEmployees ();
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnDeleteAllEmployeeActionPerformed

    private void btnSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseEntered
   
        btnSearch.setBackground (new Color (74, 74, 74));
        
    }//GEN-LAST:event_btnSearchMouseEntered

    private void btnSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseExited
      
        btnSearch.setBackground (new Color (51, 51, 51));
    }//GEN-LAST:event_btnSearchMouseExited

    private void btnSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMousePressed
      
        btnSearch.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnSearchMousePressed

    private void btnHireEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHireEmployeeMouseEntered
    
        btnHireEmployee.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnHireEmployeeMouseEntered

    private void btnHireEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHireEmployeeMouseExited
    
        btnHireEmployee.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnHireEmployeeMouseExited

    private void btnHireEmployeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHireEmployeeMousePressed
     
        btnHireEmployee.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnHireEmployeeMousePressed

    private void btnUnhireEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUnhireEmployeeMouseEntered
      
        btnUnhireEmployee.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnUnhireEmployeeMouseEntered

    private void btnUnhireEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUnhireEmployeeMouseExited
     
        btnUnhireEmployee.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnUnhireEmployeeMouseExited

    private void btnUnhireEmployeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUnhireEmployeeMousePressed
     
        btnUnhireEmployee.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnUnhireEmployeeMousePressed

    private void btnDeleteEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteEmployeeMouseEntered
    
        btnDeleteEmployee.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeleteEmployeeMouseEntered

    private void btnDeleteEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteEmployeeMouseExited
      
        btnDeleteEmployee.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnDeleteEmployeeMouseExited

    private void btnDeleteEmployeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteEmployeeMousePressed
      
        btnDeleteEmployee.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeleteEmployeeMousePressed

    private void btnDeleteAllEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllEmployeeMouseEntered
     
        btnDeleteAllEmployee.setBackground (new Color (74, 74, 74));
    }//GEN-LAST:event_btnDeleteAllEmployeeMouseEntered

    private void btnDeleteAllEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllEmployeeMouseExited
   
        btnDeleteAllEmployee.setBackground (new Color (35, 35, 35));
    }//GEN-LAST:event_btnDeleteAllEmployeeMouseExited

    private void btnDeleteAllEmployeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAllEmployeeMousePressed
    
        btnDeleteAllEmployee.setBackground (new Color (89, 89, 89));
    }//GEN-LAST:event_btnDeleteAllEmployeeMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteAllEmployee;
    private javax.swing.JButton btnDeleteEmployee;
    private javax.swing.JButton btnHireEmployee;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUnhireEmployee;
    private javax.swing.JComboBox<String> cbColumn;
    private javax.swing.JComboBox<String> cbNumRows;
    private javax.swing.JComboBox<String> cbSortBy;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDTLogin;
    private javax.swing.JLabel lblDTregistered;
    private javax.swing.JLabel lblEmployeeAddress;
    private javax.swing.JLabel lblEmployeeAge;
    private javax.swing.JLabel lblEmployeeEmail;
    private javax.swing.JLabel lblEmployeeGender;
    private javax.swing.JLabel lblEmployeeHired;
    private javax.swing.JLabel lblEmployeeImage;
    private javax.swing.JLabel lblEmployeeName;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTextField txtfUserSearch;
    // End of variables declaration//GEN-END:variables
}
