package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserNotFoundException;
import io.github.vanshikaa.fitnessplanner.gymnasium.exceptions.UserValidationException;
import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;
import io.github.vanshikaa.fitnessplanner.gymnasium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DatabaseUserService")
public class DatabaseUserService implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public DatabaseUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        if(user==null){
            throw new UserValidationException("User must not be null");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> productList = userRepository.findAll();
        if (productList.isEmpty()) {
            throw new UserNotFoundException("No user found");
        }
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));    }


    @Override
    public User getUserByName(String name) {

        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("user with name " + name + " not found"));
           }

    @Override
    public boolean deleteUser(Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            throw new UserNotFoundException("user with id " + id + " not found");
        }    }
}




