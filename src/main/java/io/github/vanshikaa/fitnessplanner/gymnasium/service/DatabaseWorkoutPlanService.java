package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserNotFoundException;
import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserValidationException;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.WorkoutPlan;
import io.github.vanshikaa.fitnessplanner.gymnasium.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseWorkoutPlanService implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    public DatabaseWorkoutPlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        if(workoutPlan==null){
            throw new UserValidationException("workoutplan must not be null");
        }
        return workoutPlanRepository.save(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> getAllWorkoutPlans() {
        List<WorkoutPlan> productList = workoutPlanRepository.findAll();
        if (productList.isEmpty()) {
            throw new UserNotFoundException("No user found");
        }
        return workoutPlanRepository.findAll();
    }

    @Override
    public WorkoutPlan getWorkoutPlanById(Long id) {
        return workoutPlanRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));    }

    @Override
    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }

    @Override
    public boolean deleteWorkoutPlan(Long id) {
        if (workoutPlanRepository.existsById(id)) {
            workoutPlanRepository.deleteById(id);
            return true;
        } else {
            throw new UserNotFoundException("user with id " + id + " not found");
        }
    }
}


