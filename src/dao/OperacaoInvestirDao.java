package dao;

import entities.Investidor;
import entities.OperacaoInvestir;
import infra.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoInvestirDao {
    private Connection conexao;
    private InvestidorDao investidorDao;

    public OperacaoInvestirDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
        this.investidorDao = new InvestidorDao();
    }

    private OperacaoInvestir parseOperacaoInvestir(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_operacao_investir");

        Long investidorId = result.getLong("cd_investidor");
        Investidor investidor = this.investidorDao.pesquisar(investidorId);

        String nomeInvestimento = result.getString("nome_investimento");
        Double valorInvestimentoInicial = result.getDouble("valor_investido_inicial");
        Boolean alerta = result.getBoolean("alerta");

        return new OperacaoInvestir(id, investidor, nomeInvestimento, valorInvestimentoInicial, alerta);
    }

    public void cadastrar(OperacaoInvestir operacaoInvestir) throws SQLException {
        String sql = "INSERT INTO tb_operacao_investir " +
                "(cd_operacao_investir, cd_investidor, nome_investimento, valor_investido_inicial, alerta) " +
                "VALUES (seq_operacao_investir.nextval, ?, ?, ?, ?)";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, operacaoInvestir.getInvestidor().getId());
        stm.setString(2, operacaoInvestir.getNomeInvestimento());
        stm.setDouble(3, operacaoInvestir.getValorInvestimentoInicial());
        stm.setString(4, operacaoInvestir.getAlerta() ? "S" : "N");

        stm.executeUpdate();
    }


    public OperacaoInvestir pesquisar(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_operacao_investir WHERE cd_operacao_investir = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new Error("OperacaoInvestir não encontrado");
        return parseOperacaoInvestir(result);
    }

    public List<OperacaoInvestir> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_operacao_investir");
        ResultSet result = stm.executeQuery();
        List<OperacaoInvestir> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseOperacaoInvestir(result));
        }
        return lista;
    }

    public void atualizar(OperacaoInvestir operacaoInvestir) throws SQLException {
        String sql = "UPDATE tb_operacao_investir " +
                "SET cd_investidor = ?, nome_investimento = ?, valor_investido_inicial = ?, alerta = ? " +
                "WHERE cd_operacao_investir = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, operacaoInvestir.getInvestidor().getId());
        stm.setString(2, operacaoInvestir.getNomeInvestimento());
        stm.setDouble(3, operacaoInvestir.getValorInvestimentoInicial());
        stm.setString(4, operacaoInvestir.getAlerta() ? "S" : "N");
        stm.setLong(5, operacaoInvestir.getId());

        stm.executeUpdate();
    }


    public void remover(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from tb_operacao_investir where cd_operacao_investir = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new Error("OperacaoInvestir não encontrado para ser removido");
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}
