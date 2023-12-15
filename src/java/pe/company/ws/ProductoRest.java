package pe.company.ws;

import java.util.Collection;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import pe.company.dao.ProductoDao;
import pe.company.vo.ProductoVo;

@Path("producto")
public class ProductoRest {

    @Context
    private UriInfo context;

    // instancia al dao
    private ProductoDao productoDao = new ProductoDao();

    public ProductoRest() {
    }

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(ProductoVo producto) {
        productoDao.insert(producto);
        return Response.ok().entity(producto).build();
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductoVo> listar() {
        return productoDao.findAll();
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminar(@PathParam("id") int id) {
        productoDao.delete(id);
        return Response.ok().build();
    }

    @Path("/modificar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(ProductoVo producto) {
        productoDao.update(producto);
        return Response.ok().build();
    }

    @Path("/buscar/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductoVo buscarPorId(@PathParam("id") int id) {
        return productoDao.findById(id);
    }
    
    @Path("/buscarPorDescripcionParcial/{partialDescripcion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductoVo> buscarPorDescripcionParcial(@PathParam("partialDescripcion") String partialDescripcion) {
        return productoDao.findByDescripcion(partialDescripcion);
    }
}