package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Client client = new Client();
        client.setFullName("jesus flores");
        client.setUsername("jesusfs@example.com");
        client.setPassword(bcrypt.encode("password123"));

        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
    }

    @Test
    public void createClientShouldSuccess() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        CreateClientDTO clientDTO = new CreateClientDTO("jesus flores", "jesusfs@example.com", "password123");
        Client client = clientService.createClient(clientDTO);
        assertEquals(client.getFullName(), clientDTO.fullName());
        assertEquals(client.getUsername(), clientDTO.email());
        assertTrue(bcrypt.matches(clientDTO.password(), client.getPassword()));
    }
}