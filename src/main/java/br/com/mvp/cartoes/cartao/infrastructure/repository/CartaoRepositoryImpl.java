package br.com.mvp.cartoes.cartao.infrastructure.repository;

import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cartao.domain.repository.CartaoInterface;
import br.com.mvp.cartoes.cartao.infrastructure.adapter.CartaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@ApplicationScoped
public class CartaoRepositoryImpl implements CartaoInterface, CartaoRepository {


    @Override
    public void cadastrarCartao(Cartao cartao) {
        this.persist(cartao);
    }

    @Override
    public List<Cartao> listarCartoes(Long idConta) {
       return  list("conta.idConta", idConta);
    }


    @Override
    public void excluirCartao(Cartao cartao) {
        this.delete(cartao);
    }

    @Override
    public void atualizarCvvCartao(Cartao cartao) {
        this.persist(cartao);
    }

    @Override
    public void atualizarCartao(Cartao cartao) {
        this.persist(cartao);
    }


}
