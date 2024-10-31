package com.jesusfs.tienda.model.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "active")
    private Boolean active;

    public User(@Valid CreateUserDTO requestUser) {
        this.username = requestUser.username();
        this.password = requestUser.password();
        this.firstName = requestUser.firstName();
        this.lastName = requestUser.lastName();
        this.phone = requestUser.phone();
        this.active = true;
    }

    public void update(@Valid UpdateUserDTO userDTO) {
        if (userDTO.username() != null && !this.username.equals(userDTO.username())) {
            this.username = userDTO.username();
        }

        if (userDTO.password() != null && !this.password.equals(userDTO.password())) {
            this.password = userDTO.password();
        }

        if (userDTO.firstName() != null && !this.firstName.equals(userDTO.firstName())) {
            this.firstName = userDTO.firstName();
        }

        if (userDTO.lastName() != null && !this.lastName.equals(userDTO.lastName())) {
            this.lastName = userDTO.lastName();
        }

        if (userDTO.phone() != null && (this.phone == null || !this.phone.equals(userDTO.phone()))) {
            this.phone = userDTO.phone();
        }

    }
}
