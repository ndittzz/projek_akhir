/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAOdata.datapelangganDAO;
import DAOImplement.dataImplementPelanggan;
import model.datapelanggan;
import model.modeltabeldatapelanggan;
import view.InputPelanggan;

import javax.swing.*;
import java.util.List;
import model.databuku;
/**
 *
 * @author ASUS
 */
public class datapelangganController {
    
    InputPelanggan frame;
    dataImplementPelanggan impldatapelanggan;
    List<datapelanggan> dp;
    
    public datapelangganController(InputPelanggan frame){
     this.frame = frame;
        impldatapelanggan = new datapelangganDAO();
        dp = impldatapelanggan.getAll();
    
    }
     public void isitabel() {
        dp = impldatapelanggan.getAll();
        modeltabeldatapelanggan mp = new modeltabeldatapelanggan(dp);
        frame.getjTablepelanggan().setModel(mp);
    }
     public void insert() {

        try {
        String nama_pelanggan = frame.getJTxtnama_pelanggan().getText();
        String jenis_kelamin = frame.getJTxtjenis_kelamin().getText();
        String alamat = frame.getjTxtalamat().getText();

        // Memeriksa apakah salah satu JTextField belum diisi
        if (nama_pelanggan.isEmpty() || jenis_kelamin.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar dari metode insert() jika ada field yang belum diisi
        }

        // Mendapatkan kode pelanggan otomatis
        String autoKode = impldatapelanggan.autoKode();
        

        // Membuat objek databuku baru
        datapelanggan pelanggan = new datapelanggan();
        pelanggan.setKd_pelanggan(autoKode); // Menggunakan kode pelanggan otomatis
        pelanggan.setNama_pelanggan(nama_pelanggan);
        pelanggan.setJenis_kelamin(jenis_kelamin);
        pelanggan.setAlamat(alamat);

        

        // Memasukkan objek databuku baru ke database
        impldatapelanggan.insertPelanggan(pelanggan);

        // Mengosongkan field setelah data disimpan
        frame.getJTxtnama_pelanggan().setText("");
        frame.getJTxtjenis_kelamin().setText("");
        frame.getjTxtalamat().setText("");

        // Memperbarui tabel dengan data terbaru
        isitabel();

    } catch (Exception e) {
        e.printStackTrace(); // Logs the exception to the console
        JOptionPane.showMessageDialog(frame, "input gagal: " + e.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
    }
     
     public void update() {
    // Mendapatkan nilai dari JTextField
    String kode = frame.getJTxtkdpelanggan().getText(); // Mendapatkan kode buku dari field
    String nama_pelanggan = frame.getJTxtnama_pelanggan().getText();
    String jenis_kelamin = frame.getJTxtjenis_kelamin().getText();
    String alamat = frame.getjTxtalamat().getText();
    

    // Memeriksa apakah salah satu JTextField belum diisi
    if (kode.isEmpty() || nama_pelanggan.isEmpty() || jenis_kelamin.isEmpty() || alamat.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return; // Keluar dari metode update() jika ada field yang belum diisi
    }

    try {
        // Retrieve existing book details from the database
        datapelanggan existingCustomer = impldatapelanggan.getPelangganByCode(kode);

        if (existingCustomer != null) {
            // Update the fields with new values
            existingCustomer.setNama_pelanggan(nama_pelanggan);
            existingCustomer.setJenis_kelamin(jenis_kelamin);
            existingCustomer.setAlamat(alamat);
            // Update the book in the database
            impldatapelanggan.updatePelanggan(existingCustomer);

            // Clear text fields after updating
            frame.getJTxtkdpelanggan().setText("");
            frame.getJTxtnama_pelanggan().setText("");
            frame.getJTxtjenis_kelamin().setText("");
            frame.getjTxtalamat().setText("");
           
            // Update the table with the latest data
            isitabel();
        } else {
            // Handle case when book with provided code doesn't exist
            JOptionPane.showMessageDialog(frame, "Buku dengan kode tersebut tidak ditemukan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Input tidak sesuai", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
     
     public void delete() {
        String kode = frame.getJTxtkdpelanggan().getText(); // Mendapatkan kode buku dari field

        // Memeriksa apakah kode buku sudah diisi
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Masukkan kode pelanggan yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar dari metode delete() jika kode pelanggan kosong
        }

        // Memanggil metode deletePelanggan dari implementasi databuku
        impldatapelanggan.deletePelanggan(kode);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

        // Mengosongkan field setelah data dihapus
        frame.getJTxtkdpelanggan().setText("");
        frame.getJTxtnama_pelanggan().setText("");
        frame.getJTxtjenis_kelamin().setText("");
        frame.getjTxtalamat().setText("");
       
    }
     
     public void clear() {
        frame.getJTxtkdpelanggan().setText("");
        frame.getJTxtnama_pelanggan().setText("");
        frame.getJTxtjenis_kelamin().setText("");
        frame.getjTxtalamat().setText("");
       
    }
}
