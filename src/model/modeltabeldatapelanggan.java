/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author ASUS
 */
public class modeltabeldatapelanggan extends AbstractTableModel{

    List<datapelanggan> dp;
    public modeltabeldatapelanggan(List<datapelanggan>dp){
        this.dp = dp;
    }
    
    
    @Override
    public int getRowCount() {
        return dp.size(); 
   }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
     public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Pelanggan";
            case 1:
                return "Nama Pelanggan";
            case 2:
                return "Jenis Kelamin";
            case 3:
                return "Alamat";
            default:
                return null;
        }
    }

    @Override
     public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dp.get(row).getKd_pelanggan();
            case 1:
                return dp.get(row).getNama_pelanggan();
            case 2:
                return dp.get(row).getJenis_kelamin();
            case 3:
                return dp.get(row).getAlamat();               
            default:
                return null;
        }    
    }
    
}
