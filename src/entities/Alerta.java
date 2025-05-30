package entities;

import java.util.UUID;

public class Alerta {
    private UUID id;
    private Cidade cidade;
    private String descricao;
    private String motivo ;

    public Alerta(UUID id, String descricao, String motivo, Cidade cidade) {
        this.id = id;
        this.descricao = descricao;
        this.motivo = motivo;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Alerta {" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", motivo='" + motivo + '\'' +
                ", cidade=" + (cidade != null ? cidade.getNome() : "Não atribuída") +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
