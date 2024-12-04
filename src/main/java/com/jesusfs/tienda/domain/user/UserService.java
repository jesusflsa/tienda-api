package com.jesusfs.tienda.domain.user;

import com.jesusfs.tienda.domain.user.dto.CreateUserDTO;
import com.jesusfs.tienda.domain.user.dto.UpdateUserDTO;
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

    public User createUser(@Valid CreateUserDTO userDTO) {
        // Validations
        validateUsername(userDTO.username());

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        // Saving user
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(bcrypt.encode(userDTO.password()));
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

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        // Updating user
        User user = getUserById(id);
        user.setUsername(userDTO.username());
        user.setPassword(bcrypt.encode(userDTO.password()));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> opUser = userRepository.findByIdAndActiveTrue(id);
        if (opUser.isEmpty()) throw new RuntimeException("User not exists.");
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

    // Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opUser = userRepository.findByUsernameIgnoreCaseAndActiveTrue(username);
        if (opUser.isEmpty()) throw new UsernameNotFoundException("Username " + username + " not exists.");

        return opUser.get();
    }
}
