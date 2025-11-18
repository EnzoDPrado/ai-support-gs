package usecases.user;

import dao.UserDao;
import entities.User;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class ListarUsuarioPorIdUseCase {
    private final UserDao userDao;

    public ListarUsuarioPorIdUseCase(UserDao userDao) {
        this.userDao = userDao;
    }

    public User execute(Long id) throws SQLException {
        User user = this.userDao.pesquisar(id).orElseThrow(() ->
                new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Credenciais Invalidas"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()));

        userDao.fecharConexao();
        return user;
    }
}
