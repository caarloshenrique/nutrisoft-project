package br.com.nutrisoft.dao;

import br.com.nutrisoft.model.Servico;
import br.com.nutrisoft.util.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoDaoImpl implements ServicoDao {

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

    public boolean salvarServico(Servico servico) {
        conn = getConection();
        String sql = "INSERT INTO servicotb (nome, descricao, data, valor, id_usuario) VALUES (?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(servico.getData()));
            stmt.setDouble(4, servico.getValor());
            stmt.setLong(5, servico.getUsuarioId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Servico> listarServico(long idUser) throws SQLException {
        List<Servico> lista = new ArrayList<>();
        Servico service;
        conn = getConection();
        String sql = "SELECT * FROM servicotb where id_usuario = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, idUser);
        rs = stmt.executeQuery();
        while (rs.next()) {
            service = new Servico();
            service.setId(rs.getInt(1));
            service.setNome(rs.getString(2));
            service.setDescricao(rs.getString(3));
            service.setData(rs.getTimestamp(4).toLocalDateTime());
            service.setValor(rs.getDouble(5));
            service.setUsuarioId(rs.getLong(6));
            lista.add(service);
        }
        return lista;
    }

    public Servico buscaServicoPorId(long id, long idUser)
            throws SQLException {
        Servico service = new Servico();
        conn = getConection();
        String sql = "SELECT * FROM servicotb WHERE id = ? AND id_usuario = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.setLong(2, idUser);
        rs = stmt.executeQuery();
        if (rs.next()) {
            service.setId(rs.getInt(1));
            service.setNome(rs.getString(2));
            service.setDescricao(rs.getString(3));
            service.setData(rs.getTimestamp(4)
                    .toLocalDateTime());
            service.setValor(rs.getDouble(5));
            service.setUsuarioId(rs.getLong(6));
        } else {
            service = null;
        }
        return service;
    }
}
