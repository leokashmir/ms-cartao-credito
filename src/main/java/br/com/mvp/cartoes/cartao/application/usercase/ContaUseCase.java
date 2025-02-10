package br.com.mvp.cartoes.cartao.application.usercase;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class ContaUseCase {

    public String gerarConta(){
        Random random = new Random();
        var numConta =  random.nextInt(100000000);
        return String.format("%08d", numConta);

    }
}
