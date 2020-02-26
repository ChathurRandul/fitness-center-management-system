package lk.ijse.dep.fcms.entity;

import java.sql.Date;
import java.sql.Time;

public class Attendance implements SuperEntity {

    private int attendanceID;
    private String memberID;
    private String memberName;
    private Time timeIn;
    private Time timeOut;
    private Date date;

    public Attendance() {
    }

    public Attendance(int attendanceID, String memberID, String memberName, Time timeIn, Time timeOut, Date date) {
        this.attendanceID = attendanceID;
        this.memberID = memberID;
        this.memberName = memberName;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.date = date;
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Time getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Time timeIn) {
        this.timeIn = timeIn;
    }

    public Time getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Time timeOut) {
        this.timeOut = timeOut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceID=" + attendanceID +
                ", memberID='" + memberID + '\'' +
                ", memberName='" + memberName + '\'' +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", date=" + date +
                '}';
    }
}
