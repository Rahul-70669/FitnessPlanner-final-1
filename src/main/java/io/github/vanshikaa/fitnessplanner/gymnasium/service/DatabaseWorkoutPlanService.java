package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserNotFoundException;
import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserValidationException;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.WorkoutPlan;
import io.github.vanshikaa.fitnessplanner.gymnasium.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DatabaseWorkoutPlanService")
public class DatabaseWorkoutPlanService implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    public DatabaseWorkoutPlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        if(workoutPlan==null){
            throw new UserValidationException("workout plan must not be null");
        }
        return workoutPlanRepository.save(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> getAllWorkoutPlans() {
        List<WorkoutPlan> workoutList = workoutPlanRepository.findAll();
        if (workoutList.isEmpty()) {
            throw new UserNotFoundException("No workout plan found");
        }
        return workoutPlanRepository.findAll();
    }

    @Override
    public WorkoutPlan getWorkoutPlanById(Long id) {
        return workoutPlanRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("workout plan with id " + id + " not found"));    }

    @Override
    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }


    @Override
    public WorkoutPlan updateDescription(Long userId, WorkoutPlan workoutPlan) {
        WorkoutPlan workoutPlanToUpdate = workoutPlanRepository.findById(workoutPlan.getUserId()).get();
        workoutPlanToUpdate.setDescription(workoutPlan.getDescription());
        return workoutPlanRepository.save(workoutPlanToUpdate);
    }

    @Override
    public WorkoutPlan updateUserId(Long id, long userId) {
        WorkoutPlan workoutPlanToUpdate = workoutPlanRepository.findById(id).get();
        workoutPlanToUpdate.setUserId(userId);
        workoutPlanRepository.save(workoutPlanToUpdate);
        return workoutPlanRepository.save(workoutPlanToUpdate);
    }

    @Override
    public boolean deleteWorkoutPlan(Long id) {
        if (workoutPlanRepository.existsById(id)) {
            workoutPlanRepository.deleteById(id);
            return true;
        } else {
            throw new UserNotFoundException("workout plan with id " + id + " not found");
        }
    }
}


