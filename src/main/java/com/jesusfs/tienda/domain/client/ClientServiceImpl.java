package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import com.jesusfs.tienda.domain.user.Role;
import com.jesusfs.tienda.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Override
    public Client createClient(CreateClientDTO clientDTO) {
//      Validations
//      Email is taken.
        if (clientRepository.existsByEmailIgnoreCase(clientDTO.email()))
            throw new RuntimeException("This email is taken.");

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        User user = User.builder()
                .password(bcrypt.encode(clientDTO.password()))
                .role(Role.CLIENT)
                .active(true)
                .build();

        Client client = Client.builder()
                .fullName(clientDTO.fullName())
                .email(clientDTO.email())
                .user(user)
                .build();

        return clientRepository.save(client);
    }
}
