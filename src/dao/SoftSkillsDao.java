package dao;

import entities.SoftSkills;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SoftSkillsDao {

    private final Connection conexao;

    public SoftSkillsDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
    }

    private SoftSkills parseSoftSkill(ResultSet result) throws SQLException {
        SoftSkills s = new SoftSkills();
        s.setId(result.getString("cd_soft_skils"));
        s.setCdUser(result.getLong("cd_user"));
        s.setName(result.getString("name"));
        return s;
    }

    public void create(SoftSkills soft) throws SQLException {
        String sql =
                "BEGIN " +
                        "   INSERT INTO tb_soft_skils (cd_soft_skils, cd_user, name) " +
                        "   VALUES (seq_tb_soft_skils.nextval, ?, ?); " +
                        "   SELECT seq_tb_soft_skils.CURRVAL INTO ? FROM dual; " +
                        "END;";

        try (CallableStatement cs = conexao.prepareCall(sql)) {

            cs.setLong(1, soft.getCdUser());

            cs.setString(2, soft.getName());


            cs.registerOutParameter(3, Types.NUMERIC);

            cs.execute();

            String generatedId = cs.getString(3);
            soft.setId(generatedId);
        }
    }

    public Optional<SoftSkills> findById(String id) throws SQLException {
        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_soft_skils WHERE cd_soft_skils = ?");

        stm.setString(1, id);

        ResultSet result = stm.executeQuery();
        if (result.next()) {
            return Optional.of(parseSoftSkill(result));
        }

        return Optional.empty();
    }

    public List<SoftSkills> findAll() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_soft_skils");
        ResultSet result = stm.executeQuery();

        List<SoftSkills> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseSoftSkill(result));
        }

        return lista;

    }

    public void update(SoftSkills soft) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement(
                "UPDATE tb_soft_skils SET name = ? WHERE cd_soft_skils = ?");

        stm.setString(1, soft.getName());
        stm.setString(2, soft.getId());

        int linhas = stm.executeUpdate();
        if (linhas == 0) {
            throw new Error("SoftSkill não encontrada para atualização");
        }

        this.fecharConexao();
    }

    public void delete(String id) throws SQLException {
        PreparedStatement stm =
                conexao.prepareStatement("DELETE FROM tb_soft_skils WHERE cd_soft_skils = ?");

        stm.setString(1, id);

        int linhas = stm.executeUpdate();
        if (linhas == 0) {
            throw new Error("SoftSkill não encontrada para remoção");
        }

        this.fecharConexao();
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}
