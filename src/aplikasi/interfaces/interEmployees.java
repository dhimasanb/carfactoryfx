package aplikasi.interfaces;

import java.util.List;

import aplikasi.model.Karyawan;

public interface interEmployees {
    List<Karyawan> select();
    void saveOrUpdate(Karyawan c);
    void delete(Karyawan c);
    Integer auto();
//    List<DiscountCode> selectCode();
//    List<MicroMarket> selectZip();
}
