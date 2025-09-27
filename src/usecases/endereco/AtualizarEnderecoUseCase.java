package usecases.endereco;

import dao.EnderecoDao;
import dao.InvestidorDao;
import entities.Endereco;
import entities.Investidor;

import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarEnderecoUseCase {

    public static void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== ATUALIZAR ENDEREÇO ===");
            System.out.print("Digite o código do endereço: ");
            long codigo = Long.parseLong(scanner.nextLine());

            EnderecoDao enderecoDao = new EnderecoDao();
            Endereco endereco = enderecoDao.pesquisar(codigo);

            System.out.println("Endereço atual: " + endereco);

            System.out.print("Digite o novo código do investidor (atual: " + endereco.getInvestidor().getId() + "): ");
            String investidorIdStr = scanner.nextLine();
            if (!investidorIdStr.isBlank()) {
                long investidorId = Long.parseLong(investidorIdStr);
                InvestidorDao investidorDao = new InvestidorDao();
                Investidor investidor = investidorDao.pesquisar(investidorId);
                endereco.setInvestidor(investidor);
                investidorDao.fecharConexao();
            }

            System.out.print("Digite a nova rua (atual: " + endereco.getRua() + "): ");
            String rua = scanner.nextLine();
            if (!rua.isBlank()) {
                endereco.setRua(rua);
            }

            System.out.print("Digite o novo número (atual: " + endereco.getNumero() + "): ");
            String numero = scanner.nextLine();
            if (!numero.isBlank()) {
                endereco.setNumero(numero);
            }

            System.out.print("Digite o novo bairro (atual: " + endereco.getBairro() + "): ");
            String bairro = scanner.nextLine();
            if (!bairro.isBlank()) {
                endereco.setBairro(bairro);
            }

            System.out.print("Digite o novo complemento (atual: " + endereco.getComplemento() + "): ");
            String complemento = scanner.nextLine();
            if (!complemento.isBlank()) {
                endereco.setComplemento(complemento);
            }

            enderecoDao.atualizar(endereco);
            enderecoDao.fecharConexao();

            System.out.println("✅ Endereço atualizado com sucesso!");
            System.out.println("Novo estado: " + endereco);

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar endereço: " + e.getMessage());
        } catch (Error e) {
            System.err.println("⚠️ " + e.getMessage());
        }
    }
}
