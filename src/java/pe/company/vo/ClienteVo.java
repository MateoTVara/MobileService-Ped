package pe.company.vo;

public class ClienteVo {
    private int idcli;
    private String razonsocial;
    private String rucdni;
    private String direccion;

    public ClienteVo() {}

    public ClienteVo(int idcli, String razonsocial, String rucdni, String direccion) {
        this.idcli = idcli;
        this.razonsocial = razonsocial;
        this.rucdni = rucdni;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "ClienteVo{" +
                "idcli=" + idcli +
                ", razonsocial='" + razonsocial + '\'' +
                ", rucdni='" + rucdni + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    // Getters and Setters

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
}
