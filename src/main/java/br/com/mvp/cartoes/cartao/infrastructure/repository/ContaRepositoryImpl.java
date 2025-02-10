package br.com.mvp.cartoes.cartao.infrastructure.repository;

import br.com.mvp.cartoes.cartao.domain.model.Conta;
import br.com.mvp.cartoes.cartao.domain.repository.ContaInterface;
import br.com.mvp.cartoes.cartao.exception.ContaException;
import br.com.mvp.cartoes.cartao.infrastructure.adapter.ContaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class ContaRepositoryImpl implements ContaRepository , ContaInterface {


    @Override
    public Boolean contaExiste(String numero) {
        return Objects.nonNull(this.findByNumeroConta(numero));
    }

    @Transactional
    @Override
    public Conta criarConta(Conta conta) {
        try{
            this.persist(conta);
            return conta;
        } catch (Exception e) {
            throw new ContaException("Não foi Possivel criar uma conta!");
        }
    }

    @Override
    public void apagarConta(String numero) {
        this.apagarContaByNumero(numero);
    }

    @Override
    public Conta getConta(String numero) {
        return this.findByNumeroConta(numero);
    }


}
