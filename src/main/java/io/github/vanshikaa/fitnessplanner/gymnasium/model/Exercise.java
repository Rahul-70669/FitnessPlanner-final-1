package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 *Manages the Exercise class according to the workoutPlan of the user.
 * @NotBlank the name of the field cannot be empty.
 * Positive represents that the number input should be positive
 * POJO class made for the Exercise which includes:
 * Constructor with no arguments
 * Constructor with arguments
 * Getter&Setter
 * Equalsto() and Hashcode()
 * toString()
 */
@Entity
public class Exercise {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String muscleGroup;
    private int durationInMinutes;
    private int sets;
    private int reps;

    /**
     * Many workoutplans can have same set of exercises.
     */
    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;

    public Exercise() {
    }

    public Exercise(String name, String muscleGroup, int durationInMinutes, int sets, int reps, WorkoutPlan workoutPlan) {

        this.name = name;
        this.muscleGroup = muscleGroup;
        this.durationInMinutes = durationInMinutes;
        this.sets = sets;
        this.reps = reps;
        this.workoutPlan = workoutPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return durationInMinutes == exercise.durationInMinutes && sets == exercise.sets && reps == exercise.reps && Objects.equals(id, exercise.id) && Objects.equals(name, exercise.name) && Objects.equals(muscleGroup, exercise.muscleGroup) && Objects.equals(workoutPlan, exercise.workoutPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, muscleGroup, durationInMinutes, sets, reps, workoutPlan);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", sets=" + sets +
                ", reps=" + reps +
                ", workoutPlan=" + workoutPlan +
                '}';
    }
}
