package lk.ijse.dep.fcms.bo.custom.impl;

import lk.ijse.dep.fcms.bo.custom.TrainerBO;
import lk.ijse.dep.fcms.dao.DAOFactory;
import lk.ijse.dep.fcms.dao.DAOTypes;
import lk.ijse.dep.fcms.dao.custom.LoginDAO;
import lk.ijse.dep.fcms.dao.custom.TrainerDAO;
import lk.ijse.dep.fcms.db.DBConnection;
import lk.ijse.dep.fcms.dto.LoginDTO;
import lk.ijse.dep.fcms.dto.TrainerDTO;
import lk.ijse.dep.fcms.entity.Login;
import lk.ijse.dep.fcms.entity.Trainer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TrainerBOImpl implements TrainerBO {

    private TrainerDAO trainerDAO = DAOFactory.getInstance().getDAO(DAOTypes.TRAINER);
    private LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOTypes.LOGIN);

    @Override
    public boolean saveTrainer(TrainerDTO trainer) throws Exception {
        return trainerDAO.save(new Trainer(trainer.getTrainerID(), trainer.getFirstName(), trainer.getLastName(), trainer.getAddress(), trainer.getContactNo(), trainer.getNic()));
    }

    @Override
    public boolean saveLogin(LoginDTO login) throws Exception {
        return loginDAO.save(new Login(login.getUserID(),
                login.getPassword(), login.getUserType()));
    }

    @Override
    public boolean updateTrainer(TrainerDTO trainer) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);

            String tId = trainer.getTrainerID();
            boolean result = trainerDAO.update(new Trainer(tId, trainer.getFirstName(), trainer.getLastName(), trainer.getAddress(), trainer.getContactNo(), trainer.getNic()));


            if (!result) {
                connection.rollback();
                throw new RuntimeException("Error, Trainer didn't updated!");
            }

            Login login = new Login();
            result = loginDAO.update(new Login(tId, login.getPassword(), login.getUserType()));

            connection.commit();
            return true;

        } catch (Throwable e) {

            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateLogin(LoginDTO login) throws Exception {
        return loginDAO.update(new Login(login.getUserID(), login.getPassword(), login.getUserType()));
    }

    @Override
    public boolean deleteTrainer(String trainerID) throws Exception {
        return false;
    }

    @Override
    public List<TrainerDTO> findAllTrainers() throws Exception {
        List<Trainer> alTrainers = trainerDAO.findAll();
        List<TrainerDTO> dtos = new ArrayList<>();
        for (Trainer trainer : alTrainers) {
            dtos.add(new TrainerDTO(trainer.getTrainerID(), trainer.getFirstName(), trainer.getLastName(), trainer.getAddress(), trainer.getContactNo()));
        }
        return dtos;
    }

    @Override
    public String getLastTrainerId() throws Exception {
        return trainerDAO.getLastTrainerId();
    }

    @Override
    public TrainerDTO findTrainer(String trainerID) throws Exception {
        Trainer trainer = trainerDAO.find(trainerID);
        return new TrainerDTO(trainer.getTrainerID(), trainer.getFirstName(), trainer.getLastName(), trainer.getAddress(), trainer.getContactNo(), trainer.getNic());
    }

    @Override
    public LoginDTO findLogin(String userID) throws Exception {
        Login login = loginDAO.find(userID);
        return new LoginDTO(login.getUserID(), login.getPassword(), login.getUserType());
    }

    @Override
    public List<String> getAllTrainerIDs() throws Exception {
        List<Trainer> trainers = trainerDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Trainer trainer : trainers) {
            ids.add(trainer.getTrainerID());
        }
        return ids;
    }
}
