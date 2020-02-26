package lk.ijse.dep.fcms.dao.custom;

import lk.ijse.dep.fcms.dao.CrudDAO;
import lk.ijse.dep.fcms.entity.Equipment;

public interface EquipmentDAO extends CrudDAO<Equipment, String>  {

    String getLastEquipmentId() throws Exception;
}
