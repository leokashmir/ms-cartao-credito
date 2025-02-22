package br.com.mvp.cartoes.cartao.application.service;

import br.com.mvp.cartoes.cartao.application.usercase.CartaoUseCase;
import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import br.com.mvp.cartoes.cartao.domain.model.Tracking;
import br.com.mvp.cartoes.cartao.domain.repository.CartaoInterface;
import br.com.mvp.cartoes.cartao.domain.repository.TrackingInterface;
import br.com.mvp.cartoes.cartao.exception.CartaoException;
import br.com.mvp.cartoes.cartao.exception.ContaException;
import br.com.mvp.cartoes.cartao.presentation.dto.CartaoRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class CartaoService {

    private final CartaoInterface cartaoInterface;
    private final CartaoUseCase cartaoUseCase;
    private final TrackingInterface trackingInterface;
    private final ContaService contaService;

    @Inject
    public CartaoService(CartaoInterface cartaoInterface, CartaoUseCase cartaoUseCase,
                         TrackingInterface trackingInterface, ContaService contaService) {
        this.cartaoInterface = cartaoInterface;
        this.cartaoUseCase = cartaoUseCase;
        this.trackingInterface = trackingInterface;
        this.contaService = contaService;
    }

    public void cadastraCartao(CartaoRequestDTO requestDTO) {
        var conta = contaService.buscarContaPorId(requestDTO.getIdConta());
        if(conta == null) {
            throw  new ContaException("Conta não encontrada");
        }

        List<Cartao> cartoes = cartaoInterface.listarCartoes(conta.getIdConta());

        Optional.ofNullable(cartoes)
                .filter(list -> !list.isEmpty())
                .orElseGet(() -> {
                    requestDTO.setTipo(TipoCartao.FISICO);
                    return Collections.emptyList();
                })
                .stream()
                .filter(cartao -> TipoCartao.FISICO.equals(cartao.getTpCartao()) && !cartao.getAtivo())
                .findFirst()
                .ifPresent(cartao -> {
                    throw new ContaException("Geração do novo cartão não autorizada");
                });

        var cartao = this.gerarCartaoCredito(conta, requestDTO.getTipo());
        cartao.setConta(conta);
        cartaoInterface.cadastrarCartao(cartao);
        if(TipoCartao.FISICO.equals(cartao.getTpCartao())){
            gravarTrack(cartao, conta);
        }

    }

    public void gravarTrack(Cartao cartao, Conta conta) {

        var tracking = cartaoUseCase.gerarTracking();
        tracking.setCartao(cartao);
        tracking.setDeliveryAddress(conta.getCliente().getEnderecos().get(0).toString());
        trackingInterface.salvarEnvioCartao(tracking);
    }

    public Cartao gerarCartaoCredito(Conta conta, TipoCartao tipo) {
        return cartaoUseCase.gerarCartao(conta, tipo );
    }

    public List<Cartao> listarCartoes(Long idConta) {
       return  cartaoInterface.listarCartoes(idConta);
    }

    public void excluirCartao(Cartao cartao){
        trackingInterface.excluirTrackingPorIdCartao(cartao.getIdCartao());
        cartaoInterface.excluirCartao(cartao);
    }

    public void atualizarCvvCartao(String numConta, String numCartao, String cvv, LocalDateTime dataExpiracao){

        Conta conta = contaService.buscarContaPorNumero(numConta);
        numCartao = numCartao.replace("-", "");
        String finalNumCartao = numCartao;
        conta.getCartoes().stream()
                .filter(cartao -> cartao.getNumCartao().equals(finalNumCartao))
                .findFirst()
                .ifPresent(cartao ->{
                    cartao.setCvv(cvv);
                    cartaoInterface.atualizarCvvCartao(cartao);
                });
    }


    public List<Tracking> listarTracking(Long idCartao){
        return trackingInterface.buscarTrackingPorIdCartao(idCartao);
    }

    public void validarCartao(String trackingId, String deliveryStatus) {
          if(!"DELIVERED".equals(deliveryStatus)){
             throw new CartaoException("Cartão não validado Status => ".concat(deliveryStatus) );
          }

        var track = trackingInterface.buscarTrackingPorId(trackingId);
        if(Objects.nonNull(track) && Objects.nonNull(track.getCartao())){
            var cartao = track.getCartao();
            cartao.setAtivo(true);
            cartaoInterface.atualizarCartao(cartao);
        }else{
            log.info("Traking {} não existente.", trackingId );
            throw new CartaoException("Traking não existente.");
        }

    }
}
