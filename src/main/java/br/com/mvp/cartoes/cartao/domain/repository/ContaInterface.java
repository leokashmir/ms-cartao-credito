package br.com.mvp.cartoes.cartao.domain.repository;

import br.com.mvp.cartoes.cartao.domain.model.Conta;

public interface ContaInterface {

    Boolean contaExiste(String numero);
    Conta criarConta(Conta conta);
    void apagarConta(String numero);
    Conta getConta(String numero);
}
