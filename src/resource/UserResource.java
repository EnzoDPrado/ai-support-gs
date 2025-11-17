package resource;

import dao.UserDao;
import dto.user.CriarUsuarioInputDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import usecases.user.CriarUsuarioUseCase;

import java.sql.SQLException;


@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    public UserResource() throws SQLException {
        criarUsuarioUseCase = new CriarUsuarioUseCase(new UserDao());
    }

    @POST
    @Path("/cadastrar")
    public Response cadastrar(CriarUsuarioInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        final var user = criarUsuarioUseCase.execute(input);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(user.getId()));
        return Response.created(uri.build()).entity(user).build();
    }
}