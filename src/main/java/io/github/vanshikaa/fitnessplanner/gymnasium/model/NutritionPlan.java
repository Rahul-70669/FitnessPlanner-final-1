package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 *Manages the NutritionPlan class according to the user preferance.
 * @NotBlank the name of the field cannot be empty.
 * Positive represents that the number input should be positive
 * POJO class made for the NutritionPlan which includes:
 * Constructor with no arguments
 * Constructor with arguments
 * Getter&Setter
 * Equalsto() and Hashcode()
 * toString()
 */
@Entity
public class NutritionPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int calories;
    private double protein;
    private double carbs;
    private double fats;


    /**
     * One user can have only one or unique diet plan/workoutplan/etc.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public NutritionPlan() {
    }

    public NutritionPlan( int calories, double protein, double carbs, double fats, User user) {

        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NutritionPlan that = (NutritionPlan) o;
        return calories == that.calories && Double.compare(protein, that.protein) == 0 && Double.compare(carbs, that.carbs) == 0 && Double.compare(fats, that.fats) == 0 && Objects.equals(id, that.id) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calories, protein, carbs, fats, user);
    }

    @Override
    public String toString() {
        return "NutritionPlan{" +
                "id=" + id +
                ", calories=" + calories +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", user=" + user +
                '}';
    }
}
