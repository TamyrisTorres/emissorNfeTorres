package com.TTecnologia.EmissorNfeTorres.model.utlis;

import com.TTecnologia.EmissorNfeTorres.exception.CpfException.ExceptionCpfInvalid;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ValidationCpfCnpj {

    public static boolean validationCNPJ(String cnpj) {

        try {
            String baseUrl = "https://open.cnpja.com/office/" + cnpj;


            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;
        }catch (Exception exception){
            throw new ExceptionCpfInvalid("CNPJ inválido");
        }

    }

    public static boolean isValidCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se tem 11 dígitos ou se todos os dígitos são iguais
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) firstDigit = 0;
            if (firstDigit != Character.getNumericValue(cpf.charAt(9))) return false;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) secondDigit = 0;

            return secondDigit == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            throw new ExceptionCpfInvalid("CPF inválido");
        }
    }

}
