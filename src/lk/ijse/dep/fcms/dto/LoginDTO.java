package lk.ijse.dep.fcms.dto;

public class LoginDTO {

    private String userID;
    private String password;
    private String userType;

    public LoginDTO() {
    }

    public LoginDTO(String userID, String password, String userType) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
