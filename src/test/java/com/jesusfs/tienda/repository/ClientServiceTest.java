package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.domain.client.ClientRepository;
import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import com.jesusfs.tienda.domain.client.Client;
import com.jesusfs.tienda.domain.client.ClientService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClientServiceTest {
    private ClientService clientService;
    private ClientRepository clientRepository;

    @Test
    public void createClient__withValidDNI__shouldSaveClientOnDatabase() {
        Optional<Client> opClient = clientRepository.findByDni("159753");
        // No client must have this dni registered
        assertTrue(opClient.isEmpty());

        CreateClientDTO clientDTO = new CreateClientDTO("jesus", "flores", "159753", null, null);
        Client client = clientService.createClient(clientDTO);

        assertEquals("159753", client.getDni());
    }
}
