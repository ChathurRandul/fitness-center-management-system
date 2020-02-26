package lk.ijse.dep.fcms.dao.custom;

import lk.ijse.dep.fcms.dao.CrudDAO;
import lk.ijse.dep.fcms.entity.Trainer;

public interface TrainerDAO extends CrudDAO<Trainer, String> {

    String getLastTrainerId() throws Exception;
}
