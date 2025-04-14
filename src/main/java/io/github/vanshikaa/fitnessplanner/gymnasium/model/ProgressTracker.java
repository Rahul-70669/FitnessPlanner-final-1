package io.github.vanshikaa.fitnessplanner.gymnasium.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 *Manages the ProgrssTracker class according to the user performance.
 * @NotBlank the name of the field cannot be empty.
 * Positive represents that the number input should be positive
 * POJO class made for the ProgrssTracker which includes:
 * Constructor with no arguments
 * Constructor with arguments
 * Getter&Setter
 * Equalsto() and Hashcode()
 * toString()
 */
@Entity
public class ProgressTracker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private double weight;
    private double bodyFatPercentage;


    /**
     * Many users can have same progress report according to their performance.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ProgressTracker() {
    }

    public ProgressTracker( LocalDate date, double weight, double bodyFatPercentage, User user) {

        this.date = date;
        this.weight = weight;
        this.bodyFatPercentage = bodyFatPercentage;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(double bodyFatPercentage) {
        this.bodyFatPercentage = bodyFatPercentage;
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
        ProgressTracker that = (ProgressTracker) o;
        return Double.compare(weight, that.weight) == 0 && Double.compare(bodyFatPercentage, that.bodyFatPercentage) == 0 && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, weight, bodyFatPercentage, user);
    }

    @Override
    public String toString() {
        return "ProgressTracker{" +
                "id=" + id +
                ", date=" + date +
                ", weight=" + weight +
                ", bodyFatPercentage=" + bodyFatPercentage +
                ", user=" + user +
                '}';
    }
}
