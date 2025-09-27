package entities;

import java.sql.Date;

public class HistoricoValorSimulacao {
    private Long id;
    private SimuladorOperacaoInvestir simuladorOperacaoInvestir;
    private Date dataRegistro;
    private Double valor;

    public HistoricoValorSimulacao(Long id, SimuladorOperacaoInvestir simuladorOperacaoInvestir, Date dataRegistro, Double valor) {
        this.id = id;
        this.simuladorOperacaoInvestir = simuladorOperacaoInvestir;
        this.dataRegistro = dataRegistro;
        this.valor = valor;
    }

    public HistoricoValorSimulacao(SimuladorOperacaoInvestir simuladorOperacaoInvestir, Date dataRegistro, Double valor) {
        this.simuladorOperacaoInvestir = simuladorOperacaoInvestir;
        this.dataRegistro = dataRegistro;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public SimuladorOperacaoInvestir getSimuladorOperacaoInvestir() {
        return simuladorOperacaoInvestir;
    }

    public void setSimuladorOperacaoInvestir(SimuladorOperacaoInvestir simuladorOperacaoInvestir) {
        this.simuladorOperacaoInvestir = simuladorOperacaoInvestir;
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