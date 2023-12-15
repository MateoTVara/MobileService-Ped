package pe.company.vo;

public class PedidoVo {
    private int idped;
    private String documento;
    private String fchareparto;
    private int idusu;
    private int idcli;  
    private double total;

    public PedidoVo() {}

    public PedidoVo(int idped, String documento, String fchareparto, int idusu, int idcli, double total) {
        this.idped = idped;
        this.documento = documento;
        this.fchareparto = fchareparto;
        this.idusu = idusu;
        this.idcli = idcli;
        this.total = total;
    }

    @Override
    public String toString() {
        return "PedidoVo{" +
                "idped=" + idped +
                ", documento='" + documento + '\'' +
                ", fchareparto='" + fchareparto + '\'' +
                ", idusu=" + idusu +
                ", idcli=" + idcli +
                ", total=" + total +
                '}';
    }
    
    

    // Getters and Setters

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

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
