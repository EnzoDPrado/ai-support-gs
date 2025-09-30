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

public class AtualizarHistoricoValorSimulacaoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== ATUALIZAR HISTÓRICO DE VALOR DE SIMULAÇÃO ===");

            System.out.print("Digite o ID do histórico: ");
            Long historicoId = Long.parseLong(scanner.nextLine());

            HistoricoValorSimulacaoDao dao = new HistoricoValorSimulacaoDao();
            HistoricoValorSimulacao historicoExistente = dao.pesquisar(historicoId);

            if (historicoExistente == null) {
                System.err.println("❌ Histórico não encontrado!");
                return;
            }

            System.out.print("Digite o novo ID do simulador (atual: " +
                    historicoExistente.getSimuladorOperacaoInvestir().getId() + "): ");
            Long simuladorId = Long.parseLong(scanner.nextLine());

            SimuladorOperacaoInvestirDao simuladorDao = new SimuladorOperacaoInvestirDao();
            SimuladorOperacaoInvestir simulador = simuladorDao.pesquisar(simuladorId);

            if (simulador == null) {
                System.err.println("❌ Simulador não encontrado!");
                return;
            }

            System.out.print("Digite a nova data (dd/MM/yyyy) (atual: " +
                    historicoExistente.getDataRegistro() + "): ");
            String dataStr = scanner.nextLine();
            Date dataRegistro = dataStr.isBlank()
                    ? historicoExistente.getDataRegistro()
                    : Date.valueOf(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            System.out.print("Digite o novo valor (atual: " + historicoExistente.getValor() + "): ");
            String valorStr = scanner.nextLine();
            Double valor = valorStr.isBlank()
                    ? historicoExistente.getValor()
                    : Double.parseDouble(valorStr);

            HistoricoValorSimulacao historicoAtualizado = new HistoricoValorSimulacao(
                    historicoId,
                    simulador,
                    dataRegistro,
                    valor
            );

            dao.atualizar(historicoAtualizado);
            dao.fecharConexao();
            simuladorDao.fecharConexao();

            System.out.println("✅ Histórico atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar histórico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
