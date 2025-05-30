package controllers;

import entities.Cidade;
import entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class CidadeController {
    private static List<Cidade> cidades = new ArrayList<>();

    private CidadeController() {
    }

    public static void criarCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome da cidade: ");
        String nome = scanner.nextLine();

        System.out.println("Estado (UF): ");
        String estado = scanner.nextLine();

        Random r = new Random();

        Cidade cidade = new Cidade(
                UUID.randomUUID(),
                nome,
                estado,
                String.valueOf(r.nextInt(400) + 100),
                String.valueOf(r.nextInt(400) + 100),
                new ArrayList<>()
        );

        cidades.add(cidade);
        System.out.println("Cidade criada com sucesso!");
    }

    public static void listarCidades() {
        cidades.forEach(cidade -> {
            System.out.println(cidade.toString());
        });
    }

    public static void listarCidadesSimples() {
        cidades.forEach(cidade -> {
            System.out.println(cidade.toStringSimple());
        });
    }

    public static void listarCidadePorId() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID da cidade:");
        String idStr = scanner.nextLine();

        try {
            UUID cidadeId = UUID.fromString(idStr.trim());

            Cidade cidade = cidades.stream()
                    .filter(c -> c.getId().equals(cidadeId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));

            System.out.println(cidade.toString());

        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido ou cidade não encontrada.");
        }
    }

    public static Cidade listarCidadePorIdERetornar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID da cidade:");
        String idStr = scanner.nextLine();

        try {
            UUID cidadeId = UUID.fromString(idStr.trim());

            Cidade cidade = cidades.stream()
                    .filter(c -> c.getId().equals(cidadeId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));

            return cidade;
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido ou cidade não encontrada.");
        }
        return null;
    }

    public static void atualizarCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID da cidade que deseja atualizar:");
        String idStr = scanner.nextLine();

        try {
            UUID cidadeId = UUID.fromString(idStr.trim());

            Cidade cidade = cidades.stream()
                    .filter(c -> c.getId().equals(cidadeId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));

            System.out.println("Novo nome da cidade (atual: " + cidade.getNome() + "):");
            String novoNome = scanner.nextLine();

            System.out.println("Novo estado (UF) (atual: " + cidade.getEstado() + "):");
            String novoEstado = scanner.nextLine();

            System.out.println("Nova latitude (atual: " + cidade.getLatitude() + "):");
            String novaLatitude = scanner.nextLine();

            System.out.println("Nova longitude (atual: " + cidade.getLongitude() + "):");
            String novaLongitude = scanner.nextLine();

            cidade.setNome(novoNome);
            cidade.setEstado(novoEstado);
            cidade.setLatitude(novaLatitude);
            cidade.setLongitude(novaLongitude);

            System.out.println("Cidade atualizada com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido ou cidade não encontrada.");
        }
    }
    public static void deletarCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID da cidade a ser deletada:");
        String idStr = scanner.nextLine();

        UUID cidadeId = UUID.fromString(idStr.trim());

        Cidade cidade = cidades.stream()
                .filter(c -> c.getId().equals(cidadeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));

        cidades.remove(cidade);
        System.out.println("Cidade removida com sucesso.");
    }
}
