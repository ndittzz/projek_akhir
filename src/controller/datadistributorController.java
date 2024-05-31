/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAOdata.datadistributorDAO;
import DAOImplement.dataImplementDistributor;
import model.datadistributor;
import model.modeltabeldatadistributor;
import view.InputDistributor;

import javax.swing.*;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class datadistributorController {
     InputDistributor frame;
    dataImplementDistributor impldatadistributor;
    List<datadistributor> dd;
    
    public datadistributorController(InputDistributor frame){
     this.frame = frame;
        impldatadistributor = new datadistributorDAO();
        dd = impldatadistributor.getAll();
    
    }
     public void isitabel() {
        dd = impldatadistributor.getAll();
        modeltabeldatadistributor mp = new modeltabeldatadistributor(dd);
        frame.getjTabledis().setModel(mp);
    }
     
     public void insert() {

        try {
       String nama_distributor = frame.getJTxtnamadistributor().getText();
       String alamat=frame.getJTxtalamatdistributor().getText();
       String telepon = frame.getJTxttelepon().getText();

        // Memeriksa apakah salah satu JTextField belum diisi
        if (nama_distributor.isEmpty() || alamat.isEmpty() || telepon.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar dari metode insert() jika ada field yang belum diisi
        }

        // Mendapatkan kode pelanggan otomatis
        String autoKode = impldatadistributor.autoKode();
        

        // Membuat objek databuku baru
        datadistributor distributor = new datadistributor();
        distributor.setKd_distributor(autoKode); // Menggunakan kode distributor otomatis
        distributor.setNama_distributor(nama_distributor);
        distributor.setAlamat(alamat);
        distributor.setTelepon(telepon);

        

        // Memasukkan objek datadistributor baru ke database
        impldatadistributor.insertDistributor(distributor);

        // Mengosongkan field setelah data disimpan
        frame.getJTxtnamadistributor().setText("");
        frame.getJTxtalamatdistributor().setText("");
        frame.getJTxttelepon().setText("");

        // Memperbarui tabel dengan data terbaru
        isitabel();

    } catch (Exception e) {
        e.printStackTrace(); // Logs the exception to the console
        JOptionPane.showMessageDialog(frame, "input gagal: " + e.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
    }
     
      public void update() {
    // Mendapatkan nilai dari JTextField
    String kode = frame.getJTxtkodedistributor().getText(); // Mendapatkan kode buku dari field
    String nama_distributor = frame.getJTxtnamadistributor().getText();
    String alamat= frame.getJTxtalamatdistributor().getText();
    String telepon = frame.getJTxttelepon().getText();
    

    // Memeriksa apakah salah satu JTextField belum diisi
    if (kode.isEmpty() || nama_distributor.isEmpty() || alamat.isEmpty() || telepon.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return; // Keluar dari metode update() jika ada field yang belum diisi
    }

    try {
        // Retrieve existing book details from the database
        datadistributor existingDistributor = impldatadistributor.getDistributorByCode(kode);

        if (existingDistributor != null) {
            // Update the fields with new values
            existingDistributor.setNama_distributor(nama_distributor);
            existingDistributor.setAlamat(alamat);
            existingDistributor.setTelepon(telepon);
            // Update the book in the database
            impldatadistributor.updateDistributor(existingDistributor);

            // Clear text fields after updating
            frame.getJTxtkodedistributor().setText("");
            frame.getJTxtnamadistributor().setText("");
            frame.getJTxtalamatdistributor().setText("");
            frame.getJTxttelepon().setText("");
           
            // Update the table with the latest data
            isitabel();
        } else {
            // Handle case when book with provided code doesn't exist
            JOptionPane.showMessageDialog(frame, "Distributor dengan kode tersebut tidak ditemukan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Input tidak sesuai", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
      
    public void delete() {
        String kode = frame.getJTxtkodedistributor().getText(); // Mendapatkan kode buku dari field

        // Memeriksa apakah kode buku sudah diisi
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Masukkan kode pelanggan yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar dari metode delete() jika kode pelanggan kosong
        }

        // Memanggil metode deletePelanggan dari implementasi databuku
        impldatadistributor.deleteDistributor(kode);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

        // Mengosongkan field setelah data dihapus
        frame.getJTxtkodedistributor().setText("");
        frame.getJTxtnamadistributor().setText("");
        frame.getJTxtalamatdistributor().setText("");
        frame.getJTxttelepon().setText("");
       
    }
    
     public void clear() {
        frame.getJTxtkodedistributor().setText("");
        frame.getJTxtnamadistributor().setText("");
      frame.getJTxtalamatdistributor().setText("");
      frame.getJTxttelepon().setText("");
       
    }
      
      
}
