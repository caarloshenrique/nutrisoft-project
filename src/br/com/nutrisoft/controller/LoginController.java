package br.com.nutrisoft.controller;

import br.com.nutrisoft.dao.UsuarioDaoImpl;
import br.com.nutrisoft.model.Usuario;
import java.sql.SQLException;

public class LoginController {

    private Usuario us;
    private UsuarioDaoImpl dao;

    public LoginController() {
        us = new Usuario();
        dao = new UsuarioDaoImpl();
    }

    public boolean efetuarLogin() throws SQLException {
        us = dao.efetuarLoginObj(us);
        if (us != null) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario getUs() {
        return us;
    }
}
