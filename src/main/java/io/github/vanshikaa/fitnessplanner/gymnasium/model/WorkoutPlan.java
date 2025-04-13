package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
public class WorkoutPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Plan name is required")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description can be up to 500 characters")
    private String description;

    @NotBlank(message = "Level is required (Beginner/Intermediate/Advanced)")
    private String difficultyLevel;


    private Long userId;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
    private List<Exercise> exercises;


    public WorkoutPlan() {
    }

    public WorkoutPlan( String name, String description, String difficultyLevel, Long userId, List<Exercise> exercises) {

        this.name = name;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.userId = userId;
        this.exercises = exercises;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Long
    getUser() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutPlan that = (WorkoutPlan) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(difficultyLevel, that.difficultyLevel) && Objects.equals(userId, that.userId) && Objects.equals(exercises, that.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, difficultyLevel, userId, exercises);
    }

    @Override
    public String toString() {
        return "WorkoutPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", userId=" + userId +
                ", exercises=" + exercises +
                '}';
    }
}
