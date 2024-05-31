/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataImplementPelanggan;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class datapelangganDAO implements dataImplementPelanggan{
    Connection connection;
    
     final String select = "SELECT * FROM pelanggan";
     final String insert = "INSERT INTO pelanggan (kd_pelanggan,nama_pelanggan,jenis_kelamin,alamat) VALUES (?,?,?,?);";
    final String update = "UPDATE pelanggan SET nama_pelanggan=?, jenis_kelamin=?, alamat=? WHERE kd_pelanggan=?;";
    final String delete = "delete from pelanggan where kd_pelanggan = ?";
    public datapelangganDAO(){
        connection = connector.connection();
    }
     
    @Override
       public String autoKode() {
    String kode = "PL0001"; // Kode pelanggan default jika tabel kosong
    try {
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM pelanggan ORDER BY kd_pelanggan DESC LIMIT 1");
        if (rs.next()) {
            String lastKode = rs.getString("kd_pelanggan");
            int lastNum = Integer.parseInt(lastKode.replaceAll("\\D", "")); // Ubah ini
            String nextNum = String.format("%04d", lastNum + 1); // Format angka dengan padding nol di depan
            kode = "PL" + nextNum;
        }
        rs.close();
    } catch (SQLException e) {
        Logger.getLogger(datapelangganDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return kode;
}

      

    @Override
    public void insertPelanggan(datapelanggan p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getKd_pelanggan());
            statement.setString(2, p.getNama_pelanggan());
            statement.setString(3, p.getJenis_kelamin());
            statement.setString(4, p.getAlamat());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                p.setKd_pelanggan(rs.getString(1));
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
    public void updatePelanggan(datapelanggan p) {
       PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getNama_pelanggan());
            statement.setString(2, p.getJenis_kelamin());
            statement.setString(3, p.getAlamat());
            statement.setString(4, p.getKd_pelanggan());
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
    public void deletePelanggan(String kd_pelanggan) {
         PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setString(1, kd_pelanggan);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<datapelanggan> getAll() {
        List<datapelanggan> dp = null; 
        try{
            dp = new ArrayList<datapelanggan>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datapelanggan p = new datapelanggan();
                p.setKd_pelanggan(rs.getString("kd_pelanggan"));
                p.setNama_pelanggan(rs.getString("nama_pelanggan"));
                p.setJenis_kelamin(rs.getString("jenis_kelamin"));
                p.setAlamat(rs.getString("alamat"));
                dp.add(p);
            }
        }catch(SQLException ex){
            Logger.getLogger(datapelangganDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return dp;  
    }
    
    @Override
   public datapelanggan getPelangganByCode(String kode) {
    datapelanggan pelanggan = null;
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pelanggan WHERE kd_pelanggan = ?");
        statement.setString(1, kode);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            pelanggan = new datapelanggan();
            pelanggan.setKd_pelanggan(rs.getString("kd_pelanggan"));
            pelanggan.setNama_pelanggan(rs.getString("nama_pelanggan"));
            pelanggan.setJenis_kelamin(rs.getString("jenis_kelamin"));
            pelanggan.setAlamat(rs.getString("alamat"));
 
        }
        rs.close();
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return pelanggan;
}
   
}
