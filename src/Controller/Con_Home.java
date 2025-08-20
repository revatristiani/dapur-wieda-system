/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Model_Home;
import View.Barang;
import View.Home;
import View.Login;
import View.Member;
import View.Penjualan;
import View.Riwayat;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Home {
    private final Home view;
    private final Model_Home user;
    private boolean sudahLogout = false;

    public Con_Home(Home view, Model_Home user) {
        this.view = view;
        this.user = user;

        inisialisasi();
        aturAkses();
        aturEvent();
    }

    private void inisialisasi() {
        view.setLocationRelativeTo(null);
        view.getLblWelcome().setText("Welcome " + user.getUsername() + " [" + user.getRole() + "]");
    }

    private void aturAkses() {
        if ("kasir".equalsIgnoreCase(user.getRole())) {
            view.getBtnBarang().setEnabled(false);     
            view.getBtnMember().setEnabled(false);     
            view.getMenuInputData().setEnabled(false);
        }
    }

    private void aturEvent() {
        // Tombol logout
        view.getBtnLogout().addActionListener(e -> logout());

        // Tombol navigasi
        view.getBtnBarang().addActionListener(e -> bukaBarang());
        view.getBtnMember().addActionListener(e -> bukaMember());
        view.getBtnPenjualan().addActionListener(e -> bukaPenjualan());
        view.getBtnRiwayat().addActionListener(e -> bukaRiwayat());

        // Menu item
        view.getItemPenjualan().addActionListener(e -> bukaPenjualan());
        view.getItemBarang().addActionListener(e -> bukaBarang());
        view.getItemMember().addActionListener(e -> bukaMember());

        // Menu Home
         view.getMenuHome().addMenuListener(new MenuListener() {
        public void menuSelected(MenuEvent e) {
            restartHome();
        }
        public void menuDeselected(MenuEvent e) {}
        public void menuCanceled(MenuEvent e) {}
        });
    }

    public void bukaBarang() {
        new Barang(user).setVisible(true);
        view.dispose();
    }

    public void bukaMember() {
        new Member(user).setVisible(true);
        view.dispose();
    }

    public void bukaPenjualan() {
        new Penjualan(user).setVisible(true);
        view.dispose();
    }

    public void bukaRiwayat() {
        new Riwayat(user).setVisible(true);
        view.dispose();
    }

    public void restartHome() {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Anda sudah logout.");
        return; // jangan buka lagi
    }

    
    Home home = new Home(user);
    home.setVisible(true);
    view.dispose();
    }

    public void logout() {
       if (sudahLogout) return; // cegah dobel logout
    sudahLogout = true;

    int confirm = JOptionPane.showConfirmDialog(view, "Yakin Logout?");
    if (confirm == JOptionPane.YES_OPTION) {
        view.getBtnLogout().setEnabled(false); 
        user.setUsername("");
        user.setPassword("");
        user.setRole("");

        JOptionPane.showMessageDialog(view, "Anda telah logout.");

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });

        view.dispose(); 
    } else {
        sudahLogout = false; 
    }
    }
}