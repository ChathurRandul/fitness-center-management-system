package lk.ijse.dep.fcms.util;

import java.sql.Date;

public class MembershipDetailTM {

    private String memberID;
    private Date dateOfIssue;
    private Date dateOfExpire;

    public MembershipDetailTM() {
    }

    public MembershipDetailTM(String memberID, Date dateOfIssue, Date dateOfExpire) {
        this.setMemberID(memberID);
        this.setDateOfIssue(dateOfIssue);
        this.setDateOfExpire(dateOfExpire);
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
        return "MembershipDetailTM{" +
                "memberID='" + memberID + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpire=" + dateOfExpire +
                '}';
    }
}
