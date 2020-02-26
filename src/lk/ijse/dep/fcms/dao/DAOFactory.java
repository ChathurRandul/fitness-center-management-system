package lk.ijse.dep.fcms.dao;

import lk.ijse.dep.fcms.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case ATTENDANCE:
                return (T) new AttendanceDAOImpl();
            case EQUIPMENT:
                return (T) new EquipmentDAOImpl();
            case EXERCISE:
                return (T) new ExerciseDAOImpl();
            case LOGIN:
                return (T) new LoginDAOImpl();
            case MEMBERSHIP:
                return (T) new MembershipDAOImpl();
            case MEMBERSHIPDETAIL:
                return (T) new MembershipDetailDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case TRAINER:
                return (T) new TrainerDAOImpl();
            case WORKOUT:
                return (T) new WorkoutDAOImpl();
            case WORKOUTDETAIL:
                return (T) new WorkoutDetailDAOImpl();
            default:
                return null;
        }
    }
}
