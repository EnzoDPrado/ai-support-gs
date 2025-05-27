package controllers;

import entities.Morador;

import java.util.*;
import java.util.stream.Collectors;

public class MoradorController {
    static List<Morador> moradores = new ArrayList<>();;


    private MoradorController() {}

    public static void listarMoradores() {
        List<String> moradores = MoradorController.moradores
                .stream()
                .map(Morador::toString)
                .collect(Collectors.toList());

        System.out.println(moradores);
    }

    public static void adicionarMorador(){
        Random r = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Morador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf do Morador: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o email do Morador: ");
        String email = scanner.nextLine();
        System.out.println("Digite o telefone do Morador: ");
        String telefone = scanner.nextLine();

        Morador novoMorador = new Morador(
                nome,
                cpf,
                email,
                telefone,
                String.valueOf(r.nextInt(400) + 100),
                String.valueOf(r.nextInt(400) + 100)
        );

        moradores.add(novoMorador);
    }

    public static void atualizarMorador(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o id do Morador: ");
        String id = scanner.nextLine();

        Morador morador = MoradorController.moradores.stream().filter(m -> m.getId().equals(id)).findFirst().get();

        if(morador == null) throw new Error("Morador invalido");

        System.out.println("Digite o nome do Morador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf do Morador: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o email do Morador: ");
        String email = scanner.nextLine();
        System.out.println("Digite o telefone do Morador: ");
        String telefone = scanner.nextLine();

        morador.setNome(nome);
        morador.setCpf(cpf);
        morador.setEmail(email);
        morador.setTelefone(telefone);
    }
}
