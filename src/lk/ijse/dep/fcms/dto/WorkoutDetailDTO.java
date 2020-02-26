package lk.ijse.dep.fcms.dto;

public class WorkoutDetailDTO {

    private String workoutID;
    private String exerciseID;
    private String exercise;
    private int reps;
    private int sets;

    public WorkoutDetailDTO() {
    }

    public WorkoutDetailDTO(String workoutID, String exerciseID, String exercise, int reps, int sets) {
        this.workoutID = workoutID;
        this.exerciseID = exerciseID;
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
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

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "WorkoutDetailDTO{" +
                "workoutID='" + workoutID + '\'' +
                ", exerciseID='" + exerciseID + '\'' +
                ", exercise='" + exercise + '\'' +
                ", reps=" + reps +
                ", sets=" + sets +
                '}';
    }
}
