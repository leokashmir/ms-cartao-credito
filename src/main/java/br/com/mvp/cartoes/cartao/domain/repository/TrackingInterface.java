package br.com.mvp.cartoes.cartao.domain.repository;

import br.com.mvp.cartoes.cartao.domain.model.Tracking;

import java.util.List;

public interface TrackingInterface {

    void salvarEnvioCartao(Tracking tracking);
    List<Tracking> buscarTrackingPorIdCartao(Long idCartao);
    void excluirTrackingPorIdCartao(Long idCartao);
    Tracking buscarTrackingPorId(String idTracking);
}
