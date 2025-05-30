package controllers;

import entities.Alerta;
import entities.Cidade;

import java.util.*;
import java.util.stream.Collectors;

public class AlertaController {
    static List<Alerta> alertas = new ArrayList<>();

    private AlertaController() {}

    public static void listarAlertas() {
        List<String> lista = alertas
                .stream()
                .map(Alerta::toString)
                .collect(Collectors.toList());

        System.out.println(lista);
    }

    public static void adicionarAlerta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a descrição do alerta:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o motivo do alerta:");
        String motivo = scanner.nextLine();

        Cidade cidade = CidadeController.listarCidadePorIdERetornar();

        Alerta novoAlerta = new Alerta(
                UUID.randomUUID(),
                descricao,
                motivo,
                cidade
        );

        alertas.add(novoAlerta);
        System.out.println("Alerta criado com sucesso!");
    }

    public static void buscarAlerta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do alerta:");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        Alerta alerta = alertas.stream()
                .filter(a -> a.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Alerta não encontrado."));

        System.out.println(alerta.toString());
    }

    public static void deletarAlerta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do alerta:");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        Alerta alerta = alertas.stream()
                .filter(a -> a.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Alerta não encontrado."));

        alertas.remove(alerta);
        System.out.println("Alerta removido com sucesso!");
    }

    public static void atualizarAlerta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do alerta:");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        Alerta alerta = alertas.stream()
                .filter(a -> a.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Alerta não encontrado."));

        System.out.println("Digite a nova descrição:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o novo motivo:");
        String motivo = scanner.nextLine();

        Cidade cidade = CidadeController.listarCidadePorIdERetornar();

        alerta.setDescricao(descricao);
        alerta.setMotivo(motivo);
        alerta.setCidade(cidade);

        System.out.println("Alerta atualizado com sucesso!");
    }
}
