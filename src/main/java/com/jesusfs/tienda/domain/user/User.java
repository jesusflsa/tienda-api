package com.jesusfs.tienda.domain.user;

import com.jesusfs.tienda.domain.user.dto.CreateUserDTO;
import com.jesusfs.tienda.domain.user.dto.UpdateUserDTO;
import com.jesusfs.tienda.domain.client.role.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column(name = "active")
    private boolean active;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return ((User) o).getId().equals(this.id);
    }

    // Spring Security User Model
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name())));
        roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName().name())));
        authorities.forEach(System.out::println);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

}
