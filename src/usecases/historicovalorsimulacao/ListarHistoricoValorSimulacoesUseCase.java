package usecases.historicovalorsimulacao;

import dao.HistoricoValorSimulacaoDao;
import entities.HistoricoValorSimulacao;

import java.sql.SQLException;
import java.util.List;

public class ListarHistoricoValorSimulacoesUseCase {

    public static void execute() {
        try {
            System.out.println("=== LISTAR HISTÓRICOS DE VALORES DE SIMULAÇÃO ===");

            HistoricoValorSimulacaoDao dao = new HistoricoValorSimulacaoDao();
            List<HistoricoValorSimulacao> historicos = dao.listar();
            dao.fecharConexao();

            if (historicos.isEmpty()) {
                System.out.println("⚠️ Nenhum histórico encontrado.");
                return;
            }

            for (HistoricoValorSimulacao h : historicos) {
                System.out.println(h);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar históricos: " + e.getMessage());
        }
    }
}
