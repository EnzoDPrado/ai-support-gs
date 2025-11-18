package resource;

import dao.CourseDao;
import dao.UserDao;
import dto.curso.CriarCursoInputDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import usecases.curso.CriarCursoUseCase;
import usecases.curso.ListarCursosPorUsuarioUseCase;

import java.sql.SQLException;


@Path("cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    private final CriarCursoUseCase criarCursoUseCase;
    private final ListarCursosPorUsuarioUseCase listarCursosPorUsuarioUseCase;

    public CourseResource() throws SQLException {
        criarCursoUseCase = new CriarCursoUseCase(new CourseDao(), new UserDao());
        listarCursosPorUsuarioUseCase = new ListarCursosPorUsuarioUseCase(new CourseDao(), new UserDao());
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
}