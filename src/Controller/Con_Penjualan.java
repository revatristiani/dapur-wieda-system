/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Model_Penjualan;
import View.Penjualan;
import View.FormEmailManual;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author REVALINA SAPUTERA
 */
public class Con_Penjualan {
    private Penjualan view;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String sql = "";

    public Con_Penjualan(Penjualan view) {
        this.view = view;
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan2", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
        }
    }

    public void cariDataRoti(String kd_roti) {
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM data_roti WHERE kd_roti='" + kd_roti + "'");
            if (rs.next()) {
                view.setNamaBarang(rs.getString("nama_roti"));
                view.setStock(rs.getString("stok"));
                view.setHarga(rs.getString("harga_jual"));

                int stok = Integer.parseInt(rs.getString("stok"));
                if (stok <= 0) {
                    JOptionPane.showMessageDialog(null, "STOK BARANG HABIS\nSilahkan cek persediaan.");
                }
            } else {
                view.clearBarangFields();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data roti: " + e.getMessage());
        }
    }

    public void cariDataMember(String kd_member) {
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM member WHERE kd_member='" + kd_member + "'");
            if (rs.next()) {
                view.setNamaMember(rs.getString("nama"));
                view.setDiskon("10");

                double diskon = 0.1;
                double total = Double.parseDouble(view.getTotal());
                double afterDiskon = total - (total * diskon);
                view.setTotalAkhir(new DecimalFormat("#.##").format(afterDiskon));
            } else {
                view.clearMemberFields();
                JOptionPane.showMessageDialog(null, "Data member tidak ditemukan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data member: " + e.getMessage());
        }
    }

    public void simpanTransaksi(Model_Penjualan model) {
        try {
            sql = "INSERT INTO penjualan (no_transaksi, tgl_transaksi, kd_roti, nama_roti, stok, jumlah, harga, total, kd_member, nama_member, diskon, total2, bayar, kembalian) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, model.getNoTransaksi());
            ps.setString(2, model.getTanggal());
            ps.setString(3, model.getKodeBarang());
            ps.setString(4, model.getNamaBarang());
            ps.setInt(5, model.getStock());
            ps.setInt(6, model.getJumlah());
            ps.setInt(7, model.getHarga());
            ps.setInt(8, model.getTotal());
            ps.setString(9, model.getKodeMember());
            ps.setString(10, model.getNamaMember());
            ps.setInt(11, model.getDiskon());
            ps.setInt(12, model.getTotal2());
            ps.setInt(13, model.getBayar());
            ps.setInt(14, model.getKembalian());

            ps.executeUpdate();
            updateStok(model.getKodeBarang(), model.getStock() - model.getJumlah());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data: " + e.getMessage());
        }
    }

    private void updateStok(String kd_roti, int newStok) {
        try {
            sql = "UPDATE data_roti SET stok = ? WHERE kd_roti = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, newStok);
            ps.setString(2, kd_roti);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal update stok: " + e.getMessage());
        }
    }

    public String autoNumber() {
        String no = "NTR001";
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT MAX(no_transaksi) AS max_no FROM penjualan");
            if (rs.next()) {
                String last = rs.getString("max_no");
                if (last != null) {
                    int num = Integer.parseInt(last.substring(3)) + 1;
                    no = "NTR" + String.format("%03d", num);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate nomor transaksi: " + e.getMessage());
        }
        return no;
    }

    public void setAutoNumber() {
        String no = autoNumber();
        view.setNomorTransaksi(no);
    }

    public boolean validasiAngka(String input, String namaField) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, namaField + " harus berupa angka.");
            return false;
        }
    }

    public void hitungKembalian(String totalAkhir, String bayar) {
        try {
            double total = Double.parseDouble(totalAkhir);
            double bayarD = Double.parseDouble(bayar);
            double kembali = bayarD - total;
            DecimalFormat df = new DecimalFormat("#.##");
            view.setKembalian(df.format(kembali));
        } catch (NumberFormatException e) {
            view.setKembalian("0");
        }
    }

    public void kirimStrukViaEmail(String emailTujuan, String isiStruk) {
        final String emailPengirim = "jonathanpys8@gmail.com";
        final String password = "ahrrkifjlfskuhbv"; // App password Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailPengirim, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailPengirim));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTujuan));
            message.setSubject("Struk Pembelian - Toko Roti");
            message.setText(isiStruk);
            Transport.send(message);

            JOptionPane.showMessageDialog(view, "Email berhasil dikirim.");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(view, "Gagal mengirim email: " + e.getMessage());
        }
    }

    public String buatIsiStruk(String tran, String tgl, String nb, String jlh, String tot2, String byr, String kem, String kasir) {
        return "Terima kasih telah berbelanja di Toko Roti.\n\n"
                + "No Transaksi: " + tran + "\n"
                + "Tanggal     : " + tgl + "\n"
                + "Nama Roti   : " + nb + "\n"
                + "Jumlah      : " + jlh + "\n"
                + "Total Bayar : Rp " + tot2 + "\n"
                + "Bayar       : Rp " + byr + "\n"
                + "Kembali     : Rp " + kem + "\n\n"
                + "Diproses oleh: " + kasir + "\n"
                + "Salam hangat,\nToko Roti.";
    }

    public void prosesTransaksi(String tran, String kb, String nb, String s,
                                String j, String h, String t, String km, String nm, String d,
                                String t2, String b, String k, String kasir) {
        try {
            int stok = Integer.parseInt(s);
            int jumlah = Integer.parseInt(j);
            int harga = Integer.parseInt(h);
            int total = Integer.parseInt(t);
            int diskon = 0;
try {
    diskon = Integer.parseInt(d.isEmpty() ? "0" : d);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(view, "Diskon harus berupa angka.");
    return;
}

 int total2 = Integer.parseInt(t2.isEmpty() ? "0" : t2);
    int bayar = Integer.parseInt(b.isEmpty() ? "0" : b);
    int kembali = Integer.parseInt(k.isEmpty() ? "0" : k);

            String tgl = view.getTanggalTransaksi();
            Model_Penjualan model = new Model_Penjualan(tran, tgl, kb, nb, stok, jumlah, harga, total, km, nm, diskon, total2, bayar, kembali);
            simpanTransaksi(model);

            view.clearBarangFields();
            view.clearMemberFields();
            setAutoNumber();

            String isiStruk = buatIsiStruk(tran, tgl, nb, j, t2, b, k, kasir);
            String[] options = {"Cetak Struk", "Kirim Email", "Batal"};
            int pilihan = JOptionPane.showOptionDialog(view, "Pilih tindakan:", "Struk",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (pilihan == 0) {
                JOptionPane.showMessageDialog(view, isiStruk, "Struk Pembelian", JOptionPane.INFORMATION_MESSAGE);
            } else if (pilihan == 1) {
                String emailTujuan = "";
                try {
                    PreparedStatement psEmail = con.prepareStatement("SELECT email FROM member WHERE kd_member = ?");
                    psEmail.setString(1, km);
                    ResultSet rs = psEmail.executeQuery();
                    if (rs.next()) {
                        emailTujuan = rs.getString("email");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(view, "Gagal mengambil email member: " + e.getMessage());
                }

                if (!emailTujuan.isEmpty()) {
                    kirimStrukViaEmail(emailTujuan, isiStruk);
                } else {
                    FormEmailManual form = new FormEmailManual(this, isiStruk); // this = Con_Penjualan
                form.setVisible(true);
                }
            }

        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Format angka tidak valid: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal menyimpan transaksi: " + e.getMessage());
        }
    }
    public void hitungTotal(String harga, String jumlah) {
    try {
        int h = Integer.parseInt(harga);
        int j = Integer.parseInt(jumlah);
        int total = h * j;
        view.setTotal(String.valueOf(total));
    } catch (NumberFormatException e) {
        view.setTotal("0");
    }
    }
    public String getTanggalTransaksi() {
    java.util.Date date = view.getTanggal(); // panggil dari view
    if (date != null) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    } else {
        return null;
    }
}
}

