package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private PasswordEncoder passwordEncoder;
    private ClientRepository clientRepository;

    @Override
    public Client createClient(CreateClientDTO clientDTO) {
//      Validations
//      Email is taken.
        if (clientRepository.existsByEmailIgnoreCase(clientDTO.email()))
            throw new RuntimeException("This email is taken.");

        Client client = new Client(clientDTO.email(), passwordEncoder.encode(clientDTO.password()), clientDTO.fullName());

        return clientRepository.save(client);
    }
}
