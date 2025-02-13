package br.com.mvp.cartoes.cartao.infrastructure.repository;

import br.com.mvp.cartoes.cartao.domain.model.Tracking;
import br.com.mvp.cartoes.cartao.domain.repository.TrackingInterface;
import br.com.mvp.cartoes.cartao.infrastructure.adapter.TrackingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped @Transactional
public class TrackingRepositoryImpl implements TrackingRepository, TrackingInterface {
    @Override
    public void salvarEnvioCartao(Tracking tracking) {
        this.persist(tracking);
    }

    @Override
    public List<Tracking> buscarTrackingPorIdCartao(Long idCartao) {
        return  list("cartao.idCartao", idCartao);
    }

    @Override
    public void excluirTrackingPorIdCartao(Long idCartao) {
        delete("cartao.idCartao", idCartao);
    }

    @Override
    public Tracking buscarTrackingPorId(String idTracking) {
        return find ("trackingId" , idTracking).firstResult();
    }
}
