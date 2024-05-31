/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAOdata.datapenjualanfinalDAO;
import DAOImplement.dataImplementPenjualanfinal;
import model.datapenjualanfinal;
import model.modeltabeldatapenjualanfinal;
import view.Penjualan;

import javax.swing.*;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class datapenjualanfinalController {
        Penjualan frame;
    dataImplementPenjualanfinal impldatapenjualanfinal;
    List<datapenjualanfinal> db;

    public datapenjualanfinalController(Penjualan frame) {
        this.frame = frame;
        impldatapenjualanfinal = new datapenjualanfinalDAO();
        db = impldatapenjualanfinal.getAll();
    }

    public void isitabel() {
        db = impldatapenjualanfinal.getAll();
        modeltabeldatapenjualanfinal mp = new modeltabeldatapenjualanfinal(db);
        frame.getjTable3().setModel(mp);
    }
}
