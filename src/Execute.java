import controllers.MoradorController;

import java.util.List;
import java.util.Scanner;
public class Execute {
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3);

        while (true) {
            System.out.println("1 -- listagens");
            System.out.println("2 -- cadastros");
            System.out.println("3 -- sair");
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
                    case 3: return;
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    private void listFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4);
        while(true){
            System.out.println("--- FLUXO DE LISTAGEM ---");
            System.out.println("1 -- moradores");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- voltar");

            Integer value = Integer.valueOf(scanner.nextLine());
            this.validateOptions(validOptions, value);

            switch (value){
                case 1:
                    MoradorController.listarMoradores();
                    break;
                case 4: return;
            }
        }
    }

    private void createFlux(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4);
        while(true){
            System.out.println("--- FLUXO DE CRIAÇÃO ---");
            System.out.println("1 -- moradores");
            System.out.println("2 -- refugios");
            System.out.println("3 -- cidades");
            System.out.println("4 -- voltar");

            Integer value = Integer.valueOf(scanner.nextLine());
            this.validateOptions(validOptions, value);

            switch (value){
                case 1:
                    MoradorController.adicionarMorador();
                    break;
                case 4: return;
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