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
public class modeltabeldatapenjualanbuku extends AbstractTableModel{

    List<datapenjualanbuku> dp;
        public modeltabeldatapenjualanbuku(List<datapenjualanbuku>dp){
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
                return "ID Buku";
            case 1:
                return "Judul Buku";
            case 2:
                return "Stok";
            case 3:
                return "Harga Jual"; 
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dp.get(row).getKd_buku();
            case 1:
                return dp.get(row).getJudul();
            case 2:
                return dp.get(row).getStok();
            case 3:
                return dp.get(row).getHarga_jual();               
            default:
                return null;
        }  
    }
    
}
