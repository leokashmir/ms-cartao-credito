package br.com.mvp.cartoes.cartao.application.service;

import br.com.mvp.cartoes.cartao.application.usercase.ContaUseCase;
import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import br.com.mvp.cartoes.cartao.infrastructure.repository.ContaRepositoryImpl;
import br.com.mvp.cartoes.cartao.presentation.dto.ContaDTO;
import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ContaService {

    private ContaUseCase contaUseCase;
    private ContaRepositoryImpl contaRepository;
    private CartaoService cartaoService;
    private ModelMapper modelMapper;

    @Inject
    public ContaService(ContaRepositoryImpl contaRepository, CartaoService cartaoService, ContaUseCase contaUseCase) {
        this.contaRepository = contaRepository;
        this.cartaoService = cartaoService;
        this.contaUseCase = contaUseCase;
    }



    public Conta criarConta(ContaDTO contaDto) {
        modelMapper = new ModelMapper();
        var conta = modelMapper.map(contaDto, Conta.class);
        conta.setNumeroConta(contaUseCase.gerarConta());
        var cartao = cartaoService.gerarCartaoCredito(conta, TipoCartao.FISICO);
        cartao.setConta(conta);

        List<Cartao> listCartao = new ArrayList<>();
        listCartao.add(cartao);
        conta.setCartoes(listCartao);
        return contaRepository.criarConta(conta);
    }
}
