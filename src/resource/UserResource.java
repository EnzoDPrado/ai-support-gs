package resource;

import dao.UserDao;
import dto.user.AtualizarUsuarioInputDTO;
import dto.user.CriarUsuarioInputDTO;
import dto.user.LogarUsuarioInputDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import usecases.user.AtualizarUsuarioUseCase;
import usecases.user.CriarUsuarioUseCase;
import usecases.user.ListarUsuarioPorIdUseCase;
import usecases.user.LogarUsuarioUseCase;

import java.sql.SQLException;


@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final LogarUsuarioUseCase logarUsuarioUseCase;
    private final ListarUsuarioPorIdUseCase listarUsuarioPorIdUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public UserResource() throws SQLException {
        criarUsuarioUseCase = new CriarUsuarioUseCase(new UserDao());
        logarUsuarioUseCase = new LogarUsuarioUseCase(new UserDao());
        listarUsuarioPorIdUseCase = new ListarUsuarioPorIdUseCase(new UserDao());
        atualizarUsuarioUseCase = new AtualizarUsuarioUseCase(new UserDao());
    }

    @POST
    @Path("/cadastrar")
    public Response cadastrar(CriarUsuarioInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        final var user = criarUsuarioUseCase.execute(input);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(user.getId()));
        return Response.created(uri.build()).entity(user).build();
    }

    @POST
    @Path("/logar")
    public Response logar(LogarUsuarioInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        final var user = logarUsuarioUseCase.execute(input);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(user.getId()));
        return Response.created(uri.build()).entity(user).build();
    }

    @GET
    @Path("/{id}")
    public Response listarPorId(@PathParam("id") Long id, @Context UriInfo uriInfo) throws SQLException {
        final var user = this.listarUsuarioPorIdUseCase.execute(id);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(user.getId()));
        return Response.created(uri.build()).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, AtualizarUsuarioInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        this.atualizarUsuarioUseCase.execute(input, id);
    }
}