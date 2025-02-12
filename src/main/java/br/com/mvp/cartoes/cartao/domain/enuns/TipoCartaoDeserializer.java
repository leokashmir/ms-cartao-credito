package br.com.mvp.cartoes.cartao.domain.enuns;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class  TipoCartaoDeserializer extends JsonDeserializer<TipoCartao> {
    @Override
    public TipoCartao deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if ("Fisico".equalsIgnoreCase(value)) {
            return TipoCartao.FISICO;
        }
        return TipoCartao.valueOf(value.toUpperCase());
    }
}
