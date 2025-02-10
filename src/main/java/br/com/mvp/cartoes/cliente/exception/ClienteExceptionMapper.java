package br.com.mvp.cartoes.cliente.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ClienteExceptionMapper implements ExceptionMapper<ClienteException> {
    @Override
    public Response toResponse(ClienteException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(exception.getMessage()))
                .build();
    }

}
