package pe.company.ws;

import pe.company.vo.DetalleVo;
import pe.company.dao.DetalleDao;

import java.util.Collection;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pe.company.vo.DetalleProductoInfoVo;

@Path("detalle")
public class DetalleRest {
    @Context
    private javax.ws.rs.core.UriInfo context;

    // instancia al dao
    private DetalleDao detalleDao = new DetalleDao();

    public DetalleRest() {
    }

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(DetalleVo detalle) {
        detalleDao.insert(detalle);
        return Response.ok().entity(detalle).build();
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DetalleVo> listar() {
        return detalleDao.findAll();
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminar(@PathParam("id") int id) {
        detalleDao.delete(id);
        return Response.ok().build();
    }

    @Path("/modificar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(DetalleVo detalle) {
        detalleDao.update(detalle);
        return Response.ok().build();
    }

    @Path("/buscar/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DetalleVo buscarPorId(@PathParam("id") int id) {
        return detalleDao.findById(id);
    }
    
    @Path("/buscarPorPedido/{idped}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DetalleProductoInfoVo> buscarPorPedido(@PathParam("idped") int idped) {
        return detalleDao.findByPedidoId(idped);
    }
    
    @Path("/listarNoAsignados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DetalleProductoInfoVo> listarNoAsignados() {
        return detalleDao.findUnassignedDetails();
    }
    
    @Path("/eliminarNoAsignados")
    @DELETE
    public Response eliminarNoAsignados() {
        detalleDao.deleteUnassignedDetails();
        return Response.ok().build();
    }
    
    @Path("/asignarIdped/{idped}")
    @POST
    public Response asignarIdped(@PathParam("idped") int idped) {
        detalleDao.assignIdpedToUnassignedDetails(idped);
        return Response.ok().build();
    }

    @Path("/registrarDetalleParcial")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarDetalleParcial(DetalleVo detalle) {
        detalleDao.insertDetalleParcial(detalle);
        return Response.ok().build();
    }


}
