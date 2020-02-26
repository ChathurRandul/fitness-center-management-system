package lk.ijse.dep.fcms.dto;

import java.sql.Date;

public class MembershipDetailDTO {

    private String memberID;
    private Date dateOfIssue;
    private Date dateOfExpire;

    public MembershipDetailDTO() {
    }

    public MembershipDetailDTO(String memberID, Date dateOfIssue, Date dateOfExpire) {
        this.memberID = memberID;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpire = dateOfExpire;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfExpire() {
        return dateOfExpire;
    }

    public void setDateOfExpire(Date dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

    @Override
    public String toString() {
        return "MembershipDetailDTO{" +
                "memberID='" + memberID + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                ", dateOfExpire='" + dateOfExpire + '\'' +
                '}';
    }
}
