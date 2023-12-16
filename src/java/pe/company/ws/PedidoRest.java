package pe.company.ws;

import java.util.Collection;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import pe.company.dao.ClienteDao;
import pe.company.vo.PedidoVo;
import pe.company.dao.PedidoDao;
import pe.company.dao.UsuarioDao;
import pe.company.vo.PedidoClienteInfoVo;
import pe.company.vo.PedidoDetalladoVo;

@Path("pedido")
public class PedidoRest {

    @Context
    private UriInfo context;

    private PedidoDao pedidoDao = new PedidoDao();

    public PedidoRest() {
    }

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(PedidoVo pedido) {
        pedidoDao.insert(pedido);
        return Response.ok().entity(pedido).build();
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PedidoVo> listar() {
        return pedidoDao.findAll();
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminar(@PathParam("id") int id) {
        pedidoDao.delete(id);
        return Response.ok().build();
    }

    @Path("/modificar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(PedidoVo pedido) {
        pedidoDao.update(pedido);
        return Response.ok().build();
    }

    @Path("/buscar/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PedidoVo buscarPorId(@PathParam("id") int id) {
        return pedidoDao.findById(id);
    }
    
    @Path("/buscarPorRazonSocial/{razonsocial}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PedidoVo> buscarPorRazonSocial(@PathParam("razonsocial") String razonsocial) {
        Collection<PedidoVo> pedidos = pedidoDao.findPedidosByRazonSocial(razonsocial);
        return pedidos;
    }
    
    @Path("/listarCustom")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PedidoClienteInfoVo> listarDetalles() {
        return pedidoDao.findAllWithClienteInfo();
    }
    
    @Path("/agregarParcial")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarParcial(PedidoVo pedido) {
        int idGenerado = pedidoDao.insertPartial(pedido);

        if (idGenerado != -1) {
            pedido.setIdped(idGenerado);
            return Response.ok().entity(pedido).build();
        } else {
            // Manejar el error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/buscarDetallado/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PedidoDetalladoVo buscarDetalladoPorId(@PathParam("id") int id) {
        return pedidoDao.findPedidoDetalladoById(id);
    }
    
    @Path("/modificarParcial/{idped}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarParcial(@PathParam("idped") int idped, PedidoVo pedido) {
        pedido.setIdped(idped);  // Asignar el idped desde la URL al objeto pedido
        pedidoDao.updatePartial(pedido);
        return Response.ok().build();
    }

    
}
