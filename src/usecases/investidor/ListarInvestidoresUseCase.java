package usecases.investidor;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ListarInvestidoresUseCase {

    public static void execute() {
        try {
            System.out.println("=== LISTA DE INVESTIDORES ===");

            InvestidorDao dao = new InvestidorDao();
            List<Investidor> investidores = dao.listar();
            dao.fecharConexao();

            if (investidores.isEmpty()) {
                System.out.println("Nenhum investidor cadastrado.");
            } else {
                for (Investidor inv : investidores) {
                    System.out.println(inv);
                }
            }

            System.out.println("=============================");

        } catch (SQLException e) {
            System.err.println("Erro ao listar investidores: " + e.getMessage());
        }
    }
}
