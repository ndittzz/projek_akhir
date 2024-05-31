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
public class modeltabeldatapenjualanfinal extends AbstractTableModel{
    List<datapenjualanfinal> dp;
        public modeltabeldatapenjualanfinal(List<datapenjualanfinal>dp){
        this.dp = dp;
    }
        
    @Override
    public int getRowCount() {
        return dp.size(); 
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    
    @Override
     public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID PRETransaksi";
            case 1:
                return "ID Transaksi";
            case 2:
                return "ID Pelanggan";
            case 3:
                return "ID Buku"; 
            case 4:
                return "Jumlah"; 
            case 5:
                return "Sub Total"; 
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dp.get(row).getKd_pretransaksi();
            case 1:
                return dp.get(row).getKd_transaksi();
            case 2:
                return dp.get(row).getKd_pelanggan();
            case 3:
                return dp.get(row).getKd_buku(); 
            case 4:
                return dp.get(row).getJumlah();  
            case 5:
                return dp.get(row).getSub_total();  
            default:
                return null;
        }  
    }
}
    
