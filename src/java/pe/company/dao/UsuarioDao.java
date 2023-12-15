package pe.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pe.company.dbase.ConexionDb;

import pe.company.vo.UsuarioVo;

public class UsuarioDao 
{
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;    
    

    public UsuarioDao() {}   
    
    public void insert(UsuarioVo usuarios)
    {
        try
        {
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("insert into usuarios(usuario,contrasenia,nombre) values(?,?,?,?,?)");
            
            ps.setString(1,usuarios.getUsuario());
            ps.setString(2,usuarios.getContrasenia());
            ps.setString(3,usuarios.getNombre());
            
            int rows=ps.executeUpdate();
            if(rows!=1)
                throw new Exception("Error!");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    public Collection<UsuarioVo> findAll() 
    {
        List<UsuarioVo> list=new ArrayList<>();

        try
        {
            conn=ConexionDb.MySQL();
            
            ps=conn.prepareStatement("select * from usuarios");
            rs=ps.executeQuery();
            
            while(rs.next())
            {
                UsuarioVo usuarios=new UsuarioVo();
                
                usuarios.setIdusu(rs.getInt("idusu"));
                usuarios.setUsuario(rs.getString("usuario"));
                usuarios.setContrasenia(rs.getString("contrasenia"));
                usuarios.setNombre(rs.getString("nombre"));
          
     
        list.add(usuarios);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return list;
    }
    
    public UsuarioVo findById(int id) {
        UsuarioVo usuario = null;

        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("select * from usuarios where idusu = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioVo();
                usuario.setIdusu(rs.getInt("idusu"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setNombre(rs.getString("nombre"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    public void update(UsuarioVo usuario) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("update usuarios set usuario=?, contrasenia=?, nombre=? where idusu=?");

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasenia());
            ps.setString(3, usuario.getNombre());
            ps.setInt(4, usuario.getIdusu());

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            conn = ConexionDb.MySQL();
            ps = conn.prepareStatement("delete from usuarios where idusu=?");
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows != 1)
                throw new Exception("Error!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
    
    
