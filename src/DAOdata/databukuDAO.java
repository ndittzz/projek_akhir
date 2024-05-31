/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataImplementBuku;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class databukuDAO implements dataImplementBuku{
    
    Connection connection;
    
    final String select = "SELECT * FROM buku";
    final String insert = "INSERT INTO buku (kd_buku,judul,jenis,penulis, penerbit,tahun,stok,harga_pokok,harga_jual) VALUES (?,?,?,?,?,?,?,?,?);";
    final String update = "UPDATE buku SET judul=?, jenis=?, penulis=?, penerbit=?, tahun=?,stok=?, harga_pokok=?, harga_jual=? WHERE kd_buku=?;";
    final String delete = "delete from buku where kd_buku = ?";
    
    public databukuDAO(){
        connection = connector.connection();
    }
    
    @Override
    public String autoKode() {
        String kode = "K0001"; // Kode buku default jika tabel kosong
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM buku ORDER BY kd_buku DESC LIMIT 1");
            if (rs.next()) {
                String lastKode = rs.getString("kd_buku");
                int lastNum = Integer.parseInt(lastKode.substring(1)); // Ambil angka dari kode terakhir
                String nextNum = String.format("%04d", lastNum + 1); // Format angka dengan padding nol di depan
                kode = "K" + nextNum;
            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return kode;
    }

    @Override
    public void insertBuku(databuku p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getKd_buku());
            statement.setString(2, p.getJudul());
            statement.setString(3, p.getJenis());
            statement.setString(4, p.getPenulis());
            statement.setString(5, p.getPenerbit());
            statement.setInt(6, p.getTahun());
            statement.setInt(7, p.getStok()); 
            statement.setInt(8, p.getHarga_pokok()); 
            statement.setInt(9, p.getHarga_jual());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                p.setKd_buku(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void updateBuku(databuku p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getJenis());
            statement.setString(3, p.getPenulis());
            statement.setString(4, p.getPenerbit());
            statement.setInt(5, p.getTahun());
            statement.setInt(6, p.getStok()); 
            statement.setInt(7, p.getHarga_pokok()); 
            statement.setInt(8, p.getHarga_jual()); 
            statement.setString(9, p.getKd_buku());
            statement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }        
    }


    @Override
    public void deleteBuku(String kd_buku) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setString(1, kd_buku);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }    }

    @Override
    public List<databuku> getAll() {
        List<databuku> db = null; 
        try{
            db = new ArrayList<databuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                databuku b = new databuku();
                b.setKd_buku(rs.getString("kd_buku"));
                b.setJudul(rs.getString("judul"));
                b.setJenis(rs.getString("jenis"));
                b.setPenulis(rs.getString("penulis"));
                b.setPenerbit(rs.getString("penerbit"));
                b.setTahun(rs.getInt("tahun"));
                b.setStok(rs.getInt("stok"));
                b.setHarga_pokok(rs.getInt("harga_pokok"));
                b.setHarga_jual(rs.getInt("harga_jual"));
                db.add(b);
            }
        }catch(SQLException ex){
            Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return db;  
    }

    @Override
    public databuku getBookByCode(String kode) {
    databuku book = null;
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM buku WHERE kd_buku = ?");
        statement.setString(1, kode);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            book = new databuku();
            book.setKd_buku(rs.getString("kd_buku"));
            book.setJudul(rs.getString("judul"));
            book.setJenis(rs.getString("jenis"));
            book.setPenulis(rs.getString("penulis"));
            book.setPenerbit(rs.getString("penerbit"));
            book.setTahun(rs.getInt("tahun"));
            book.setStok(rs.getInt("stok"));
            book.setHarga_pokok(rs.getInt("harga_pokok"));
            book.setHarga_jual(rs.getInt("harga_jual"));
        }
        rs.close();
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return book;
}

    
}
