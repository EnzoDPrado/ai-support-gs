package entities;


public class OperacaoInvestir{
    private Long id;
    private Investidor investidor;
    private String nomeInvestimento;
    private Double valorInvestimentoInicial;
    private Boolean alerta = false;

    public OperacaoInvestir(Long id, Investidor investidor, String nomeInvestimento, Double valorInvestimentoInicial, Boolean alerta) {
        this.id = id;
        this.investidor = investidor;
        this.nomeInvestimento = nomeInvestimento;
        this.valorInvestimentoInicial = valorInvestimentoInicial;
        this.alerta = alerta;
    }

    public OperacaoInvestir(Investidor investidor, String nomeInvestimento, Double valorInvestimentoInicial, Boolean alerta) {
        this.investidor = investidor;
        this.nomeInvestimento = nomeInvestimento;
        this.valorInvestimentoInicial = valorInvestimentoInicial;
        this.alerta = alerta;
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

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public Double getValorInvestimentoInicial() {
        return valorInvestimentoInicial;
    }

    public void setValorInvestimentoInicial(Double valorInvestimentoInicial) {
        this.valorInvestimentoInicial = valorInvestimentoInicial;
    }

    public Boolean getAlerta() {
        return alerta;
    }

    public void setAlerta(Boolean alerta) {
        this.alerta = alerta;
    }
}
