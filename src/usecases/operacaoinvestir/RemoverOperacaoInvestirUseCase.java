package usecases.operacaoinvestir;

import dao.OperacaoInvestirDao;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverOperacaoInvestirUseCase {
    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== REMOVER OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID da operação a ser removida: ");
            Long operacaoId = Long.parseLong(scanner.nextLine());

            OperacaoInvestirDao dao = new OperacaoInvestirDao();
            dao.remover(operacaoId);
            dao.fecharConexao();

            System.out.println("✅ Operação de investimento removida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao remover operação de investimento: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico: " + e.getMessage());
        } catch (Error e) {
            System.err.println("❌ " + e.getMessage());
        }
    }
}