/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.implement;

import aplikasi.config.config2;
import aplikasi.model.Karyawan;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import aplikasi.interfaces.interEmployees;

@Repository
public class implEmployees implements interEmployees {
    @Autowired
    configSessionFactory csf;

    @Override
    public void saveOrUpdate(Karyawan c) {
        if (c.getId()==null) {
            csf.create(c);
        }else{
            csf.update(c);
        }
    }

    @Override
    public Integer auto() {
        Integer ret = null;
        try {
            Query q = csf.getSf().openSession().createQuery("select max(t.id) from karyawan t");
            Object o = q.uniqueResult();
            ret = Integer.parseInt(o.toString())+1;
        } catch (HibernateException | NumberFormatException e) {
        }
        return ret;
    }

//    @Override
//    public List<DiscountCode> selectCode() {
//        Criteria cr = csf.getSf().openSession().createCriteria(DiscountCode.class);
//        cr.setProjection(Projections.property("discountCode"));
//        return cr.list();
//    }
//
//    @Override
//    public List<MicroMarket> selectZip() {
//        Criteria cr = csf.getSf().openSession().createCriteria(MicroMarket.class);
//        cr.setProjection(Projections.property("zipCode"));
//        return cr.list();
//    }

    @Override
    public void delete(Karyawan c) {
        try {
            Query q = csf.getSf().openSession().createQuery("delete from karyawan where id = :id");
            q.setParameter("id", c.getId());
            q.executeUpdate();
            config2.dialog(Alert.AlertType.INFORMATION, "Success Delete");
        } catch (Exception e) {
            config2.dialog(Alert.AlertType.INFORMATION, "Sorry, Can't Delete This Record");
        }
    }

    @Override
    public List<Karyawan> select() {
        return csf.getSf().openSession().createCriteria(Karyawan.class).list();
    }
}
