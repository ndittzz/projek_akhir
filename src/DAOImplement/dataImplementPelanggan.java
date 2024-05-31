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
public interface dataImplementPelanggan {
    public void insertPelanggan(datapelanggan p);
    public void updatePelanggan(datapelanggan p);
    public void deletePelanggan(String kd_pelanggan);
    public List<datapelanggan> getAll();
    public datapelanggan getPelangganByCode(String kode);
    public String autoKode();

}
