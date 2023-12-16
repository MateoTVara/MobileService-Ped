package pe.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pe.company.dbase.ConexionDb;
import pe.company.vo.ClienteVo;
import pe.company.vo.PedidoClienteInfoVo;
import pe.company.vo.PedidoDetalladoVo;
import pe.company.vo.PedidoVo;
import pe.company.vo.UsuarioVo;

public class PedidoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PedidoDao() {
    }

    public int insert(PedidoVo pedido) {
        int generatedId = -1; // Valor predeterminado para indicar error
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO pedidos(documento, fchareparto, idusu, idcli, total) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pedido.getDocumento());
            ps.setString(2, pedido.getFchareparto());
            ps.setInt(3, pedido.getIdusu());
            ps.setInt(4, pedido.getIdcli());
            ps.setDouble(5, pedido.getTotal());

            int rows = ps.executeUpdate();
            if (rows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Obtener el ID generado
                }
            } else {
                throw new Exception("Error al insertar el pedido");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return generatedId;
    }

    public Collection<PedidoVo> findAll() {
        List<PedidoVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();

            ps = conn.prepareStatement("SELECT * FROM pedidos");
            rs = ps.executeQuery();

            while (rs.next()) {
                PedidoVo pedido = new PedidoVo();

                pedido.setIdped(rs.getInt("idped"));
                pedido.setDocumento(rs.getString("documento"));
                pedido.setFchareparto(rs.getString("fchareparto"));
                pedido.setIdusu(rs.getInt("idusu"));
                pedido.setIdcli(rs.getInt("idcli"));
                pedido.setTotal(rs.getDouble("total"));

                list.add(pedido);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void delete(int id) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("DELETE FROM pedidos WHERE idped = ?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error deleting pedido with id " + id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(PedidoVo pedido) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("UPDATE pedidos SET documento = ?, fchareparto = ?, idusu = ?, idcli = ?, total = ? WHERE idped = ?");

            ps.setString(1, pedido.getDocumento());
            ps.setString(2, pedido.getFchareparto());
            ps.setInt(3, pedido.getIdusu());
            ps.setInt(4, pedido.getIdcli());
            ps.setDouble(5, pedido.getTotal());
            ps.setInt(6, pedido.getIdped());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error updating pedido with id " + pedido.getIdped());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public PedidoVo findById(int id) {
        PedidoVo pedido = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM pedidos WHERE idped = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new PedidoVo();
                pedido.setIdped(rs.getInt("idped"));
                pedido.setDocumento(rs.getString("documento"));
                pedido.setFchareparto(rs.getString("fchareparto"));
                pedido.setIdusu(rs.getInt("idusu"));
                pedido.setIdcli(rs.getInt("idcli"));
                pedido.setTotal(rs.getDouble("total"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pedido;
    }
    
    public Collection<PedidoVo> findPedidosByRazonSocial(String razonsocial) {
        List<PedidoVo> pedidos = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT p.* FROM pedidos p JOIN clientes c ON p.idcli = c.idcli WHERE c.razonsocial LIKE ?");
            ps.setString(1, "%" + razonsocial + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                PedidoVo pedido = new PedidoVo();
                pedido.setIdped(rs.getInt("idped"));
                pedido.setDocumento(rs.getString("documento"));
                pedido.setFchareparto(rs.getString("fchareparto"));
                pedido.setIdusu(rs.getInt("idusu"));
                pedido.setIdcli(rs.getInt("idcli"));
                pedido.setTotal(rs.getDouble("total"));

                pedidos.add(pedido);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pedidos;
    }  
    
    public Collection<PedidoClienteInfoVo> findAllWithClienteInfo() {
        List<PedidoClienteInfoVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();

            ps = conn.prepareStatement("SELECT p.idped, p.documento, p.fchareparto, c.razonsocial, c.rucdni, c.direccion, u.nombre " +
                                        "FROM pedidos p " +
                                        "JOIN clientes c ON p.idcli = c.idcli " +
                                        "JOIN usuarios u ON p.idusu = u.idusu");
            rs = ps.executeQuery();

            while (rs.next()) {
                PedidoClienteInfoVo pedidoClienteInfo = new PedidoClienteInfoVo();

                pedidoClienteInfo.setIdped(rs.getInt("idped"));
                pedidoClienteInfo.setDocumento(rs.getString("documento"));
                pedidoClienteInfo.setFchareparto(rs.getString("fchareparto"));
                pedidoClienteInfo.setRazonsocial(rs.getString("razonsocial"));
                pedidoClienteInfo.setRucdni(rs.getString("rucdni"));
                pedidoClienteInfo.setDireccion(rs.getString("direccion"));
                pedidoClienteInfo.setNombre(rs.getString("nombre"));

                list.add(pedidoClienteInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    public int insertPartial(PedidoVo pedido) {
        int generatedId = -1; // Valor predeterminado para indicar error
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO pedidos(documento, fchareparto, idusu, idcli) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pedido.getDocumento());
            ps.setString(2, pedido.getFchareparto());
            ps.setInt(3, pedido.getIdusu());
            ps.setInt(4, pedido.getIdcli());

            int rows = ps.executeUpdate();
            if (rows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Obtener el ID generado
                }
            } else {
                throw new Exception("Error al insertar el pedido");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return generatedId;
    }

    public PedidoDetalladoVo findPedidoDetalladoById(int idped) {
        PedidoDetalladoVo pedidoDetallado = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT p.idped, p.documento, p.fchareparto, " +
                                       "c.idcli, c.razonsocial, c.rucdni, c.direccion, " +
                                       "u.idusu, u.nombre " +
                                       "FROM pedidos p " +
                                       "JOIN clientes c ON p.idcli = c.idcli " +
                                       "JOIN usuarios u ON p.idusu = u.idusu " +
                                       "WHERE p.idped = ?");
            ps.setInt(1, idped);
            rs = ps.executeQuery();

            if (rs.next()) {
                pedidoDetallado = new PedidoDetalladoVo();
                pedidoDetallado.setIdped(rs.getInt("idped"));
                pedidoDetallado.setDocumento(rs.getString("documento"));
                pedidoDetallado.setFchareparto(rs.getString("fchareparto"));
                pedidoDetallado.setIdcli(rs.getInt("idcli"));
                pedidoDetallado.setRazonsocial(rs.getString("razonsocial"));
                pedidoDetallado.setRucdni(rs.getString("rucdni"));
                pedidoDetallado.setDireccion(rs.getString("direccion"));
                pedidoDetallado.setIdusu(rs.getInt("idusu"));
                pedidoDetallado.setNombre(rs.getString("nombre"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pedidoDetallado;
    }

    public void updatePartial(PedidoVo pedido) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("UPDATE pedidos SET documento = ?, fchareparto = ?, idusu = ?, idcli = ? WHERE idped = ?");

            ps.setString(1, pedido.getDocumento());
            ps.setString(2, pedido.getFchareparto());
            ps.setInt(3, pedido.getIdusu());
            ps.setInt(4, pedido.getIdcli());
            ps.setInt(5, pedido.getIdped());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error updating pedido with id " + pedido.getIdped());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
