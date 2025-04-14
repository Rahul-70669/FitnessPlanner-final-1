package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserNotFoundException;
import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserValidationException;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import io.github.vanshikaa.fitnessplanner.gymnasium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A sevice class that uses database to store and manage user information.
 * This class implements the userService interface and uses of user repository to perform operations on the database.
 */
@Service("DatabaseUserService")
public class DatabaseUserService implements UserService {

    /**
     * The repository used to access the database.
     */
    private final UserRepository userRepository;

    /**
     * Creates a new DatabaseUserService with the provided repository.
     *
     * @param userRepository the repository to use for database operations
     */
    @Autowired
    public DatabaseUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    /**
     * Adds a new user to the database.
     *
     * @param user the user to add
     * @return the saved user with any database-generated values (like ID)
     */
    @Override
    public User addUser(User user) {
        if(user==null){
            throw new UserValidationException("User must not be null");
        }
        return userRepository.save(user);
    }

    /**
     * Gets a list of all users from the database.
     *
     * @return a list containing all user.
     */
    @Override
    public List<User> getAllUsers() {

        List<User> productList = userRepository.findAll();
        if (productList.isEmpty()) {
            throw new UserNotFoundException("No user found");
        }
        return userRepository.findAll();
    }

    /**
     * Finds a user in the database using its ID.
     *
     * @param id the ID of the user to find
     * @return the found user, or null if no user exists with the given ID
     */
    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));    }


    /**
     * Finds a user in the database using its name.
     *
     * @param name the name of the user to find
     * @return the found user, or null if no user exists with the given name
     */
    @Override
    public User getUserByName(String name) {

        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("user with name " + name + " not found"));
           }

    /**
     * Updates the name of a user in the database.
     *
     * @param id the ID of the user to update
     * @param  height the new height to set
     * @return the updated user info, or null if no user exists with the given ID
     */
    @Override
    public User updateUserHeight(Long id, double height) {
        if(height<=100){
            throw new UserValidationException("height must be at least 100cm");
        }
        User user = getUserById(id);
        if(user!=null){
            user.setHeight(height);
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Updates the name of a user in the database.
     *
     * @param id the ID of the user to update
     * @param  weight the new weight to set
     * @return the updated user info, or null if no user exists with the given ID
     */
    @Override
    public User updateUserWeight(Long id, double weight) {
        if(weight<=20){
            throw new UserValidationException("weight must be at least 20kg");
        }
        User user = getUserById(id);
        if(user!=null){
            user.setWeight(weight);
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Updates the name of a user in the database.
     *
     * @param id the ID of the user to update
     * @param  transformationtype the new type to set
     * @return the updated user info, or null if no user exists with the given ID
     */
    @Override
    public User updateUsertransformationtype(Long id, String transformationtype) {
        if(transformationtype==null || transformationtype.isEmpty() || transformationtype.equals("Gaining/Leaning")){
            throw new UserValidationException("Type is required (Gaining/Leaning)");
        }
        User user = getUserById(id);
        if(user!=null){
            user.setTransformationtype(transformationtype);
            return userRepository.save(user);
        }
        return null;

    }


    /**
     * Deletes a user from the database.
     *
     * @param id the ID of the user to delete
     * @return true if a user was deleted, false if no user exists with the given ID
     */
    @Override
    public boolean deleteUser(Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            throw new UserNotFoundException("user with id " + id + " not found");
        }
    }
}




