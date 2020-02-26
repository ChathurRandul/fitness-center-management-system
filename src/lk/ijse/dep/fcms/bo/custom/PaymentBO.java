package lk.ijse.dep.fcms.bo.custom;

import lk.ijse.dep.fcms.bo.SuperBO;
import lk.ijse.dep.fcms.dto.MembershipDTO;
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;
import lk.ijse.dep.fcms.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {

    boolean savePayment(PaymentDTO payment)throws Exception;

    boolean updatePayment(PaymentDTO payment)throws Exception;

    boolean deletePayment(String paymentID) throws Exception;

    List<PaymentDTO>findAllPayments() throws Exception;

    String getLastPaymentId()throws Exception;

    PaymentDTO findPayment(String paymentID) throws Exception;

    List<String> getAllPaymentIDs() throws Exception;
}
