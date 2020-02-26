package lk.ijse.dep.fcms.dao.custom.impl;

import lk.ijse.dep.fcms.dao.CrudUtil;
import lk.ijse.dep.fcms.dao.custom.TrainerDAO;
import lk.ijse.dep.fcms.entity.Trainer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO {

    @Override
    public List<Trainer> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Trainer");
        List<Trainer> trainers = new ArrayList<>();
        while (rst.next()) {
            trainers.add(new Trainer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)));
        }
        return trainers;
    }

    @Override
    public Trainer find(String trainerID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Trainer WHERE trainerID=?", trainerID);
        if (rst.next()) {
            return new Trainer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    @Override
    public boolean save(Trainer entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Trainer VALUES (?,?,?,?,?,?)", entity.getTrainerID(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getContactNo(), entity.getNic());
    }

    @Override
    public boolean update(Trainer entity) throws Exception {
        return CrudUtil.execute("UPDATE Trainer SET firstName=?, lastName=?, address=?, contactNo=?, nic=? WHERE trainerID=?", entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getContactNo(), entity.getNic(), entity.getTrainerID());
    }

    @Override
    public boolean delete(String trainerID) throws Exception {
        return CrudUtil.execute("DELETE FROM Trainer WHERE trainerID=?", trainerID);
    }

    @Override
    public String getLastTrainerId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT trainerID FROM Trainer ORDER BY trainerID DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
