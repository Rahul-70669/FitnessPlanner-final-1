package io.github.vanshikaa.fitnessplanner.gymnasium.repository;

import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities in the database.
 * <p>
 * This interface extends {@link JpaRepository} which provides standard CRUD operations:
 * <ul>
 *     <li>{@code save(User entity)}: Saves a user entity to the database</li>
 *     <li>{@code findById(Long id)}: Finds a user by its ID</li>
 *     <li>{@code findAll()}: Retrieves all users</li>
 *     <li>{@code delete(User entity)}: Deletes a specific user entity</li>
 *     <li>{@code count()}: Returns the total number of users</li>
 *     <li>{@code existsById(Long id)}: Checks if a user with given ID exists</li>
 * </ul>
 * <p>
 * The {@code @Repository} annotation marks this interface as a Spring Data repository,
 * which allows Spring to find and configure it automatically.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by its name.
     * <p>
     * This custom method is created because JpaRepository doesn't provide a
     * built-in method to search by the user name. Spring Data JPA will automatically
     * implement this method based on its name following the pattern "findBy[PropertyName]".
     * <p>
     * The method returns an Optional object which may or may not contain a user,
     * avoiding potential null pointer exceptions when no user is found with the given name.
     *
     * @param name the name of the user to search for
     * @return an Optional containing the found user, or an empty Optional if no user exists with the given name
     */
    Optional<User> findByName(String name);

}
