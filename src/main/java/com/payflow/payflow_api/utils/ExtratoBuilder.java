package com.payflow.payflow_api.utils;

import com.payflow.payflow_api.domain.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public class ExtratoBuilder {
    private  ExtratoBuilder() {}
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static String gerarCabecalho(String titular, String numeroDeConta){
       var sb = new StringBuilder();
       sb.append("=========== EXTRATO ===========\n")
       .append(String.format("Titular: %s\n",titular))
       .append(String.format("Conta: %s",numeroDeConta));
       return sb.toString();
    }

    public static String gerarLinhaTransacao(TipoTransacao tipoTransacao, String descricao, BigDecimal valor, LocalDateTime data) {
        return String.format("[%s] [%s] %s - R$ %.2f", data.format(FORMATTER), tipoTransacao, descricao, valor);
    }

    public static String gerarExtrato(String cabecalho, List<String> linhasRecebidas, List<String> linhasEnviadas){
        var joiner = new StringJoiner("\n");
        joiner.add(cabecalho);
        linhasRecebidas.forEach(t -> joiner.add(String.format("%s (+)",t)));
        linhasEnviadas.forEach(t -> joiner.add(String.format("%s (-)",t)));
        return joiner.toString();
    }

    public static String gerarExtrato(String cabecalho, List<String> todasLinhas) {
        var joiner = new StringJoiner("\n");
        joiner.add(cabecalho);
        todasLinhas.forEach(joiner::add);
        return joiner.toString();
    }
    public static String gerarDescricao(TipoTransacao tipoTransacao) {

        return switch (tipoTransacao){
            case TRANSFERENCIA -> "Transferência entre contas";
            case DEPOSITO ->  "Depósito em conta";
            case SAQUE ->   "Saque da conta";
        };
    }
}
