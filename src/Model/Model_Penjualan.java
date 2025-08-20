/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Model_Penjualan {
    private String noTransaksi;
    private String kodeBarang;
    private String namaBarang;
    private String kodeMember;
    private String namaMember;
    private String tanggal;
    private int stock;
    private int jumlah;
    private int harga;
    private int total;
    private int diskon;
    private int bayar;
    private int kembalian;
    private int total2;

    public Model_Penjualan(String noTransaksi, String tanggal, String kodeBarang, String namaBarang,
                       int stock, int jumlah, int harga, int total,
                       String kodeMember, String namaMember, int diskon,
                       int total2, int bayar, int kembalian) {
    this.noTransaksi = noTransaksi;
    this.tanggal = tanggal;
    this.kodeBarang = kodeBarang;
    this.namaBarang = namaBarang;
    this.stock = stock;
    this.jumlah = jumlah;
    this.harga = harga;
    this.total = total;
    this.kodeMember = kodeMember;
    this.namaMember = namaMember;
    this.diskon = diskon;
    this.total2 = total2;
    this.bayar = bayar;
    this.kembalian = kembalian;
}

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
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

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }
}
