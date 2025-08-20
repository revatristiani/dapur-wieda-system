/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Model_Register;
import View.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Register {
    private Connection conn;
    private Statement stat;
    private ResultSet res;

    public Con_Register() {
        conn = Koneksi.Config.getConnection();
    }

   
    public void register(Model_Register user) {
        try {
            stat = conn.createStatement();

            String sqlCek = "SELECT * FROM user WHERE username='" + user.getUsername() + "'";
            res = stat.executeQuery(sqlCek);

            if (res.next()) {
                JOptionPane.showMessageDialog(null, "Username sudah digunakan, coba yang lain.");
            } else {
                
                String sqlInsert = "INSERT INTO user (username, password, role) VALUES ('"
                        + user.getUsername() + "', '"
                        + user.getPassword() + "', '"
                        + user.getRole() + "')";
                stat.executeUpdate(sqlInsert);

                JOptionPane.showMessageDialog(null, "Pendaftaran berhasil!");
                new Login().setVisible(true); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat register:\n" + e.getMessage());
        }
    }
}
