package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HistoricoValores {
    private Integer id;
    private Investidor investidor;
    private OperacaoInvestir operacaoInvestir;
    private LocalDate dataRegistro;
    private BigDecimal valor = new BigDecimal("0");

    public HistoricoValores(Integer id, Investidor investidor, OperacaoInvestir operacaoInvestir, LocalDate dataRegistro, BigDecimal valor) {
        this.id = id;
        this.investidor = investidor;
        this.operacaoInvestir = operacaoInvestir;
        this.dataRegistro = dataRegistro;
        this.valor = valor;
    }

    public HistoricoValores(Investidor investidor, OperacaoInvestir operacaoInvestir, LocalDate dataRegistro, BigDecimal valor) {
        this.investidor = investidor;
        this.operacaoInvestir = operacaoInvestir;
        this.dataRegistro = dataRegistro;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public OperacaoInvestir getOperacaoInvestir() {
        return operacaoInvestir;
    }

    public void setOperacaoInvestir(OperacaoInvestir operacaoInvestir) {
        this.operacaoInvestir = operacaoInvestir;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}