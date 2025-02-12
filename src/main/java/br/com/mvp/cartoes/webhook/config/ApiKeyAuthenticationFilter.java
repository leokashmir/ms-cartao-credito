package br.com.mvp.cartoes.webhook.config;


import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class ApiKeyAuthenticationFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "app.api.key")
    String apiKey;



    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        var headerApiKey = requestContext.getHeaderString("X-API-KEY");
        var path = requestContext.getUriInfo().getPath();

        for (Pattern pattern : naoRequerAutenticacao()) {
            if (pattern.matcher(path).matches()) {
                return;
            }
        }

        if (headerApiKey == null || !headerApiKey.equals(apiKey)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("API Key inválida ou ausente.")
                            .build()
            );
        }
    }

    private List<Pattern> naoRequerAutenticacao(){
      return  Arrays.asList(
              Pattern.compile("^/cliente/.*")
        );
    }
}