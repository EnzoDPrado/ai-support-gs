package usecases.simuladoroperacaoinvestir;

import dao.InvestidorDao;
import dao.SimuladorOperacaoInvestirDao;
import entities.Investidor;
import entities.SimuladorOperacaoInvestir;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarSimuladorOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== ATUALIZAR SIMULADOR DE OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID do simulador: ");
            Long simuladorId = Long.parseLong(scanner.nextLine());

            SimuladorOperacaoInvestirDao dao = new SimuladorOperacaoInvestirDao();
            SimuladorOperacaoInvestir simuladorExistente = dao.pesquisar(simuladorId);

            if (simuladorExistente == null) {
                System.err.println("❌ Simulador não encontrado!");
                return;
            }

            System.out.print("Digite o novo ID do investidor (atual: " +
                    simuladorExistente.getInvestidor().getId() + "): ");
            Long investidorId = Long.parseLong(scanner.nextLine());

            InvestidorDao investidorDao = new InvestidorDao();
            Investidor investidor = investidorDao.pesquisar(investidorId);

            if (investidor == null) {
                System.err.println("❌ Investidor não encontrado!");
                return;
            }

            System.out.print("Digite o novo nome da simulação (atual: " +
                    simuladorExistente.getNomeSimulacao() + "): ");
            String nomeSimulacao = scanner.nextLine();
            if (nomeSimulacao.isBlank()) {
                nomeSimulacao = simuladorExistente.getNomeSimulacao();
            }

            System.out.print("Digite o novo valor investido simulado (atual: " +
                    simuladorExistente.getValorInvestidoSimulado() + "): ");
            String valorStr = scanner.nextLine();
            Double valorInvestidoSimulado = valorStr.isBlank()
                    ? simuladorExistente.getValorInvestidoSimulado()
                    : Double.parseDouble(valorStr);

            System.out.print("Deseja ativar alerta (S/N) (atual: " +
                    (simuladorExistente.getAlertaSimulacao() ? "S" : "N") + "): ");
            String alertaStr = scanner.nextLine().trim().toUpperCase();
            Boolean alerta = alertaStr.isBlank()
                    ? simuladorExistente.getAlertaSimulacao()
                    : alertaStr.equals("S");

            System.out.print("Digite o novo saldo fictício (atual: " +
                    simuladorExistente.getSaldoFicticio() + "): ");
            String saldoStr = scanner.nextLine();
            Double saldoFicticio = saldoStr.isBlank()
                    ? simuladorExistente.getSaldoFicticio()
                    : Double.parseDouble(saldoStr);

            SimuladorOperacaoInvestir simuladorAtualizado = new SimuladorOperacaoInvestir(
                    simuladorId, investidor, nomeSimulacao, valorInvestidoSimulado, alerta, saldoFicticio
            );

            dao.atualizar(simuladorAtualizado);
            dao.fecharConexao();
            investidorDao.fecharConexao();

            System.out.println("✅ Simulador atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar simulador: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
