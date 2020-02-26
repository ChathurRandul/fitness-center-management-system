package lk.ijse.dep.fcms.entity;

public class WorkoutDetail implements SuperEntity {

    private WorkoutDetailPK workoutDetailPK;
    private String exercise;
    private int reps;
    private int sets;

    public WorkoutDetail() {
    }

    public WorkoutDetail(WorkoutDetailPK workoutDetailPK, String exercise, int reps, int sets) {
        this.workoutDetailPK = workoutDetailPK;
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
    }

    public WorkoutDetail(String workoutID, String exerciseID, String exercise, int reps, int sets) {
        this.workoutDetailPK = new WorkoutDetailPK(workoutID, exerciseID);
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
    }

    public void setWorkoutDetailPK(WorkoutDetailPK workoutDetailPK) {
        this.workoutDetailPK = workoutDetailPK;
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
        return "WorkoutDetail{" +
                "workoutDetailPK=" + workoutDetailPK +
                ", exercise='" + exercise + '\'' +
                ", reps=" + reps +
                ", sets=" + sets +
                '}';
    }
}
