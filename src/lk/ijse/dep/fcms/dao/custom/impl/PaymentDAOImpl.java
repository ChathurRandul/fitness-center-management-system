package lk.ijse.dep.fcms.dao.custom.impl;

import lk.ijse.dep.fcms.dao.CrudUtil;
import lk.ijse.dep.fcms.dao.custom.PaymentDAO;
import lk.ijse.dep.fcms.entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public List<Payment> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Payment");
        List<Payment> payments = new ArrayList<>();
        while (rst.next()) {
            payments.add(new Payment(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDate(4)));
        }
        return payments;
    }

    @Override
    public Payment find(String paymentID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Payment WHERE paymentID=?", paymentID);
        if (rst.next()) {
            return new Payment(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(2),
                    rst.getDate(3));
        }
        return null;
    }

    @Override
    public boolean save(Payment entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Payment VALUES (?,?,?,?)", entity.getPaymentID(), entity.getMemberID(), entity.getPayment(), entity.getPaymentDate());
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return CrudUtil.execute("UPDATE Payment SET memberID=?, payment=?, paymentDate=? WHERE paymentID=?", entity.getMemberID(), entity.getPayment(), entity.getPaymentDate(), entity.getPaymentID());
    }

    @Override
    public boolean delete(String paymentID) throws Exception {
        return CrudUtil.execute("DELETE FROM Payment WHERE paymentID=?", paymentID);
    }

    @Override
    public String getLastPaymentId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT paymentID FROM Payment ORDER BY paymentID DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
