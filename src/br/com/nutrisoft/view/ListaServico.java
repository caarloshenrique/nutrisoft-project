package br.com.nutrisoft.view;

import br.com.nutrisoft.controller.ServicoController;
import br.com.nutrisoft.dao.ServicoDaoImpl;
import br.com.nutrisoft.model.Servico;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaServico extends JFrame {

    private static ServicoController svc;
    private ServicoDaoImpl dao;
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ListaServico(long id) throws SQLException {
        super("Listagem de serviços prestados");
        criaJTable(id);
        criaJanela();
    }

    public void criaJanela() {
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setBackground(new Color(247, 247, 249));
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(330, 180, 700, 400);
        setResizable(false);
        setVisible(true);
    }

    private void criaJTable(long id) throws SQLException {
        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Descrição");
        modelo.addColumn("Data");
        modelo.addColumn("Valor (R$)");
        tabela.getColumnModel().getColumn(0)
                .setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1)
                .setPreferredWidth(220);
        tabela.getColumnModel().getColumn(1)
                .setPreferredWidth(290);
        tabela.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
        tabela.getColumnModel().getColumn(1)
                .setPreferredWidth(80);
        pesquisar(modelo, id);
    }

    public static void pesquisar(DefaultTableModel modelo, long id) throws SQLException {
        modelo.setNumRows(0);
        ServicoDaoImpl dao = new ServicoDaoImpl();

        for (Servico servico : dao.listarServico(id)) {
            modelo.addRow(new Object[]{servico.getId(), servico.getNome(), servico.getDescricao(),
                servico.getData(), servico.getValor()});
        }
    }

    public static void main(String[] args) throws SQLException {
        ListaServico ls = new ListaServico(1);
    }

}
