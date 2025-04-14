package io.github.vanshikaa.fitnessplanner.gymnasium.repository;

import io.github.vanshikaa.fitnessplanner.gymnasium.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link WorkoutPlan} entities in the database.
 * <p>
 * This interface extends {@link JpaRepository} which provides standard CRUD operations:
 * <ul>
 *     <li>{@code save(WorkoutPlan entity)}: Saves a WorkoutPlan entity to the database</li>
 *     <li>{@code findById(Long id)}: Finds a WorkoutPlan by its ID</li>
 *     <li>{@code findAll()}: Retrieves all WorkoutPlan</li>
 *     <li>{@code deleteById(Long id)}: Deletes a WorkoutPlan by its ID</li>
 *     <li>{@code delete(WorkoutPlan entity)}: Deletes a specific WorkoutPlan entity</li>
 *     <li>{@code count()}: Returns the total number of WorkoutPlan</li>
 *     <li>{@code existsById(Long id)}: Checks if a WorkoutPlan with given ID exists</li>
 * </ul>
 * <p>
 * The {@code @Repository} annotation marks this interface as a Spring Data repository,
 * which allows Spring to find and configure it automatically.
 */
@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    /**
     * Finds a WorkoutPlan by its userId.
     * <p>
     * This custom method is created because JpaRepository doesn't provide a
     * built-in method to search by the WorkoutPlan userId. Spring Data JPA will automatically
     * implement this method based on its userId following the pattern "findBy[PropertyUserId]".
     * <p>
     * The method returns an Optional object which may or may not contain a WorkoutPlan,
     * avoiding potential null pointer exceptions when no WorkoutPlan is found with the given userId.
     *
     * @param userId the userId of the WorkoutPlan to search for
     * @return an Optional containing the found WorkoutPlan, or an empty Optional if no WorkoutPlan exists with the given userId
     */
    List<WorkoutPlan> findByUserId(Long userId);
}
