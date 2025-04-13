package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Min(value = 13, message = "Age must be at least 13")
    @Max(value = 70, message = "Age must be less than or equal to 70")
    private int age;

    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank(message = "height is required")
    private double height;

    @NotBlank(message = "weight is required")
    private double weight;

    /**
     * Mention here if suffering from any sort of disorder or health issue and related problems.
     */
    private String healthissue;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WorkoutPlan> workoutPlans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProgressTracker> progressTrackers;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private NutritionPlan nutritionPlan;

    public User() {

    }

    public User( String name, String email, String password, int age, String gender, double height, double weight, List<WorkoutPlan> workoutPlans, List<ProgressTracker> progressTrackers, NutritionPlan nutritionPlan ,String healthissue ) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.workoutPlans = workoutPlans;
        this.progressTrackers = progressTrackers;
        this.nutritionPlan = nutritionPlan;
        this.healthissue = healthissue;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public List<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    public void setProgressTrackers(List<ProgressTracker> progressTrackers) {
        this.progressTrackers = progressTrackers;
    }

    public NutritionPlan getNutritionPlan() {
        return nutritionPlan;
    }

    public void setNutritionPlan(NutritionPlan nutritionPlan) {
        this.nutritionPlan = nutritionPlan;
    }

    public String getHealthissue() {
        return healthissue;
    }

    public void setHealthissue(String healthissue) {
        this.healthissue = healthissue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Double.compare(height, user.height) == 0 && Double.compare(weight, user.weight) == 0 && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(gender, user.gender) && Objects.equals(workoutPlans, user.workoutPlans) && Objects.equals(progressTrackers, user.progressTrackers) && Objects.equals(nutritionPlan, user.nutritionPlan) && Objects.equals(healthissue, user.healthissue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, age, gender, height, weight, workoutPlans, progressTrackers, nutritionPlan, healthissue);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", workoutPlans=" + workoutPlans +
                ", progressTrackers=" + progressTrackers +
                ", nutritionPlan=" + nutritionPlan +
                ", healthissue='" + healthissue + '\'' +
                '}';
    }

}
