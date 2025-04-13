package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserValidationException;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import io.github.vanshikaa.fitnessplanner.gymnasium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        if(user==null){
            throw new UserValidationException("User must not be null");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}




