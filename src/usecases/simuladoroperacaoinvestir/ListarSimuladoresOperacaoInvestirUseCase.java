package usecases.simuladoroperacaoinvestir;

import dao.SimuladorOperacaoInvestirDao;
import entities.SimuladorOperacaoInvestir;

import java.sql.SQLException;
import java.util.List;

public class ListarSimuladoresOperacaoInvestirUseCase {

    public static void execute() {
        try {
            System.out.println("=== LISTAR SIMULADORES DE OPERAÇÃO DE INVESTIMENTO ===");

            SimuladorOperacaoInvestirDao dao = new SimuladorOperacaoInvestirDao();
            List<SimuladorOperacaoInvestir> simuladores = dao.listar();
            dao.fecharConexao();

            if (simuladores.isEmpty()) {
                System.out.println("⚠️ Nenhum simulador encontrado.");
                return;
            }

            for (SimuladorOperacaoInvestir simulador : simuladores) {
                System.out.println(simulador);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar simuladores: " + e.getMessage());
        }
    }
}
