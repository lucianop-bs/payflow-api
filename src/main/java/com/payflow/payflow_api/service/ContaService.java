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
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuario não encontrado"));

        Conta conta = new Conta();
        conta.setNumeroConta(UUID.randomUUID());
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

}
