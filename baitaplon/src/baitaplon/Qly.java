/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitaplon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anhbando
 */
public class Qly extends javax.swing.JFrame {

    public List<SinhVien> students;
    private DefaultTableModel tblModel1, tblModel2;
    public List<DiemRenLuyen> drl;
    
    ConnectDB cn = new ConnectDB();
    Connection conn;
    /**
     * Creates new form Menu
     */
    public Qly() throws SQLException {
        initComponents();
        students = new ArrayList<>();
        drl = new ArrayList<>();
        this.conn = cn.getConnection();
        tblModel1 = (DefaultTableModel) tblThongTin.getModel();
        tblModel2 = (DefaultTableModel) tblDrl.getModel();
        loadData();
    }
    
    private void loadData() {
        try {
            loadStudents();
            loadDrl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadStudents() throws SQLException {
        String query = "SELECT * FROM SinhVien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            String maSoDoanVien = rs.getString("maSoDoanVien");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String email = rs.getString("email");

            SinhVien sv = new SinhVien(name, maSoDoanVien, phone, email, address);
            students.add(sv);
        }
        updateTableStudents();
    }
    
    private void updateTableStudents() {
        tblModel1.setRowCount(0);
        for (SinhVien sv : students) {
            tblModel1.addRow(new Object[]{
                sv.getName(), sv.getMaSoDoanVien(), sv.getPhone(), sv.getEmail(), sv.getAddress()
            });
        }
    }

    private void loadDrl() throws SQLException {
        String query = "SELECT * FROM DiemRenLuyen";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            String maSo = rs.getString("maSo");
            String name = rs.getString("name");
            String loai = rs.getString("loai");
            int drl_load = rs.getInt("drl");

            DiemRenLuyen drlItem = new DiemRenLuyen(name, maSo, drl_load, loai);
            drl.add(drlItem);
        }
        updateTableDrl();
    }
    
    private void updateTableDrl() {
        tblModel2.setRowCount(0);
        for (DiemRenLuyen drlItem : drl) {
            tblModel2.addRow(new Object[]{
                drlItem.getName(), drlItem.getMaSo(), drlItem.getDrl(), drlItem.getLoai()
            });
        }
    }

    public void addStudent(SinhVien sv, DiemRenLuyen Drl){
        students.add(sv);
        tblModel1.setRowCount(0);
        drl.add(Drl);
        tblModel2.setRowCount(0);
        updateTableStudents();
        updateTableDrl();
    }
    
    private void addDiemRenLuyen(String name, String maSo, String loai, int drl) {
        DiemRenLuyen drlItem = new DiemRenLuyen(name, maSo, drl, loai);

        try {
            insertDiemRenLuyen(drlItem);
            // Optionally update your in-memory list and table view if necessary
            this.drl.add(drlItem);
            updateTableDrl();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., show a dialog to the user)
        }
    }
    
    public void insertDiemRenLuyen(DiemRenLuyen drlItem) throws SQLException {
        String query = "INSERT INTO DiemRenLuyen (maSo, name, loai, drl) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, drlItem.getMaSo());
            pstmt.setString(2, drlItem.getName());
            pstmt.setString(3, drlItem.getLoai());
            pstmt.setInt(4, drlItem.getDrl());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void insertSinhVien(SinhVien sv) throws SQLException {
        String query = "INSERT INTO SinhVien (maSoDoanVien, name, phone, address, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sv.getMaSoDoanVien());
            pstmt.setString(2, sv.getName());
            pstmt.setString(3, sv.getPhone());
            pstmt.setString(4, sv.getAddress());
            pstmt.setString(5, sv.getEmail());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void addDataToDatabase(SinhVien sv, DiemRenLuyen drl) {
    try {
        insertSinhVien(sv);
        insertDiemRenLuyen(drl);
        // Optionally update your in-memory lists and table views if necessary
        this.students.add(sv);
        this.drl.add(drl);
        updateTableDrl();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception (e.g., show a dialog to the user)
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        j2 = new javax.swing.JTextField();
        j1 = new javax.swing.JTextField();
        j4 = new javax.swing.JTextField();
        j3 = new javax.swing.JTextField();
        j5 = new javax.swing.JTextField();
        addStudent = new javax.swing.JButton();
        deleleStudent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDrl = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        joinAction = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý thông tin đoàn viên");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Số điện thoại");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mã số đoàn viên");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Địa chỉ");

        j2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        j1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        j4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        j3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        j5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        addStudent.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        addStudent.setText("Thêm");
        addStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentActionPerformed(evt);
            }
        });

        deleleStudent.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        deleleStudent.setText("Xóa");
        deleleStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleleStudentActionPerformed(evt);
            }
        });

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ và tên", "Mã số đoàn viên", "Số điện thoại", "Email", "Địa chỉ"
            }
        ));
        jScrollPane1.setViewportView(tblThongTin);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(342, 342, 342)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(j4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(361, 361, 361))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(deleleStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleleStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(j4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý thông tin cá nhân", jPanel1);

        tblDrl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ và tên", "Mã số đoàn viên", "Điểm rèn luyện", "Loại"
            }
        ));
        jScrollPane2.setViewportView(tblDrl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(691, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý điểm rèn luyện", jPanel2);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Họ và tên");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mã số đoàn viên");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        joinAction.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        joinAction.setText("Đăng ký tham gia hoạt động đoàn");
        joinAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(joinAction)))
                .addContainerGap(930, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(joinAction, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(305, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý hoạt động đoàn", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentActionPerformed
        String name = "", phone = "", address = "",email = "", id = "";

        name = j1.getText();
        id = j2.getText();
        phone = j3.getText();
        email = j4.getText();
        address = j5.getText();

        SinhVien st = new SinhVien(name , id ,  phone , email , address);
        DiemRenLuyen Drl = new DiemRenLuyen(name , id , 0 , "Kém");
        addStudent(st , Drl);
        addDataToDatabase(st,Drl);
    }//GEN-LAST:event_addStudentActionPerformed

    public void updateDiemRenLuyen(String maSo, String name, int newDrl, String newLoai) throws SQLException {
        String query = "UPDATE DiemRenLuyen SET drl = ?, loai = ? WHERE maSo = ? AND name = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newDrl);
            pstmt.setString(2, newLoai);
            pstmt.setString(3, maSo);
            pstmt.setString(4, name);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void registerForActivity(String name, String maSo, int newDrl, String newLoai) {
    try {
        updateDiemRenLuyen(maSo, name, newDrl, newLoai);
        // Optionally update your in-memory list and table view if necessary
        // This depends on how you're managing your data in memory
        updateTableDrl();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception (e.g., show a dialog to the user)
    }
}
    
    private void joinActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinActionActionPerformed
       String id = "", name = "";
       
       name = jTextField1.getText();
       id = jTextField2.getText();
       
       for(DiemRenLuyen drluyen : drl){
           if(drluyen.getMaSo().equals(id) && drluyen.getName().equals(name)){
                DiemRenLuyen Drl = new DiemRenLuyen(drluyen.getName() , drluyen.getMaSo() , drluyen.congDiem() , drluyen.xepLoai());
                drl.add(Drl);
                tblModel2.addRow(new Object[]{
                    drluyen.getName() , drluyen.getMaSo() , drluyen.getDrl(), drluyen.xepLoai()
                });
                registerForActivity(drluyen.getName() , drluyen.getMaSo() , drluyen.getDrl(), drluyen.xepLoai());
                break;
           }
       }
       int cnt = 0;
       for(DiemRenLuyen drluyen : drl){
           if(drluyen.getMaSo().equals(id) && drluyen.getName().equals(name)){
                tblModel2.removeRow(cnt);
                drl.remove(cnt);
                break;
           }
           ++cnt;
       }
    }//GEN-LAST:event_joinActionActionPerformed

    private void deleleStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleleStudentActionPerformed
        int selectedRow = tblThongTin.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model1 = (DefaultTableModel) tblThongTin.getModel();
            DefaultTableModel model2 = (DefaultTableModel) tblDrl.getModel();

            String maSoDoanVien = (String) model1.getValueAt(selectedRow, 1);

            for (int i = 0; i < model2.getRowCount(); i++) {
                String maSoDrl = (String) model2.getValueAt(i, 1);
                if (maSoDrl.equals(maSoDoanVien)) {

                    model1.removeRow(selectedRow);
                    model2.removeRow(i);
                    break;
                }
            }

            // Xóa sinh viên khỏi danh sách sinh viên
            students.remove(selectedRow);
        }
    }//GEN-LAST:event_deleleStudentActionPerformed

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
            java.util.logging.Logger.getLogger(Qly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Qly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Qly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Qly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Qly().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Qly.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudent;
    private javax.swing.JButton deleleStudent;
    private javax.swing.JTextField j1;
    private javax.swing.JTextField j2;
    private javax.swing.JTextField j3;
    private javax.swing.JTextField j4;
    private javax.swing.JTextField j5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton joinAction;
    private javax.swing.JTable tblDrl;
    private javax.swing.JTable tblThongTin;
    // End of variables declaration//GEN-END:variables
}
