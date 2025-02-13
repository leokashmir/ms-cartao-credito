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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartaoServiceTest {

    @Mock
    private CartaoInterface cartaoInterface;

    @Mock
    private CartaoUseCase cartaoUseCase;

    @Mock
    private TrackingInterface trackingInterface;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private CartaoService cartaoService;

    private CartaoRequestDTO cartaoRequestDTO;
    private Conta conta;
    private Cartao cartao;
    private Tracking tracking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cartaoRequestDTO = CartaoRequestDTO.builder().build();
        cartaoRequestDTO.setIdConta(1L);
        cartaoRequestDTO.setTipo(TipoCartao.FISICO);

        conta = new Conta();
        conta.setIdConta(1L);

        cartao = new Cartao();
        cartao.setIdCartao(1L);
        cartao.setNumCartao("1234567890123456");
        cartao.setTpCartao(TipoCartao.FISICO);

        tracking = new Tracking();
        tracking.setTrackingId("123");
        tracking.setCartao(cartao);
    }

    @Test
    void testCadastraCartao_throwsContaException_whenContaNotFound() {
        // Mocking contaService to return null
        when(contaService.buscarContaPorId(cartaoRequestDTO.getIdConta())).thenReturn(null);

        ContaException exception = assertThrows(ContaException.class, () -> {
            cartaoService.cadastraCartao(cartaoRequestDTO);
        });

        assertEquals("Conta não encontrada", exception.getMessage());
    }




    @Test
    void testExcluirCartao_success() {
        // Mocking the necessary methods
        doNothing().when(trackingInterface).excluirTrackingPorIdCartao(cartao.getIdCartao());
        doNothing().when(cartaoInterface).excluirCartao(cartao);

        cartaoService.excluirCartao(cartao);

        // Verifying the methods are called
        verify(trackingInterface).excluirTrackingPorIdCartao(cartao.getIdCartao());
        verify(cartaoInterface).excluirCartao(cartao);
    }



    @Test
    void testListarTracking() {
        // Mocking trackingInterface
        when(trackingInterface.buscarTrackingPorIdCartao(cartao.getIdCartao())).thenReturn(List.of(tracking));

        List<Tracking> result = cartaoService.listarTracking(cartao.getIdCartao());

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testValidarCartao_throwsCartaoException_whenTrackingNotFound() {
        // Mocking trackingInterface to return null
        when(trackingInterface.buscarTrackingPorId("123")).thenReturn(null);

        CartaoException exception = assertThrows(CartaoException.class, () -> {
            cartaoService.validarCartao("123", "DELIVERED");
        });

        assertEquals("Traking não existente.", exception.getMessage());
    }

    @Test
    void testValidarCartao_success() {
        // Mocking trackingInterface to return a valid tracking
        when(trackingInterface.buscarTrackingPorId("123")).thenReturn(tracking);

        cartaoService.validarCartao("123", "DELIVERED");

        // Verifying that the card was updated
        verify(cartaoInterface).atualizarCartao(cartao);
    }
}
