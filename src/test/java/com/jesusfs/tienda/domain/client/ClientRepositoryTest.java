package com.jesusfs.tienda.domain.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Client client = new Client();
        client.setFullName("Jesús Flores");
        client.setUsername("jesusfs");
        client.setPassword(bcrypt.encode("password123"));
        client.setGender(Gender.MALE);
        testEntityManager.persist(client);
    }

    @Test
    public void findByEmailFound() {
        boolean exists = clientRepository.existsByEmailIgnoreCase("jesusfs");
        assertTrue(exists);
    }
}