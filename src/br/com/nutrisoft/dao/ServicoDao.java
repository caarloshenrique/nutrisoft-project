package br.com.nutrisoft.dao;

import br.com.nutrisoft.model.Servico;
import java.sql.SQLException;
import java.util.List;

public interface ServicoDao {

    public void closeConnections() throws SQLException;

    public boolean salvarServico(Servico servico);

    public Servico buscaServicoPorId(long id, long idUser) throws SQLException;

    public List<Servico> listarServico(long id) throws SQLException;
}
