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

@RestController
@RequestMapping("/api/workoutplans")
@CrossOrigin
public class WorkoutPlanController {
    private final WorkoutPlanService workoutPlanService;

    @Autowired
    public WorkoutPlanController(@Qualifier("DatabaseWorkoutPlanService") WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }


    @PostMapping("/add")
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@Valid @RequestBody WorkoutPlan workoutPlan) {
        WorkoutPlan savedWorkoutPlan = workoutPlanService.createWorkoutPlan(workoutPlan);
        return new ResponseEntity<>(savedWorkoutPlan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutPlan>> getAllWorkoutPlans() {

        List<WorkoutPlan> workout= workoutPlanService.getAllWorkoutPlans();
        return ResponseEntity.ok(workout);
    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable @Positive Long id) {
        Optional<WorkoutPlan> workoutPlan = Optional.ofNullable(workoutPlanService.getWorkoutPlanById(id));
        return workoutPlan.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlansByUserId(userId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Positive Long id) {
        boolean deleted = workoutPlanService.deleteWorkoutPlan(id);

        if (deleted) {
            return ResponseEntity.ok("workout deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("workout not found");
    }
}
