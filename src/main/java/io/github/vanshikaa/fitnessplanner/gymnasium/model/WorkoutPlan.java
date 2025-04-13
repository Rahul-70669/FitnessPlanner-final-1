package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class WorkoutPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
    private List<Exercise> exercises;


    public WorkoutPlan() {
    }

    public WorkoutPlan( String name, String description, String difficultyLevel, User user, List<Exercise> exercises) {

        this.name = name;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(difficultyLevel, that.difficultyLevel) && Objects.equals(user, that.user) && Objects.equals(exercises, that.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, difficultyLevel, user, exercises);
    }

    @Override
    public String toString() {
        return "WorkoutPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", user=" + user +
                ", exercises=" + exercises +
                '}';
    }
}
