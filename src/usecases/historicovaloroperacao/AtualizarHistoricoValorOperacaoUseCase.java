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

public class AtualizarHistoricoValorOperacaoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== ATUALIZAR HISTÓRICO DE VALOR DA OPERAÇÃO ===");

            System.out.print("Digite o ID do histórico: ");
            Long historicoId = Long.parseLong(scanner.nextLine());

            HistoricoValorOperacaoDao dao = new HistoricoValorOperacaoDao();
            HistoricoValorOperacao historicoExistente = dao.pesquisar(historicoId);

            if (historicoExistente == null) {
                System.err.println("❌ Histórico não encontrado!");
                return;
            }

            System.out.print("Digite o novo ID da operação (atual: " +
                    historicoExistente.getOperacaoInvestir().getId() + "): ");
            Long operacaoId = Long.parseLong(scanner.nextLine());

            OperacaoInvestirDao operacaoDao = new OperacaoInvestirDao();
            OperacaoInvestir operacao = operacaoDao.pesquisar(operacaoId);

            if (operacao == null) {
                System.err.println("❌ Operação de investimento não encontrada!");
                return;
            }

            System.out.print("Digite a nova data de registro (dd/MM/yyyy) (atual: " +
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

            HistoricoValorOperacao historicoAtualizado = new HistoricoValorOperacao(
                    historicoId,
                    operacao,
                    dataRegistro,
                    valor
            );

            dao.atualizar(historicoAtualizado);
            dao.fecharConexao();
            operacaoDao.fecharConexao();

            System.out.println("✅ Histórico atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar histórico: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
