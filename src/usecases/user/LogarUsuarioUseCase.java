package usecases.user;

import dao.UserDao;
import dto.user.LogarUsuarioInputDTO;
import entities.User;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class LogarUsuarioUseCase {
    private final UserDao userDao;

    public LogarUsuarioUseCase(UserDao userDao) {
        this.userDao = userDao;
    }

    public User execute(LogarUsuarioInputDTO input) throws SQLException {
        String email = input.email();
        String senha = input.password();

        return this.getUser(email, senha);
    }

    private User getUser(String email, String senha) throws SQLException {
        return this.userDao.pesquisarPorEmailSenha(email, senha)
                .orElseThrow(() -> new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Credenciais Invalidas"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()));
    }
}
