package br.com.mvp.cartoes.cartao.application.service;

import br.com.mvp.cartoes.cartao.application.usercase.ContaUseCase;
import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import br.com.mvp.cartoes.cartao.domain.repository.ContaInterface;
import br.com.mvp.cartoes.cartao.presentation.dto.ContaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class ContaService {

    private ContaUseCase contaUseCase;
    private ContaInterface contaInterface;
    private CartaoService cartaoService;
    private ModelMapper modelMapper;

    @Inject
    public ContaService(ContaInterface contaInterface, CartaoService cartaoService, ContaUseCase contaUseCase) {
        this.contaInterface = contaInterface;
        this.cartaoService = cartaoService;
        this.contaUseCase = contaUseCase;
    }



    public Conta criarConta(ContaDTO contaDto) {
        log.info("Criando conta: {}", contaDto);

        modelMapper = new ModelMapper();
        var conta = modelMapper.map(contaDto, Conta.class);

        conta.setNumeroConta(contaUseCase.gerarConta());
        var cartao = cartaoService.gerarCartaoCredito(conta, TipoCartao.FISICO);

        cartao.setConta(conta);
        List<Cartao> listCartao = new ArrayList<>();
        listCartao.add(cartao);

        conta.setCartoes(listCartao);

        var contaCriada = contaInterface.criarConta(conta);

        contaCriada.getCartoes().forEach(cartaoCriado -> cartaoService.gravarTrack(cartaoCriado, conta));
        return contaCriada;
    }

    public void excluirContaCliente(Long idCliente) {
       log.info("Excluindo conta de cliente: {}", idCliente);

        var conta =  (contaInterface.listaContasPorIdCliente(idCliente).isEmpty())
               ? null : contaInterface.listaContasPorIdCliente(idCliente).get(0);

       if(conta != null) {
           conta.getCartoes().forEach(cartao -> cartaoService.excluirCartao(cartao));
           contaInterface.excluirContaPorIdCliente(conta.getCliente().getIdCliente());
       }

    }

    public void desativarContaCliente(Long idCliente) {
        log.info("Desativar conta de cliente: {}", idCliente);
        var conta =  (contaInterface.listaContasPorIdCliente(idCliente).isEmpty())
                ? null : contaInterface.listaContasPorIdCliente(idCliente).get(0);

        if(conta != null) {
            conta.getCartoes().forEach(cartao -> cartao.setAtivo(false));
            conta.setAtivo(false);
        }
    }

    public Conta buscarContaPorId(Long idConta) {
        return contaInterface.buscarContaPorId(idConta);
    }

    public List<Conta> listarContas(Long idCliente) {
        return contaInterface.listaContasPorIdCliente(idCliente);
    }

    public Conta buscarContaPorNumero(String numeroConta) {
        return contaInterface.buscarContaPorNumero(numeroConta);
    }
}
