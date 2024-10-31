package com.jesusfs.tienda.service;

import com.jesusfs.tienda.model.user.CreateUserDTO;
import com.jesusfs.tienda.model.user.UpdateUserDTO;
import com.jesusfs.tienda.model.user.User;
import com.jesusfs.tienda.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(@Valid CreateUserDTO requestUser) {
        // Validations
        // Unique username
        Optional<User> opUser = userRepository.findByUsernameIgnoreCase(requestUser.username());
        if (opUser.isPresent()) throw new RuntimeException("Username already exists. Please choose another.");

        // Unique phone number
        opUser = userRepository.findByPhone(requestUser.phone());
        if (opUser.isPresent()) throw new RuntimeException("Phone already in use. Please use another.");

        // Saving user
        User user = new User(requestUser);
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        // Validations
        // User not exist or is already not active
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isEmpty() || !opUser.get().getActive()) return false;

        // Deleting user
        User user = opUser.get();
        user.setActive(false);
        userRepository.save(user);

        return true;
    }

    public List<User> getUsers() {
        return userRepository.findByActiveTrue();
    }

    public User updateUser(Long id, UpdateUserDTO userDTO) {
        // Validations
        Optional<User> opUser;

        // New username is already in use
        if (userDTO.username() != null) {
            opUser = userRepository.findByUsernameIgnoreCase(userDTO.username());
            if (opUser.isPresent()) throw new RuntimeException("Username is already in use. Choose another.");
        }
        // New phone is already in use
        if (userDTO.phone() != null) {
            opUser = userRepository.findByPhone(userDTO.phone());
            if (opUser.isPresent()) throw new RuntimeException("Phone number is already in use. Choose another.");
        }
        // User not exist
        opUser = userRepository.findByIdAndActiveTrue(id);
        if (opUser.isEmpty()) throw new RuntimeException("User not exists.");

        // Updating user
        User user = opUser.get();
        user.update(userDTO);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        // Validations
        // User not exist
        Optional<User> opUser = userRepository.findByIdAndActiveTrue(id);
        if (opUser.isEmpty()) throw new RuntimeException("User not exists.");

        // Getting user
        return opUser.get();
    }
}
