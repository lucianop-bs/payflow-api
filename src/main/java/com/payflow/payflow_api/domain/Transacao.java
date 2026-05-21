package com.payflow.payflow_api.domain;

import com.payflow.payflow_api.domain.enums.StatusTransacao;
import com.payflow.payflow_api.domain.enums.TipoTransacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;
    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestinatario;
    private LocalDateTime dataTransacao;
    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTransacao;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;


    public Transacao(Conta contaOrigem,
                     Conta contaDestinatario,
                     LocalDateTime dataTransacao,
                     BigDecimal valor,
                     StatusTransacao statusTransacao,
                     TipoTransacao tipoTransacao) {
        this.contaOrigem = contaOrigem;
        this.contaDestinatario = contaDestinatario;
        this.dataTransacao = dataTransacao;
        this.valor = valor;
        this.statusTransacao = statusTransacao;
        this.tipoTransacao = tipoTransacao;
    }


    public Transacao() {
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestinatario() {
        return contaDestinatario;
    }

    public void setContaDestinatario(Conta contaDestinatario) {
        this.contaDestinatario = contaDestinatario;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public void setStatusTransacao(StatusTransacao statusTransacao) {
        this.statusTransacao = statusTransacao;
    }
}
