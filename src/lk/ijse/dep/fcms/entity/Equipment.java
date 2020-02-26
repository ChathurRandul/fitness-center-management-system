package lk.ijse.dep.fcms.entity;

import java.sql.Date;

public class Equipment implements SuperEntity {

    private String equipmentID;
    private String description;
    private double price;
    private Date dateAdded;

    public Equipment() {
    }

    public Equipment(String equipmentID, String description, double price, Date dateAdded) {
        this.equipmentID = equipmentID;
        this.description = description;
        this.price = price;
        this.dateAdded = dateAdded;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentID='" + equipmentID + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
