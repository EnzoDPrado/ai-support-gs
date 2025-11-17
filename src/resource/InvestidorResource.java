package resource;

import dao.InvestidorDao;
import dto.investidor.CreateInvestidorInputDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import usecases.investidor.CriarInvestidorUseCase;

import java.sql.SQLException;


@Path("investidores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvestidorResource {

    private final CriarInvestidorUseCase criarInvestidorUseCase;

    public InvestidorResource() throws SQLException {
        criarInvestidorUseCase = new CriarInvestidorUseCase(new InvestidorDao());
    }

    @POST
    public Response cadastrar(CreateInvestidorInputDTO investidorInput, @Context UriInfo uriInfo) throws SQLException {
        final var investidor = investidorInput.toInvestidor();
        criarInvestidorUseCase.execute(investidor);

        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(investidor.getId()));
        return Response.created(uri.build()).entity(investidor).build();
    }
}