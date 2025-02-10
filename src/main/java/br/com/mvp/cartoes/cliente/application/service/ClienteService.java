package br.com.mvp.cartoes.cliente.application.service;


import br.com.mvp.cartoes.cliente.application.usercase.ClienteUseCase;
import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import br.com.mvp.cartoes.cliente.infrastructure.repository.ClienteRepositoryImpl;
import br.com.mvp.cartoes.cliente.presentation.dto.ClienteRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ClienteService {


    private final ClienteRepositoryImpl repository;
    private ClienteUseCase clienteUseCase;

    @Inject
    public ClienteService(ClienteRepositoryImpl repository, ClienteUseCase clienteUseCase) {
        this.repository = repository;
        this.clienteUseCase = clienteUseCase;
    }

    public void cadastrarCliente(ClienteRequestDTO request) {

        if(!clienteUseCase.isCPFValido(request.getDocumento())){
            throw new RuntimeException("Documento Invalido");
        }

        Cliente cliente = mapperDtoToCliente(request);
        cliente.getEnderecos().forEach(endereco -> endereco.setCliente(cliente));
        repository.cadastrarCliente(cliente);


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
