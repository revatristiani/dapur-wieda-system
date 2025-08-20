/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ZAKY
 */
public class Config {
    private static Connection con;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/penjualan2", "root", "");
            System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal\n" + e.getMessage());
            System.exit(0);
        }
        return con;
    }
}
