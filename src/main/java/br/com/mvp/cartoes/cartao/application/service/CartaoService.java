package br.com.mvp.cartoes.cartao.application.service;

import br.com.mvp.cartoes.cartao.application.usercase.CartaoUseCase;
import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartaoService {


    private CartaoUseCase cartaoUseCase;

    @Inject
    public CartaoService(CartaoUseCase cartaoUseCase) {
        this.cartaoUseCase = cartaoUseCase;
    }

    public Cartao gerarCartaoCredito(Conta conta, TipoCartao tipo) {
        return cartaoUseCase.gerarCartao(conta, tipo );
    }
}
