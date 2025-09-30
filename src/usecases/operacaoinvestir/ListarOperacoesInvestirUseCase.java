package usecases.operacaoinvestir;

import dao.OperacaoInvestirDao;
import entities.OperacaoInvestir;

import java.sql.SQLException;
import java.util.List;

public class ListarOperacoesInvestirUseCase {
    public static void execute() {
        try {
            System.out.println("=== LISTAR TODAS AS OPERAÇÕES DE INVESTIMENTO ===");

            OperacaoInvestirDao dao = new OperacaoInvestirDao();
            List<OperacaoInvestir> operacoes = dao.listar();
            dao.fecharConexao();

            if (operacoes.isEmpty()) {
                System.out.println("⚠️ Nenhuma operação de investimento encontrada.");
                return;
            }

            for (OperacaoInvestir operacao : operacoes) {
                System.out.println(operacao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar operações de investimento: " + e.getMessage());
        }
    }
}