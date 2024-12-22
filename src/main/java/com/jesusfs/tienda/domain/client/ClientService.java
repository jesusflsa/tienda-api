package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import jakarta.validation.Valid;

public interface ClientService {
    Client createClient(@Valid CreateClientDTO clientDTO);
}
