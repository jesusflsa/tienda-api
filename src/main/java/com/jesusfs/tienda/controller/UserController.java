package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.dto.user.CreateUserDTO;
import com.jesusfs.tienda.dto.user.ResponseUserDTO;
import com.jesusfs.tienda.dto.user.UpdateUserDTO;
import com.jesusfs.tienda.model.User;
import com.jesusfs.tienda.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    UserService userService;

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
