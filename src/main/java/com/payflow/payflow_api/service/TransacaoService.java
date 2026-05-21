package com.payflow.payflow_api.service;

import com.payflow.payflow_api.domain.Conta;
import com.payflow.payflow_api.domain.Transacao;
import com.payflow.payflow_api.domain.enums.StatusTransacao;
import com.payflow.payflow_api.domain.enums.TipoTransacao;
import com.payflow.payflow_api.dto.request.RealizarTransferenciaRequest;
import com.payflow.payflow_api.dto.response.TransacaoResponse;
import com.payflow.payflow_api.exception.RecursoNaoEncontrado;
import com.payflow.payflow_api.repository.ContaRepository;
import com.payflow.payflow_api.repository.TransacaoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.payflow.payflow_api.utils.ExtratoBuilder.*;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public TransacaoResponse realizarTransferencia(RealizarTransferenciaRequest request) {
        Conta contaOrigem = contaRepository.findByNumeroConta(request.contaOrigem())
                .orElseThrow(() -> new RecursoNaoEncontrado("Conta origem não encontrada"));

        Conta contaDestinatario = contaRepository.findByNumeroConta(request.contaDestino())
                .orElseThrow(() -> new RecursoNaoEncontrado("Conta destino não encontrada"));

        Transacao transacao = new Transacao();
        transacao.setStatusTransacao(StatusTransacao.PROCESSANDO);
        contaOrigem.debitar(request.valor());
        contaDestinatario.creditar(request.valor());
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestinatario(contaDestinatario);
        transacao.setValor(request.valor());
        transacao.setDataTransacao(LocalDateTime.now());
        transacao.setStatusTransacao(StatusTransacao.CONCLUIDO);
        transacao.setTipoTransacao(TipoTransacao.TRANSFERENCIA);

        transacaoRepository.save(transacao);
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestinatario);

        return new TransacaoResponse(transacao.getId(),
                transacao.getStatusTransacao().toString(),
                gerarDescricao(transacao.getTipoTransacao()));
    }


}
