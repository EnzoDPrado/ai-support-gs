package usecases.historicovalorsimulacao;

import dao.HistoricoValorSimulacaoDao;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverHistoricoValorSimulacaoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== REMOVER HISTÓRICO DE VALOR DE SIMULAÇÃO ===");

            System.out.print("Digite o ID do histórico: ");
            Long historicoId = Long.parseLong(scanner.nextLine());

            HistoricoValorSimulacaoDao dao = new HistoricoValorSimulacaoDao();
            dao.remover(historicoId);
            dao.fecharConexao();

            System.out.println("✅ Histórico removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao remover histórico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
