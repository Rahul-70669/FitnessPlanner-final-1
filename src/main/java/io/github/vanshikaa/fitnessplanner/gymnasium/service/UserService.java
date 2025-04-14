package io.github.vanshikaa.fitnessplanner.gymnasium.service;

import io.github.vanshikaa.fitnessplanner.gymnasium.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByName(String name);

    User updateUserHeight(Long id ,double height);

    User updateUserWeight(Long id ,double weight);

    User updateUsertransformationtype(Long id ,String transformationtype);

    boolean deleteUser(Long id);
}
