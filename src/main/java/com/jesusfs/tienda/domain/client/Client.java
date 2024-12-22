package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.address.Address;
import com.jesusfs.tienda.domain.cart.Cart;
import com.jesusfs.tienda.domain.order.Order;
import com.jesusfs.tienda.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToMany(mappedBy = "client")
    private List<Address> addressList;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
