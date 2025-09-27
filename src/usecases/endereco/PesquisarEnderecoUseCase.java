package usecases.endereco;

import dao.EnderecoDao;
import dao.InvestidorDao;
import entities.Endereco;
import entities.Investidor;

import java.sql.SQLException;
import java.util.Scanner;

public class PesquisarEnderecoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CONSULTAR ENDEREÇO ===");
            System.out.print("Digite o código do endereço: ");
            long codigo = Long.parseLong(scanner.nextLine());

            EnderecoDao enderecoDao = new EnderecoDao();
            Endereco endereco = enderecoDao.pesquisar(codigo);
            enderecoDao.fecharConexao();

            System.out.println("✅ Endereço encontrado:");
            System.out.println(endereco);

        } catch (SQLException e) {
            System.err.println("Erro ao consultar endereço: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
