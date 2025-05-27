import controllers.UserController;

import java.util.List;
import java.util.Scanner;
public class Execute {
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3);

        while (true) {
            System.out.println("1 -- listagens");
            System.out.println("2 -- cadastros");
            System.out.println("3 -- atualizar");
            System.out.println("4 -- sair");
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
                    case 4: return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4);
        while(true){
            System.out.println("--- FLUXO DE LISTAGEM ---");
            System.out.println("1 -- users");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.listarUsers();
                        break;
                    case 4: return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void createFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4);
        while(true){
            System.out.println("--- FLUXO DE CRIAÇÃO ---");
            System.out.println("1 -- users");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.adicionarUser();
                        break;
                    case 4: return;
                }
            }catch (Exception e){
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void updateFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4);
        while(true){
            System.out.println("--- FLUXO DE ATUALIZAÇÃO ---");
            System.out.println("1 -- users");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- voltar");

            try{
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value){
                    case 1:
                        UserController.atualizarUser();
                        break;
                    case 4: return;
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
            throw new RuntimeException(e);
        }
    }
}