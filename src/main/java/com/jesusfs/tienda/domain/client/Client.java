package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.address.Address;
import com.jesusfs.tienda.domain.cart.Cart;
import com.jesusfs.tienda.domain.order.Order;
import com.jesusfs.tienda.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DiscriminatorValue("CLIENT")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Client extends User {
    @Column(name = "full_name")
    private String fullName;

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

    public Client(String username, String password, String fullName) {
        super(username, password);
        this.fullName = fullName;
    }
}
