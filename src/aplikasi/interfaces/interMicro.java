/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.interfaces;

import aplikasi.model.MicroMarket;
import java.util.List;

/**
 *
 * @author aplikasi
 */
public interface interMicro {
    List<MicroMarket> selectData();
    void saveOrUpdate(MicroMarket m);
    void delete(MicroMarket m);
    Integer chekZip(String a);
}
