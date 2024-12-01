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

    public User createUser(@Valid CreateUserDTO requestUser) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        // Validations
        validateUsername(requestUser.username());

        // Saving user
        User user = new User(requestUser);
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

        // Updating user
        User user = getUserById(id);
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

    // Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCaseAndActiveTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User \"" + username + "\" not exists"));
    }
}
