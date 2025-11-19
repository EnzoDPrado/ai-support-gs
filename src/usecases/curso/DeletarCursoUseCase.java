package usecases.curso;

import dao.CourseDao;
import entities.Course;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class DeletarCursoUseCase {
    private final CourseDao courseDao;

    public DeletarCursoUseCase(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course execute(Long id) throws SQLException {
        final var course = this.getCourse(id);

        this.courseDao.remover(id);

        return course;
    }

    private Course getCourse(Long id) throws SQLException {
        return this.courseDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Curso não encontrado"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        ));
    }
}
