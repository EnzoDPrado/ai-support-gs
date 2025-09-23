package dao;

import entities.Investidor;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestidorDao {
    private Connection conexao;

    public InvestidorDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
    }

    private Investidor parseInvestidor(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_investidor");
        Date dataNasc = result.getDate("dt_nascimento");
        String cpf = result.getString("vl_cpf");
        Double saldo = result.getDouble("vl_saldo");

        return new Investidor(id,cpf, saldo, dataNasc);
    }

    public void cadastrar(Investidor investidor) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement(
                "INSERT INTO tb_investidor (cd_investidor, dt_nascimento, vl_cpf, vl_saldo) " +
                        "VALUES (seq_investidor.nextval, ?, ?, ?)"
        );
        stm.setDate(1, Date.valueOf(investidor.getDataNasc().toLocalDate()));
        stm.setString(2, investidor.getCpf());
        stm.setDouble(3, investidor.getSaldo());
        stm.executeUpdate();
    }

    public Investidor pesquisar(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_investidor WHERE cd_investidor = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new Error("Investidor não encontrado");
        return parseInvestidor(result);
    }

    public List<Investidor> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_investidor");
        ResultSet result = stm.executeQuery();
        List<Investidor> lista = new ArrayList<>();
        while (result.next()){
            lista.add(parseInvestidor(result));
        }
        return lista;
    }

    public void atualizar(Investidor investidor) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("UPDATE tb_investidor SET dt_nascimento = ?, vl_cpf = ?, vl_saldo = ? where cd_investidor = ?");

        stm.setDate(1, Date.valueOf(investidor.getDataNasc().toLocalDate()));
        stm.setString(2, investidor.getCpf());
        stm.setDouble(3, investidor.getSaldo());
        stm.setLong(4, investidor.getId());
        stm.executeUpdate();
    }

    public void remover(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from tb_investidor where cd_investidor = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new Error("Investidor não encontrado para ser removido");
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }

}
