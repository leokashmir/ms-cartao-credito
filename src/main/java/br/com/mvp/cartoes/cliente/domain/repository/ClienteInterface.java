package br.com.mvp.cartoes.cliente.domain.repository;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;

public interface ClienteInterface {

    public void cadastrarCliente(Cliente cliente);
    public void excluirCliente(Cliente cliente);
    public Cliente buscarClientePorDoucmento(String documento);

    void atualizarCliente(Cliente cliente);
}
