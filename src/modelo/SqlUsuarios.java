
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class SqlUsuarios extends Conexion{
    
    public boolean registrar(Usuarios usr){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO usuarios (usuario, password, nombre, correo, id_tipo) VALUES(?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getNombre());
            ps.setInt(5, usr.getId_tipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }    
    }
    
    public boolean login(Usuarios usr){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con= getConexion();
        
        String sql = "SELECT u.id, u.usuario, u.password, u.nombre, u.id_Tipo, t.nombre, FROM usuarios AS u INNER JOIN tipo_usuario AS t ON u.id_tipo = t.id WHERE u.usario=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();
            
            if (rs.next()) {
                if (usr.getPassword().equals(rs.getString(3))) {
                    String sqlUpdate = "UPDATE usuarios SET last_session=? WHERE id=?";
                    ps = con.prepareStatement(sqlUpdate);
                    ps.setString(1, usr.getLast_session());
                    ps.setInt(2, rs.getInt(1));
                    ps.execute();
                    
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setId_tipo(rs.getInt(5));
                    usr.setNombre_tipo(rs.getString(6));
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public int existeUsuario(String usuario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        return 0; 
    }
    public boolean esEmail(String correo){
        return false;     
    }

}
