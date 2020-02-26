package lk.ijse.dep.fcms.dto;

public class ExerciseDTO {

    private String exerciseID;
    private String exercise;

    public ExerciseDTO() {
    }

    public ExerciseDTO(String exerciseID, String exercise) {
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
        return "ExerciseDTO{" +
                "exerciseID='" + exerciseID + '\'' +
                ", exercise='" + exercise + '\'' +
                '}';
    }
}
