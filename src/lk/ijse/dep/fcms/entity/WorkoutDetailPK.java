package lk.ijse.dep.fcms.entity;

public class WorkoutDetailPK {

    private String workoutID;
    private String exerciseID;

    public WorkoutDetailPK() {
    }

    public WorkoutDetailPK(String workoutID, String exerciseID) {
        this.workoutID = workoutID;
        this.exerciseID = exerciseID;
    }

    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    @Override
    public String toString() {
        return "WorkoutDetailPK{" +
                "workoutID='" + workoutID + '\'' +
                ", exerciseID='" + exerciseID + '\'' +
                '}';
    }
}
