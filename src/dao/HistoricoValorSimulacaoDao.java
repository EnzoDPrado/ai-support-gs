package dao;

import entities.HistoricoValorSimulacao;
import entities.OperacaoInvestir;
import entities.SimuladorOperacaoInvestir;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoValorSimulacaoDao {
    private Connection conexao;
    private SimuladorOperacaoInvestirDao simuladorOperacaoInvestir;

    public HistoricoValorSimulacaoDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
        this.simuladorOperacaoInvestir = new SimuladorOperacaoInvestirDao();
    }

    private HistoricoValorSimulacao parseHistoricoValorSimulacao(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_investidor");

        Long simuladorOperacaoInvestirId = result.getLong("cd_simulador_operacao");
        SimuladorOperacaoInvestir simuladorOperacaoInvestir = this.simuladorOperacaoInvestir.pesquisar(simuladorOperacaoInvestirId);

        Date dataRegistro = result.getDate("data_registro");
        Double valor = result.getDouble("valor");

        return new HistoricoValorSimulacao(id, simuladorOperacaoInvestir, dataRegistro, valor);
    }

    public void cadastrar(HistoricoValorSimulacao historico) throws SQLException {
        String sql = "INSERT INTO tb_historico_valor_simulacao " +
                "(cd_historico_valor_simulacao, cd_simulador_operacao, data_registro, valor) " +
                "VALUES (seq_historico_valor_simulacao.nextval, ?, ?, ?)";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, historico.getSimuladorOperacaoInvestir().getId());
        stm.setDate(2, new java.sql.Date(historico.getDataRegistro().getTime()));
        stm.setDouble(3, historico.getValor());
        stm.executeUpdate();
    }

    public HistoricoValorSimulacao pesquisar(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_historico_valor_simulacao WHERE cd_historico_valor_simulacao = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new Error("HistoricoValorSimulacao não encontrado");
        return parseHistoricoValorSimulacao(result);
    }

    public List<HistoricoValorSimulacao> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_historico_valor_simulacao");
        ResultSet result = stm.executeQuery();
        List<HistoricoValorSimulacao> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseHistoricoValorSimulacao(result));
        }
        return lista;
    }

    public void atualizar(HistoricoValorSimulacao historico) throws SQLException {
        String sql = "UPDATE tb_historico_valor_simulacao " +
                "SET cd_simulador_operacao = ?, data_registro = ?, valor = ? " +
                "WHERE cd_historico_valor_simulacao = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, historico.getSimuladorOperacaoInvestir().getId());
        stm.setDate(2, new java.sql.Date(historico.getDataRegistro().getTime()));
        stm.setDouble(3, historico.getValor());
        stm.setLong(4, historico.getId());
        stm.executeUpdate();
    }


    public void remover(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from tb_historico_valor_simulacao where cd_simulador_operacao = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new Error("HistoricoValorSimulacao não encontrado para ser removido");
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}
