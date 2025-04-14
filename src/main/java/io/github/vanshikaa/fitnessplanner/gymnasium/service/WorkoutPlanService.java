package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.model.WorkoutPlan;

import java.util.List;

public interface WorkoutPlanService {
    WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan);

    List<WorkoutPlan> getAllWorkoutPlans();

    WorkoutPlan getWorkoutPlanById(Long id);

    List<WorkoutPlan> getWorkoutPlansByUserId(Long userId);

    WorkoutPlan updateDescription(Long userId, WorkoutPlan workoutPlan);

    WorkoutPlan updateUserId(Long id, long userId);

    boolean deleteWorkoutPlan(Long id);
}
