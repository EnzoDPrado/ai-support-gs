package usecases.operacaoinvestir;

import dao.OperacaoInvestirDao;
import entities.OperacaoInvestir;

import java.sql.SQLException;
import java.util.Scanner;

public class PesquisarOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== PESQUISAR OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID da operação: ");
            Long operacaoId = Long.parseLong(scanner.nextLine());

            OperacaoInvestirDao dao = new OperacaoInvestirDao();
            OperacaoInvestir operacao = dao.pesquisar(operacaoId);
            dao.fecharConexao();

            if (operacao == null) {
                System.err.println("❌ Operação de investimento não encontrada!");
                return;
            }

            System.out.println("\n=== DETALHES DA OPERAÇÃO ===");
            System.out.println(operacao);

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar operação de investimento: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico: " + e.getMessage());
        }
    }
}