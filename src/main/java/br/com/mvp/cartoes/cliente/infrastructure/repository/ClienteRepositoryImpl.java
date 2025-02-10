package br.com.mvp.cartoes.cliente.infrastructure.repository;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import br.com.mvp.cartoes.cliente.domain.repository.ClienteInterface;
import br.com.mvp.cartoes.cliente.infrastructure.adapter.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteInterface, ClienteRepository {


    @Override
    @Transactional
    public void cadastrarCliente(Cliente cliente) {
        this.persist(cliente);
    }
}
