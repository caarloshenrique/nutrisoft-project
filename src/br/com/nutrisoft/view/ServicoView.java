package br.com.nutrisoft.view;

import br.com.nutrisoft.controller.LoginController;
import br.com.nutrisoft.controller.ServicoController;
import br.com.nutrisoft.dao.ServicoDaoImpl;
import br.com.nutrisoft.executavel.Executavel;
import br.com.nutrisoft.model.Servico;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ServicoView {

    private ServicoController svc;
    private Scanner sc;
    private long idUser;
    private JFrame janela;
    private JLabel rotulo, titulo;
    private JPanel painel;
    private JTextField nome, descricao, data, valor;
    private JButton botaoCadastro, botaoListar, botaoVoltar, botaoCadastroServico;
    private DefaultTableModel modelo = new DefaultTableModel();
    private LoginController lc;

    public ServicoView(long id) {
        sc = new Scanner(System.in);
        svc = new ServicoController();
        svc.novo();
        svc.getServico().setUsuarioId(id);
        idUser = id;
    }

    public void cadastrarServico() {
        svc.getServico().setNome(nome.getText());
        svc.getServico().setDescricao(descricao.getText());
        String st = data.getText();
        svc.getServico().setData(dataFormatada(st));
        svc.getServico().setValor(Double.parseDouble(valor.getText()));
        svc.getServico().setUsuarioId(idUser);
        if (svc.salvarServico()) {
            JOptionPane.showMessageDialog(null, "Serviço salvo com sucesso!");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao tentar salvar serviço!");
        }
    }

    public LocalDateTime dataFormatada(String st) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(st, formatter);
    }

    public void listarServicos() throws SQLException {
        svc.listarServico(svc.getServico().getUsuarioId());
        if (svc.getLista().isEmpty()) {
            System.out.println("Lista Vazia!");
        } else {
            for (Servico servico : svc.getLista()) {
                System.out.println(servico);
            }
        }
    }

    public void buscarPorId() throws SQLException {
        System.out.println("Digite o id do serviço:");
        long id = sc.nextLong();
        svc.buscarServicoPorId(id, svc.getServico().getUsuarioId());
        System.out.println(svc.getServico());
    }

    public void initMenu() {
        janela = new JFrame("NutriSoft - Consultório de Nutrição");
        janela.setBounds(0, 0, 1370, 730);
        janela.setResizable(false);

        painel = new JPanel(null);
        painel.setBackground(new Color(247, 247, 249));
        painel.setBounds(0, 0, 1370, 730);

        titulo = new JLabel("Menu de operações");
        titulo.setFont(new Font("Comic Sans", 1, 20));
        titulo.setForeground(new Color(68, 95, 232));
        titulo.setBounds(585, 160, 600, 45);
        painel.add(titulo);

        botaoCadastro = new JButton();
        botaoCadastro.setBackground(new Color(68, 95, 232));
        botaoCadastro.setForeground(Color.WHITE);
        botaoCadastro.setText("Cadastrar novo serviço");
        botaoCadastro.setBounds(380, 260, 600, 30);
        painel.add(botaoCadastro);

        botaoListar = new JButton();
        botaoListar.setBackground(new Color(68, 95, 232));
        botaoListar.setForeground(Color.WHITE);
        botaoListar.setText("Listar serviços");
        botaoListar.setBounds(380, 320, 600, 30);
        painel.add(botaoListar);

        botaoVoltar = new JButton();
        botaoVoltar.setBackground(Color.WHITE);
        botaoVoltar.setForeground(new Color(68, 95, 232));
        botaoVoltar.setText("Voltar");
        botaoVoltar.setBounds(380, 380, 600, 30);
        painel.add(botaoVoltar);

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janela.dispose();
                initCadastro();
            }
        });

        botaoListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    ListaServico ls = new ListaServico(idUser);
                } catch (SQLException ex) {
                    Logger.getLogger(ServicoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janela.dispose();
                Executavel ex = new Executavel();
                ex.init();
            }
        });

        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initCadastro() {
        janela = new JFrame("NutriSoft - Consultório de Nutrição");
        janela.setBounds(0, 0, 1370, 730);
        janela.setResizable(false);

        painel = new JPanel(null);
        painel.setBackground(new Color(247, 247, 249));
        painel.setBounds(0, 0, 1370, 730);

        titulo = new JLabel("Cadastro de serviço");
        titulo.setFont(new Font("Comic Sans", 1, 20));
        titulo.setForeground(new Color(68, 95, 232));
        titulo.setBounds(595, 160, 600, 45);
        painel.add(titulo);

        rotulo = new JLabel("Nome: ");
        rotulo.setFont(new Font("Comic Sans", 1, 15));
        rotulo.setForeground(new Color(68, 95, 232));
        rotulo.setHorizontalAlignment(JLabel.RIGHT);
        rotulo.setBounds(320, 230, 90, 45);
        painel.add(rotulo);

        nome = new JTextField();
        nome.setFont(new Font("Comic Sans", 1, 15));
        nome.setBounds(410, 240, 600, 30);
        painel.add(nome);

        rotulo = new JLabel("Descrição: ");
        rotulo.setFont(new Font("Comic Sans", 1, 15));
        rotulo.setForeground(new Color(68, 95, 232));
        rotulo.setHorizontalAlignment(JLabel.RIGHT);
        rotulo.setBounds(320, 270, 90, 45);
        painel.add(rotulo);

        descricao = new JTextField();
        descricao.setFont(new Font("Comic Sans", 1, 15));
        descricao.setBounds(410, 280, 600, 30);
        painel.add(descricao);

        rotulo = new JLabel("Data: ");
        rotulo.setFont(new Font("Comic Sans", 1, 15));
        rotulo.setForeground(new Color(68, 95, 232));
        rotulo.setHorizontalAlignment(JLabel.RIGHT);
        rotulo.setBounds(320, 310, 90, 45);
        painel.add(rotulo);

        data = new JTextField();
        data.setFont(new Font("Comic Sans", 1, 15));
        data.setBounds(410, 320, 600, 30);
        painel.add(data);

        rotulo = new JLabel("Valor R$: ");
        rotulo.setFont(new Font("Comic Sans", 1, 15));
        rotulo.setHorizontalAlignment(JLabel.RIGHT);
        rotulo.setForeground(new Color(68, 95, 232));
        rotulo.setBounds(320, 350, 90, 45);
        painel.add(rotulo);

        valor = new JTextField();
        valor.setFont(new Font("Comic Sans", 1, 15));
        valor.setBounds(410, 360, 600, 30);
        painel.add(valor);

        botaoCadastroServico = new JButton();
        botaoCadastroServico.setBackground(new Color(68, 95, 232));
        botaoCadastroServico.setForeground(Color.WHITE);
        botaoCadastroServico.setText("Cadastrar serviço");
        botaoCadastroServico.setBounds(410, 410, 600, 30);
        painel.add(botaoCadastroServico);

        botaoVoltar = new JButton();
        botaoVoltar.setBackground(Color.WHITE);
        botaoVoltar.setForeground(new Color(68, 95, 232));
        botaoVoltar.setText("Voltar");
        botaoVoltar.setBounds(410, 450, 600, 30);
        painel.add(botaoVoltar);

        botaoCadastroServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!validarCampos()) {
                    cadastrarServico();
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janela.dispose();
                initMenu();
            }
        });

        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void pesquisar(DefaultTableModel modelo) throws SQLException {
        modelo.setNumRows(0);
        ServicoDaoImpl dao = new ServicoDaoImpl();

        for (Servico servico : dao.listarServico(1)) {
            modelo.addRow(new Object[]{servico.getId(), servico.getNome(), servico.getDescricao(),
                servico.getData(), servico.getValor()});
        }
    }

    public void limparCampos() {
        nome.setText("");
        descricao.setText("");
        data.setText("");
        valor.setText("");
    }

    public boolean validarCampos() {
        if ((nome.getText() == null || nome.getText().trim().isEmpty())
                || (descricao.getText() == null || descricao.getText().trim().isEmpty())
                || (data.getText() == null || data.getText().trim().isEmpty())
                || (valor.getText() == null || valor.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos !");
            return true;
        } else {
            return false;
        }
    }
}
