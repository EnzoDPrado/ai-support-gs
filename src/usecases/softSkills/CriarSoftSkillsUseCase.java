package usecases.softSkills;

import dao.SoftSkillsDao;
import dao.UserDao;
import dto.softSkills.CriarSoftSkillInputDTO;
import dto.softSkills.SoftSkillOutputDTO;
import entities.SoftSkills;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class CriarSoftSkillsUseCase {
    private final SoftSkillsDao dao;

    public CriarSoftSkillsUseCase(SoftSkillsDao dao) {
        this.dao = dao;
    }

    public SoftSkillOutputDTO execute(CriarSoftSkillInputDTO input) throws SQLException {


        Long userId = Long.valueOf(input.cdUser());


        UserDao userDao = new UserDao();
        try {
            var u = userDao.pesquisar(userId);
            if (u.isEmpty()) {
                throw new WebApplicationException(
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(new ApiError("Usuário não encontrado"))
                                .type(MediaType.APPLICATION_JSON)
                                .build()
                );
            }
        } finally {
            userDao.fecharConexao();
        }

        SoftSkills soft = input.toSoftSkill();
        dao.create(soft);
        dao.fecharConexao();

        return new SoftSkillOutputDTO(
                soft.getId(),
                soft.getCdUser(),
                soft.getName()
        );
    }
}
