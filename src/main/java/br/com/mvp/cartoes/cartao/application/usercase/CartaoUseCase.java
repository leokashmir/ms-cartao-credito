package br.com.mvp.cartoes.cartao.application.usercase;


import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import br.com.mvp.cartoes.cartao.domain.model.Tracking;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@ApplicationScoped
public class CartaoUseCase {

    private static final RandomGenerator RANDOM = RandomGenerator.getDefault();

    public Cartao gerarCartao(Conta conta, TipoCartao tipoCartao) {

        var nomeCliente = conta.getCliente().getNome();
        var nomeImpresso = nomeCliente.length() > 20 ? nomeCliente.substring(0, 20) : nomeCliente;
        var ativo = !TipoCartao.FISICO.equals(tipoCartao);

        return Cartao.builder()
                .tpCartao(tipoCartao)
                .numCartao(gerarNumeroCartao())
                .cvv(gerarCvv())
                .dtValidade(gerarDataValidade())
                .dataExpiracaoCvv(gerarDataValidade().atTime(23, 59, 59))
                .ativo(ativo)
                .titular(nomeImpresso)
                .build();
    }

    public Tracking gerarTracking() {
        Random random = new Random();
        var numTracking  =  String.format("%08d", random.nextInt(100000000));

        return Tracking.builder()
                .trackingId(numTracking)
                .deliveryStatus("SHIPPED")
                .deliveryDate(LocalDateTime.now())
                .build();

    }

    private String gerarNumeroCartao(){
        int numeroCartao = 14;
        int prefixo = 4; //Visa
        String numeroParcial = prefixo + RANDOM.ints(numeroCartao, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());


        return numeroParcial + calcularDigito(numeroParcial);
    }

    private  int calcularDigito(String numeroParcial) {
        AtomicInteger index = new AtomicInteger(0);
        int soma =  new StringBuilder(numeroParcial)
                .chars()
                .map(Character::getNumericValue)
                .map(num -> {
                    if (index.getAndIncrement() % 2 == 0) {
                        num *= 2;
                        if (num > 9) num -= 9;
                    }
                    return num;
                })
                .sum();
        return (soma % 10 == 0) ? 0 : 10 - (soma % 10);
    }
    private String gerarCvv(){
            return RANDOM.ints(3, 0, 10)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining());
    }

    private LocalDate gerarDataValidade(){
        LocalDate hoje = LocalDate.now();
        LocalDate dataMinima = hoje.plusYears(3);
        LocalDate dataMaxima = hoje.plusYears(5);

        long diasAleatorios = ThreadLocalRandom.current().nextLong(
                ChronoUnit.DAYS.between(dataMinima, dataMaxima)
        );
        return dataMinima.plusDays(diasAleatorios);
    }


}
