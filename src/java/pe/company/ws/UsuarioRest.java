/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.company.ws;

import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pe.company.dao.UsuarioDao;
import pe.company.vo.UsuarioVo;

/**
 * REST Web Service
 *
 * @author jc_va
 */
@Path("usuarios")
public class UsuarioRest {

    @Context
    private UriInfo context;

    private UsuarioDao usuarioDao=new UsuarioDao();
    
    /**
     * Creates a new instance of AutosRest
     */
    public UsuarioRest() {
    }

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(UsuarioVo usuarios) 
    {
        usuarioDao.insert(usuarios);
        return Response.ok().entity(usuarios).build();
    }
    
    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UsuarioVo> listar() {
        return usuarioDao.findAll();
    }
    
    @Path("/buscar/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioVo buscarPorId(@PathParam("id") int id) {
        return usuarioDao.findById(id);
    }

    @Path("/actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(UsuarioVo usuario) {
        usuarioDao.update(usuario);
        return Response.ok().entity(usuario).build();
    }

    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") int id) {
        usuarioDao.delete(id);
        return Response.ok().build();
    }
    
    @Path("/buscarPorNombreParcial/{partialNombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UsuarioVo> buscarPorNombreParcial(@PathParam("partialNombre") String partialNombre) {
        return usuarioDao.findByNombre(partialNombre);
    }
    
    @Path("/autenticar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(@QueryParam("usuario") String usuario, @QueryParam("contrasenia") String contrasenia) {
        UsuarioVo authenticatedUser = usuarioDao.authenticate(usuario, contrasenia);

        if (authenticatedUser != null) {
            return Response.ok().entity(authenticatedUser).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
    
   
