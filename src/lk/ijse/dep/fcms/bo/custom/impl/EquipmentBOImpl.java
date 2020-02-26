package lk.ijse.dep.fcms.bo.custom.impl;

import lk.ijse.dep.fcms.bo.custom.EquipmentBO;
import lk.ijse.dep.fcms.dao.DAOFactory;
import lk.ijse.dep.fcms.dao.DAOTypes;
import lk.ijse.dep.fcms.dao.custom.EquipmentDAO;
import lk.ijse.dep.fcms.dto.EquipmentDTO;
import lk.ijse.dep.fcms.dto.PaymentDTO;
import lk.ijse.dep.fcms.entity.Equipment;
import lk.ijse.dep.fcms.entity.Payment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBOImpl implements EquipmentBO {

    private EquipmentDAO equipmentDAO = DAOFactory.getInstance().getDAO(DAOTypes.EQUIPMENT);

    @Override
    public boolean saveEquipment(EquipmentDTO equipment) throws Exception {
        LocalDate date = LocalDate.now();
        return equipmentDAO.save(new Equipment(equipment.getEquipmentID(),
                equipment.getDescription(), equipment.getPrice(), Date.valueOf(date)));
    }

    @Override
    public boolean updateEquipment(EquipmentDTO equipment) throws Exception {
        return equipmentDAO.update(new Equipment(equipment.getEquipmentID(), equipment.getDescription(), equipment.getPrice(), equipment.getDateAdded()));
    }

    @Override
    public boolean deleteEquipment(String equipmentID) throws Exception {
        return false;
    }

    @Override
    public List<EquipmentDTO> findAllEquipments() throws Exception {
        List<Equipment> alEquipments = equipmentDAO.findAll();
        List<EquipmentDTO> dtos = new ArrayList<>();
        for (Equipment equipment : alEquipments) {
            dtos.add(new EquipmentDTO(equipment.getEquipmentID(), equipment.getDescription(), equipment.getPrice(), equipment.getDateAdded()));
        }
        return dtos;
    }

    @Override
    public String getLastEquipmentId() throws Exception {
        return equipmentDAO.getLastEquipmentId();
    }

    @Override
    public EquipmentDTO findEquipment(String equipmentID) throws Exception {
        Equipment equipment = equipmentDAO.find(equipmentID);
        return new EquipmentDTO(equipment.getEquipmentID(), equipment.getDescription(), equipment.getPrice(), equipment.getDateAdded());
    }

    @Override
    public List<String> getAllEquipmentIDs() throws Exception {
        return null;
    }
}
