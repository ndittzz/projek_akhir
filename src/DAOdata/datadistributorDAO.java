/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataImplementDistributor;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ASUS
 */
public class datadistributorDAO implements dataImplementDistributor{
    Connection connection;
    final String select = "SELECT * FROM distributor";
    final String insert = "INSERT INTO distributor (kd_distributor,nama_distributor,alamat,telepon) VALUES (?,?,?,?);";
    final String delete = "delete from distributor where kd_distributor = ?";
    final String update = "UPDATE distributor SET nama_distributor=?, alamat=?, telepon=? WHERE kd_distributor=?;";
    public datadistributorDAO(){
        connection = connector.connection();
    }
    
    
    @Override
    public String autoKode() {
    String kode = "D0001"; // Kode pelanggan default jika tabel kosong
    try {
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM distributor ORDER BY kd_distributor DESC LIMIT 1");
        if (rs.next()) {
            String lastKode = rs.getString("kd_distributor");
            int lastNum = Integer.parseInt(lastKode.replaceAll("\\D", "")); // Ubah ini
            String nextNum = String.format("%04d", lastNum + 1); // Format angka dengan padding nol di depan
            kode = "D" + nextNum;
        }
        rs.close();
    } catch (SQLException e) {
        Logger.getLogger(datadistributorDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return kode;
}
    
    
    @Override
     public List<datadistributor> getAll() {
        List<datadistributor> dd = null; 
        try{
            dd = new ArrayList<datadistributor>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datadistributor d = new datadistributor();
                d.setKd_distributor(rs.getString("kd_distributor"));
                d.setNama_distributor(rs.getString("nama_distributor"));
                d.setAlamat(rs.getString("alamat"));
                d.setTelepon(rs.getString("telepon"));
                dd.add(d);
            }
        }catch(SQLException ex){
            Logger.getLogger(datadistributorDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return dd;  
    }

    @Override
    public void insertDistributor(datadistributor d) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, d.getKd_distributor());
            statement.setString(2, d.getNama_distributor());
            statement.setString(3, d.getAlamat());
            statement.setString(4, d.getTelepon());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                d.setKd_distributor(rs.getString(1));
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
    public void updateDistributor(datadistributor d) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, d.getNama_distributor());
            statement.setString(2, d.getAlamat());
            statement.setString(3, d.getTelepon());
            statement.setString(4, d.getKd_distributor());
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
    public void deleteDistributor(String kd_distributor) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setString(1, kd_distributor);
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
    public datadistributor getDistributorByCode(String kode) {
         datadistributor distributor = null;
        try {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM distributor WHERE kd_distributor = ?");
        statement.setString(1, kode);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            distributor = new datadistributor();
            distributor.setKd_distributor(rs.getString("kd_distributor"));
            distributor.setNama_distributor(rs.getString("nama_distributor"));
            distributor.setAlamat(rs.getString("alamat"));
            distributor.setTelepon(rs.getString("alamat"));
 
        }
        rs.close();
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return distributor;
    }

    
}
