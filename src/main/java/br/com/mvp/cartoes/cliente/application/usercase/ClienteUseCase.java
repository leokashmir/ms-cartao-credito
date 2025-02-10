package br.com.mvp.cartoes.cliente.application.usercase;


import jakarta.enterprise.context.ApplicationScoped;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

@ApplicationScoped
public class ClienteUseCase {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");

    public boolean isCPFValido(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {  return false; }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        return isDigitoValido(cpf, 9) && isDigitoValido(cpf, 10);
    }

    private  boolean isDigitoValido(String cpf, int position) {
        int sum = IntStream.range(0, position)
                .map(i -> (cpf.charAt(i) - '0') * (position + 1 - i))
                .sum();

        int checkDigit = 11 - (sum % 11);
        if (checkDigit >= 10) checkDigit = 0;

        return checkDigit == (cpf.charAt(position) - '0');
    }


}
