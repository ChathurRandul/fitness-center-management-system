package lk.ijse.dep.fcms.bo.custom;

import lk.ijse.dep.fcms.bo.SuperBO;
import lk.ijse.dep.fcms.dto.LoginDTO;
import lk.ijse.dep.fcms.dto.TrainerDTO;

import java.util.List;

public interface TrainerBO extends SuperBO {

    boolean saveTrainer(TrainerDTO trainer) throws Exception;

    boolean saveLogin(LoginDTO login) throws Exception;

    boolean updateTrainer(TrainerDTO trainer) throws Exception;

    boolean updateLogin(LoginDTO login) throws Exception;

    boolean deleteTrainer(String trainerID) throws Exception;

    List<TrainerDTO> findAllTrainers() throws Exception;

    String getLastTrainerId() throws Exception;

    TrainerDTO findTrainer(String trainerID) throws Exception;

    LoginDTO findLogin(String userID) throws Exception;

    List<String> getAllTrainerIDs() throws Exception;
}
