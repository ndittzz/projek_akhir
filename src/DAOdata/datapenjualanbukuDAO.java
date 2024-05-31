/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataImplementpenjualanbuku;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class datapenjualanbukuDAO implements dataImplementpenjualanbuku{
    Connection connection;
    
    final String select = "SELECT * FROM buku";
     
    public datapenjualanbukuDAO(){
        connection = connector.connection();
    }
    @Override
    public List<datapenjualanbuku> getAll() {
    List<datapenjualanbuku> dp = null; 
        try{
            dp = new ArrayList<datapenjualanbuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datapenjualanbuku p = new datapenjualanbuku();
                p.setKd_buku(rs.getString("kd_buku"));
                p.setJudul(rs.getString("judul"));
                p.setStok(rs.getInt("stok"));
                p.setHarga_jual(rs.getInt("harga_jual"));
                dp.add(p);
            }
        }catch(SQLException ex){
            Logger.getLogger(datapelangganDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return dp;      
    }
}
