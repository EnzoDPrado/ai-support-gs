package usecases.softSkills;

import dao.SoftSkillsDao;
import entities.SoftSkills;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class DeletarSoftSkillsUseCase {

    private final SoftSkillsDao dao;

    public DeletarSoftSkillsUseCase(SoftSkillsDao dao) {
        this.dao = dao;
    }

    public void execute(String id) throws SQLException {
        var existente = dao.findById(id);
        if (existente.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity(new ApiError("SoftSkill não encontrada"))
                            .type(MediaType.APPLICATION_JSON)
                            .build()
            );
        }

        dao.delete(id);
        dao.fecharConexao();
    }
}
