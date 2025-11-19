package usecases.roadmap;

import dao.RoadMapDao;
import dao.UserDao;
import entities.RoadMap;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

public class ListarRoadMapsPorUsuarioUseCase {
    private final UserDao userDao;
    private final RoadMapDao roadMapDao;

    public ListarRoadMapsPorUsuarioUseCase(RoadMapDao roadMapDao, UserDao userDao) {
        this.roadMapDao = roadMapDao;
        this.userDao = userDao;
    }

    public List<RoadMap> execute(Long userId) throws SQLException {
        this.validateUser(userId);

        return this.roadMapDao.listarPorUsuario(userId);
    }

    private void validateUser(Long id) throws SQLException {
        this.userDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Usuário não encontrado"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        ));
    }
}
