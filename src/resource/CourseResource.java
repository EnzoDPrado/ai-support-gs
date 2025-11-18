package resource;

import dao.CourseDao;
import dao.UserDao;
import dto.curso.CriarCursoInputDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import usecases.curso.CriarCursoUseCase;

import java.sql.SQLException;


@Path("cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    private final CriarCursoUseCase criarCursoUseCase;

    public CourseResource() throws SQLException {
        criarCursoUseCase = new CriarCursoUseCase(new CourseDao(), new UserDao());
    }

    @POST
    @Path("/cadastrar")
    public Response cadastrar(CriarCursoInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        final var course = criarCursoUseCase.execute(input);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(course.getId()));
        return Response.created(uri.build()).entity(course).build();
    }

//    @PUT
//    @Path("/{id}")
//    public void atualizar(@PathParam("id") Long id, AtualizarUsuarioInputDTO input, @Context UriInfo uriInfo) throws SQLException {
//        this.atualizarUsuarioUseCase.execute(input, id);
//    }
}