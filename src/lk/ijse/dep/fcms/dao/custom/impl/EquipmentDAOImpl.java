package lk.ijse.dep.fcms.dao.custom.impl;

import lk.ijse.dep.fcms.dao.CrudUtil;
import lk.ijse.dep.fcms.dao.custom.EquipmentDAO;
import lk.ijse.dep.fcms.entity.Equipment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {

    @Override
    public List<Equipment> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Equipment");
        List<Equipment> equipments = new ArrayList<>();
        while (rst.next()) {
            equipments.add(new Equipment(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDate(4)));
        }
        return equipments;
    }

    @Override
    public Equipment find(String equipmentID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Equipment WHERE equipmentID=?", equipmentID);
        if (rst.next()) {
            return new Equipment(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(2),
                    rst.getDate(3));
        }
        return null;
    }

    @Override
    public boolean save(Equipment entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Equipment VALUES (?,?,?,?)", entity.getEquipmentID(), entity.getDescription(), entity.getPrice(), entity.getDateAdded());
    }

    @Override
    public boolean update(Equipment entity) throws Exception {
        return CrudUtil.execute("UPDATE Equipment SET description=?, price=?, dateAdded=? WHERE equipmentID=?", entity.getEquipmentID(), entity.getDescription(), entity.getPrice(), entity.getDateAdded());
    }

    @Override
    public boolean delete(String equipmentID) throws Exception {
        return CrudUtil.execute("DELETE FROM Equipment WHERE equipmentID=?", equipmentID);
    }

    @Override
    public String getLastEquipmentId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT equipmentID FROM Equipment ORDER BY equipmentID DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
