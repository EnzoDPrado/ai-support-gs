package usecases.historicovaloroperacao;

import dao.HistoricoValorOperacaoDao;
import dao.OperacaoInvestirDao;
import entities.HistoricoValorOperacao;
import entities.OperacaoInvestir;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CriarHistoricoValorOperacaoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CRIAR HISTÓRICO DE VALOR DA OPERAÇÃO ===");

            System.out.print("Digite o ID da operação de investimento: ");
            Long operacaoId = Long.parseLong(scanner.nextLine());

            OperacaoInvestirDao operacaoDao = new OperacaoInvestirDao();
            OperacaoInvestir operacao = operacaoDao.pesquisar(operacaoId);

            if (operacao == null) {
                System.err.println("❌ Operação de investimento não encontrada!");
                return;
            }

            System.out.print("Digite a data de registro (formato: dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDate dataRegistro = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.print("Digite o valor: ");
            Double valor = Double.parseDouble(scanner.nextLine());

            HistoricoValorOperacao historico = new HistoricoValorOperacao(
                    operacao,
                    Date.valueOf(dataRegistro),
                    valor
            );

            HistoricoValorOperacaoDao dao = new HistoricoValorOperacaoDao();
            dao.cadastrar(historico);
            dao.fecharConexao();
            operacaoDao.fecharConexao();

            System.out.println("✅ Histórico cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar histórico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
