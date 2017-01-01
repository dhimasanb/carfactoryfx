/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.interfaces;

import aplikasi.model.Customer;
import aplikasi.model.DiscountCode;
import aplikasi.model.MicroMarket;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author aplikasi
 */
public interface interCustomer {
    List<Customer> select();
    void saveOrUpdate(Customer c);
    void delete(Customer c);
    Integer auto();
    List<DiscountCode> selectCode();
    List<MicroMarket> selectZip();
}
