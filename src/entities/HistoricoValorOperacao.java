package entities;

import java.sql.Date;

public class HistoricoValorOperacao {
    private Long id;
    private OperacaoInvestir operacaoInvestir;
    private Date dataRegistro;
    private Double valor;

    public HistoricoValorOperacao(Long id, OperacaoInvestir operacaoInvestir, Date dataRegistro, Double valor) {
        this.id = id;
        this.operacaoInvestir = operacaoInvestir;
        this.dataRegistro = dataRegistro;
        this.valor = valor;
    }

    public HistoricoValorOperacao(OperacaoInvestir operacaoInvestir, Date dataRegistro, Double valor) {
        this.operacaoInvestir = operacaoInvestir;
        this.dataRegistro = dataRegistro;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public OperacaoInvestir getOperacaoInvestir() {
        return operacaoInvestir;
    }

    public void setOperacaoInvestir(OperacaoInvestir operacaoInvestir) {
        this.operacaoInvestir = operacaoInvestir;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}