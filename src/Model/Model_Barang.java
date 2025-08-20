/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Model_Barang {
    private String kode, nama, deskripsi;
    private int stok, hargaBeli, hargaJual;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String sql;
    
     public Model_Barang() {
        try {
            con = Koneksi.Config.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Model_Barang (String kode, String nama, int stock, int hargaBeli, int hargaJual, String deskripsi) {
        this.kode = kode;
        this.nama = nama;
        this.stok = stock;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.deskripsi = deskripsi;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getStock() { return stok; }
    public void setStock(int stock) { this.stok = stock; }

    public int getHargaBeli() { return hargaBeli; }
    public void setHargaBeli(int hargaBeli) { this.hargaBeli = hargaBeli; }

    public int getHargaJual() { return hargaJual; }
    public void setHargaJual(int hargaJual) { this.hargaJual = hargaJual; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    
     public DefaultTableModel getAll() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Kode");
        tbl.addColumn("Nama");
        tbl.addColumn("Stok");
        tbl.addColumn("Harga Beli");
        tbl.addColumn("Harga Jual");
        tbl.addColumn("Deskripsi");

        try {
            sql = "SELECT * FROM data_roti";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int no = 1;
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    rs.getString("kd_roti"),
                    rs.getString("nama_roti"),
                    rs.getInt("stok"),
                    rs.getInt("harga"),
                    rs.getInt("harga_jual"),
                    rs.getString("deskripsi")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbl;
    }

    public boolean isExist(String kode) {
        try {
            sql = "SELECT * FROM data_roti WHERE kd_roti = '" + kode + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public void insert(String kode, String nama, int stok, int hargaBeli, int hargaJual, String deskripsi) {
        try {
            sql = "INSERT INTO data_roti (kd_roti, nama_roti, stok, harga, harga_jual, deskripsi) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, stok);
            ps.setInt(4, hargaBeli);
            ps.setInt(5, hargaJual);
            ps.setString(6, deskripsi);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String kode, String nama, int stok, int hargaBeli, int hargaJual, String deskripsi) {
        try {
            sql = "UPDATE data_roti SET nama_roti=?, stok=?, harga=?, harga_jual=?, deskripsi=? WHERE kd_roti=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setInt(2, stok);
            ps.setInt(3, hargaBeli);
            ps.setInt(4, hargaJual);
            ps.setString(5, deskripsi);
            ps.setString(6, kode);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String kode) {
        try {
            sql = "DELETE FROM data_roti WHERE kd_roti=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kode);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String autoNumber() {
        String kode = "R001";
        try {
            sql = "SELECT MAX(RIGHT(kd_roti,3)) AS nomor FROM data_roti";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                int next = rs.getInt("nomor") + 1;
                kode = String.format("R%03d", next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kode;
    }
    
    public Model_Barang cariData(String kode) {
    Model_Barang barang = null;
    try {
        String query = "SELECT * FROM data_roti WHERE kd_roti = '" + kode + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            barang = new Model_Barang();
            barang.setKode(kode);  
            barang.setNama(rs.getString("nama_roti"));
            barang.setStock(rs.getInt("stok"));
            barang.setHargaBeli(rs.getInt("harga"));
            barang.setHargaJual(rs.getInt("harga_jual"));
            barang.setDeskripsi(rs.getString("deskripsi"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari data:\n" + e.getMessage());
    }
    return barang;
    }
}
