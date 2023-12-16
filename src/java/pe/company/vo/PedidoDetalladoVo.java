/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.company.vo;

/**
 *
 * @author Mateo Torres
 */
public class PedidoDetalladoVo {
    
    private int idped;
    private String documento;
    private int idcli;
    private String razonsocial;
    private String rucdni;
    private String direccion;
    private String fchareparto;
    private int idusu;
    private String nombre;

    public PedidoDetalladoVo() {
    }

    public PedidoDetalladoVo(int idped, String documento, int idcli, String razonsocial, String rucdni, String direccion, String fchareparto, int idusu, String nombre) {
        this.idped = idped;
        this.documento = documento;
        this.idcli = idcli;
        this.razonsocial = razonsocial;
        this.rucdni = rucdni;
        this.direccion = direccion;
        this.fchareparto = fchareparto;
        this.idusu = idusu;
        this.nombre = nombre;
    }

    public int getIdped() {
        return idped;
    }

    public void setIdped(int idped) {
        this.idped = idped;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRucdni() {
        return rucdni;
    }

    public void setRucdni(String rucdni) {
        this.rucdni = rucdni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFchareparto() {
        return fchareparto;
    }

    public void setFchareparto(String fchareparto) {
        this.fchareparto = fchareparto;
    }

    public int getIdusu() {
        return idusu;
    }

    public void setIdusu(int idusu) {
        this.idusu = idusu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
