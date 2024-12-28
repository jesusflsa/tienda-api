package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.domain.client.Client;
import com.jesusfs.tienda.domain.client.ClientService;
import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import com.jesusfs.tienda.domain.user.UserRepository;
import com.jesusfs.tienda.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ClientController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;

    private Client client;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        client = new Client();
        client.setId(1L);
        client.setFullName("jesus flores");
        client.setUsername("jesusfs@example.com");
        client.setPassword(bcrypt.encode("password123"));
    }

    @Test
    public void createClient() throws Exception {
        CreateClientDTO clientDTO = new CreateClientDTO("jesus flores", "jesusfs@example.com", "password123");

        Mockito.when(clientService.createClient(clientDTO)).thenReturn(client);
        mockMvc.perform(post("/clients")
                .header("Authorization")
                .contentType(MediaType.APPLICATION_JSON).content("""
                {
                    "full_name": "jesus flores",
                    "email": "jesusfs@example.com",
                    "password": "password123"
                }
                """)).andExpect(status().isOk());
    }
}