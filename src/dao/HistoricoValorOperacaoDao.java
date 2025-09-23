package dao;

import entities.HistoricoValorOperacao;
import entities.OperacaoInvestir;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoValorOperacaoDao {
    private Connection conexao;
    private OperacaoInvestirDao operacaoInvestirDao;

    public HistoricoValorOperacaoDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
        this.operacaoInvestirDao = new OperacaoInvestirDao();
    }

    private HistoricoValorOperacao parseHistoricoValorOperacao(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_investidor");

        Long operacaoInvestirId = result.getLong("cd_operacao_investir");
        OperacaoInvestir operacaoInvestir = this.operacaoInvestirDao.pesquisar(operacaoInvestirId);

        Date dataRegistro = result.getDate("data_registro");
        Double valor = result.getDouble("valor");

        return new HistoricoValorOperacao(id, operacaoInvestir, dataRegistro, valor);
    }

    public void cadastrar(HistoricoValorOperacao historico) throws SQLException {
        String sql = "INSERT INTO tb_historico_valor_operacao " +
                "(cd_historico_valor_operacao, cd_operacao_investir, data_registro, valor) " +
                "VALUES (seq_historico_valor_operacao.nextval, ?, ?, ?)";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, historico.getOperacaoInvestir().getId());
        stm.setDate(2, historico.getDataRegistro());
        stm.setDouble(3, historico.getValor());
        stm.executeUpdate();
    }

    public HistoricoValorOperacao pesquisar(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_historico_valor_operacao WHERE cd_historico_valor_operacao = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new Error("HistoricoValorOperacao não encontrado");
        return parseHistoricoValorOperacao(result);
    }

    public List<HistoricoValorOperacao> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_historico_valor_operacao");
        ResultSet result = stm.executeQuery();
        List<HistoricoValorOperacao> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseHistoricoValorOperacao(result));
        }
        return lista;
    }

    public void atualizar(HistoricoValorOperacao historico) throws SQLException {
        String sql = "UPDATE tb_historico_valor_operacao " +
                "SET cd_operacao_investir = ?, data_registro = ?, valor = ? " +
                "WHERE cd_historico_valor_operacao = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, historico.getOperacaoInvestir().getId());
        stm.setDate(2, historico.getDataRegistro());
        stm.setDouble(3, historico.getValor());
        stm.setLong(4, historico.getId());
        stm.executeUpdate();
    }

    public void remover(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from tb_historico_valor_operacao where cd_historico_valor_operacao = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new Error("HistoricoValorOperacao não encontrado para ser removido");
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }

}
