package usecases.endereco;

import dao.EnderecoDao;
import dao.InvestidorDao;
import entities.Endereco;
import entities.Investidor;

import java.sql.SQLException;
import java.util.Scanner;

public class CriarEnderecoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CADASTRO DE ENDEREÇO ===");

            System.out.print("Digite o código do investidor: ");
            long investidorId = Long.parseLong(scanner.nextLine());

            InvestidorDao investidorDao = new InvestidorDao();
            Investidor investidor = investidorDao.pesquisar(investidorId);

            System.out.print("Digite a rua: ");
            String rua = scanner.nextLine();

            System.out.print("Digite o número: ");
            String numero = scanner.nextLine();

            System.out.print("Digite o bairro: ");
            String bairro = scanner.nextLine();

            System.out.print("Digite o complemento: ");
            String complemento = scanner.nextLine();

            Endereco novoEndereco = new Endereco(null, investidor, rua, numero, complemento, bairro);

            EnderecoDao enderecoDao = new EnderecoDao();
            enderecoDao.cadastrar(novoEndereco);
            enderecoDao.fecharConexao();

            System.out.println("✅ Endereço cadastrado com sucesso!");
            System.out.println(novoEndereco);

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar endereço: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
