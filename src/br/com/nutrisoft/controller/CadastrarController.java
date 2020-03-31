package br.com.nutrisoft.controller;

import br.com.nutrisoft.dao.UsuarioDaoImpl;
import br.com.nutrisoft.model.Usuario;

public class CadastrarController {

    public UsuarioDaoImpl dao;
    private Usuario us;

    public CadastrarController() {
        dao = new UsuarioDaoImpl();
        us = new Usuario();
    }

    public void cadastrarUsuario() {
        dao.salvarUsuario(us);
    }

    public Usuario getUs() {
        return us;
    }

}
