package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.user.UserServiceImpl;
import com.jesusfs.tienda.domain.user.dto.CreateUserDTO;
import com.jesusfs.tienda.domain.user.dto.ResponseUserDTO;
import com.jesusfs.tienda.domain.user.dto.UpdateUserDTO;
import com.jesusfs.tienda.domain.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class UserController {
    UserServiceImpl userService;

    @GetMapping
    public List<ResponseUserDTO> getUsers() {
        List<User> userList = userService.getUsers();
        return userList.stream().map(ResponseUserDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseUserDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseUserDTO(user);
    }

    @PostMapping
    public ResponseUserDTO createUser(@RequestBody @Valid CreateUserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return new ResponseUserDTO(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(new ResponseUserDTO(user));
    }
}
