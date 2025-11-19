package usecases.softSkills;

import dao.SoftSkillsDao;
import dto.softSkills.SoftSkillOutputDTO;
import entities.SoftSkills;
import java.sql.SQLException;
import java.util.List;

public class ListarSoftSkillsUseCase {

    private final SoftSkillsDao dao;

    public ListarSoftSkillsUseCase(SoftSkillsDao dao) {
        this.dao = dao;
    }

    public List<SoftSkillOutputDTO> execute() throws SQLException {
        List<SoftSkills> list = dao.findAll();
        dao.fecharConexao();

        return list.stream()
                .map(s -> new SoftSkillOutputDTO(
                        s.getId(),
                        s.getCdUser(),
                        s.getName()
                ))
                .toList();
    }
}
