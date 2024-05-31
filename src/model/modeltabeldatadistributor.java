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
public class modeltabeldatadistributor extends AbstractTableModel{

    List<datadistributor> dd;
     public modeltabeldatadistributor(List<datadistributor>dd){
        this.dd = dd;
    }
    
    @Override
    public int getRowCount() {
        return dd.size();
    }

    @Override
    public int getColumnCount() {
       return 4;
    }
    
    @Override
     public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Distributor";
            case 1:
                return "Nama Distributor";
            case 2:
                return "Alamat";
            case 3:
                return "Telepon";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dd.get(row).getKd_distributor();
            case 1:
                return dd.get(row).getNama_distributor();
            case 2:
                return dd.get(row).getAlamat();
            case 3:
                return dd.get(row).getTelepon();               
            default:
                return null;
        }    
    }
    
}
