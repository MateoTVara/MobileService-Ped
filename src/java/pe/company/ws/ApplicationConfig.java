/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.company.ws;

import java.util.Set;

/**
 *
 * @author Mateo Torres
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(pe.company.ws.ClienteRest.class);
        resources.add(pe.company.ws.DetalleRest.class);
        resources.add(pe.company.ws.PedidoRest.class);
        resources.add(pe.company.ws.ProductoRest.class);
        resources.add(pe.company.ws.UsuarioRest.class);
    }
    
}
