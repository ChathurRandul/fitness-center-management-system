package lk.ijse.dep.fcms.dao.custom;

import lk.ijse.dep.fcms.dao.CrudDAO;
import lk.ijse.dep.fcms.entity.Payment;

public interface PaymentDAO extends CrudDAO<Payment, String> {

    String getLastPaymentId() throws Exception;
}

