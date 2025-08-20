/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Koneksi.Config;
import Model.Model_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Login {
    private Connection conn;

    public Con_Login() {
         this.conn = Config.getConnection();
    }
    
    public boolean login(String username, String password, Model_Login model) {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
         try {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username.toLowerCase());
            pst.setString(2, password);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                model.setUsername(result.getString("username"));
                model.setPassword(result.getString("password"));
                model.setRole(result.getString("role"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Login gagal! Username atau password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            return false;
        }
    }
}
