package controllers;

import entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserController {
    static List<User> users = new ArrayList<>();;


    private UserController() {}

    public static void listarUsers() {
        List<String> users = UserController.users
                .stream()
                .map(User::toString)
                .collect(Collectors.toList());

        System.out.println(users);
    }

    public static void adicionarUser(){
        Random r = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do User: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf do User: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o email do User: ");
        String email = scanner.nextLine();
        System.out.println("Digite o telefone do User: ");
        String telefone = scanner.nextLine();

        User novoUser = new User(
                nome,
                cpf,
                email,
                telefone,
                String.valueOf(r.nextInt(400) + 100),
                String.valueOf(r.nextInt(400) + 100)
        );

        users.add(novoUser);
    }
    public static void buscarUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do User: ");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        User user = UserController.users.stream()
                .filter(m -> m.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User inválido"));

        System.out.println(user.toString());
    }

    public static void deletarUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do User: ");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        User user = UserController.users.stream()
                .filter(m -> m.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User inválido"));

        UserController.users.remove(user);

        System.out.println("Removido!");
    }

    public static void atualizarUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do User: ");
        String id = scanner.nextLine();

        UUID uuid = UUID.fromString(id.trim());

        User user = UserController.users.stream()
                .filter(m -> m.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User inválido"));

        System.out.println("Digite o nome do User: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf do User: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o email do User: ");
        String email = scanner.nextLine();
        System.out.println("Digite o telefone do User: ");
        String telefone = scanner.nextLine();

        user.setNome(nome);
        user.setCpf(cpf);
        user.setEmail(email);
        user.setTelefone(telefone);
    }
}
