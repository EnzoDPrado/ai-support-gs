package dao;

import entities.Investidor;
import entities.Endereco;
import infra.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {
    private Connection conexao;
    private InvestidorDao investidorDao;

    public EnderecoDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
        this.investidorDao = new InvestidorDao();
    }

    private Endereco parseEndereco(ResultSet result) throws SQLException {
        Long id = result.getLong("cd_endereco");

        Long investidorId = result.getLong("cd_investidor");
        Investidor investidor = this.investidorDao.pesquisar(investidorId);

        String rua = result.getString("rua");
        String numero = result.getString("numero");
        String bairro = result.getString("bairro");
        String complemento = result.getString("complemento");


        return new Endereco(id, investidor, rua, numero, complemento, bairro);
    }

    public void cadastrar(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO tb_endereco " +
                "(cd_endereco, cd_investidor, rua, numero, complemento, bairro) " +
                "VALUES (seq_endereco.nextval, ?, ?, ?, ?, ?)";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, endereco.getInvestidor().getId());
        stm.setString(2, endereco.getRua());
        stm.setString(3, endereco.getNumero());
        stm.setString(4, endereco.getComplemento());
        stm.setString(5, endereco.getBairro());
        stm.executeUpdate();
    }


    public Endereco pesquisar(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_endereco WHERE cd_endereco = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new Error("Endereco não encontrado");
        return parseEndereco(result);
    }

    public List<Endereco> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_endereco");
        ResultSet result = stm.executeQuery();
        List<Endereco> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseEndereco(result));
        }
        return lista;
    }

    public void atualizar(Endereco endereco) throws SQLException {
        String sql = "UPDATE tb_endereco " +
                "SET cd_investidor = ?, rua = ?, numero = ?, complemento = ?, bairro = ? " +
                "WHERE cd_endereco = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setLong(1, endereco.getInvestidor().getId());
        stm.setString(2, endereco.getRua());
        stm.setString(3, endereco.getNumero());
        stm.setString(4, endereco.getComplemento());
        stm.setString(5, endereco.getBairro());
        stm.setLong(6, endereco.getId());
        stm.executeUpdate();
    }


    public void remover(long codigo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from tb_endereco where cd_endereco = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new Error("Endereco não encontrado para ser removido");
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}
