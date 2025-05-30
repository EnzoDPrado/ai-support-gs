import controllers.AlertaController;
import controllers.CidadeController;
import controllers.RefugioController;
import controllers.UserController;
import entities.Cidade;

import java.util.List;
import java.util.Scanner;
public class Execute {
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5, 6);

        System.out.println("-------------------------INSTRUÇÕES INICIAIS-------------------------------");
        System.out.println("--- Crie primeiramente uma cidade para vincular com as demais entidades ---");
        System.out.println("---------------------------------------------------------------------------");

        while (true) {
            System.out.println("1 -- listagens");
            System.out.println("2 -- cadastros");
            System.out.println("3 -- atualizar");
            System.out.println("4 -- buscar um");
            System.out.println("5 -- deletar um");
            System.out.println("6 -- sair");
            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        this.listFlux();
                        break;
                    case 2:
                        this.createFlux();
                        break;
                    case 3:
                        this.updateFlux();
                        break;
                    case 4:
                        this.listOneFlux();
                        break;
                    case 5:
                        this.deleteOneFlux();
                        break;
                    case 6:
                        System.out.println("Encerrando aplicação");
                        return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void deleteOneFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5);
        while(true){
            System.out.println("--- FLUXO DE DELETAR UM ---");
            System.out.println("1 -- user");
            System.out.println("2 -- refugio");
            System.out.println("3 -- cidade");
            System.out.println("4 -- alerta");
            System.out.println("5 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.deletarUser();
                        break;
                    case 2:
                        RefugioController.deletarRefugio();
                        break;
                    case 3:
                        CidadeController.deletarCidade();
                        break;
                    case 4:
                        AlertaController.deletarAlerta();
                        break;
                    case 5:
                        return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listOneFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5);
        while(true){
            System.out.println("--- FLUXO DE BUSCAR UM ---");
            System.out.println("1 -- user");
            System.out.println("2 -- refugio");
            System.out.println("3 -- cidade");
            System.out.println("4 -- alerta");
            System.out.println("5 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.buscarUser();
                        break;
                    case 2:
                        RefugioController.buscarRefugio();
                        break;
                    case 3:
                        CidadeController.listarCidadePorId();
                        break;
                    case 4:
                        AlertaController.buscarAlerta();
                        break;
                    case 5:
                        return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5);
        while(true){
            System.out.println("--- FLUXO DE LISTAGEM ---");
            System.out.println("1 -- users");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- alertas");
            System.out.println("5 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.listarUsers();
                        break;
                    case 2:
                        RefugioController.listarRefugios();
                        break;
                    case 3:
                        CidadeController.listarCidades();
                        break;
                    case 4:
                        AlertaController.listarAlertas();
                        break;
                    case 5:
                        return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void createFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5);
        while(true){
            System.out.println("--- FLUXO DE CRIAÇÃO ---");
            System.out.println("1 -- user");
            System.out.println("2 -- refugio");
            System.out.println("3 -- cidade");
            System.out.println("4 -- alerta");
            System.out.println("5 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.adicionarUser();
                        break;
                    case 2:
                        RefugioController.adicionarRefugio();
                        break;
                    case 3:
                        CidadeController.criarCidade();
                        break;
                    case 4:
                        AlertaController.adicionarAlerta();
                        break;
                    case 5:
                        return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void updateFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5);
        while(true){
            System.out.println("--- FLUXO DE ATUALIZAÇÃO ---");
            System.out.println("1 -- users");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- alertas");
            System.out.println("5 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.atualizarUser();
                        break;
                    case 2:
                        RefugioController.atualizarRefugio();
                        break;
                    case 3:
                        CidadeController.atualizarCidade();
                        break;
                    case 4:
                        AlertaController.atualizarAlerta();
                        break;
                    case 5:
                        return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }



    private void validateOptions(List<Integer> options, Integer value){
        try{
            if(!options.contains(value)) throw new Error("Valor invalido");
        } catch (Exception e) {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}