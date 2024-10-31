package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.model.user.CreateUserDTO;
import com.jesusfs.tienda.model.user.ResponseUserDTO;
import com.jesusfs.tienda.model.user.UpdateUserDTO;
import com.jesusfs.tienda.model.user.User;
import com.jesusfs.tienda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
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
    public ResponseEntity deleteUser(@PathVariable Long id) {
        boolean res = userService.deleteUser(id);
        return res ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(new ResponseUserDTO(user));
    }
}
