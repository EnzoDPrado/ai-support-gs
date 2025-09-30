package usecases.historicovalorsimulacao;

import dao.HistoricoValorSimulacaoDao;
import dao.SimuladorOperacaoInvestirDao;
import entities.HistoricoValorSimulacao;
import entities.SimuladorOperacaoInvestir;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CriarHistoricoValorSimulacaoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CRIAR HISTÓRICO DE VALOR DE SIMULAÇÃO ===");

            System.out.print("Digite o ID do simulador: ");
            Long simuladorId = Long.parseLong(scanner.nextLine());

            SimuladorOperacaoInvestirDao simuladorDao = new SimuladorOperacaoInvestirDao();
            SimuladorOperacaoInvestir simulador = simuladorDao.pesquisar(simuladorId);

            if (simulador == null) {
                System.err.println("❌ Simulador não encontrado!");
                return;
            }

            System.out.print("Digite a data de registro (formato: dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDate dataRegistro = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.print("Digite o valor: ");
            Double valor = Double.parseDouble(scanner.nextLine());

            HistoricoValorSimulacao historico = new HistoricoValorSimulacao(
                    simulador,
                    Date.valueOf(dataRegistro),
                    valor
            );

            HistoricoValorSimulacaoDao dao = new HistoricoValorSimulacaoDao();
            dao.cadastrar(historico);
            dao.fecharConexao();
            simuladorDao.fecharConexao();

            System.out.println("✅ Histórico de simulação cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar histórico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
