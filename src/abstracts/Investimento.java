package abstracts;

import entities.Investidor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Investimento {
    private Investidor usuario;
    private String nome;
    private BigDecimal valorInvestido;
    private boolean alerta;
    private List<BigDecimal> historicoValores = new ArrayList<>();


    public Investimento(Investidor usuario, String nome, boolean alerta, BigDecimal valorInvestido) {
        this.usuario = usuario;
        this.nome = nome;
        this.alerta = alerta;
        this.valorInvestido = valorInvestido;
        this.historicoValores.add(valorInvestido);
    }

    public void render(int porcentagem) {
        BigDecimal fator = BigDecimal.ONE.add(
                BigDecimal.valueOf(porcentagem).divide(BigDecimal.valueOf(100))
        );
        BigDecimal novoValor = this.valorInvestido.multiply(fator);
        this.setValorInvestido(novoValor);
        this.historicoValores.add(novoValor);
    }

    public void desvalorizar(int porcentagem) {
        BigDecimal fator = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(porcentagem).divide(BigDecimal.valueOf(100))
        );
        BigDecimal novoValor = this.valorInvestido.multiply(fator);
        this.setValorInvestido(novoValor);
        this.historicoValores.add(novoValor);
    }

    public Investidor getUsuario() {
        return usuario;
    }

    public void setUsuario(Investidor usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

    public List<BigDecimal> getHistoricoValores() {
        return historicoValores;
    }

    public void setHistoricoValores(List<BigDecimal> historicoValores) {
        this.historicoValores = historicoValores;
    }
}
