package usecases.roadmap;

import dao.RoadMapDao;
import dto.roadmap.AtualizarRoadMapInputDTO;
import entities.RoadMap;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class EditarRoadMapUseCase {
    private final RoadMapDao roadMapDao;

    public EditarRoadMapUseCase(RoadMapDao roadMapDao) {
        this.roadMapDao = roadMapDao;
    }

    public RoadMap execute(Long roadMapId, AtualizarRoadMapInputDTO input) throws SQLException {
        final var roadMap = this.validateRoadMap(roadMapId);

        this.merge(roadMap, input);

        this.roadMapDao.atualizar(roadMap);

        return roadMap;
    }

    private void merge(RoadMap roadMap, AtualizarRoadMapInputDTO input) {
        roadMap.setTitle(input.title());
        roadMap.setDescription(input.description());
    }

    private RoadMap validateRoadMap(Long id) throws SQLException {
        return this.roadMapDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Road map não encontrado"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        ));
    }
}
