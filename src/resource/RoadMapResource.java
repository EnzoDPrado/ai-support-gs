package resource;

import dao.RoadMapDao;
import dao.UserDao;
import dto.roadmap.AtualizarRoadMapInputDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import usecases.roadmap.*;

import java.sql.SQLException;


@Path("road-maps")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoadMapResource {

    private final CriarRoadMapUseCase criarRoadMapUseCase;
    private final ListarRoadMapPorIdUseCase listarRoadMapPorIdUseCase;
    private final ListarRoadMapsPorUsuarioUseCase listarRoadMapsPorUsuarioUseCase;
    private final DeletarRoadMapPorIdUseCase deletarRoadMapPorIdUseCase;
    private final EditarRoadMapUseCase editarRoadMapUseCase;


    public RoadMapResource() throws SQLException {
        criarRoadMapUseCase = new CriarRoadMapUseCase(new RoadMapDao(), new UserDao());
        listarRoadMapPorIdUseCase = new ListarRoadMapPorIdUseCase(new RoadMapDao());
        listarRoadMapsPorUsuarioUseCase = new ListarRoadMapsPorUsuarioUseCase(new RoadMapDao(), new UserDao());
        deletarRoadMapPorIdUseCase = new DeletarRoadMapPorIdUseCase(new RoadMapDao());
        editarRoadMapUseCase = new EditarRoadMapUseCase(new RoadMapDao());
    }

    @POST
    @Path("/usuarios/{id}")
    public Response cadastrar(@PathParam("id") Long id, @Context UriInfo uriInfo) throws SQLException {
        final var roadMap = criarRoadMapUseCase.execute(id);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(roadMap.getId()));
        return Response.created(uri.build()).entity(roadMap).build();
    }

    @GET
    @Path("/usuarios/{id}")
    public Response listarPorUsuario(@PathParam("id") Long id, @Context UriInfo uriInfo) throws SQLException {
        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        final var roadMaps = this.listarRoadMapsPorUsuarioUseCase.execute(id);

        return Response.ok(uri.build()).entity(roadMaps).build();
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@PathParam("id") Long id, AtualizarRoadMapInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        final var roadMaps = this.editarRoadMapUseCase.execute(id, input);

        return Response.ok(uri.build()).entity(roadMaps).build();
    }

    @GET
    @Path("/{id}")
    public Response listarPorId(@PathParam("id") Long id, @Context UriInfo uriInfo) throws SQLException {
        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        final var roadMap = this.listarRoadMapPorIdUseCase.execute(id);

        return Response.ok(uri.build()).entity(roadMap).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) throws SQLException {
        this.deletarRoadMapPorIdUseCase.execute(id);
        return Response.noContent().build();
    }
}