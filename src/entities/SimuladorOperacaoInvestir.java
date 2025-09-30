package entities;


public class SimuladorOperacaoInvestir{
    private Long id;
    private Investidor investidor;
    private String nomeSimulacao;
    private Double valorInvestidoSimulado;
    private Boolean alertaSimulacao = false;
    private Double saldoFicticio;

    public SimuladorOperacaoInvestir(Long id, Investidor investidor, String nomeSimulacao, Double valorInvestidoSimulado, Boolean alertaSimulacao, Double saldoFicticio) {
        this.id = id;
        this.investidor = investidor;
        this.nomeSimulacao = nomeSimulacao;
        this.valorInvestidoSimulado = valorInvestidoSimulado;
        this.alertaSimulacao = alertaSimulacao;
        this.saldoFicticio = saldoFicticio;
    }

    public SimuladorOperacaoInvestir(Investidor investidor, String nomeSimulacao, Double valorInvestidoSimulado, Boolean alertaSimulacao, Double saldoFicticio) {
        this.investidor = investidor;
        this.nomeSimulacao = nomeSimulacao;
        this.valorInvestidoSimulado = valorInvestidoSimulado;
        this.alertaSimulacao = alertaSimulacao;
        this.saldoFicticio = saldoFicticio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public String getNomeSimulacao() {
        return nomeSimulacao;
    }

    public void setNomeSimulacao(String nomeSimulacao) {
        this.nomeSimulacao = nomeSimulacao;
    }

    public Double getValorInvestidoSimulado() {
        return valorInvestidoSimulado;
    }

    public void setValorInvestidoSimulado(Double valorInvestidoSimulado) {
        this.valorInvestidoSimulado = valorInvestidoSimulado;
    }

    public Boolean getAlertaSimulacao() {
        return alertaSimulacao;
    }

    public void setAlertaSimulacao(Boolean alertaSimulacao) {
        this.alertaSimulacao = alertaSimulacao;
    }

    public Double getSaldoFicticio() {
        return saldoFicticio;
    }

    public void setSaldoFicticio(Double saldoFicticio) {
        this.saldoFicticio = saldoFicticio;
    }

    @Override
    public String toString() {
        return "SimuladorOperacaoInvestir {" +
                "id=" + id +
                ", investidorId=" + (investidor != null ? investidor.getId() : "null") +
                ", nomeSimulacao='" + nomeSimulacao + '\'' +
                ", valorInvestidoSimulado=" + valorInvestidoSimulado +
                ", alertaSimulacao=" + (alertaSimulacao ? "Sim" : "Não") +
                ", saldoFicticio=" + saldoFicticio +
                '}';
    }
}