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
public class modeltabeldatabuku extends AbstractTableModel{

    List<databuku> db;
    public modeltabeldatabuku(List<databuku>db){
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }
    
        @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Buku";
            case 1:
                return "Judul Buku";
            case 2:
                return "Jenis Buku";
            case 3:
                return "Penulis";
            case 4:
                return "Penerbit";
            case 5:
                return "Tahun";
            case 6:
                return "Stok";
            case 7:
                return "Harga Pokok";
            case 8:
                return "Harga Jual"; 
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return db.get(row).getKd_buku();
            case 1:
                return db.get(row).getJudul();
            case 2:
                return db.get(row).getJenis();
            case 3:
                return db.get(row).getPenulis();
            case 4:
                return db.get(row).getPenerbit();
            case 5:
                return db.get(row).getTahun();
            case 6:
                return db.get(row).getStok();
            case 7:
                return db.get(row).getHarga_pokok();
            case 8:
                return db.get(row).getHarga_jual();               
            default:
                return null;
        }    
    }
    
}
