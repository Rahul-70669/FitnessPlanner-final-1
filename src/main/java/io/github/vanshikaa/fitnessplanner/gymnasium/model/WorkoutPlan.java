package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;


/**
 *Manages the workout plan according to their transformation type,height and weight.
 * @NotBlank the name of the field cannot be empty.
 * Positive represents that the number input should be positive
 * POJO class made for the workout plan which includes:
 * Constructor with no arguments
 * Constructor with arguments
 * Getter&Setter
 * Equalsto() and Hashcode()
 * toString()
 */
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

    @NotBlank(message = "Type is required (Gaining/Leaning)")
    private String transformationtype;



    public WorkoutPlan() {
    }

    public WorkoutPlan( String name, String description, String difficultyLevel, Long userId,String transformationtype) {

        this.name = name;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.userId = userId;
        this.transformationtype = transformationtype;

    }

    public String getTransformationtype() {
        return transformationtype;
    }

    public void setTransformationtype(String transformationtype) {
        this.transformationtype = transformationtype;
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
    getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutPlan that = (WorkoutPlan) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(difficultyLevel, that.difficultyLevel) && Objects.equals(userId, that.userId) && Objects.equals(transformationtype, that.transformationtype)              ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, difficultyLevel, userId , transformationtype);
    }

    @Override
    public String toString() {
        return "WorkoutPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", userId=" + userId +
                ", transformationtype" + transformationtype +
                '}';
    }
}
