package entities;

import java.util.UUID;

public class Refugio {
    private UUID id;
    private String nome;
    private String referencia;
    private Cidade cidade;
    private String latitude;
    private String longitude;

    public Refugio(UUID id, String nome, String referencia, String latitude, String longitude, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Refugio {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", referencia='" + referencia + '\'' +
                ", cidade=" + (cidade != null ? cidade.getNome() : "null") +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
