package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.client.Client;
import com.jesusfs.tienda.domain.client.ClientService;
import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import com.jesusfs.tienda.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@PreAuthorize("permitAll()")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;
    private JwtService jwtService;

    @PostMapping
    public String registerClient(@Valid @RequestBody CreateClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        return jwtService.createToken(client);
    }
}
