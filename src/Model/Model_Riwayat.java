/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Model_Riwayat {
    private String noTransaksi;
    private String tanggal;
    private String kodeMember;
    private String namaMember;
    private String kodeRoti;
    private String namaRoti;
    private int jumlah;
    private int diskon;
    private int total;
    private int bayar;
    private int kembalian;

    
    public Model_Riwayat() {
    }

    public Model_Riwayat(String noTransaksi, String tanggal, String kodeMember, String namaMember,
                         String kodeRoti, String namaRoti, int jumlah, int diskon, int total, int bayar, int kembalian) {
        this.noTransaksi = noTransaksi;
        this.tanggal = tanggal;
        this.kodeMember = kodeMember;
        this.namaMember = namaMember;
        this.kodeRoti = kodeRoti;
        this.namaRoti = namaRoti;
        this.jumlah = jumlah;
        this.diskon = diskon;
        this.total = total;
        this.bayar = bayar;
        this.kembalian = kembalian;
    }

    
    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeMember() {
        return kodeMember;
    }

    public void setKodeMember(String kodeMember) {
        this.kodeMember = kodeMember;
    }

    public String getNamaMember() {
        return namaMember;
    }

    public void setNamaMember(String namaMember) {
        this.namaMember = namaMember;
    }

    public String getKodeRoti() {
        return kodeRoti;
    }

    public void setKodeRoti(String kodeRoti) {
        this.kodeRoti = kodeRoti;
    }

    public String getNamaRoti() {
        return namaRoti;
    }

    public void setNamaRoti(String namaRoti) {
        this.namaRoti = namaRoti;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

    public int getKembalian() {
        return kembalian;
    }

    public void setKembalian(int kembalian) {
        this.kembalian = kembalian;
    }
}
