package pe.company.vo;

public class ProductoVo {
    private int idproduc;
    private String desproduc;
    private String uniproduc;
    private double precio;

    public ProductoVo() {}

    public ProductoVo(int idproduc, String desproduc, String uniproduc, double precio) {
        this.idproduc = idproduc;
        this.desproduc = desproduc;
        this.uniproduc = uniproduc;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ProductoVo{" +
                "idproduc=" + idproduc +
                ", desproduc='" + desproduc + '\'' +
                ", uniproduc='" + uniproduc + '\'' +
                ", precio=" + precio +
                '}';
    }

    // Getters and Setters

    public int getIdproduc() {
        return idproduc;
    }

    public void setIdproduc(int idproduc) {
        this.idproduc = idproduc;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
