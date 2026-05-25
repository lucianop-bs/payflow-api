package com.payflow.payflow_api.utils;

public class CpfUtils {

    private CpfUtils() {
    }
    private static final int[] PESOS_PRIMEIRO_DIGITO = {10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO  = {11, 10, 9, 8, 7, 6, 5, 4, 3,
            2};
    public static String limpar(String cpf) {
        return cpf.replace("-", "")
                .replace(".", "");
    }
    public static boolean validaCPF(String cpf) {
        return verificarDigito(cpf, PESOS_PRIMEIRO_DIGITO, 9)
                && verificarDigito(cpf, PESOS_SEGUNDO_DIGITO, 10);
    }
    public static String formatar(String cpf) {
        var cpfLimpo = limpar(cpf);
        var bloco1 = cpfLimpo.substring(0, 3);
        var bloco2 = cpfLimpo.substring(3, 6);
        var bloco3 = cpfLimpo.substring(6, 9);
        var bloco4 = cpfLimpo.substring(9, 11);

        return String.format("%s.%s.%s-%s", bloco1, bloco2, bloco3, bloco4);
    }

    private static boolean verificarDigito(String cpf, int[] pesos, int
            posicaoDigito) {
        var soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            var digito = cpf.charAt(i) - '0';
            soma += digito * pesos[i];
        }
        var digitoReal = cpf.substring(posicaoDigito, posicaoDigito + 1);
        return validar(soma, digitoReal);
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
