package dao;

import entities.Investidor;
import entities.SimuladorOperacaoInvestir;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimuladorOperacaoInvestirDao {
    private Connection conexao;
    private InvestidorDao investidorDao;

    public SimuladorOperacaoInvestirDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
        this.investidorDao = new InvestidorDao();
    }

    private SimuladorOperacaoInvestir parseSimuladorOperacaoInvestir(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_simulador_operacao");

        Long investidorId = result.getLong("cd_investidor");
        Investidor investidor = this.investidorDao.pesquisar(investidorId);

        String nomeSimulacao = result.getString("nome_simulacao");
        Double valorInvestidoSimulado = result.getDouble("valor_investido_simulado");
        Boolean alertaSimulacao = result.getBoolean("alerta_simulacao");
        Double saldoFicticio = result.getDouble("saldo_ficticio");

        return new SimuladorOperacaoInvestir(id, investidor, nomeSimulacao, valorInvestidoSimulado, alertaSimulacao, saldoFicticio);
    }

    public void cadastrar(SimuladorOperacaoInvestir simuladorOperacaoInvestir) throws SQLException {
        String sql = "INSERT INTO tb_simulador_operacao_investir " +
                "(cd_simulador_operacao, cd_investidor, nome_simulacao, valor_investido_simulado, alerta_simulacao, saldo_ficticio) " +
                "VALUES (seq_simulador_operacao_investir.nextval, ?, ?, ?, ?, ?)";

        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setLong(1, simuladorOperacaoInvestir.getInvestidor().getId());
        stm.setString(2, simuladorOperacaoInvestir.getNomeSimulacao());
        stm.setDouble(3, simuladorOperacaoInvestir.getValorInvestidoSimulado());
        stm.setString(4, simuladorOperacaoInvestir.getAlertaSimulacao() ? "S" : "N");
        stm.setDouble(5, simuladorOperacaoInvestir.getSaldoFicticio());

        stm.executeUpdate();

    }

    public SimuladorOperacaoInvestir pesquisar(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_simulador_operacao_investir WHERE cd_simulador_operacao = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new Error("SimuladorOperacaoInvestir não encontrado");
        return parseSimuladorOperacaoInvestir(result);
    }

    public List<SimuladorOperacaoInvestir> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_simulador_operacao_investir");
        ResultSet result = stm.executeQuery();
        List<SimuladorOperacaoInvestir> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseSimuladorOperacaoInvestir(result));
        }
        return lista;
    }

    public void atualizar(SimuladorOperacaoInvestir simuladorOperacaoInvestir) throws SQLException {
        String sql = "UPDATE tb_simulador_operacao_investir " +
                "SET cd_investidor = ?, nome_simulacao = ?, valor_investido_simulado = ?, alerta_simulacao = ?, saldo_ficticio = ? " +
                "WHERE cd_simulador_operacao = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setLong(1, simuladorOperacaoInvestir.getInvestidor().getId());
        stm.setString(2, simuladorOperacaoInvestir.getNomeSimulacao());
        stm.setDouble(3, simuladorOperacaoInvestir.getValorInvestidoSimulado());
        stm.setString(4, simuladorOperacaoInvestir.getAlertaSimulacao() ? "S" : "N");
        stm.setDouble(5, simuladorOperacaoInvestir.getSaldoFicticio());
        stm.setLong(6, simuladorOperacaoInvestir.getId());

        stm.executeUpdate();
    }


    public void remover(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from tb_simulador_operacao_investir where cd_simulador_operacao = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new Error("SimuladorOperacaoInvestir não encontrado para ser removido");
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}
