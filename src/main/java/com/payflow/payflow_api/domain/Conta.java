package com.payflow.payflow_api.domain;

import com.payflow.payflow_api.exception.RegraDeNegocioException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID numeroConta;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private BigDecimal saldo;
    private LocalDateTime dataCriacao;
    @OneToMany(mappedBy = "contaOrigem")
    private List<Transacao> transacoesEnviadas;
    @OneToMany(mappedBy = "contaDestinatario")
    private List<Transacao> transacoesRecebidas;

    public Conta(UUID numeroConta, Usuario usuario, BigDecimal saldo, LocalDateTime dataCriacao) {
        this.numeroConta = numeroConta;
        this.usuario = usuario;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
    }

    public Conta() {
    }

    public List<Transacao> getTransacoesEnviadas() {
        return transacoesEnviadas;
    }

    public void setTransacoesEnviadas(List<Transacao> transacoesEnviadas) {
        this.transacoesEnviadas = transacoesEnviadas;
    }

    public List<Transacao> getTransacoesRecebidas() {
        return transacoesRecebidas;
    }

    public void setTransacoesRecebidas(List<Transacao> transacoesRecebidas) {
        this.transacoesRecebidas = transacoesRecebidas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(UUID numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void debitar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor deve ser maior que zero");
        }
        if (saldo.compareTo(valor) < 0) {
            throw new RegraDeNegocioException("Saldo insuficiente");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor deve ser maior que zero");
        }
        this.saldo = this.saldo.add(valor);
    }
}
