package br.com.nutrisoft.controller;

import br.com.nutrisoft.dao.ServicoDaoImpl;
import br.com.nutrisoft.model.Servico;
import java.sql.SQLException;
import java.util.List;
import br.com.nutrisoft.dao.ServicoDao;

public class ServicoController {

    private Servico servico;
    private List<Servico> lista;
    private ServicoDao dao;

    public ServicoController() {
        dao = new ServicoDaoImpl();
        novo();
    }

    public void listarServico(long id) throws SQLException {
        lista = dao.listarServico(id);
    }

    public List<Servico> getLista() {
        return lista;
    }

    public void buscarServicoPorId(long id, long idUser) throws SQLException {
        servico = dao.buscaServicoPorId(id, idUser);
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void novo() {
        servico = new Servico();
    }

    public boolean salvarServico() {
        return dao.salvarServico(servico);
    }

    public void sair() throws SQLException {
        dao.closeConnections();
    }
}
