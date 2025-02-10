package br.com.mvp.cartoes.cliente.infrastructure.adapter;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;


public interface ClienteRepository extends PanacheRepositoryBase<Cliente, Long> {

        default Cliente findByDocumento(String documento){
            return find("documento", documento).firstResult();
        }
}
