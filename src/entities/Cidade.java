package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cidade {
    private UUID id;
    private String nome;
    private String estado;
    private String latitude;
    private String longitude;

    private List<User> users = new ArrayList<>();
    private List<Refugio> refugios = new ArrayList<>();

    private List<Alerta> alertas = new ArrayList<>();

    public Cidade(UUID id, String nome, String estado, String latitude, String longitude, List<User> users) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Cidade {" +
                "\n  id=" + id +
                ",\n  nome='" + nome + '\'' +
                ",\n  estado='" + estado + '\'' +
                ",\n  latitude='" + latitude + '\'' +
                ",\n  longitude='" + longitude + '\'' +
                ",\n  users=" + users.stream()
                .map(User::toString)
                .toList() +
                ",\n  refugios=" + refugios.stream()
                .map(Refugio::toString)
                .toList() +
                ",\n  alertas=" + alertas.stream()
                .map(Alerta::toString)
                .toList() +
                "\n}";
    }

    public String toStringSimple() {
        return "Cidade {" +
                "\n  id=" + id +
                ",\n  nome='" + nome + '\'' +
                ",\n  estado='" + estado + '\'' +
                "\n}";
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public List<Refugio> getRefugios() {
        return refugios;
    }

    public void setRefugios(List<Refugio> refugios) {
        this.refugios = refugios;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
