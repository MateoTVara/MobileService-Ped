package pe.company.vo;

public class PedidoClienteInfoVo {
    private int idped; //Dato de la tabla pedidos
    private String documento; //Dato de la tabla pedidos
    private String razonsocial; //Dato de la tabla clientes
    private String rucdni; //Dato de la tabla clientes
    private String direccion; //Dato de la tabla clientes
    private String fchareparto; //Dato de la tabla pedidos
    private String nombre; //Dato de la tabla usuarios
    

    public PedidoClienteInfoVo() {}

    public PedidoClienteInfoVo(int idped, String documento, String razonsocial, String rucdni, String direccion, String fchareparto, String nombre) {
        this.idped = idped;
        this.documento = documento;
        this.razonsocial = razonsocial;
        this.rucdni = rucdni;
        this.direccion = direccion;
        this.fchareparto = fchareparto;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
