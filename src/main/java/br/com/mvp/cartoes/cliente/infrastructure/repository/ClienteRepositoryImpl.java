package br.com.mvp.cartoes.cliente.infrastructure.repository;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import br.com.mvp.cartoes.cliente.domain.repository.ClienteInterface;
import br.com.mvp.cartoes.cliente.exception.ClienteException;
import br.com.mvp.cartoes.cliente.infrastructure.adapter.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;


@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteInterface, ClienteRepository {

    EntityManager entityManager;

    @Inject
    public ClienteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
      try{
        this.persist(cliente);
        return cliente;

      }catch (ConstraintViolationException e){
            throw new ClienteException("Documento ou Email ja Cadastrado");
      }catch (Exception e){
          throw new ClienteException("Erro ao cadastrar Cliente");
      }
    }


    @Override
    @Transactional
    public void excluirCliente(Cliente cliente) {
        if(!deleteById(cliente.getIdCliente())){
            throw new ClienteException("Não existe um cliente com este identificador.");
        };
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

    @Override
    public Cliente buscarPorDocumento(String documento) {
        return this.findByDocumento(documento);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return this.findById(id);
    }


}
