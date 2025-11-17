package usecases.user;

import dao.UserDao;
import dto.user.CriarUsuarioInputDTO;
import entities.User;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class CriarUsuarioUseCase {
    private final UserDao userDao;

    public CriarUsuarioUseCase(UserDao userDao) {
        this.userDao = userDao;
    }

    public User execute(CriarUsuarioInputDTO input) throws SQLException {
        final var usuarioPorEmail = this.userDao.pesquisarPorEmail(input.email());

        usuarioPorEmail.ifPresent(user -> {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity(new ApiError("Email já cadastrado"))
                            .type(MediaType.APPLICATION_JSON)
                            .build()
            );
        });

        User user = input.toUser();
        this.userDao.cadastrar(user);

        return user;
    }
}
