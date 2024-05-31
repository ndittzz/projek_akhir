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
public interface dataImplementDistributor {
     public void insertDistributor(datadistributor d);
    public void updateDistributor(datadistributor d);
    public void deleteDistributor(String kd_distributor);
    public List<datadistributor> getAll();
    public datadistributor getDistributorByCode(String kode);
    public String autoKode();
}
