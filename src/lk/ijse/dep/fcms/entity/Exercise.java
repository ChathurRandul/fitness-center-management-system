package lk.ijse.dep.fcms.entity;

public class Exercise implements SuperEntity {

    private String exerciseID;
    private String exercise;

    public Exercise() {
    }

    public Exercise(String exerciseID, String exercise) {
        this.exerciseID = exerciseID;
        this.exercise = exercise;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseID='" + exerciseID + '\'' +
                ", exercise='" + exercise + '\'' +
                '}';
    }
}
