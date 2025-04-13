package io.github.vanshikaa.fitnessplanner.gymnasium.controller;


import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import io.github.vanshikaa.fitnessplanner.gymnasium.service.UserService;
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
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

//is this wirk
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Positive Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("user deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }
}
