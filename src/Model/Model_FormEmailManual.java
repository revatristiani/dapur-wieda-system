/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author REVALINA SAPUTERA
 */
public class Model_FormEmailManual {
    private String emailTujuan;
    private String isiStruk;

    public Model_FormEmailManual(String emailTujuan, String isiStruk) {
        this.emailTujuan = emailTujuan;
        this.isiStruk = isiStruk;
    }

    public String getEmailTujuan() {
        return emailTujuan;
    }

    public void setEmailTujuan(String emailTujuan) {
        this.emailTujuan = emailTujuan;
    }

    public String getIsiStruk() {
        return isiStruk;
    }

    public void setIsiStruk(String isiStruk) {
        this.isiStruk = isiStruk;
    }
}
