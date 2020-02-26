package lk.ijse.dep.fcms.bo.custom.impl;

import lk.ijse.dep.fcms.bo.custom.PaymentBO;
import lk.ijse.dep.fcms.dao.DAOFactory;
import lk.ijse.dep.fcms.dao.DAOTypes;
import lk.ijse.dep.fcms.dao.custom.PaymentDAO;
import lk.ijse.dep.fcms.dto.PaymentDTO;
import lk.ijse.dep.fcms.entity.Payment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    private PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);

    @Override
    public boolean savePayment(PaymentDTO payment) throws Exception {
        LocalDate date = LocalDate.now();
        return paymentDAO.save(new Payment(payment.getPaymentID(),
                payment.getMemberID(), payment.getPayment(), Date.valueOf(date)));
    }

    @Override
    public boolean updatePayment(PaymentDTO payment) throws Exception {
        return paymentDAO.update(new Payment(payment.getPaymentID(), payment.getMemberID(), payment.getPayment(), payment.getPaymentDate()));
    }

    @Override
    public boolean deletePayment(String paymentID) throws Exception {
        return false;
    }

    @Override
    public List<PaymentDTO> findAllPayments() throws Exception {
        List<Payment> alPayments = paymentDAO.findAll();
        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment payment : alPayments) {
            dtos.add(new PaymentDTO(payment.getPaymentID(), payment.getMemberID(), payment.getPayment(), payment.getPaymentDate()));
        }
        return dtos;
    }

    @Override
    public String getLastPaymentId() throws Exception {
        return paymentDAO.getLastPaymentId();
    }

    @Override
    public PaymentDTO findPayment(String paymentID) throws Exception {
        Payment payment = paymentDAO.find(paymentID);
        return new PaymentDTO(payment.getPaymentID(), payment.getMemberID(), payment.getPayment(), payment.getPaymentDate());
    }

    @Override
    public List<String> getAllPaymentIDs() throws Exception {
        List<Payment> payments = paymentDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Payment payment : payments) {
            ids.add(payment.getPaymentID());
        }
        return ids;
    }
}
