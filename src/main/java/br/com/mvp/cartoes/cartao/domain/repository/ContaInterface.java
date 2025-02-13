package br.com.mvp.cartoes.cartao.domain.repository;

import br.com.mvp.cartoes.cartao.domain.model.Conta;

import java.util.List;

public interface ContaInterface {

    Boolean contaExiste(String numero);
    Conta criarConta(Conta conta);
    void excluirContaPorNumero(String numero);
    Conta buscarContaPorNumero(String numero);
    void excluirContaPorIdCliente(Long idCliente);
    Conta buscarContaPorId(Long idConta);
    List<Conta> listaContasPorIdCliente(Long idCliente);
}

