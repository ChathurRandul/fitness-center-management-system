package lk.ijse.dep.fcms.bo;

import lk.ijse.dep.fcms.bo.custom.impl.*;
import lk.ijse.dep.fcms.dao.custom.impl.AttendanceDAOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null)? (boFactory = new BOFactory()): boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case ATTENDANCE:
                return (T) new AttendanceBOImpl();
            case EQUIPMENT:
                return (T) new EquipmentBOImpl();
            case EXERCISE:
                return (T) new ExerciseBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            case MEMBER:
                return (T) new MemberBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
            case TRAINER:
                return (T) new TrainerBOImpl();
            case WORKOUT:
                return (T) new WorkoutBOImpl();
            default:
                return null;
        }
    }
}
