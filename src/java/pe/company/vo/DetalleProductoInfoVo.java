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
public class DetalleProductoInfoVo {
    
    private int idped; //Dato de la tabla pedidos
    private int iddetalle; //Dato de la tabla detalles_pedido
    private String desproduc;//Dato de la tabla productos
    private String uniproduc;//Dato de la tabla productos
    private int cantidad;//Dato de la tabla detalles_pedido
    private double importe;//Dato de la tabla detalles_pedido

    public DetalleProductoInfoVo() {
    }

    public DetalleProductoInfoVo(int idped, int iddetalle, String desproduc, String uniproduc, int cantidad, double importe) {
        this.idped = idped;
        this.iddetalle = iddetalle;
        this.desproduc = desproduc;
        this.uniproduc = uniproduc;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public int getIdped() {
        return idped;
    }

    public void setIdped(int idped) {
        this.idped = idped;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public String getDesproduc() {
        return desproduc;
    }

    public void setDesproduc(String desproduc) {
        this.desproduc = desproduc;
    }

    public String getUniproduc() {
        return uniproduc;
    }

    public void setUniproduc(String uniproduc) {
        this.uniproduc = uniproduc;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    
    
}
