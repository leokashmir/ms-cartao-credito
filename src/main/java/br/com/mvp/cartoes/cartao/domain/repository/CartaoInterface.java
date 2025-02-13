package br.com.mvp.cartoes.cartao.domain.repository;

import br.com.mvp.cartoes.cartao.domain.model.Cartao;

import java.util.List;

public interface CartaoInterface {

    void cadastrarCartao(Cartao cartao);
    List<Cartao> listarCartoes(Long idConta);
    void excluirCartao(Cartao cartao);
    void atualizarCvvCartao(Cartao cartao);
    void atualizarCartao(Cartao cartao);

}
