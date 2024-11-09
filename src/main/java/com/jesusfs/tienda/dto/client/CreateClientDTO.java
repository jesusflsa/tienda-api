package com.jesusfs.tienda.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClientDTO(
        @NotBlank(message = "First name cannot be empty.")
        String firstName,

        @NotBlank(message = "Last name cannot be empty.")
        String lastName,

        @Size(min = 8, max = 8, message = "Invalid DNI.")
        @NotBlank(message = "DNI cannot be empty.")
        String dni,

        @Size(min = 7, max = 9, message = "Invalid phone.")
        String phone,

        String streetAddress
) { }
