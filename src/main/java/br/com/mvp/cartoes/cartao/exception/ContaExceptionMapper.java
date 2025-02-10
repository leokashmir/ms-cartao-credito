package br.com.mvp.cartoes.cartao.exception;


import br.com.mvp.cartoes.cliente.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ContaExceptionMapper implements ExceptionMapper<ContaException> {

    @Override
    public Response toResponse(ContaException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
    }
}
