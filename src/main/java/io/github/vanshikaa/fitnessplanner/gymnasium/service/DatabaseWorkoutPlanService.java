package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserNotFoundException;
import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserValidationException;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.WorkoutPlan;
import io.github.vanshikaa.fitnessplanner.gymnasium.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Service class that handles business logic related to Workout Plans.
 */
@Service("DatabaseWorkoutPlanService")
public class DatabaseWorkoutPlanService implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    public DatabaseWorkoutPlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }


    /**
     * Creates a new Workout Plan.
     * @param workoutPlan The WorkoutPlan object containing the details of the workout plan.
     * @return The created WorkoutPlan object.
     * @throws UserValidationException if the workout plan details are invalid.
     */
    @Override
    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        if(workoutPlan==null){
            throw new UserValidationException("workout plan must not be null");
        }
        return workoutPlanRepository.save(workoutPlan);
    }

    /**
     * Retrieves all exixting WorkoutPlans
     * @return The WorkoutPlan object if found.
     * @throws UserNotFoundException if no workout plan is found with the provided ID.
     */
    @Override
    public List<WorkoutPlan> getAllWorkoutPlans() {
        List<WorkoutPlan> workoutList = workoutPlanRepository.findAll();
        if (workoutList.isEmpty()) {
            throw new UserNotFoundException("No workout plan found");
        }
        return workoutPlanRepository.findAll();
    }

    /**
     * Retrieves a Workout Plan by its ID.
     * @param id The ID of the workout plan to retrieve.
     * @return The WorkoutPlan object if found.
     * @throws UserNotFoundException if no workout plan is found with the provided ID.
     */
    @Override
    public WorkoutPlan getWorkoutPlanById(Long id) {
        return workoutPlanRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("workout plan with id " + id + " not found"));    }

    @Override
    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }


    /**
     * Updates the description of workout split assigned to the users.
     * @param userId Id of workout plan to retrieve user.
     * @param workoutPlan Updates new description with the new description provided.
     * @return Returns updated workout plan with new description.
     */
    @Override
    public WorkoutPlan updateDescription(Long userId, WorkoutPlan workoutPlan) {
        WorkoutPlan workoutPlanToUpdate = workoutPlanRepository.findById(workoutPlan.getUserId()).get();
        workoutPlanToUpdate.setDescription(workoutPlan.getDescription());
        return workoutPlanRepository.save(workoutPlanToUpdate);
    }

    /**
     * Updates user id according to the new updated description if needed to assign.
     * @param id Retrives workoutPLan
     * @param userId assigns workoutplan according to the user id.
     * @return Returns new workout plan accoording to the updated id.
     */
    @Override
    public WorkoutPlan updateUserId(Long id, long userId) {
        WorkoutPlan workoutPlanToUpdate = workoutPlanRepository.findById(id).get();
        workoutPlanToUpdate.setUserId(userId);
        workoutPlanRepository.save(workoutPlanToUpdate);
        return workoutPlanRepository.save(workoutPlanToUpdate);
    }

    /**
     * Deletes workout plan.
     * @param id Retrives workoutplan to be deleted.
     * @return Returns a msg "workoutplan deleted successfully."
     */
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


