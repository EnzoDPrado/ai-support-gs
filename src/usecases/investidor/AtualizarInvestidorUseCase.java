package usecases.investidor;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AtualizarInvestidorUseCase {

    public static void atualizarInvestidor() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== ATUALIZAR INVESTIDOR ===");

            System.out.print("Digite o código do investidor: ");
            long codigo = Long.parseLong(scanner.nextLine());

            InvestidorDao dao = new InvestidorDao();
            Investidor investidor = dao.pesquisar(codigo);

            System.out.println("Investidor atual: " + investidor);

            System.out.print("Digite o novo CPF (atual: " + investidor.getCpf() + "): ");
            String cpf = scanner.nextLine();
            if (!cpf.isBlank()) {
                investidor.setCpf(cpf);
            }

            System.out.print("Digite a nova data de nascimento (dd/MM/yyyy) (atual: " + investidor.getDataNasc() + "): ");
            String dataStr = scanner.nextLine();
            if (!dataStr.isBlank()) {
                LocalDate dataNasc = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                investidor.setDataNasc(java.sql.Date.valueOf(dataNasc));
            }

            System.out.print("Digite o novo saldo (atual: " + investidor.getSaldo() + "): ");
            String saldoStr = scanner.nextLine();
            if (!saldoStr.isBlank()) {
                Double saldo = Double.parseDouble(saldoStr);
                investidor.setSaldo(saldo);
            }

            dao.atualizar(investidor);
            dao.fecharConexao();

            System.out.println("✅ Investidor atualizado com sucesso!");
            System.out.println("Novo estado: " + investidor);

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar investidor: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
