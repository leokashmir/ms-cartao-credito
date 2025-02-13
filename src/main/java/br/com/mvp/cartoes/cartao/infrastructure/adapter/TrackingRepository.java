package br.com.mvp.cartoes.cartao.infrastructure.adapter;

import br.com.mvp.cartoes.cartao.domain.model.Tracking;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface TrackingRepository extends PanacheRepositoryBase<Tracking, Long> {
}
