package lk.ijse.dep.fcms.dto;

import java.sql.Date;

public class PaymentDTO {

    private String paymentID;
    private String memberID;
    private double payment;
    private Date paymentDate;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentID, String memberID, double payment, Date paymentDate) {
        this.paymentID = paymentID;
        this.memberID = memberID;
        this.payment = payment;
        this.paymentDate = paymentDate;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentID='" + paymentID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", payment=" + payment +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
