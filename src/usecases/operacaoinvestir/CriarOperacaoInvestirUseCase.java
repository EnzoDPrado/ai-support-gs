package usecases.operacaoinvestir;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


import dao.InvestidorDao;
import dao.OperacaoInvestirDao;
import entities.Investidor;
import entities.OperacaoInvestir;

import java.sql.SQLException;
import java.util.Scanner;


public class CriarOperacaoInvestirUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CRIAR OPERAÇÃO DE INVESTIMENTO ===");

            System.out.print("Digite o ID do investidor: ");
            Long investidorId = Long.parseLong(scanner.nextLine());

            InvestidorDao investidorDao = new InvestidorDao();
            Investidor investidor = investidorDao.pesquisar(investidorId);

            if (investidor == null) {
                System.err.println("❌ Investidor não encontrado!");
                return;
            }

            System.out.print("Digite o nome do investimento: ");
            String nomeInvestimento = scanner.nextLine();

            System.out.print("Digite o valor inicial investido: ");
            Double valorInvestimentoInicial = Double.parseDouble(scanner.nextLine());

            System.out.print("Deseja ativar alerta (S/N): ");
            String alertaStr = scanner.nextLine().trim().toUpperCase();
            Boolean alerta = alertaStr.equals("S");

            OperacaoInvestir operacao = new OperacaoInvestir(
                    null,
                    investidor,
                    nomeInvestimento,
                    valorInvestimentoInicial,
                    alerta
            );

            OperacaoInvestirDao dao = new OperacaoInvestirDao();
            dao.cadastrar(operacao);
            dao.fecharConexao();
            investidorDao.fecharConexao();

            System.out.println("✅ Operação de investimento cadastrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar operação de investimento: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico: " + e.getMessage());
        }
    }
}