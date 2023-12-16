package pe.company.dao;

import pe.company.dbase.ConexionDb;
import pe.company.vo.DetalleVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pe.company.vo.DetalleProductoInfoVo;

public class DetalleDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private PedidoDao pedidoDao = new PedidoDao();
    private ProductoDao productoDao = new ProductoDao();

    public DetalleDao() {
    }

    public void insert(DetalleVo detalle) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO detalles_pedido(idped, idproduc, cantidad, importe) VALUES (?, ?, ?, ?)");

            ps.setInt(1, detalle.getIdped());
            ps.setInt(2, detalle.getIdproduc());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getImporte());

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Collection<DetalleVo> findAll() {
        List<DetalleVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();

            ps = conn.prepareStatement("SELECT * FROM detalles_pedido");
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleVo detalle = new DetalleVo();

                detalle.setIddetalle(rs.getInt("iddetalle"));
                detalle.setIdped(rs.getInt("idped"));
                detalle.setIdproduc(rs.getInt("idproduc"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setImporte(rs.getDouble("importe"));

                list.add(detalle);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void delete(int id) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("DELETE FROM detalles_pedido WHERE iddetalle = ?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error deleting detalle with id " + id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(DetalleVo detalle) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("UPDATE detalles_pedido SET idped = ?, idproduc = ?, cantidad = ?, importe = ? WHERE iddetalle = ?");

            ps.setInt(1, detalle.getIdped());
            ps.setInt(2, detalle.getIdproduc());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getImporte());
            ps.setInt(5, detalle.getIddetalle());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error updating detalle with id " + detalle.getIddetalle());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public DetalleVo findById(int id) {
        DetalleVo detalle = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM detalles_pedido WHERE iddetalle = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                detalle = new DetalleVo();
                detalle.setIddetalle(rs.getInt("iddetalle"));
                detalle.setIdped(rs.getInt("idped"));
                detalle.setIdproduc(rs.getInt("idproduc"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setImporte(rs.getDouble("importe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return detalle;
    }
    
    public Collection<DetalleProductoInfoVo> findDetailsByPedidoId(int idped) {
        List<DetalleProductoInfoVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT dp.idped, dp.iddetalle, p.desproduc, p.uniproduc, dp.cantidad, dp.importe " +
                                       "FROM detalles_pedido dp " +
                                       "JOIN productos p ON dp.idproduc = p.idproduc " +
                                       "WHERE dp.idped = ?");
            ps.setInt(1, idped);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleProductoInfoVo detalleInfo = new DetalleProductoInfoVo();
                detalleInfo.setIdped(rs.getInt("idped"));
                detalleInfo.setIddetalle(rs.getInt("iddetalle"));
                detalleInfo.setDesproduc(rs.getString("desproduc"));
                detalleInfo.setUniproduc(rs.getString("uniproduc"));
                detalleInfo.setCantidad(rs.getInt("cantidad"));
                detalleInfo.setImporte(rs.getDouble("importe"));

                list.add(detalleInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    public Collection<DetalleProductoInfoVo> findUnassignedDetails() {
        List<DetalleProductoInfoVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT dp.idped, dp.iddetalle, p.desproduc, p.uniproduc, dp.cantidad, dp.importe " +
                                       "FROM detalles_pedido dp " +
                                       "JOIN productos p ON dp.idproduc = p.idproduc " +
                                       "WHERE dp.idped IS NULL");
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleProductoInfoVo detalleInfo = new DetalleProductoInfoVo();
                detalleInfo.setIdped(rs.getInt("idped"));
                detalleInfo.setIddetalle(rs.getInt("iddetalle"));
                detalleInfo.setDesproduc(rs.getString("desproduc"));
                detalleInfo.setUniproduc(rs.getString("uniproduc"));
                detalleInfo.setCantidad(rs.getInt("cantidad"));
                detalleInfo.setImporte(rs.getDouble("importe"));

                list.add(detalleInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    public void deleteUnassignedDetails() {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("DELETE FROM detalles_pedido WHERE idped IS NULL");
            int rows = ps.executeUpdate();
            System.out.println(rows + " registros eliminados sin asignar a un idped.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void assignIdpedToUnassignedDetails(int idped) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("UPDATE detalles_pedido SET idped = ? WHERE idped IS NULL");
            ps.setInt(1, idped);
            int rows = ps.executeUpdate();
            System.out.println(rows + " registros actualizados con el idped: " + idped);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertDetalleParcial(DetalleVo detalle) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO detalles_pedido(idproduc, cantidad) VALUES (?, ?)");

            ps.setInt(1, detalle.getIdproduc());
            ps.setInt(2, detalle.getCantidad());

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error al insertar el detalle parcial.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertDetalleParcialWithIdped(DetalleVo detalle) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO detalles_pedido(idped, idproduc, cantidad) VALUES (?, ?, ?)");

            ps.setInt(1, detalle.getIdped());
            ps.setInt(2, detalle.getIdproduc());
            ps.setInt(3, detalle.getCantidad());

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error al insertar el detalle parcial.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
}
