/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;
import java.util.List;
import model.*;
/**
 *
 * @author ASUS
 */
public interface dataImplementBuku {
    public void insertBuku(databuku p);
    public void updateBuku(databuku p);
    public void deleteBuku(String kd_buku);
    public List<databuku> getAll();

    public String autoKode();

    public databuku getBookByCode(String kode);
}
