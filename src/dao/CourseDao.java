package dao;

import entities.Course;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDao {

    private final Connection conexao;

    public CourseDao() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
    }

    private Course parseCourse(ResultSet result) throws SQLException {
        Course course = new Course();
        course.setId(result.getLong("cd_courses"));
        course.setUserId(result.getLong("cd_user"));
        course.setName(result.getString("name"));
        course.setHours(result.getLong("hours"));
        course.setDescription(result.getString("description"));
        return course;
    }

    public void cadastrar(Course course) throws SQLException {

        String sql =
                "BEGIN " +
                        "   INSERT INTO tb_courses (cd_courses, cd_user, name, hours, description) " +
                        "   VALUES (seq_tb_courses.nextval, ?, ?, ?, ?) " +
                        "   RETURNING cd_courses INTO ?; " +
                        "END;";

        CallableStatement cs = conexao.prepareCall(sql);

        cs.setLong(1, course.getUserId());
        cs.setString(2, course.getName());
        cs.setLong(3, course.getHours());
        cs.setString(4, course.getDescription());

        cs.registerOutParameter(5, Types.VARCHAR);

        cs.execute();

        Long generatedId = cs.getLong(5);

        course.setId(generatedId);

        this.fecharConexao();
    }

    public Optional<Course> pesquisar(Long id) throws SQLException {

        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_courses WHERE cd_courses = ?");

        stm.setLong(1, id);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            return Optional.of(parseCourse(result));
        }


        this.fecharConexao();
        return Optional.empty();
    }

    public List<Course> listar() throws SQLException {

        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_courses");

        ResultSet result = stm.executeQuery();
        List<Course> lista = new ArrayList<>();

        while (result.next()) {
            lista.add(parseCourse(result));
        }


        this.fecharConexao();
        return lista;
    }

    public List<Course> listarPorUsuario(Long userId) throws SQLException {

        PreparedStatement stm =
                conexao.prepareStatement("SELECT * FROM tb_courses WHERE cd_user = ?");

        stm.setLong(1, userId);

        ResultSet result = stm.executeQuery();
        List<Course> lista = new ArrayList<>();

        while (result.next()) {
            lista.add(parseCourse(result));
        }

        this.fecharConexao();
        return lista;
    }

    public void atualizar(Course course) throws SQLException {

        PreparedStatement stm =
                conexao.prepareStatement(
                        "UPDATE tb_courses " +
                                "SET cd_user = ?, name = ?, hours = ?, description = ? " +
                                "WHERE cd_courses = ?");

        stm.setLong(1, course.getUserId());
        stm.setString(2, course.getName());
        stm.setLong(3, course.getHours());
        stm.setString(4, course.getDescription());
        stm.setLong(5, course.getId());

        int linhas = stm.executeUpdate();

        if (linhas == 0) {
            throw new Error("Curso não encontrado para atualização");
        }

        this.fecharConexao();
    }

    public void remover(Long id) throws SQLException {

        PreparedStatement stm =
                conexao.prepareStatement("DELETE FROM tb_courses WHERE cd_courses = ?");

        stm.setLong(1, id);

        int linhas = stm.executeUpdate();

        if (linhas == 0) {
            throw new Error("Curso não encontrado para remoção");
        }

        this.fecharConexao();
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}
