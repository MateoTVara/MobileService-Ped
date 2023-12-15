package pe.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pe.company.dbase.ConexionDb;
import pe.company.vo.ProductoVo;

public class ProductoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductoDao() {
    }

    public void insert(ProductoVo producto) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("INSERT INTO productos(desproduc, uniproduc, precio) VALUES (?, ?, ?)");

            ps.setString(1, producto.getDesproduc());
            ps.setString(2, producto.getUniproduc());
            ps.setDouble(3, producto.getPrecio());

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Collection<ProductoVo> findAll() {
        List<ProductoVo> list = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();

            ps = conn.prepareStatement("SELECT * FROM productos");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoVo producto = new ProductoVo();

                producto.setIdproduc(rs.getInt("idproduc"));
                producto.setDesproduc(rs.getString("desproduc"));
                producto.setUniproduc(rs.getString("uniproduc"));
                producto.setPrecio(rs.getDouble("precio"));

                list.add(producto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void delete(int id) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("DELETE FROM productos WHERE idproduc = ?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error deleting producto with id " + id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(ProductoVo producto) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("UPDATE productos SET desproduc = ?, uniproduc = ?, precio = ? WHERE idproduc = ?");

            ps.setString(1, producto.getDesproduc());
            ps.setString(2, producto.getUniproduc());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getIdproduc());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new Exception("Error updating producto with id " + producto.getIdproduc());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ProductoVo findById(int id) {
        ProductoVo producto = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM productos WHERE idproduc = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto = new ProductoVo();
                producto.setIdproduc(rs.getInt("idproduc"));
                producto.setDesproduc(rs.getString("desproduc"));
                producto.setUniproduc(rs.getString("uniproduc"));
                producto.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return producto;
    }
    
    public Collection<ProductoVo> findByDescripcion(String partialDescripcion) {
        List<ProductoVo> resultList = new ArrayList<>();

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("SELECT * FROM productos WHERE desproduc LIKE ?");
            ps.setString(1, "%" + partialDescripcion + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoVo producto = new ProductoVo();
                producto.setIdproduc(rs.getInt("idproduc"));
                producto.setDesproduc(rs.getString("desproduc"));
                producto.setUniproduc(rs.getString("uniproduc"));
                producto.setPrecio(rs.getDouble("precio"));

                resultList.add(producto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultList;
    }
}
