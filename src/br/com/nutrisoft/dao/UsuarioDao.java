package br.com.nutrisoft.dao;

import br.com.nutrisoft.model.Usuario;
import java.sql.SQLException;

public interface UsuarioDao {

    public void closeConnections() throws SQLException;

    public boolean salvarUsuario(Usuario usuario);

    public boolean efetuarLogin(Usuario usuario) throws SQLException;

    public Usuario efetuarLoginObj(Usuario usuario) throws SQLException;
}
