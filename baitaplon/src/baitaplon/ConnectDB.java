/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baitaplon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anhbando
 */
public class ConnectDB {
    public Connection conn = null;
    public Connection getConnection() throws SQLException {
        String path = "jdbc:mysql://localhost:3306/QL_ttdoanvien";
        this.conn = DriverManager.getConnection(path,"root","");
        return this.conn;
    }
    
}
