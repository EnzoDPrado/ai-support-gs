package usecases.curso;

import dao.CourseDao;
import dao.UserDao;
import dto.curso.AtualizarCursoInputDTO;
import entities.Course;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class AtualizarCursoUseCase {
    private final CourseDao courseDao;

    public AtualizarCursoUseCase(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course execute(AtualizarCursoInputDTO input, Long id) throws SQLException {
        final var course = this.getCourse(id);
        this.merge(input, course);

        this.courseDao.atualizar(course);

        return course;
    }

    private void merge(AtualizarCursoInputDTO input, Course course) {
        course.setName(input.name());
        course.setHours(input.hours());
        course.setDescription(input.description());
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
