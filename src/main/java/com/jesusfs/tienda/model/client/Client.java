package com.jesusfs.tienda.model.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "phone")
    private String phone;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "active")
    private boolean active;

    public Client(CreateClientDTO clientDTO) {
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.dni = clientDTO.dni();
        this.phone = clientDTO.phone();
        this.streetAddress = clientDTO.streetAddress();
        this.active = true;
    }

    public void update(UpdateClientDTO clientDTO) {
        if (clientDTO.firstName() != null && !this.firstName.equals(clientDTO.firstName())) {
            this.firstName = clientDTO.firstName();
        }

        if (clientDTO.lastName() != null && !this.lastName.equals(clientDTO.lastName())) {
            this.lastName = clientDTO.lastName();
        }

        if (clientDTO.dni() != null && !this.dni.equals(clientDTO.dni())) {
            this.dni = clientDTO.dni();
        }

        if (clientDTO.phone() != null && !this.phone.equals(clientDTO.phone())) {
            this.phone = clientDTO.phone();
        }

        if (clientDTO.streetAddress() != null && !this.streetAddress.equals(clientDTO.streetAddress())) {
            this.streetAddress = clientDTO.streetAddress();
        }
    }
}
