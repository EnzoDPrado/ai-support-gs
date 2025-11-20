package usecases.user;

import dao.UserDao;
import entities.User;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class DeletarUsuarioPorIdUseCase {
    private final UserDao userDao;

    public DeletarUsuarioPorIdUseCase(UserDao userDao) {
        this.userDao = userDao;
    }

    public void execute(Long id) throws SQLException {
        User user = this.userDao.pesquisar(id).orElseThrow(() ->
                new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Usuário não encontrado!"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()));


        userDao.remover(id);
        userDao.fecharConexao();
    }
}
