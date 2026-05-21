package com.payflow.payflow_api.service;

import com.payflow.payflow_api.domain.Conta;
import com.payflow.payflow_api.domain.Usuario;
import com.payflow.payflow_api.dto.request.CriarContaRequest;
import com.payflow.payflow_api.dto.request.DepositarRequest;
import com.payflow.payflow_api.dto.response.ContaResponse;
import com.payflow.payflow_api.dto.response.DepositoResponse;
import com.payflow.payflow_api.exception.RecursoNaoEncontrado;
import com.payflow.payflow_api.repository.ContaRepository;
import com.payflow.payflow_api.repository.UsuarioRepository;
import com.payflow.payflow_api.utils.ExtratoBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;

    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ContaResponse criarConta(CriarContaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));

        Conta conta = new Conta();
        UUID numeroDaConta;
        var resultado = true;
        do{
            numeroDaConta = UUID.randomUUID();
            var contaExistente = contaRepository.existsByNumeroConta(numeroDaConta);
            if(!contaExistente){
                resultado = false;
            }
        }
        while(resultado);

        conta.setNumeroConta(numeroDaConta);
        conta.setDataCriacao(LocalDateTime.now());
        conta.setSaldo(BigDecimal.ZERO);
        conta.setUsuario(usuario);
        contaRepository.save(conta);

        return new ContaResponse(conta.getNumeroConta(), conta.getUsuario().getNome());
    }

    public DepositoResponse depositar(DepositarRequest request) {
        Conta contaDeposito = contaRepository.findByNumeroConta(request.numeroConta())
                .orElseThrow(() -> new RecursoNaoEncontrado("Conta não encontrada"));

        contaDeposito.creditar(request.valor());

        contaRepository.save(contaDeposito);

        return new DepositoResponse(contaDeposito.getNumeroConta(), contaDeposito.getSaldo());
    }
    @Transactional
    public String extratoConta(UUID numeroConta) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new RecursoNaoEncontrado("Conta não encontrada"));

        var cabecalho = ExtratoBuilder.gerarCabecalho(conta.getUsuario().getNome(),
                conta.getNumeroConta().toString());
        var linhasEnviadas = conta.getTransacoesEnviadas().stream().map(t -> ExtratoBuilder.gerarLinhaTransacao(t.getTipoTransacao(),ExtratoBuilder.gerarDescricao(t.getTipoTransacao()),t.getValor())).toList();
        var linhasRecebidas = conta.getTransacoesRecebidas().stream().map(t -> ExtratoBuilder.gerarLinhaTransacao(t.getTipoTransacao(),ExtratoBuilder.gerarDescricao(t.getTipoTransacao()),t.getValor())).toList();

        return ExtratoBuilder.gerarExtrato(cabecalho,linhasRecebidas,linhasEnviadas);
    }

}
