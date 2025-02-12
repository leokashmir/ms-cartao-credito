package br.com.mvp.cartoes.cartao.domain.enuns;

public enum TipoCartao {

    FISICO("Fisico"),
    VIRTUAL("Virtual");

    private final String tipo;

    TipoCartao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
