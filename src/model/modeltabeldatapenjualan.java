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
public class modeltabeldatapenjualan extends AbstractTableModel{
    List<datapenjualan> dp;
        public modeltabeldatapenjualan(List<datapenjualan>dp){
        this.dp = dp;
    }
    @Override
    public int getRowCount() {
        return dp.size(); 
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
     public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Pelanggan";
            case 1:
                return "Nama Pelanggan";
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
            default:
                return null;
        }     
    }
    
}
