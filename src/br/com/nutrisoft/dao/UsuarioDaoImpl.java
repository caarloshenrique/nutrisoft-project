package br.com.nutrisoft.dao;

import br.com.nutrisoft.model.Usuario;
import br.com.nutrisoft.util.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDaoImpl implements UsuarioDao {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private Connection getConection() {
        if (conn == null) {
            return ConexaoFactory.getConnection();
        } else {
            return conn;
        }
    }

    public void closeConnections() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public boolean salvarUsuario(Usuario usuario) {
        conn = getConection();
        String sql = "INSERT INTO usuariotb (email, senha) VALUES (?,?)";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean efetuarLogin(Usuario usuario)
            throws SQLException {
        conn = getConection();
        String sql = "SELECT * FROM usuariotb WHERE email = ? AND senha = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        rs = stmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario efetuarLoginObj(Usuario usuario)
            throws SQLException {
        Usuario us = new Usuario();
        conn = getConection();
        String sql = "SELECT * FROM usuariotb WHERE email = ? AND senha = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        rs = stmt.executeQuery();
        if (rs.next()) {
            us.setId(rs.getLong(1));
            us.setEmail(rs.getString(2));
            us.setSenha(rs.getString(3));

            return us;
        } else {
            return null;
        }
    }

}
