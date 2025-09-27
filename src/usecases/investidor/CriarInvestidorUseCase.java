package usecases.investidor;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CriarInvestidorUseCase {

    public static void criarInvestidor() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CADASTRO DE INVESTIDOR ===");

            System.out.print("Digite o CPF do investidor: ");
            String cpf = scanner.nextLine();

            System.out.print("Digite a data de nascimento (formato: dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDate dataNasc = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.print("Digite o saldo inicial: ");
            Double saldo = scanner.nextDouble();

            Investidor novoInvestidor = new Investidor(null, cpf, saldo, java.sql.Date.valueOf(dataNasc));

            InvestidorDao dao = new InvestidorDao();
            dao.cadastrar(novoInvestidor);
            dao.fecharConexao();

            System.out.println("✅ Investidor cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar investidor: " + e.getMessage());
        }
    }
}
