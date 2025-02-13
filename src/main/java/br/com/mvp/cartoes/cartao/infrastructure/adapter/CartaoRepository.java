package br.com.mvp.cartoes.cartao.infrastructure.adapter;

import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface CartaoRepository extends PanacheRepositoryBase<Cartao, Long> {
}
