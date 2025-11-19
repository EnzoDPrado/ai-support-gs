package usecases.curso;

import dao.CourseDao;
import dao.UserDao;
import dto.curso.CriarCursoInputDTO;
import entities.Course;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class CriarCursoUseCase {
    private final CourseDao courseDao;
    private final UserDao userDao;

    public CriarCursoUseCase(CourseDao courseDao, UserDao userDao) {
        this.courseDao = courseDao;
        this.userDao = userDao;
    }

    public Course execute(CriarCursoInputDTO input) throws SQLException {
        this.validateUser(input.userId());

        final var course = input.toEntity();

        this.courseDao.cadastrar(course);

        return course;
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
