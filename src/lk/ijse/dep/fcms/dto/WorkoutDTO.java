package lk.ijse.dep.fcms.dto;

import java.sql.Date;

public class WorkoutDTO {

    private String workoutID;
    private String memberID;
    private String trainerID;
    private int weight;
    private int height;
    private Date issueDate;
    private Date expireDate;

    public WorkoutDTO() {
    }

    public WorkoutDTO(String workoutID, String memberID, String trainerID, int weight, int height, Date issueDate, Date expireDate) {
        this.workoutID = workoutID;
        this.memberID = memberID;
        this.trainerID = trainerID;
        this.weight = weight;
        this.height = height;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
    }

    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "WorkoutDTO{" +
                "workoutID='" + workoutID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", trainerID='" + trainerID + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", issueDate=" + issueDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
