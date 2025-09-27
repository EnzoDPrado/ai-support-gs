package usecases.investidor;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;
import java.util.Scanner;

public class PesquisarInvestidorUseCase {

    public static void pesquisarInvestidor() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== PESQUISAR INVESTIDOR ===");
            System.out.print("Digite o código do investidor: ");
            long codigo = scanner.nextLong();

            InvestidorDao dao = new InvestidorDao();
            Investidor investidor = dao.pesquisar(codigo);
            dao.fecharConexao();

            System.out.println("✅ Investidor encontrado:");
            System.out.println(investidor);

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar investidor: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
