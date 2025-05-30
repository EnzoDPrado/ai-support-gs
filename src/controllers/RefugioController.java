package controllers;

import entities.Cidade;
import entities.Refugio;
import entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class RefugioController {
    static List<Refugio> refugios = new ArrayList<>();

    private RefugioController() {}

    public static void listarRefugios() {
        List<String> lista = refugios
                .stream()
                .map(Refugio::toString)
                .collect(Collectors.toList());

        System.out.println(lista);
    }

    public static void adicionarRefugio() {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do Refugio: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a referência do Refugio: ");
        String referencia = scanner.nextLine();

        Cidade cidade = CidadeController.listarCidadePorIdERetornar();

        Refugio novoRefugio = new Refugio(
                UUID.randomUUID(),
                nome,
                referencia,
                String.valueOf(r.nextInt(400) + 100),
                String.valueOf(r.nextInt(400) + 100),
                cidade
        );

        refugios.add(novoRefugio);
        System.out.println("Refúgio adicionado com sucesso!");
    }

    public static void buscarRefugio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do Refugio: ");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        Refugio refugio = refugios.stream()
                .filter(r -> r.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Refúgio inválido"));

        System.out.println(refugio.toString());
    }

    public static void deletarRefugio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do Refugio: ");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        Refugio refugio = refugios.stream()
                .filter(r -> r.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Refúgio inválido"));

        refugios.remove(refugio);
        System.out.println("Refúgio removido com sucesso!");
    }

    public static void atualizarRefugio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do Refugio: ");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        Refugio refugio = refugios.stream()
                .filter(r -> r.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Refúgio inválido"));

        System.out.println("Digite o novo nome do Refugio: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a nova referência do Refugio: ");
        String referencia = scanner.nextLine();

        Cidade cidade = CidadeController.listarCidadePorIdERetornar();

        refugio.setNome(nome);
        refugio.setReferencia(referencia);
        refugio.setCidade(cidade);

        System.out.println("Refúgio atualizado com sucesso!");
    }
}
