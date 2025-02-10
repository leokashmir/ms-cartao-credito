package br.com.mvp.cartoes.cliente.application.service;


import br.com.mvp.cartoes.cliente.infrastructure.repository.ClienteRepositoryImpl;
import br.com.mvp.cartoes.cliente.presentation.dto.ClienteRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClienteService {


    private final ClienteRepositoryImpl repository;

    @Inject
    public ClienteService(ClienteRepositoryImpl repository) {
        this.repository = repository;
    }

    public void cadastrarCliente(ClienteRequestDTO request) {

        repository.cadastrarCliente(null);
    }
}
