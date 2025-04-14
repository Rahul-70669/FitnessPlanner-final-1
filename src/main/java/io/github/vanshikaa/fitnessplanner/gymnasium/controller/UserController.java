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

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("DatabaseUserService") UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> products = userService.getAllUsers();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Positive Long id) {
        User product = userService.getUserById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/byName")
    public ResponseEntity<User> getProductByName(@RequestParam String name) {
        User product = userService.getUserByName(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/height/{id}")
    public ResponseEntity<User> updateUserheight(@PathVariable @Positive Long id,
                                                 @RequestParam @Min(value = 100)double height) {
        User product = userService.updateUserHeight(id, height);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/weight/{id}")
    public ResponseEntity<User> updateUserWeight(@PathVariable @Positive Long id,
                                                 @RequestParam @Min(value = 20)double weight) {
        User product = userService.updateUserWeight(id, weight);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/byName/{id}")
    public ResponseEntity<User> updateUserTransformationtype(@PathVariable @Positive Long id,
                                                             @RequestParam @Size(min=7,max=7) String transformationtype) {
        User product = userService.updateUsertransformationtype(id, transformationtype);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Positive Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("user deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }
}
