package usecases.roadmap;

import dao.RoadMapDao;
import entities.RoadMap;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class ListarRoadMapPorIdUseCase {
    private final RoadMapDao roadMapDao;

    public ListarRoadMapPorIdUseCase(RoadMapDao roadMapDao) {
        this.roadMapDao = roadMapDao;
    }

    public RoadMap execute(Long roadMapId) throws SQLException {
        return this.getRoadMap(roadMapId);
    }

    private RoadMap getRoadMap(Long id) throws SQLException {
        return this.roadMapDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Usuário não encontrado"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        ));
    }
}
