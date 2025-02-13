package br.com.mvp.cartoes.cliente.application.service;

import br.com.mvp.cartoes.cartao.application.service.ContaService;
import br.com.mvp.cartoes.cartao.presentation.dto.ContaDTO;
import br.com.mvp.cartoes.cliente.application.usercase.ClienteUseCase;
import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import br.com.mvp.cartoes.cliente.domain.repository.ClienteInterface;
import br.com.mvp.cartoes.cliente.exception.ClienteException;
import br.com.mvp.cartoes.cliente.presentation.dto.ClientePatchRequestDTO;
import br.com.mvp.cartoes.cliente.presentation.dto.ClientePostRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteInterface clienteRepository;

    @Mock
    private ClienteUseCase clienteUseCase;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ClienteService clienteService;

    private ClientePostRequestDTO clientePostRequestDTO;
    private ClientePatchRequestDTO clientePatchRequestDTO;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clientePostRequestDTO =  ClientePostRequestDTO.builder().build();
        clientePostRequestDTO.setDocumento("12345678901"); // CPF válido
        clientePatchRequestDTO =  ClientePatchRequestDTO.builder().build();
        clientePatchRequestDTO.setIdCliente(1L);

        cliente = new Cliente();
        cliente.setIdCliente(1L);
    }

    @Test
    void testCadastrarCliente_throwsClienteException_whenCpfInvalid() {
        // Mocking CPF validation to return false
        when(clienteUseCase.isCPFValido(clientePostRequestDTO.getDocumento())).thenReturn(false);

        ClienteException exception = assertThrows(ClienteException.class, () -> {
            clienteService.cadastrarCliente(clientePostRequestDTO);
        });

        assertEquals("Documento Invalido", exception.getMessage());
    }

    @Test
    void testCadastrarCliente_success() {

        when(clienteUseCase.isCPFValido(clientePostRequestDTO.getDocumento())).thenReturn(true);
        when(clienteRepository.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);
        clientePostRequestDTO.setEnderecos(new ArrayList<>());
        clienteService.cadastrarCliente(clientePostRequestDTO);


        verify(clienteRepository).cadastrarCliente(any(Cliente.class));
        verify(contaService).criarConta(any(ContaDTO.class));
    }

    @Test
    void testBuscarPorDocumento() {
        when(clienteRepository.buscarPorDocumento("12345678901")).thenReturn(cliente);

        Cliente result = clienteService.buscarPorDocumento("12345678901");

        assertNotNull(result);
        assertEquals(cliente.getIdCliente(), result.getIdCliente());
    }

    @Test
    void testAtualizarCliente_throwsClienteException_whenClienteNotFound() {
        when(clienteRepository.buscarPorId(clientePatchRequestDTO.getIdCliente())).thenReturn(null);

        ClienteException exception = assertThrows(ClienteException.class, () -> {
            clienteService.atualizarCliente(clientePatchRequestDTO);
        });

        assertEquals("Cliente não cadastrado na base.", exception.getMessage());
    }

    @Test
    void testAtualizarCliente_success() {
        when(clienteRepository.buscarPorId(clientePatchRequestDTO.getIdCliente())).thenReturn(cliente);
        doNothing().when(clienteRepository).atualizarCliente(any(Cliente.class));

        clienteService.atualizarCliente(clientePatchRequestDTO);

        verify(clienteRepository).atualizarCliente(any(Cliente.class));
    }

    @Test
    void testExcluirCliente() {
        doNothing().when(contaService).excluirContaCliente(cliente.getIdCliente());
        doNothing().when(clienteRepository).excluirCliente(any(Cliente.class));

        clienteService.excluirCliente(cliente.getIdCliente());

        verify(contaService).excluirContaCliente(cliente.getIdCliente());
        verify(clienteRepository).excluirCliente(any(Cliente.class));
    }

    @Test
    void testAtualizarCliente_throwsClienteException_whenUpdateFails() {
        when(clienteRepository.buscarPorId(clientePatchRequestDTO.getIdCliente())).thenReturn(cliente);
        doThrow(new RuntimeException("Update failed")).when(clienteRepository).atualizarCliente(any(Cliente.class));

        ClienteException exception = assertThrows(ClienteException.class, () -> {
            clienteService.atualizarCliente(clientePatchRequestDTO);
        });

        assertEquals("Não foi possivel atualizar cliente.", exception.getMessage());
    }
}
