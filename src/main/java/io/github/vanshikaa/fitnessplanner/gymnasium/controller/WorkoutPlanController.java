package io.github.vanshikaa.fitnessplanner.gymnasium.controller;


import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.WorkoutPlan;
import io.github.vanshikaa.fitnessplanner.gymnasium.service.UserService;
import io.github.vanshikaa.fitnessplanner.gymnasium.service.WorkoutPlanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing workoutPlans in the FitnessPlanner application.
 * Provides endpoints for CRUD operations on products.
 */
@RestController
@RequestMapping("/api/workoutplans")
@CrossOrigin
public class WorkoutPlanController {
    private final WorkoutPlanService workoutPlanService;

    @Autowired
    public WorkoutPlanController(@Qualifier("DatabaseWorkoutPlanService") WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }


    /**
     * Creates a new workoutPlan.
     *
     * @param workoutPlan The workoutPlan information to create
     * @return The created workoutPlan with HTTP status 201 (created)
     */
    @PostMapping("/add")
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@Valid @RequestBody WorkoutPlan workoutPlan) {
        WorkoutPlan savedWorkoutPlan = workoutPlanService.createWorkoutPlan(workoutPlan);
        return new ResponseEntity<>(savedWorkoutPlan, HttpStatus.CREATED);
    }

    /**
     * Retrieves all workoutPlans from the system.
     *
     * @return ResponseEntity containing a list of all workoutPlans with HTTP status 201 (OK)
     */
    @GetMapping
    public ResponseEntity<List<WorkoutPlan>> getAllWorkoutPlans() {

        List<WorkoutPlan> workout= workoutPlanService.getAllWorkoutPlans();
        return ResponseEntity.ok(workout);
    }


    /**
     * Retrieves a specific WorkoutPlan by its ID.
     * @Pathvariable id the unique identifier of the WorkoutPlan
     * @return ResponseEntity containing the WorkoutPlan with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the WorkoutPlan doesn't exist
     */

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable @Positive Long id) {
        Optional<WorkoutPlan> workoutPlan = Optional.ofNullable(workoutPlanService.getWorkoutPlanById(id));
        return workoutPlan.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a specific WorkoutPlan by its ID.
     * @Pathvariable userId fetches workoutPlan.
     * @return ResponseEntity containing the workoutPlan with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the workoutPlan doesn't exist
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlansByUserId(userId));
    }

    /**
     * Updates the description of a specific workoutPlan.
     *
     * @param userId the unique identifier of the product to update
     * @param workoutPlan the new description (must be between 10 and 100 characters)
     * @return ResponseEntity containing the updated workoutPlan with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the workoutPlan doesn't exist
     */
    @PutMapping("/user/{userId}")
    public ResponseEntity<WorkoutPlan> updateDescription(@PathVariable @Positive Long userId, @Valid @RequestBody WorkoutPlan workoutPlan) {
        WorkoutPlan Work=workoutPlanService.updateDescription(userId, workoutPlan);
        return ResponseEntity.ok(Work);
    }

    /**
     * Updates the userId of a specific WorkoutPlan.
     *
     * @param id    the unique identifier of the WorkoutPlan to update
     * @param userId the new userId value (must be non-negative)
     * @return ResponseEntity containing the updated WorkoutPlan with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the WorkoutPlan doesn't exist
     */
    @PutMapping("/userId/{id}")
    public ResponseEntity<WorkoutPlan> updateuserId(@PathVariable @Positive Long id, @Valid @RequestParam Long userId) {
        WorkoutPlan workoutPlan = workoutPlanService.updateUserId(id, userId);
        return ResponseEntity.ok(workoutPlan);
    }


    /**
     * Deletes a specific Workoutplan from the system.
     *
     * @param id the unique identifier of the Workoutplan to delete
     * @return ResponseEntity with a success message and HTTP status 200 (OK) if deleted,
     * or an error message and HTTP status 404 (Not Found) if the Workoutplan doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Positive Long id) {
        boolean deleted = workoutPlanService.deleteWorkoutPlan(id);

        if (deleted) {
            return ResponseEntity.ok("workout deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("workout not found");
    }
}
