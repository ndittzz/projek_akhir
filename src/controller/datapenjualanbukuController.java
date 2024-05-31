/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAOdata.datapenjualanbukuDAO;
import DAOImplement.dataImplementpenjualanbuku;
import model.datapenjualanbuku;
import model.modeltabeldatapenjualanbuku;
import view.Penjualan;

import javax.swing.*;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class datapenjualanbukuController {
    Penjualan frame;
    dataImplementpenjualanbuku impldatapenjualanbuku;
    List<datapenjualanbuku> db;

    public datapenjualanbukuController(Penjualan frame) {
        this.frame = frame;
        impldatapenjualanbuku = new datapenjualanbukuDAO();
        db = impldatapenjualanbuku.getAll();
    }

    public void isitabel() {
        db = impldatapenjualanbuku.getAll();
        modeltabeldatapenjualanbuku mp = new modeltabeldatapenjualanbuku(db);
        frame.getjTable1().setModel(mp);
    }
}
