package resource;

import dao.SoftSkillsDao;
import dto.softSkills.AtualizarSoftSkillInputDTO;
import dto.softSkills.CriarSoftSkillInputDTO;
import dto.softSkills.SoftSkillOutputDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import usecases.softSkills.CriarSoftSkillsUseCase;
import usecases.softSkills.ListarSoftSkillsUseCase;
import usecases.softSkills.AtualizarSoftSkillsUseCase;
import usecases.softSkills.DeletarSoftSkillsUseCase;

import java.sql.SQLException;
import java.util.List;

@Path("softskills")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SoftSkillsResource {

    private final CriarSoftSkillsUseCase criarSoftSkillUseCase;
    private final ListarSoftSkillsUseCase listarSoftSkillsUseCase;
    private final AtualizarSoftSkillsUseCase atualizarSoftSkillUseCase;
    private final DeletarSoftSkillsUseCase deletarSoftSkillUseCase;

    public SoftSkillsResource() throws SQLException {
        SoftSkillsDao dao = new SoftSkillsDao();
        this.criarSoftSkillUseCase = new CriarSoftSkillsUseCase(dao);
        this.listarSoftSkillsUseCase = new ListarSoftSkillsUseCase(dao);
        this.atualizarSoftSkillUseCase = new AtualizarSoftSkillsUseCase(dao);
        this.deletarSoftSkillUseCase = new DeletarSoftSkillsUseCase(dao);
    }

    // POST - Criar SoftSkill
    @POST
    @Path("/cadastrar")
    public Response cadastrar(CriarSoftSkillInputDTO input, @Context UriInfo uriInfo) throws SQLException {
        SoftSkillOutputDTO output = criarSoftSkillUseCase.execute(input);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(output.id());

        return Response.created(uri.build()).entity(output).build();
    }

    // GET - Listar todas
    @GET
    public Response listarTodos() throws SQLException {
        List<SoftSkillOutputDTO> lista = listarSoftSkillsUseCase.execute();
        return Response.ok(lista).build();
    }

    // PUT - Atualizar
    @PUT
    @Path("/{id}")
    public Response atualizar(
            @PathParam("id") String id,
            AtualizarSoftSkillInputDTO input
    ) throws SQLException {

        SoftSkillOutputDTO atualizado = atualizarSoftSkillUseCase.execute(id, input);
        return Response.ok(atualizado).build();
    }

    // DELETE - Deletar
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") String id) throws SQLException {
        deletarSoftSkillUseCase.execute(id);
        return Response.noContent().build();
    }
}
