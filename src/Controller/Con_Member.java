/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Model_Home;
import View.Home;
import View.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Member {
     private Connection con;
    private Member view;
    private Model_Home user;

    public Con_Member(Member view, Model_Home user) {
        this.view = view;
        this.user = user;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan2", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
        
        this.view.getMenuHome().addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                new Home(user).setVisible(true);
                view.dispose();
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });
    }

    public String generateNewNumber() {
        String newNumber = "MEM001";
        try {
            Statement st = con.createStatement();
            String sql = "SELECT MAX(kd_member) AS max_number FROM member";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String maxNumber = rs.getString("max_number");
                if (maxNumber != null) {
                    int num = Integer.parseInt(maxNumber.substring(3));
                    num++;
                    newNumber = "MEM" + String.format("%03d", num);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newNumber;
    }

    public DefaultTableModel tampilData() {
        DefaultTableModel data = new DefaultTableModel();
        data.addColumn("No");
        data.addColumn("Kode Member");
        data.addColumn("Nama");
        data.addColumn("No Tlp");
        data.addColumn("Alamat");
        data.addColumn("Email");

        try {
            int i = 1;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM member");

            while (rs.next()) {
                data.addRow(new Object[]{
                    "" + i++,
                    rs.getString("kd_member"),
                    rs.getString("nama"),
                    rs.getString("no_tlp"),
                    rs.getString("alamat"),
                    rs.getString("email")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Data: " + e.getMessage());
        }
        return data;
    }

    public boolean isDataExists(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS count FROM member WHERE kd_member = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt("count") > 0) return true;
        } catch (SQLException e) {
            System.out.println("Error checking data existence: " + e.getMessage());
        }
        return false;
    }
    
    public ResultSet cariMemberById(String id) {
    try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM member WHERE kd_member = ?");
        ps.setString(1, id);
        return ps.executeQuery();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Kesalahan saat mencari data: " + e.getMessage());
        return null;
        }
    }
    
    public boolean insertMember(String id, String nama, String tlp, String alamat, String email) {
    try {
        String sql = "INSERT INTO member (kd_member, nama, no_tlp, alamat, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, nama);
        ps.setString(3, tlp);
        ps.setString(4, alamat);
        ps.setString(5, email);
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal insert: " + e.getMessage());
        return false;
        }
    }
    
    public boolean updateMember(String id, String nama, String tlp, String alamat, String email) {
    try {
        String sql = "UPDATE member SET nama=?, no_tlp=?, alamat=?, email=? WHERE kd_member=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nama);
        ps.setString(2, tlp);
        ps.setString(3, alamat);
        ps.setString(4, email);
        ps.setString(5, id);
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal update: " + e.getMessage());
        return false;
        }
    }
    
    public boolean deleteMember(String id) {
    try {
        String sql = "DELETE FROM member WHERE kd_member=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal hapus: " + e.getMessage());
        return false;
        }
    }
    
    public void bukaHome() {
    view.dispose(); // Menutup form Barang
    new View.Home(user).setVisible(true); // Buka form Home
}
}
