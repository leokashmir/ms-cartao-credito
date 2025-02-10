package br.com.mvp.cartoes.cliente.application.service;


import br.com.mvp.cartoes.cartao.application.service.ContaService;
import br.com.mvp.cartoes.cartao.presentation.dto.ContaDTO;
import br.com.mvp.cartoes.cliente.application.usercase.ClienteUseCase;
import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import br.com.mvp.cartoes.cliente.exception.ClienteException;
import br.com.mvp.cartoes.cliente.infrastructure.repository.ClienteRepositoryImpl;
import br.com.mvp.cartoes.cliente.presentation.dto.ClienteRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ClienteService {


    private final ClienteRepositoryImpl repository;
    private ClienteUseCase clienteUseCase;
    private ContaService contaService;

    @Inject
    public ClienteService(ClienteRepositoryImpl repository, ClienteUseCase clienteUseCase, ContaService contaService) {
        this.repository = repository;
        this.clienteUseCase = clienteUseCase;
        this.contaService = contaService;
    }

    public void cadastrarCliente(ClienteRequestDTO request) {

        if(!clienteUseCase.isCPFValido(request.getDocumento())){
            throw new ClienteException("Documento Invalido");
        }

        Cliente cliente = mapperDtoToCliente(request);
        cliente.getEnderecos().forEach(endereco -> endereco.setCliente(cliente));
        var clienteSalvo = repository.cadastrarCliente(cliente);

        contaService.criarConta(ContaDTO.builder()
                        .ativo(true)
                        .cliente(clienteSalvo)
                .build());



    }

    public Cliente buscarPorDocumento(String documento) {
        return repository.findByDocumento(documento);
    }

    public void atualizarCliente(ClienteRequestDTO request) {
        Cliente cliente = mapperDtoToCliente(request);
        cliente.getEnderecos().forEach(endereco -> endereco.setCliente(cliente));

        repository.atualizarCliente(cliente);
    }

    public void excluirCliente(Long idCliente) {
        repository.excluirCliente(Cliente.builder().idCliente(idCliente).build());
    }

    private Cliente mapperDtoToCliente(ClienteRequestDTO request) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, Cliente.class);

    }
}
