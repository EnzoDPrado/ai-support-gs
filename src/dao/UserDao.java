package dao;

import entities.Investidor;
import entities.User;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private Connection conexao;

    public UserDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
    }

    private User parseUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getLong("cd_user"));
        user.setName(result.getString("name"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        return user;
    }

    public void cadastrar(User user) throws SQLException {

        String sql =
                "BEGIN " +
                        "   INSERT INTO tb_users (cd_user, name, email, password) " +
                        "   VALUES (seq_tb_users.nextval, ?, ?, ?) " +
                        "   RETURNING cd_user INTO ?; " +
                        "END;";

        CallableStatement cs = conexao.prepareCall(sql);

        cs.setString(1, user.getName());
        cs.setString(2, user.getEmail());
        cs.setString(3, user.getPassword());

        cs.registerOutParameter(4, Types.VARCHAR);

        cs.execute();

        Long generatedId = cs.getLong(4);

        user.setId(generatedId);
    }

    public Optional<User> pesquisar(Long id) throws SQLException {

        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_users WHERE cd_user = ?");

        stm.setLong(1, id);

        ResultSet result = stm.executeQuery();
        if (result.next()) {
            return Optional.of(parseUser(result));
        }

        return Optional.empty();
    }

    public Optional<User> pesquisarPorEmail(String email) throws SQLException {
        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_users WHERE email = ?");

        stm.setString(1, email);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            return Optional.of(parseUser(result));
        }

        return Optional.empty();
    }

    public Optional<User> pesquisarPorEmailSenha(String email, String senha) throws SQLException {
        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_users WHERE email = ? and  password = ?");

        stm.setString(1, email);
        stm.setString(2, senha);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            return Optional.of(parseUser(result));
        }

        return Optional.empty();
    }

    public List<User> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_users");
        ResultSet result = stm.executeQuery();

        List<User> lista = new ArrayList<>();

        while (result.next()) {
            lista.add(parseUser(result));
        }

        return lista;
    }

    public void atualizar(User user) throws SQLException {

        PreparedStatement stm = conexao.prepareStatement(
                "UPDATE tb_users SET name = ?, email = ?, password = ? WHERE cd_user = ?");

        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.setLong(4, user.getId());

        int linhas = stm.executeUpdate();
        if (linhas == 0) {
            throw new Error("Usuário não encontrado para atualização");
        }
    }

    public void remover(String id) throws SQLException {
        PreparedStatement stm =
                conexao.prepareStatement("DELETE FROM tb_users WHERE cd_user = ?");

        stm.setString(1, id);

        int linhas = stm.executeUpdate();
        if (linhas == 0) {
            throw new Error("Usuário não encontrado para remoção");
        }
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}