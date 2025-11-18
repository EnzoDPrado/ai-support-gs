package usecases.user;

import dao.UserDao;
import dto.user.AtualizarUsuarioInputDTO;
import entities.User;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Optional;

public class AtualizarUsuarioUseCase {
    private final UserDao userDao;

    public AtualizarUsuarioUseCase(UserDao userDao) {
        this.userDao = userDao;
    }

    public void execute(AtualizarUsuarioInputDTO input, Long id) throws SQLException {
        final var userByEmail = this.userDao.pesquisarPorEmail(input.email());
        final var userById = listarUsuarioPorId(id);

        this.validate(userByEmail, userById);
        final var updatedUser = input.toUser(id);

        this.userDao.atualizar(updatedUser);
        this.userDao.fecharConexao();
    }

    private User listarUsuarioPorId(Long id) throws SQLException {
        return this.userDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApiError("Usuário não encontrado!"))
                .type(MediaType.APPLICATION_JSON)
                .build()));

    }

    private void validate(Optional<User> userByEmail, User userById) {
        if (userByEmail.isEmpty())
            return;

        final var user = userByEmail.get();

        if (!user.getId().equals(userById.getId()))
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiError("Email já existente"))
                    .type(MediaType.APPLICATION_JSON)
                    .build());
    }

    private User getUser(String email, String senha) throws SQLException {
        return this.userDao.pesquisarPorEmailSenha(email, senha)
                .orElseThrow(() -> new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Credenciais Invalidas"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()));
    }
}
