package lk.ijse.dep.fcms.bo.custom;

import lk.ijse.dep.fcms.bo.SuperBO;
import lk.ijse.dep.fcms.dto.EquipmentDTO;
import lk.ijse.dep.fcms.dto.PaymentDTO;

import java.util.List;

public interface EquipmentBO extends SuperBO {

    boolean saveEquipment(EquipmentDTO equipment)throws Exception;

    boolean updateEquipment(EquipmentDTO equipment)throws Exception;

    boolean deleteEquipment(String equipmentID) throws Exception;

    List<EquipmentDTO>findAllEquipments() throws Exception;

    String getLastEquipmentId()throws Exception;

    EquipmentDTO findEquipment(String equipmentID) throws Exception;

    List<String> getAllEquipmentIDs() throws Exception;
}
