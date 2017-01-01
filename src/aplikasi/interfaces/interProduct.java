/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.interfaces;

import aplikasi.model.Manufacturer;
import aplikasi.model.Product;
import aplikasi.model.ProductCode;
import java.util.List;

/**
 *
 * @author aplikasi
 */
public interface interProduct {
    List<Product> selectData();
    void saveOrUpdate(Product p);
    void delete(Product p);
    Integer auto();
    List<Manufacturer> selectManufacturerID();
    List<ProductCode> selectProductCode();
}
