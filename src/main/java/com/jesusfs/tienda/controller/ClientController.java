package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.client.Client;
import com.jesusfs.tienda.domain.client.ClientServiceImpl;
import com.jesusfs.tienda.domain.client.dto.ResponseClientDTO;
import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private ClientServiceImpl clientService;

    @PostMapping
    public ResponseClientDTO registerClient(@Valid @RequestBody CreateClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        return new ResponseClientDTO(client);
    }
}
