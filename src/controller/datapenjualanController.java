/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAOdata.datapenjualanDAO;
import DAOImplement.dataImplementPenjualan;
import model.datapenjualan;
import model.modeltabeldatapenjualan;
import view.Penjualan;

import javax.swing.*;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class datapenjualanController {
    Penjualan frame;
    dataImplementPenjualan impldatapenjualan;
    List<datapenjualan> db;

    public datapenjualanController(Penjualan frame) {
        this.frame = frame;
        impldatapenjualan = new datapenjualanDAO();
        db = impldatapenjualan.getAll();
    }

    public void isitabel() {
        db = impldatapenjualan.getAll();
        modeltabeldatapenjualan mp = new modeltabeldatapenjualan(db);
        frame.getjTable2().setModel(mp);
    }
}
