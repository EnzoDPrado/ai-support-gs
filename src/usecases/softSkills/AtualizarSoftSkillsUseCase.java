package usecases.softSkills;

import dao.SoftSkillsDao;
import dto.softSkills.AtualizarSoftSkillInputDTO;
import dto.softSkills.SoftSkillOutputDTO;
import entities.SoftSkills;
import entities.SoftSkills;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class AtualizarSoftSkillsUseCase {

    private final SoftSkillsDao dao;

    public AtualizarSoftSkillsUseCase(SoftSkillsDao dao) {
        this.dao = dao;
    }

    public SoftSkillOutputDTO execute(String id, AtualizarSoftSkillInputDTO input) throws SQLException {

        SoftSkills existente = dao.findById(id)
                .orElseThrow(() -> new WebApplicationException(
                        Response.status(Response.Status.NOT_FOUND)
                                .entity(new ApiError("SoftSkill não encontrada"))
                                .type(MediaType.APPLICATION_JSON)
                                .build()
                ));

        SoftSkills atualizado = input.toSoftSkill(id, existente.getCdUser());

        dao.update(atualizado);
        dao.fecharConexao();

        return new SoftSkillOutputDTO(
                atualizado.getId(),
                atualizado.getCdUser(),
                atualizado.getName()
        );
    }
}
