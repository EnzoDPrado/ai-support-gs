package resource;

import dao.CourseDao;
import dao.UserDao;
import dto.curso.AtualizarCursoInputDTO;
import dto.curso.CriarCursoInputDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import usecases.curso.AtualizarCursoUseCase;
import usecases.curso.CriarCursoUseCase;
import usecases.curso.DeletarCursoUseCase;
import usecases.curso.ListarCursosPorUsuarioUseCase;

import java.sql.SQLException;


@Path("cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    private final CriarCursoUseCase criarCursoUseCase;
    private final ListarCursosPorUsuarioUseCase listarCursosPorUsuarioUseCase;
    private final AtualizarCursoUseCase atualizarCursoUseCase;
    private final DeletarCursoUseCase deletarCursoUseCase;

    public CourseResource() throws SQLException {
        criarCursoUseCase = new CriarCursoUseCase(new CourseDao(), new UserDao());
        listarCursosPorUsuarioUseCase = new ListarCursosPorUsuarioUseCase(new CourseDao(), new UserDao());
        atualizarCursoUseCase = new AtualizarCursoUseCase(new CourseDao());
        deletarCursoUseCase = new DeletarCursoUseCase(new CourseDao());
    }

    @POST
    @Path("/cadastrar")
    public Response cadastrar(CriarCursoInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        final var course = criarCursoUseCase.execute(input);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(course.getId()));
        return Response.created(uri.build()).entity(course).build();
    }

    @GET
    @Path("/usuarios/{id}")
    public Response listarPorUsuario(@PathParam("id") Long id, @Context UriInfo uriInfo) throws SQLException {
        UriBuilder uri = uriInfo.getAbsolutePathBuilder();

        final var courses = this.listarCursosPorUsuarioUseCase.execute(id);

        return Response.ok(uri.build()).entity(courses).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, AtualizarCursoInputDTO input) throws SQLException {
        this.atualizarCursoUseCase.execute(input, id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException {
        this.deletarCursoUseCase.execute(id);
        return Response.noContent().build();
    }
}