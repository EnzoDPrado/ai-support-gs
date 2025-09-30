package usecases.historicovalorsimulacao;

import dao.HistoricoValorSimulacaoDao;
import entities.HistoricoValorSimulacao;

import java.sql.SQLException;
import java.util.Scanner;

public class PesquisarHistoricoValorSimulacaoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== PESQUISAR HISTÓRICO DE VALOR DE SIMULAÇÃO ===");

            System.out.print("Digite o ID do histórico: ");
            Long historicoId = Long.parseLong(scanner.nextLine());

            HistoricoValorSimulacaoDao dao = new HistoricoValorSimulacaoDao();
            HistoricoValorSimulacao historico = dao.pesquisar(historicoId);
            dao.fecharConexao();

            if (historico == null) {
                System.err.println("❌ Histórico não encontrado!");
                return;
            }

            System.out.println("\n=== DETALHES DO HISTÓRICO ===");
            System.out.println(historico);

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar histórico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
