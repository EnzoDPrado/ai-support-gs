package usecases.investidor;

import dao.InvestidorDao;
import entities.Investidor;

import java.sql.SQLException;

public class CriarInvestidorUseCase {
    private final InvestidorDao investidorDao;

    public CriarInvestidorUseCase(InvestidorDao investidorDao) {
        this.investidorDao = investidorDao;
    }

    public Investidor execute(Investidor investidor) throws SQLException {
        if(investidor.getCpf().length() != 11){
            throw new Error("cpf errado");
        }

        this.investidorDao.cadastrar(investidor);

        return investidor;
    }
}
