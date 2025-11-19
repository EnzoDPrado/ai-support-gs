package usecases.roadmap;

import dao.RoadMapDao;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class DeletarRoadMapPorIdUseCase {
    private final RoadMapDao roadMapDao;

    public DeletarRoadMapPorIdUseCase(RoadMapDao roadMapDao) {
        this.roadMapDao = roadMapDao;
    }

    public void execute(Long roadMapId) throws SQLException {
        this.validateRoadMap(roadMapId);

        roadMapDao.remover(roadMapId);
    }

    private void validateRoadMap(Long id) throws SQLException {
        this.roadMapDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Usuário não encontrado"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        ));
    }
}
