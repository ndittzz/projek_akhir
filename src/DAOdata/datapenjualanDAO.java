/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataImplementPenjualan;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class datapenjualanDAO implements dataImplementPenjualan{
    Connection connection;
    
    final String select = "SELECT * FROM pelanggan";
     
    public datapenjualanDAO(){
        connection = connector.connection();
    }
    
    @Override
    public List<datapenjualan> getAll() {
    List<datapenjualan> dp = null; 
        try{
            dp = new ArrayList<datapenjualan>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datapenjualan p = new datapenjualan();
                p.setKd_pelanggan(rs.getString("kd_pelanggan"));
                p.setNama_pelanggan(rs.getString("nama_pelanggan"));

                dp.add(p);
            }
        }catch(SQLException ex){
            Logger.getLogger(datapelangganDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return dp;      
    }
    
}
