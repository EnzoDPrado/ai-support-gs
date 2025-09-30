package usecases.simuladoroperacaoinvestir;

import dao.SimuladorOperacaoInvestirDao;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverSimuladorOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== REMOVER SIMULADOR DE OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID do simulador: ");
            Long simuladorId = Long.parseLong(scanner.nextLine());

            SimuladorOperacaoInvestirDao dao = new SimuladorOperacaoInvestirDao();
            dao.remover(simuladorId);
            dao.fecharConexao();

            System.out.println("✅ Simulador removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao remover simulador: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
