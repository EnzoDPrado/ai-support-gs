package resource;

import dao.InvestidorDao;
import entities.Investidor;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.SQLException;


@Path("investidores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvestidorResource {

    private final InvestidorDao investidorDao;

    public InvestidorResource() throws SQLException {
        investidorDao = new InvestidorDao();
    }

    @POST
    public Response cadastrar(Investidor investidor, @Context UriInfo uriInfo) throws SQLException {
        investidorDao.cadastrar(investidor);
        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(String.valueOf(investidor.getId()));
        return Response.created(uri.build()).entity(investidor).build();
    }
}