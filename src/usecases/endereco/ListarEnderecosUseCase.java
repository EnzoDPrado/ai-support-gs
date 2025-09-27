package usecases.endereco;

import dao.EnderecoDao;
import entities.Endereco;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ListarEnderecosUseCase {

    public static void execute() {
        try {
            System.out.println("=== LISTA DE ENDEREÇOS ===");

            EnderecoDao enderecoDao = new EnderecoDao();
            List<Endereco> enderecos = enderecoDao.listar();
            enderecoDao.fecharConexao();

            if (enderecos.isEmpty()) {
                System.out.println("Nenhum endereço cadastrado.");
            } else {
                for (Endereco e : enderecos) {
                    System.out.println(e);
                }
            }

            System.out.println("==========================");

        } catch (SQLException e) {
            System.err.println("Erro ao listar endereços: " + e.getMessage());
        }
    }
}
