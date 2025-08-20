/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Model_Riwayat;
import View.Riwayat;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Riwayat {
    private Connection con;
    private Riwayat view;

    public Con_Riwayat(Riwayat view) {
        this.view = view;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan2", "root", "");
    } catch (Exception e) {
        System.out.println("Koneksi gagal: " + e.getMessage());
    }
}


    public ArrayList<Model_Riwayat> ambilDataTransaksi() {
        ArrayList<Model_Riwayat> list = new ArrayList<>();
        String query = "SELECT * FROM penjualan";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Model_Riwayat r = new Model_Riwayat(
                    rs.getString("no_transaksi"),
                    rs.getString("tgl_transaksi"),
                    rs.getString("kd_member"),
                    rs.getString("nama_member"),
                    rs.getString("kd_roti"),
                    rs.getString("nama_roti"),
                    rs.getInt("jumlah"),
                    rs.getInt("diskon"),
                    rs.getInt("total"),
                    rs.getInt("bayar"),
                    rs.getInt("kembalian")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error ambil data: " + e.getMessage());
        }
        return list;
    }

    public void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "No Transaksi", "Tanggal", "Kode Member", "Nama Member",
            "Kode Roti", "Nama Roti", "Jumlah", "Diskon", "Total", "Bayar", "Kembalian"
        });

        for (Model_Riwayat r : ambilDataTransaksi()) {
            model.addRow(new Object[]{
                r.getNoTransaksi(), r.getTanggal(), r.getKodeMember(), r.getNamaMember(),
                r.getKodeRoti(), r.getNamaRoti(), r.getJumlah(), r.getDiskon(),
                r.getTotal(), r.getBayar(), r.getKembalian()
            });
        }

        view.getTblModel().setModel(model); 
    }
    
    public void exportHariIni(File file) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Riwayat Transaksi Hari Ini");

        TableModel model = view.getTblModel().getModel(); // Ambil model dari view

        // Header
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            headerRow.createCell(i).setCellValue(model.getColumnName(i));
        }

        // Isi data sesuai tanggal hari ini
        int rowIndex = 1;
        for (int i = 0; i < model.getRowCount(); i++) {
            String tanggal = model.getValueAt(i, 1).toString();
            if (tanggal.equals(today)) {
                Row row = sheet.createRow(rowIndex++);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    row.createCell(j).setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        workbook.close();

        JOptionPane.showMessageDialog(null, "Data berhasil diexport ke Excel!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Gagal export data: " + ex.getMessage());
        ex.printStackTrace();
        }
    }
}
