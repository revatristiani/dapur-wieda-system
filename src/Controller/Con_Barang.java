/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Model_Barang;
import Model.Model_Home;
import View.Barang;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Barang {
    private Barang view;
    private Model_Barang model;
    private Model_Home user;

    public Con_Barang(Barang view, Model_Home user) {
        this.view = view;
        this.user = user;
        model = new Model_Barang(); 
        tampilData();
        view.setAutoKode(model.autoNumber());
    }

    public void simpanData() {
        try {
            String kode = view.getKode();
            String nama = view.getNama();
            int stok = Integer.parseInt(view.getStok());
            int hargaBeli = Integer.parseInt(view.getHargaBeli());
            int hargaJual = Integer.parseInt(view.getHargaJual());
            String deskripsi = view.getDeskripsi();

            if (kode.isEmpty() || nama.isEmpty() || deskripsi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi.");
                return;
            }

            model.insert(kode, nama, stok, hargaBeli, hargaJual, deskripsi);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            tampilData();
            resetForm();
            view.setAutoKode(model.autoNumber());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Stok dan Harga harus berupa angka.");
        }
    }

    public void updateData() {
        try {
            String kode = view.getKode();
            String nama = view.getNama();
            int stok = Integer.parseInt(view.getStok());
            int hargaBeli = Integer.parseInt(view.getHargaBeli());
            int hargaJual = Integer.parseInt(view.getHargaJual());
            String deskripsi = view.getDeskripsi();

            if (!model.isExist(kode)) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk diedit.");
                return;
            }

            model.update(kode, nama, stok, hargaBeli, hargaJual, deskripsi);
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
            tampilData();
            resetForm();
            view.setAutoKode(model.autoNumber());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Stok dan Harga harus berupa angka.");
        }
    }

    public void deleteData() {
        String kode = view.getKode();
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
            return;
        }

        if (!model.isExist(kode)) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk dihapus.");
            return;
        }

        model.delete(kode);
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        tampilData();
        resetForm();
        view.setAutoKode(model.autoNumber());
    }

    public void tampilData() {
        DefaultTableModel tableModel = model.getAll();
        view.setTableModel(tableModel);
    }

    public void resetForm() {
        view.clearForm();
    }

    public void klikTable(int row) {
        String kode = view.getTableValue(row, 1);
        String nama = view.getTableValue(row, 2);
        String stok = view.getTableValue(row, 3);
        String hBeli = view.getTableValue(row, 4);
        String hJual = view.getTableValue(row, 5);
        String desc = view.getTableValue(row, 6);

        view.setForm(kode, nama, stok, hBeli, hJual, desc);
    }
    
    public Model_Barang cariData(String kode) {
       return model.cariData(kode);
    }
    
    public String getAutoKode() {
        return model.autoNumber();
    }
    
    public void bukaHome() {
    view.dispose(); // Menutup form Barang
    new View.Home(user).setVisible(true); // Buka form Home
}

public void bukaMember() {
    view.dispose();
    new View.Member(user).setVisible(true);
}

public void bukaPenjualan() {
    view.dispose();
    new View.Penjualan(user).setVisible(true);
}

public void bukaRiwayat() {
    view.dispose();
    new View.Riwayat(user).setVisible(true);
}
}
