package usecases.curso;

import dao.CourseDao;
import dao.UserDao;
import entities.Course;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

public class ListarCursosPorUsuarioUseCase {
    private final CourseDao courseDao;
    private final UserDao userDao;

    public ListarCursosPorUsuarioUseCase(CourseDao courseDao, UserDao userDao) {
        this.courseDao = courseDao;
        this.userDao = userDao;
    }

    public List<Course> execute(Long userId) throws SQLException {
        this.validateUser(userId);

        return this.courseDao.listarPorUsuario(userId);
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
