/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.company.vo;

/**
 *
 * @author jc_va
 */
public class UsuarioVo {
    private int idusu;
    private String usuario;
    private String contrasenia;
    private String nombre;
  
    

    public UsuarioVo() {
    }

    public UsuarioVo(int idusu, String usuario, String contrasenia, String nombre) {
        this.idusu = idusu;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
    }

    
    public int getIdusu() {
        return idusu;
    }

    public void setIdusu(int idusu) {
        this.idusu = idusu;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    

    
    
}
