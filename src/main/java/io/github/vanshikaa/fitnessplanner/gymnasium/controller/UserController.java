package io.github.vanshikaa.fitnessplanner.gymnasium.controller;


import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import io.github.vanshikaa.fitnessplanner.gymnasium.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing user in the FitnessPlanner application.
 * Provides endpoints for CRUD operations on users.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("DatabaseUserService") UserService userService) {
        this.userService = userService;
    }



    /**
     * Creates a new product.
     *
     * @param user The product information to create
     * @return The created product with HTTP status 201 (created)
     */
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves all user from the system.
     *
     * @return ResponseEntity containing a list of all users with HTTP status 201 (OK)
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> products = userService.getAllUsers();
        return ResponseEntity.ok(products);
    }


    /**
     * Retrieves a specific user by its ID.
     *
     * @param id the unique identifier of the user
     * @return ResponseEntity containing the product with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the user doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Positive Long id) {
        User product = userService.getUserById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Retrieves a user by its name.
     *
     * @param name the name of the user to search for
     * @return ResponseEntity containing the user with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if no user with the given name exists
     */
    @GetMapping("/byName")
    public ResponseEntity<User> getProductByName(@RequestParam String name) {
        User product = userService.getUserByName(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Updates the height of a specific user.
     *
     * @param id    the unique identifier of the user to update
     * @param height the new height value (must be non-negative)
     * @return ResponseEntity containing the updated user with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the user doesn't exist
     */
    @PutMapping("/height/{id}")
    public ResponseEntity<User> updateUserheight(@PathVariable @Positive Long id,
                                                 @RequestParam @Min(value = 100)double height) {
        User product = userService.updateUserHeight(id, height);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Updates the weight of a specific user.
     *
     * @param id    the unique identifier of the user to update
     * @param weight the new weight value (must be non-negative)
     * @return ResponseEntity containing the updated user with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the user doesn't exist
     */
    @PutMapping("/weight/{id}")
    public ResponseEntity<User> updateUserWeight(@PathVariable @Positive Long id,
                                                 @RequestParam @Min(value = 20)double weight) {
        User product = userService.updateUserWeight(id, weight);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Updates the transformationtype of a specific user.
     *
     * @param id    the unique identifier of the user to update
     * @param transformationtype the new transformationtype value (must be non-negative)
     * @return ResponseEntity containing the updated user with HTTP status 201 (OK) if found,
     * or HTTP status 404 (Not Found) if the user doesn't exist
     */
    @PutMapping("/byName/{id}")
    public ResponseEntity<User> updateUserTransformationtype(@PathVariable @Positive Long id,
                                                             @RequestParam @Size(min=7,max=7) String transformationtype) {
        User product = userService.updateUsertransformationtype(id, transformationtype);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Deletes a specific user from the system.
     *
     * @param id the unique identifier of the user to delete
     * @return ResponseEntity with a success message and HTTP status 201 (OK) if deleted,
     * or an error message and HTTP status 404 (Not Found) if the product doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Positive Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("user deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }
}
