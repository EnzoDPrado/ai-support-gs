package usecases.operacaoinvestir;


import dao.InvestidorDao;
import dao.OperacaoInvestirDao;
import entities.Investidor;
import entities.OperacaoInvestir;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== ATUALIZAR OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID da operação a ser atualizada: ");
            Long operacaoId = Long.parseLong(scanner.nextLine());

            OperacaoInvestirDao operacaoDao = new OperacaoInvestirDao();
            OperacaoInvestir operacaoExistente = operacaoDao.pesquisar(operacaoId);

            if (operacaoExistente == null) {
                System.err.println("❌ Operação de investimento não encontrada!");
                return;
            }

            System.out.print("Digite o novo ID do investidor (atual: " + operacaoExistente.getInvestidor().getId() + "): ");
            Long investidorId = Long.parseLong(scanner.nextLine());

            InvestidorDao investidorDao = new InvestidorDao();
            Investidor investidor = investidorDao.pesquisar(investidorId);

            if (investidor == null) {
                System.err.println("❌ Investidor não encontrado!");
                return;
            }

            System.out.print("Digite o novo nome do investimento (atual: " + operacaoExistente.getNomeInvestimento() + "): ");
            String nomeInvestimento = scanner.nextLine();
            if (nomeInvestimento.isBlank()) {
                nomeInvestimento = operacaoExistente.getNomeInvestimento();
            }

            System.out.print("Digite o novo valor inicial investido (atual: " + operacaoExistente.getValorInvestimentoInicial() + "): ");
            String valorStr = scanner.nextLine();
            Double valorInvestimentoInicial = valorStr.isBlank()
                    ? operacaoExistente.getValorInvestimentoInicial()
                    : Double.parseDouble(valorStr);

            System.out.print("Deseja ativar alerta (S/N) (atual: " + (operacaoExistente.getAlerta() ? "S" : "N") + "): ");
            String alertaStr = scanner.nextLine().trim().toUpperCase();
            Boolean alerta = alertaStr.isBlank()
                    ? operacaoExistente.getAlerta()
                    : alertaStr.equals("S");

            OperacaoInvestir operacaoAtualizada = new OperacaoInvestir(
                    operacaoId,
                    investidor,
                    nomeInvestimento,
                    valorInvestimentoInicial,
                    alerta
            );

            operacaoDao.atualizar(operacaoAtualizada);
            operacaoDao.fecharConexao();
            investidorDao.fecharConexao();

            System.out.println("✅ Operação de investimento atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar operação de investimento: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico: " + e.getMessage());
        }
    }
}