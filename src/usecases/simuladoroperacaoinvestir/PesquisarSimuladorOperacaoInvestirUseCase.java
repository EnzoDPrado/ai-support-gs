package usecases.simuladoroperacaoinvestir;

import dao.SimuladorOperacaoInvestirDao;
import entities.SimuladorOperacaoInvestir;

import java.sql.SQLException;
import java.util.Scanner;

public class PesquisarSimuladorOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== PESQUISAR SIMULADOR DE OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID do simulador: ");
            Long simuladorId = Long.parseLong(scanner.nextLine());

            SimuladorOperacaoInvestirDao dao = new SimuladorOperacaoInvestirDao();
            SimuladorOperacaoInvestir simulador = dao.pesquisar(simuladorId);
            dao.fecharConexao();

            if (simulador == null) {
                System.err.println("❌ Simulador não encontrado!");
                return;
            }

            System.out.println("\n=== DETALHES DO SIMULADOR ===");
            System.out.println(simulador);

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar simulador: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
