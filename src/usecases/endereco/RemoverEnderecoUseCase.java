package usecases.endereco;

import dao.EnderecoDao;
import entities.Endereco;

import java.sql.SQLException;
import java.util.Scanner;

public class RemoverEnderecoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== REMOVER ENDEREÇO ===");
            System.out.print("Digite o código do endereço: ");
            long codigo = Long.parseLong(scanner.nextLine());

            EnderecoDao enderecoDao = new EnderecoDao();
            Endereco endereco = enderecoDao.pesquisar(codigo);

            System.out.println("Endereço encontrado: " + endereco);
            System.out.print("Confirma a exclusão? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                enderecoDao.remover(codigo);
                System.out.println("✅ Endereço removido com sucesso!");
            } else {
                System.out.println("❌ Operação cancelada.");
            }

            enderecoDao.fecharConexao();

        } catch (SQLException e) {
            System.err.println("Erro ao remover endereço: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
