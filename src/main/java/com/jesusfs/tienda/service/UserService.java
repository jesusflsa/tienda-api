package com.jesusfs.tienda.service;

import com.jesusfs.tienda.dto.user.CreateUserDTO;
import com.jesusfs.tienda.dto.user.UpdateUserDTO;
import com.jesusfs.tienda.model.Role;
import com.jesusfs.tienda.model.User;
import com.jesusfs.tienda.repository.RoleRepository;
import com.jesusfs.tienda.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public User createUser(@Valid CreateUserDTO requestUser) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        // Validations
        validateUsername(requestUser.username());
        validatePhone(requestUser.phone());

        // Saving user
        User user = new User(requestUser);
        List<Role> roles = roleRepository.findAllById(requestUser.roles());
        user.setRoles(roles);
        user.setPassword(bcrypt.encode(requestUser.password()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        // Deleting user
        User user = getUserById(id);
        user.setActive(false);
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findByActiveTrue();
    }

    public User updateUser(Long id, UpdateUserDTO userDTO) {
        // Validations
        validateUsername(id, userDTO.username());
        validatePhone(id, userDTO.phone());

        // Updating user
        User user = getUserById(id);
        List<Role> roles = roleRepository.findAllById(userDTO.roles());
        user.setRoles(roles);
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

    // Validations
    private void validateUsername(Long id, String username) {
        Optional<User> opUser;
        if (id == null) opUser = userRepository.findByUsernameIgnoreCase(username);
        else opUser = userRepository.findByUsernameIgnoreCaseAndIdNot(username, id);

        if (opUser.isPresent()) throw new RuntimeException("Username is already in use. Please choose another.");
    }

    private void validateUsername(String username) {
        validateUsername(null, username);
    }

    private void validatePhone(Long id, String phone) {
        Optional<User> opUser;
        if (id == null) opUser = userRepository.findByPhone(phone);
        else opUser = userRepository.findByPhoneAndIdNot(phone, id);

        if (opUser.isPresent()) throw new RuntimeException("Phone is already in use. Please use another.");
    }

    private void validatePhone(String phone) {
        validatePhone(null, phone);
    }

    // Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCaseAndActiveTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User \"" + username + "\" not exists"));
    }
}
