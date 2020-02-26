package lk.ijse.dep.fcms.dto;

import java.sql.Date;

public class MembershipDTO {

    private String memberID;
    private String firstName;
    private String lastName;
    private String address;
    private String contactNo;
    private Date dateOfBirth;
    private String nic;
    private String gender;
    private int weight;
    private int height;
    private MembershipDetailDTO membershipDetails;

    public MembershipDTO() {
    }

    public MembershipDTO(String memberID, String firstName, String lastName, String address, String contactNo) {
        this.setMemberID(memberID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setContactNo(contactNo);
    }

    public MembershipDTO(String memberID, String firstName, String lastName, String address, String contactNo, Date dateOfBirth, String nic, String gender, int weight, int height) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNo = contactNo;
        this.dateOfBirth = dateOfBirth;
        this.nic = nic;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public MembershipDTO(String memberID, String firstName, String lastName, String address, String contactNo, Date dateOfBirth, String nic, String gender, int weight, int height, MembershipDetailDTO membershipDetails) {
        this.setMemberID(memberID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setContactNo(contactNo);
        this.setDateOfBirth(dateOfBirth);
        this.setNic(nic);
        this.setGender(gender);
        this.setWeight(weight);
        this.setHeight(height);
        this.setMembershipDetails(membershipDetails);
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public MembershipDetailDTO getMembershipDetails() {
        return membershipDetails;
    }

    public void setMembershipDetails(MembershipDetailDTO membershipDetails) {
        this.membershipDetails = membershipDetails;
    }

    @Override
    public String toString() {
        return "MembershipDTO{" +
                "memberID='" + memberID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", dateOfBirth=" + dateOfBirth +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", membershipDetails=" + membershipDetails +
                '}';
    }
}
