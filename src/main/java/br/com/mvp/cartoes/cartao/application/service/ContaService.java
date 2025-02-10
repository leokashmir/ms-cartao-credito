package br.com.mvp.cartoes.cartao.application.service;

import br.com.mvp.cartoes.cartao.application.usercase.ContaUseCase;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import br.com.mvp.cartoes.cartao.infrastructure.repository.ContaRepositoryImpl;
import br.com.mvp.cartoes.cartao.presentation.dto.ContaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ContaService {

    private ContaUseCase contaUseCase;
    private ContaRepositoryImpl contaRepository;
    private ModelMapper modelMapper;

    @Inject
    public ContaService(ContaRepositoryImpl contaRepository, ContaUseCase contaUseCase) {
        this.contaRepository = contaRepository;
        this.contaUseCase = contaUseCase;
    }


    public Conta criarConta(ContaDTO contaDto) {
        modelMapper = new ModelMapper();
        var conta = modelMapper.map(contaDto, Conta.class);
        conta.setNumeroConta(contaUseCase.gerarConta());
        return contaRepository.criarConta(conta);
    }
}
