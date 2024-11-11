package com.jesusfs.tienda.domain.client;

import com.jesusfs.tienda.domain.client.dto.CreateClientDTO;
import com.jesusfs.tienda.domain.client.dto.UpdateClientDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public Client createClient(@Valid CreateClientDTO clientDTO) {
        // Validations
        validateDni(clientDTO.dni());
        validatePhone(clientDTO.phone());

        // Creating client
        Client client = new Client(clientDTO);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        // Deleting client
        Client client = getClientById(id);
        client.setActive(false);
        clientRepository.save(client);
    }

    public Client updateClient(Long id, @Valid UpdateClientDTO clientDTO) {
        // Validations
        validateDni(id, clientDTO.dni());
        validatePhone(id, clientDTO.phone());

        // Updating client
        Client client = getClientById(id);
        client.update(clientDTO);
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        // Validations
        // Client not exists
        Optional<Client> opClient = clientRepository.findByIdAndActiveTrue(id);
        if (opClient.isEmpty()) throw new RuntimeException("Client not exists.");

        // Getting client
        return opClient.get();
    }

    public List<Client> getClients() {
        return clientRepository.findByActiveTrue();
    }

    // Validations
    private void validateDni(Long id, String dni) {
        Optional<Client> opClient;
        if (id == null) {
            opClient = clientRepository.findByDni(dni);
        } else {
            opClient = clientRepository.findByDniAndIdNot(dni, id);
        }
        if (opClient.isPresent()) throw new RuntimeException("DNI is taken. Use another.");
    }

    private void validatePhone(Long id, String phone) {
        if (phone == null) return;

        Optional<Client> opClient;
        if (id == null) opClient = clientRepository.findByPhone(phone);
        else opClient = clientRepository.findByPhoneAndIdNot(phone, id);

        if (opClient.isPresent()) throw new RuntimeException("Phone is taken. Use another.");
    }

    private void validatePhone(String phone) { validatePhone(null, phone); }
    private void validateDni(String dni) { validateDni(null, dni); }
}
