package pe.company.dao;

import pe.company.dbase.ConexionDb;
import pe.company.vo.ClienteVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClienteDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ClienteDao() {
    }

    public void insert(ClienteVo cliente) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO clientes(razonsocial, rucdni, direccion) VALUES (?, ?, ?)");

            ps.setString(1, cliente.getRazonsocial());
            ps.setString(2, cliente.getRucdni());
            ps.setString(3, cliente.getDireccion());

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Collection<ClienteVo> findAll() {
        List<ClienteVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();

            ps = conn.prepareStatement("SELECT * FROM clientes");
            rs = ps.executeQuery();

            while (rs.next()) {
                ClienteVo cliente = new ClienteVo();

                cliente.setIdcli(rs.getInt("idcli"));
                cliente.setRazonsocial(rs.getString("razonsocial"));
                cliente.setRucdni(rs.getString("rucdni"));
                cliente.setDireccion(rs.getString("direccion"));

                list.add(cliente);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void delete(int id) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("DELETE FROM clientes WHERE idcli = ?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error deleting cliente with id " + id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(ClienteVo cliente) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("UPDATE clientes SET razonsocial = ?, rucdni = ?, direccion = ? WHERE idcli = ?");

            ps.setString(1, cliente.getRazonsocial());
            ps.setString(2, cliente.getRucdni());
            ps.setString(3, cliente.getDireccion());
            ps.setInt(4, cliente.getIdcli());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error updating cliente with id " + cliente.getIdcli());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ClienteVo findById(int id) {
        ClienteVo cliente = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM clientes WHERE idcli = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new ClienteVo();
                cliente.setIdcli(rs.getInt("idcli"));
                cliente.setRazonsocial(rs.getString("razonsocial"));
                cliente.setRucdni(rs.getString("rucdni"));
                cliente.setDireccion(rs.getString("direccion"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }
    
    public List<String> getAllRazonesSociales() {
        List<String> razonesSociales = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();

            ps = conn.prepareStatement("SELECT razonsocial FROM clientes");
            rs = ps.executeQuery();

            while (rs.next()) {
                razonesSociales.add(rs.getString("razonsocial"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return razonesSociales;
    }
    
    public ClienteVo getClienteByRazonSocial(String razonSocial) {
        ClienteVo cliente = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM clientes WHERE razonsocial = ?");
            ps.setString(1, razonSocial);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new ClienteVo();
                cliente.setIdcli(rs.getInt("idcli"));
                cliente.setRazonsocial(rs.getString("razonsocial"));
                cliente.setRucdni(rs.getString("rucdni"));
                cliente.setDireccion(rs.getString("direccion"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }
    
    public Collection<ClienteVo> findByRazonSocial(String partialRazonSocial) {
        List<ClienteVo> resultList = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM clientes WHERE razonsocial LIKE ?");
            ps.setString(1, "%" + partialRazonSocial + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ClienteVo cliente = new ClienteVo();
                cliente.setIdcli(rs.getInt("idcli"));
                cliente.setRazonsocial(rs.getString("razonsocial"));
                cliente.setRucdni(rs.getString("rucdni"));
                cliente.setDireccion(rs.getString("direccion"));

                resultList.add(cliente);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultList;
    }

    
}
