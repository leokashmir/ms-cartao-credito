package br.com.mvp.cartoes.cliente.domain.repository;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;

public interface ClienteInterface  {

     Cliente cadastrarCliente(Cliente cliente);
     void excluirCliente(Cliente cliente);
     Cliente buscarClientePorDoucmento(String documento);
     void atualizarCliente(Cliente cliente);
     Cliente buscarPorDocumento(String documento);
     Cliente buscarPorId(Long id);
}
