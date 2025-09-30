package usecases.historicovaloroperacao;

import dao.HistoricoValorOperacaoDao;
import entities.HistoricoValorOperacao;

import java.sql.SQLException;
import java.util.List;

public class ListarHistoricoValorOperacoesUseCase {

    public static void execute() {
        try {
            System.out.println("=== LISTAR HISTÓRICOS DE VALORES DAS OPERAÇÕES ===");

            HistoricoValorOperacaoDao dao = new HistoricoValorOperacaoDao();
            List<HistoricoValorOperacao> historicos = dao.listar();
            dao.fecharConexao();

            if (historicos.isEmpty()) {
                System.out.println("⚠️ Nenhum histórico encontrado.");
                return;
            }

            for (HistoricoValorOperacao h : historicos) {
                System.out.println(h);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar históricos: " + e.getMessage());
        }
    }
}
