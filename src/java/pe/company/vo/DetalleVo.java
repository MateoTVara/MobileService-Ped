package pe.company.vo;

public class DetalleVo {
    private int iddetalle;
    private int idped;
    private int idproduc;
    private int cantidad;
    private double importe;

    public DetalleVo() {}

    public DetalleVo(int iddetalle, int idped, int idproduc, int cantidad, double importe) {
        this.iddetalle = iddetalle;
        this.idped = idped;
        this.idproduc = idproduc;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "DetalleVo{" +
                "iddetalle=" + iddetalle +
                ", idped=" + idped +
                ", idproduc=" + idproduc +
                ", cantidad=" + cantidad +
                ", importe=" + importe +
                '}';
    }

    // Getters and Setters

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIdped() {
        return idped;
    }

    public void setIdped(int idped) {
        this.idped = idped;
    }

    public int getIdproduc() {
        return idproduc;
    }

    public void setIdproduc(int idproduc) {
        this.idproduc = idproduc;
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
