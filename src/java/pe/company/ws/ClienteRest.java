package pe.company.ws;

import pe.company.vo.ClienteVo;
import pe.company.dao.ClienteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("cliente")
public class ClienteRest {
    @Context
    private UriInfo context;

    // instancia al dao
    private ClienteDao clienteDao = new ClienteDao();

    public ClienteRest() {
    }

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(ClienteVo cliente) {
        clienteDao.insert(cliente);
        return Response.ok().entity(cliente).build();
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ClienteVo> listar() {
        return clienteDao.findAll();
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminar(@PathParam("id") int id) {
        clienteDao.delete(id);
        return Response.ok().build();
    }

    @Path("/modificar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(ClienteVo cliente) {
        clienteDao.update(cliente);
        return Response.ok().build();
    }

    @Path("/buscar/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ClienteVo buscarPorId(@PathParam("id") int id) {
        return clienteDao.findById(id);
    }
    
    @Path("/listarRazonesSociales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> listarRazonesSociales() {
        return clienteDao.getAllRazonesSociales();
    }
    
    @Path("/buscarPorRazonSocial/{razonSocial}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ClienteVo buscarPorRazonSocial(@PathParam("razonSocial") String razonSocial) {
        return clienteDao.getClienteByRazonSocial(razonSocial);
    }
    
    @Path("/buscarPorRazonSocialParcial/{partialRazonSocial}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ClienteVo> buscarPorRazonSocialParcial(@PathParam("partialRazonSocial") String partialRazonSocial) {
        return clienteDao.findByRazonSocial(partialRazonSocial);
    }

}
