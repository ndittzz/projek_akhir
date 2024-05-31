/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataImplementPenjualanfinal;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class datapenjualanfinalDAO implements dataImplementPenjualanfinal{
    Connection connection;
    
    final String select = "SELECT * FROM penjualan";
     
    public datapenjualanfinalDAO(){
        connection = connector.connection();
    }
    @Override
    public List<datapenjualanfinal> getAll() {
    List<datapenjualanfinal> dp = null; 
        try{
            dp = new ArrayList<datapenjualanfinal>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datapenjualanfinal p = new datapenjualanfinal();
                p.setKd_pretransaksi(rs.getString("kd_pretransaksi"));
                p.setKd_transaksi(rs.getString("kd_transaksi"));
                p.setKd_pelanggan(rs.getString("kd_pelanggan"));
                p.setKd_buku(rs.getString("kd_buku"));
                p.setJumlah(rs.getInt("jumlah"));
                p.setSub_total(rs.getInt("sub_total"));
                dp.add(p);
            }
        }catch(SQLException ex){
            Logger.getLogger(datapelangganDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return dp;      
    }
}
