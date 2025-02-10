package br.com.mvp.cartoes.cliente.infrastructure.repository;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import br.com.mvp.cartoes.cliente.domain.repository.ClienteInterface;
import br.com.mvp.cartoes.cliente.infrastructure.adapter.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteInterface, ClienteRepository {

    EntityManager entityManager;

    @Inject
    public ClienteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void cadastrarCliente(Cliente cliente) {
        this.persist(cliente);
    }


    @Override
    public void excluirCliente(Cliente cliente) {
        this.delete(cliente);
    }

    @Override
    public Cliente buscarClientePorDoucmento(String documento) {
        return this.findByDocumento(documento);
    }

    @Override
    @Transactional
    public void atualizarCliente(Cliente cliente) {
        entityManager.merge(cliente);
        entityManager.flush();
    }


}
