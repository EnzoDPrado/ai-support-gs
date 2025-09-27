package entities;

public class Endereco {
    private Long id;
    private Investidor investidor;
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;

    public Endereco(Long id, Investidor investidor, String rua, String numero, String complemento, String bairro) {
        this.id = id;
        this.investidor = investidor;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public Endereco(String rua, String numero, String bairro, String complemento, Investidor investidor) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.investidor = investidor;
    }

    @Override
    public String toString() {
        return "Rua: " + rua + ", Número: " + numero + ", Bairro: " + bairro + ", Complemento: " + complemento;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public Long getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
