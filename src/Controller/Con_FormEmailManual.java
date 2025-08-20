/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Model_FormEmailManual;
import View.FormEmailManual;
import java.util.Properties;
import javax.mail.Authenticator;
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
public class Con_FormEmailManual {
    private FormEmailManual view;
    private String isiStruk;

    public Con_FormEmailManual(FormEmailManual view, String isiStruk) {
        this.view = view;
        this.isiStruk = isiStruk;
        initController();
    }

    private void initController() {
        view.getBtnKirim().addActionListener(e -> kirimEmail());
    }

    private void kirimEmail() {
        String emailTujuan = view.getTxtEmailManual().getText().trim();

        if (emailTujuan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Harap masukkan alamat email.");
            return;
        }

        Model_FormEmailManual model = new Model_FormEmailManual(emailTujuan, isiStruk);
        boolean status = kirimEmailKeServer(model);
        if (status) {
            JOptionPane.showMessageDialog(view, "Struk telah dikirim.");
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Gagal mengirim email.");
        }
    }

    private boolean kirimEmailKeServer(Model_FormEmailManual model) {
        final String emailPengirim = "jonathanpys8@gmail.com";
        final String password = "ahrrkifjlfskuhbv";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailPengirim, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailPengirim));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(model.getEmailTujuan()));
            message.setSubject("Struk Pembelian - Toko Roti");
            message.setText(model.getIsiStruk());
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
