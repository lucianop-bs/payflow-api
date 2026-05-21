package com.payflow.payflow_api.utils;

public class CpfUtils {

    private CpfUtils() {
    }

    public static String limpar(String cpf) {
        return cpf.replace("-", "")
                .replace(".", "");
    }
    public static boolean validaCPF(String cpf) {
       return verificarPrimeiroDigito(cpf) && verificarSegundoDigito(cpf);

    }
    public static String formatar(String cpf) {
        var cpfLimpo = limpar(cpf);
        var bloco1 = cpfLimpo.substring(0, 3);
        var bloco2 = cpfLimpo.substring(3, 6);
        var bloco3 = cpfLimpo.substring(6, 9);
        var bloco4 = cpfLimpo.substring(9, 11);

        return String.format("%s.%s.%s-%s", bloco1, bloco2, bloco3, bloco4);
    }

    private static boolean verificarPrimeiroDigito (String cpf){
        var peso = 0;
        var digito = 0;
        var soma = 0;
        var validacaoPrimeiroBloco = cpf.substring(0,9);
        var validacaoPrimeiroNumero = cpf.substring(9,10);
        for (int i = 0; i < validacaoPrimeiroBloco.length(); i++) {
            peso = 10 - i;
            digito = cpf.charAt(i) - '0';
            soma += digito * peso;
        }
        return validar(soma, validacaoPrimeiroNumero);

    }
    private static boolean verificarSegundoDigito (String cpf){
        var peso = 0;
        var digito = 0;
        var soma = 0;
        var validacaoPrimeiroBloco = cpf.substring(0,10);
        var validacaoPrimeiroNumero = cpf.substring(10,11);
        for (int i = 0; i < validacaoPrimeiroBloco.length(); i++) {
            peso = 11 - i;
            digito = cpf.charAt(i) - '0';
            soma += digito * peso;
        }
        return validar(soma, validacaoPrimeiroNumero);

    }

    private static boolean validar(int soma, String validacaoNumero) {
        var resultadoParcial = soma % 11;
        var resultadoFinal = 11 - resultadoParcial;
        if(resultadoParcial < 2 && validacaoNumero.charAt(0) - '0'  == 0 ){
            return true;
        }
        else
            return resultadoParcial >= 2 && validacaoNumero.charAt(0) - '0' == resultadoFinal;
    }

}
