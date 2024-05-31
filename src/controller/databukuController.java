/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAOdata.databukuDAO;
import DAOImplement.dataImplementBuku;
import model.databuku;
import model.modeltabeldatabuku;
import view.InputBuku;

import javax.swing.*;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class databukuController {
    InputBuku frame;
    dataImplementBuku impldatabuku;
    List<databuku> db;

    public databukuController(InputBuku frame) {
        this.frame = frame;
        impldatabuku = new databukuDAO();
        db = impldatabuku.getAll();
    }

    public void isitabel() {
        db = impldatabuku.getAll();
        modeltabeldatabuku mp = new modeltabeldatabuku(db);
        frame.getjTable1().setModel(mp);
    }
    
    public void insert() {
        // Mendapatkan nilai dari JTextField
        String penerbit = frame.getJTxtpenerbit().getText();
        String penulis = frame.getJTxtpenulis().getText();
        String pokok = frame.getJTxtpokok().getText();
        String stok = frame.getJTxtstok().getText();
        String jual = frame.getJTxtjual().getText();
        String tahun = frame.getJTxttahun().getText();
        String jenis = (String) frame.getJTxtjenis().getSelectedItem();
        String judul = frame.getJTxtjudul().getText();

        // Memeriksa apakah salah satu JTextField belum diisi
        if (judul.isEmpty() || jenis.isEmpty() || penerbit.isEmpty() || penulis.isEmpty() || pokok.isEmpty() || stok.isEmpty() || jual.isEmpty() || tahun.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar dari metode insert() jika ada field yang belum diisi
        }

        try {
            // Mendapatkan kode buku otomatis
            String autoKode = impldatabuku.autoKode();

            // Membuat objek databuku baru
            databuku buku = new databuku();
            buku.setKd_buku(autoKode); // Menggunakan kode buku otomatis
            buku.setJudul(judul);
            buku.setJenis(jenis);
            buku.setPenulis(penulis);
            buku.setPenerbit(penerbit);
            buku.setTahun(Integer.parseInt(tahun));
            buku.setStok(Integer.parseInt(stok));
            buku.setHarga_pokok(Integer.parseInt(pokok));
            buku.setHarga_jual(Integer.parseInt(jual));

            // Memasukkan objek databuku baru ke database
            impldatabuku.insertBuku(buku);

            // Mengosongkan field setelah data disimpan
            frame.getJTxtpenerbit().setText("");
            frame.getJTxtpenulis().setText("");
            frame.getJTxtpokok().setText("");
            frame.getJTxtstok().setText("");
            frame.getJTxtjual().setText("");
            frame.getJTxttahun().setText("");
            frame.getJTxtjudul().setText("");

            // Memperbarui tabel dengan data terbaru
            isitabel();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Pastikan semua input berupa angka pada 'Pokok', 'Stok', 'Harga Jual', dan 'Tahun Terbit'", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
  public void update() {
    // Mendapatkan nilai dari JTextField
    String kode = frame.getJTxtkode().getText(); // Mendapatkan kode buku dari field
    String penerbit = frame.getJTxtpenerbit().getText();
    String penulis = frame.getJTxtpenulis().getText();
    String pokok = frame.getJTxtpokok().getText();
    String stok = frame.getJTxtstok().getText();
    String jual = frame.getJTxtjual().getText();
    String tahun = frame.getJTxttahun().getText();
    String jenis = (String) frame.getJTxtjenis().getSelectedItem();
    String judul = frame.getJTxtjudul().getText();

    // Memeriksa apakah salah satu JTextField belum diisi
    if (kode.isEmpty() || judul.isEmpty() || jenis.isEmpty() || penerbit.isEmpty() || penulis.isEmpty() || pokok.isEmpty() || stok.isEmpty() || jual.isEmpty() || tahun.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return; // Keluar dari metode update() jika ada field yang belum diisi
    }

    try {
        // Retrieve existing book details from the database
        databuku existingBook = impldatabuku.getBookByCode(kode);

        if (existingBook != null) {
            // Update the fields with new values
            existingBook.setJudul(judul);
            existingBook.setJenis(jenis);
            existingBook.setPenulis(penulis);
            existingBook.setPenerbit(penerbit);
            existingBook.setTahun(Integer.parseInt(tahun));
            existingBook.setStok(Integer.parseInt(stok));
            existingBook.setHarga_pokok(Integer.parseInt(pokok));
            existingBook.setHarga_jual(Integer.parseInt(jual));

            // Update the book in the database
            impldatabuku.updateBuku(existingBook);

            // Clear text fields after updating
            frame.getJTxtkode().setText("");
            frame.getJTxtpenerbit().setText("");
            frame.getJTxtpenulis().setText("");
            frame.getJTxtpokok().setText("");
            frame.getJTxtstok().setText("");
            frame.getJTxtjual().setText("");
            frame.getJTxttahun().setText("");
            frame.getJTxtjudul().setText("");

            // Update the table with the latest data
            isitabel();
        } else {
            // Handle case when book with provided code doesn't exist
            JOptionPane.showMessageDialog(frame, "Buku dengan kode tersebut tidak ditemukan", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Pastikan semua input berupa angka pada 'Pokok', 'Stok', 'Harga Jual', dan 'Tahun Terbit'", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}

    public void delete() {
        String kode = frame.getJTxtkode().getText(); // Mendapatkan kode buku dari field

        // Memeriksa apakah kode buku sudah diisi
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Masukkan kode buku yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Keluar dari metode delete() jika kode buku kosong
        }

        // Memanggil metode deleteBuku dari implementasi databuku
        impldatabuku.deleteBuku(kode);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

        // Mengosongkan field setelah data dihapus
        frame.getJTxtkode().setText("");
        frame.getJTxtpenerbit().setText("");
        frame.getJTxtpenulis().setText("");
        frame.getJTxtpokok().setText("");
        frame.getJTxtstok().setText("");
        frame.getJTxtjual().setText("");
        frame.getJTxttahun().setText("");
        frame.getJTxtjudul().setText("");
    }
    
    public void clear() {
        frame.getJTxtkode().setText("");
        frame.getJTxtpenerbit().setText("");
        frame.getJTxtpenulis().setText("");
        frame.getJTxtpokok().setText("");
        frame.getJTxtstok().setText("");
        frame.getJTxtjual().setText("");
        frame.getJTxttahun().setText("");
        frame.getJTxtjudul().setText("");
    }


    




}
