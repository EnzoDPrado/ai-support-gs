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

    // Listar cidades
    public static void listarCidades() {
        cidades.forEach(c -> {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " - " + c.getEstado());
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
    // Buscar cidade por ID
    private static Cidade buscarCidadePorId(UUID id) {
        return cidades.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));
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

    // Adicionar usuário à cidade
    public static void adicionarUserNaCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID da cidade: ");
        UUID cidadeId = UUID.fromString(scanner.nextLine());

        Cidade cidade = buscarCidadePorId(cidadeId);

        System.out.println("Nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();

        User novoUser = new User(
                nome,
                cpf,
                email,
                telefone,
                String.valueOf(new Random().nextInt(400) + 100),
                String.valueOf(new Random().nextInt(400) + 100)
        );

        cidade.getUsers().add(novoUser);
        System.out.println("Usuário adicionado à cidade com sucesso.");
    }

    // Listar usuários de uma cidade
    public static void listarUsersDaCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID da cidade: ");
        UUID cidadeId = UUID.fromString(scanner.nextLine());

        Cidade cidade = buscarCidadePorId(cidadeId);

        List<String> users = cidade.getUsers()
                .stream()
                .map(User::toString)
                .collect(Collectors.toList());

        System.out.println(users);
    }

    // Buscar um usuário de uma cidade
    public static void buscarUserNaCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID da cidade: ");
        UUID cidadeId = UUID.fromString(scanner.nextLine());

        Cidade cidade = buscarCidadePorId(cidadeId);

        System.out.println("ID do usuário: ");
        UUID userId = UUID.fromString(scanner.nextLine());

        User user = cidade.getUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        System.out.println(user.toString());
    }

    // Atualizar usuário de uma cidade
    public static void atualizarUserNaCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID da cidade: ");
        UUID cidadeId = UUID.fromString(scanner.nextLine());

        Cidade cidade = buscarCidadePorId(cidadeId);

        System.out.println("ID do usuário: ");
        UUID userId = UUID.fromString(scanner.nextLine());

        User user = cidade.getUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        System.out.println("Novo nome: ");
        user.setNome(scanner.nextLine());

        System.out.println("Novo CPF: ");
        user.setCpf(scanner.nextLine());

        System.out.println("Novo email: ");
        user.setEmail(scanner.nextLine());

        System.out.println("Novo telefone: ");
        user.setTelefone(scanner.nextLine());

        System.out.println("Usuário atualizado com sucesso.");
    }

    // Deletar usuário da cidade
    public static void deletarUserDaCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID da cidade: ");
        UUID cidadeId = UUID.fromString(scanner.nextLine());

        Cidade cidade = buscarCidadePorId(cidadeId);

        System.out.println("ID do usuário: ");
        UUID userId = UUID.fromString(scanner.nextLine());

        cidade.getUsers().removeIf(u -> u.getId().equals(userId));
        System.out.println("Usuário removido com sucesso.");
    }
}
