package com.sodemed.services.users.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.response.DtoClient;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.exceptions.NotUpdateException;
import com.sodemed.models.users.Client;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.UserType;
import com.sodemed.repositories.users.ClientRepository;
import com.sodemed.repositories.users.UserRepository;
import com.sodemed.services.users.ClientService;
import com.sodemed.utils.users.EntityMapper;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DtoClient create(DtoRequestClient client) {
        try {
            if (this.clientRepository.existsByEmailAndUserType(client.getEmail(), UserType.client)) {
                throw new NotCreateException("El correo ya se encuentra registrado");
            }
            if (this.clientRepository.existsByIdentificationAndUserType(client.getIdentification(), UserType.client)) {
                throw new NotCreateException("La identificación ya se encuentra registrada");
            }
            Client clientSave = EntityMapper.dtoRequestClientToEntity(client);
            clientSave.setPassword(passwordEncoder.encode(client.getPassword()));
            clientSave.setStatus(StatusUser.active);
            return EntityMapper.entityToDtoClient(this.clientRepository.save(clientSave));
        } catch (NotCreateException e) {
            throw new NotCreateException(e.getMessage());
        } catch (Exception e) {
            throw new NotCreateException("No se pudo crear el usuario");
        }
    }

    @Override
    public DtoClient fetch(long id) {
        try {
            Client client = this.clientRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            return EntityMapper.entityToDtoClient(client);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró usuario");
        }
    }

    @Override
    public DtoClient update(long id, DtoRequestClient client) {
        try {
            Client clientExist = this.clientRepository.findById(id)
                    .orElseThrow(() -> new NotUpdateException("No se encontró usuario"));
            ;
            if (clientExist != null && !clientExist.getEmail().equals(client.getEmail())) {
                if (this.userRepository.existsByEmailAndUserType(client.getEmail(), UserType.client)) {
                    throw new NotUpdateException("El correo ya se encuentra registrado");
                }
            }
            if (clientExist != null && !clientExist.getIdentification().equals(client.getIdentification())) {
                if (this.userRepository.existsByIdentificationAndUserType(client.getIdentification(),
                        UserType.client)) {
                    throw new NotUpdateException("La identificación ya se encuentra registrada");
                }
            }
            clientExist = EntityMapper.dtoRequestClientToEntity(client);
            clientExist.setId(id);
            clientExist.setPassword(passwordEncoder.encode(client.getPassword()));
            return EntityMapper.entityToDtoClient(this.clientRepository.save(clientExist));
        } catch (NotUpdateException e) {
            throw new NotUpdateException(e.getMessage());
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar el usuario con ID " + id);
        }
    }

    @Override
    public DtoClient delete(long id) {
        try {
            Client existingClient = this.clientRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            existingClient.setStatus(StatusUser.inactive);
            return EntityMapper.entityToDtoClient(this.clientRepository.save(existingClient));
        } catch (Exception e) {
            throw new NotFoundException("No se pudo eliminar el usuario con ID " + id);
        }
    }

    @Override
    public List<DtoClient> fetchAll() {
        try {
            List<Client> clients = this.clientRepository.findAll();
            List<DtoClient> dtoClients = clients.stream().map(EntityMapper::entityToDtoClient)
                    .collect(Collectors.toList());
            return dtoClients;
        } catch (Exception e) {
            throw new NotFoundException("No se encontró usuarios");
        }
    }

}
