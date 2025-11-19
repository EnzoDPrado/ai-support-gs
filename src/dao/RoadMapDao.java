package dao;

import entities.RoadMap;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoadMapDao {

    private RoadMap parseRoadMap(ResultSet result) throws SQLException {
        RoadMap roadMap = new RoadMap();
        roadMap.setId(result.getLong("cd_road_map"));
        roadMap.setUserId(result.getLong("cd_user"));
        roadMap.setDescription(result.getString("description"));
        return roadMap;
    }

    public void cadastrar(RoadMap roadMap) throws SQLException {

        String sql =
                "BEGIN " +
                        "   INSERT INTO tb_road_map (cd_road_map, cd_user, description) " +
                        "   VALUES (seq_tb_road_map.nextval, ?, ?) " +
                        "   RETURNING cd_road_map INTO ?; " +
                        "END;";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setLong(1, roadMap.getUserId());
            cs.setString(2, roadMap.getDescription());
            cs.registerOutParameter(3, Types.VARCHAR);

            cs.execute();

            roadMap.setId(cs.getLong(3));
        }
    }

    public Optional<RoadMap> pesquisar(Long id) throws SQLException {

        String sql = "SELECT * FROM tb_road_map WHERE cd_road_map = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet result = stm.executeQuery()) {
                if (result.next()) {
                    return Optional.of(parseRoadMap(result));
                }
            }
        }

        return Optional.empty();
    }

    public List<RoadMap> listar() throws SQLException {

        String sql = "SELECT * FROM tb_road_map";

        List<RoadMap> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {

            while (result.next()) {
                lista.add(parseRoadMap(result));
            }
        }

        return lista;
    }

    public List<RoadMap> listarPorUsuario(Long userId) throws SQLException {

        String sql = "SELECT * FROM tb_road_map WHERE cd_user = ?";

        List<RoadMap> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setLong(1, userId);

            try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                    lista.add(parseRoadMap(result));
                }
            }
        }

        return lista;
    }

    public void atualizar(RoadMap roadMap) throws SQLException {

        String sql =
                "UPDATE tb_road_map " +
                        "SET cd_user = ?, description = ? " +
                        "WHERE cd_road_map = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setLong(1, roadMap.getUserId());
            stm.setString(2, roadMap.getDescription());
            stm.setLong(3, roadMap.getId());

            int linhas = stm.executeUpdate();

            if (linhas == 0) {
                throw new Error("RoadMap não encontrado para atualização");
            }
        }
    }

    public void remover(Long id) throws SQLException {

        String sql = "DELETE FROM tb_road_map WHERE cd_road_map = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setLong(1, id);

            int linhas = stm.executeUpdate();

            if (linhas == 0) {
                throw new Error("RoadMap não encontrado para remoção");
            }
        }
    }
}
