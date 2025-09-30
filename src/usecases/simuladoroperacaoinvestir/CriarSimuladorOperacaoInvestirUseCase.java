package usecases.simuladoroperacaoinvestir;

import dao.InvestidorDao;
import dao.SimuladorOperacaoInvestirDao;
import entities.Investidor;
import entities.SimuladorOperacaoInvestir;

import java.sql.SQLException;
import java.util.Scanner;

public class CriarSimuladorOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CRIAR SIMULADOR DE OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID do investidor: ");
            Long investidorId = Long.parseLong(scanner.nextLine());

            InvestidorDao investidorDao = new InvestidorDao();
            Investidor investidor = investidorDao.pesquisar(investidorId);

            if (investidor == null) {
                System.err.println("❌ Investidor não encontrado!");
                return;
            }

            System.out.print("Digite o nome da simulação: ");
            String nomeSimulacao = scanner.nextLine();

            System.out.print("Digite o valor investido simulado: ");
            Double valorInvestidoSimulado = Double.parseDouble(scanner.nextLine());

            System.out.print("Deseja ativar alerta (S/N): ");
            String alertaStr = scanner.nextLine().trim().toUpperCase();
            Boolean alerta = alertaStr.equals("S");

            System.out.print("Digite o saldo fictício: ");
            Double saldoFicticio = Double.parseDouble(scanner.nextLine());

            SimuladorOperacaoInvestir simulador = new SimuladorOperacaoInvestir(
                    investidor, nomeSimulacao, valorInvestidoSimulado, alerta, saldoFicticio
            );

            SimuladorOperacaoInvestirDao dao = new SimuladorOperacaoInvestirDao();
            dao.cadastrar(simulador);
            dao.fecharConexao();
            investidorDao.fecharConexao();

            System.out.println("✅ Simulador cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar simulador: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
