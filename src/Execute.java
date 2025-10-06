import usecases.endereco.*;
import usecases.investidor.*;
import usecases.operacaoinvestir.*;
import usecases.historicovaloroperacao.*;
import usecases.historicovalorsimulacao.*;
import usecases.simuladoroperacaoinvestir.*;

import java.util.List;
import java.util.Scanner;

public class Execute {

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> validOptions = List.of(1, 2, 3, 4, 5, 6, 7);

        while (true) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1 -- Investidores");
            System.out.println("2 -- Endereços");
            System.out.println("3 -- Operações Investir");
            System.out.println("4 -- Histórico Valor Operação");
            System.out.println("5 -- Histórico Valor Simulação");
            System.out.println("6 -- Simuladores");
            System.out.println("7 -- Sair");

            try {
                Integer value = Integer.valueOf(scanner.nextLine());
                this.validateOptions(validOptions, value);

                switch (value) {
                    case 1: this.menuInvestidores(); break;
                    case 2: this.menuEnderecos(); break;
                    case 3: this.menuOperacoesInvestir(); break;
                    case 4: this.menuHistoricoOperacao(); break;
                    case 5: this.menuHistoricoSimulacao(); break;
                    case 6: this.menuSimuladores(); break;
                    case 7:
                        System.out.println("Encerrando aplicação...");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // ---------------- SUBMENUS ----------------

    private void menuEnderecos() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3, 4, 5, 6);

        while (true) {
            System.out.println("--- MENU ENDEREÇOS ---");
            System.out.println("1 -- Listar");
            System.out.println("2 -- Criar");
            System.out.println("3 -- Atualizar");
            System.out.println("4 -- Pesquisar");
            System.out.println("5 -- Remover");
            System.out.println("6 -- Voltar");

            try {
                int op = Integer.parseInt(scanner.nextLine());
                this.validateOptions(options, op);
                switch (op) {
                    case 1: ListarEnderecosUseCase.execute(); break;
                    case 2: CriarEnderecoUseCase.execute(); break;
                    case 3: AtualizarEnderecoUseCase.execute(); break;
                    case 4: PesquisarEnderecoUseCase.execute(); break;
                    case 5: RemoverEnderecoUseCase.execute(); break;
                    case 6: return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void menuInvestidores() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3, 4, 5, 6);

        while (true) {
            System.out.println("--- MENU INVESTIDORES ---");
            System.out.println("1 -- Listar");
            System.out.println("2 -- Criar");
            System.out.println("3 -- Atualizar");
            System.out.println("4 -- Pesquisar");
            System.out.println("5 -- Remover");
            System.out.println("6 -- Voltar");

            try {
                int op = Integer.parseInt(scanner.nextLine());
                this.validateOptions(options, op);
                switch (op) {
                    case 1: ListarInvestidoresUseCase.execute(); break;
                    case 2: CriarInvestidorUseCase.execute(); break;
                    case 3: AtualizarInvestidorUseCase.execute(); break;
                    case 4: PesquisarInvestidorUseCase.execute(); break;
                    case 5: RemoverInvestidorUseCase.execute(); break;
                    case 6: return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void menuOperacoesInvestir() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3, 4, 5, 6);

        while (true) {
            System.out.println("--- MENU OPERAÇÕES INVESTIR ---");
            System.out.println("1 -- Listar");
            System.out.println("2 -- Criar");
            System.out.println("3 -- Atualizar");
            System.out.println("4 -- Pesquisar");
            System.out.println("5 -- Remover");
            System.out.println("6 -- Voltar");

            try {
                int op = Integer.parseInt(scanner.nextLine());
                this.validateOptions(options, op);
                switch (op) {
                    case 1: ListarOperacoesInvestirUseCase.execute(); break;
                    case 2: CriarOperacaoInvestirUseCase.execute(); break;
                    case 3: AtualizarOperacaoInvestirUseCase.execute(); break;
                    case 4: PesquisarOperacaoInvestirUseCase.execute(); break;
                    case 5: RemoverOperacaoInvestirUseCase.execute(); break;
                    case 6: return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void menuHistoricoOperacao() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3, 4, 5, 6);

        while (true) {
            System.out.println("--- MENU HISTÓRICO VALOR OPERAÇÃO ---");
            System.out.println("1 -- Listar");
            System.out.println("2 -- Criar");
            System.out.println("3 -- Atualizar");
            System.out.println("4 -- Pesquisar");
            System.out.println("5 -- Remover");
            System.out.println("6 -- Voltar");

            try {
                int op = Integer.parseInt(scanner.nextLine());
                this.validateOptions(options, op);
                switch (op) {
                    case 1: ListarHistoricoValorOperacoesUseCase.execute(); break;
                    case 2: CriarHistoricoValorOperacaoUseCase.execute(); break;
                    case 3: AtualizarHistoricoValorOperacaoUseCase.execute(); break;
                    case 4: PesquisarHistoricoValorOperacaoUseCase.execute(); break;
                    case 5: RemoverHistoricoValorOperacaoUseCase.execute(); break;
                    case 6: return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void menuHistoricoSimulacao() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3, 4, 5, 6);

        while (true) {
            System.out.println("--- MENU HISTÓRICO VALOR SIMULAÇÃO ---");
            System.out.println("1 -- Listar");
            System.out.println("2 -- Criar");
            System.out.println("3 -- Atualizar");
            System.out.println("4 -- Pesquisar");
            System.out.println("5 -- Remover");
            System.out.println("6 -- Voltar");

            try {
                int op = Integer.parseInt(scanner.nextLine());
                this.validateOptions(options, op);
                switch (op) {
                    case 1: ListarHistoricoValorSimulacoesUseCase.execute(); break;
                    case 2: CriarHistoricoValorSimulacaoUseCase.execute(); break;
                    case 3: AtualizarHistoricoValorSimulacaoUseCase.execute(); break;
                    case 4: PesquisarHistoricoValorSimulacaoUseCase.execute(); break;
                    case 5: RemoverHistoricoValorSimulacaoUseCase.execute(); break;
                    case 6: return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void menuSimuladores() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3, 4, 5, 6);

        while (true) {
            System.out.println("--- MENU SIMULADORES ---");
            System.out.println("1 -- Listar");
            System.out.println("2 -- Criar");
            System.out.println("3 -- Atualizar");
            System.out.println("4 -- Pesquisar");
            System.out.println("5 -- Remover");
            System.out.println("6 -- Voltar");

            try {
                int op = Integer.parseInt(scanner.nextLine());
                this.validateOptions(options, op);
                switch (op) {
                    case 1: ListarSimuladoresOperacaoInvestirUseCase.execute(); break;
                    case 2: CriarSimuladorOperacaoInvestirUseCase.execute(); break;
                    case 3: AtualizarSimuladorOperacaoInvestirUseCase.execute(); break;
                    case 4: PesquisarSimuladorOperacaoInvestirUseCase.execute(); break;
                    case 5: RemoverSimuladorOperacaoInvestirUseCase.execute(); break;
                    case 6: return;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    // ---------------- VALIDATE ----------------
    private void validateOptions(List<Integer> options, Integer value) {
        if (!options.contains(value)) throw new IllegalArgumentException("Valor inválido");
    }
}
