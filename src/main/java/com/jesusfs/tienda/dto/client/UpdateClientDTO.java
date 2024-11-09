package com.jesusfs.tienda.dto.client;

import jakarta.validation.constraints.Size;

public record UpdateClientDTO(
        String firstName,

        String lastName,

        @Size(min = 8, max = 8, message = "Invalid DNI.")
        String dni,

        @Size(min = 7, max = 9, message = "Invalid phone.")
        String phone,

        String streetAddress
) {}
