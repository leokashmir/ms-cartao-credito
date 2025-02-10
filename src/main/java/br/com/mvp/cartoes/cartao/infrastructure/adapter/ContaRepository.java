package br.com.mvp.cartoes.cartao.infrastructure.adapter;

import br.com.mvp.cartoes.cartao.domain.model.Conta;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface ContaRepository extends PanacheRepositoryBase<Conta, Long> {

    default Conta findByNumeroConta(String numeroConta){
        return find("numeroConta", numeroConta).firstResult();
    }
    default void apagarContaByNumero(String numero) {
        delete("numeroConta", numero);
    }
}
