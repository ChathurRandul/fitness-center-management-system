package lk.ijse.dep.fcms.dto;

import java.sql.Date;

public class TrainerDTO {

    private String trainerID;
    private String firstName;
    private String lastName;
    private String address;
    private String contactNo;
    private String nic;

    public TrainerDTO() {
    }

    public TrainerDTO(String trainerID, String firstName, String lastName, String address, String contactNo) {
        this.trainerID = trainerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNo = contactNo;
    }

    public TrainerDTO(String trainerID, String firstName, String lastName, String address, String contactNo, String nic) {
        this.trainerID = trainerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNo = contactNo;
        this.nic = nic;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "TrainerDTO{" +
                "trainerID='" + trainerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", nic='" + nic + '\'' +
                '}';
    }
}
