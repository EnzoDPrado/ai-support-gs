package usecases.investidor;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RemoverInvestidorUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== REMOVER INVESTIDOR ===");
            System.out.print("Digite o código do investidor: ");
            long codigo = Long.parseLong(scanner.nextLine());

            InvestidorDao dao = new InvestidorDao();
            Investidor investidor = dao.pesquisar(codigo);

            System.out.println("Investidor encontrado: " + investidor);
            System.out.print("Confirma a exclusão? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                dao.remover(codigo);
                System.out.println("✅ Investidor removido com sucesso!");
            } else {
                System.out.println("❌ Operação cancelada.");
            }

            dao.fecharConexao();

        } catch (SQLException e) {
            System.err.println("Erro ao remover investidor: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
