package entities;


import java.sql.Date;

public class Investidor {
    private Long id;
    private String cpf;
    private Double saldo;
    private Date dataNasc;

    public Investidor() {
    }

    public Investidor(Long id, String cpf, Double saldo, Date dataNasc) {
        this.id = id;
        this.cpf = cpf;
        this.saldo = saldo;
        this.dataNasc = dataNasc;
    }

    public Investidor(String cpf, Double saldo, Date dataNasc) {
        this.cpf = cpf;
        this.saldo = saldo;
        this.dataNasc = dataNasc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        return "Investidor {" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", saldo=" + saldo +
                ", dataNasc=" + dataNasc +
                '}';
    }
}
