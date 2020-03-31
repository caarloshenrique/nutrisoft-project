package br.com.nutrisoft.executavel;

import br.com.nutrisoft.view.CadastrarView;
import br.com.nutrisoft.view.LoginView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Executavel {

    private JFrame janela;
    private JLabel titulo;
    private JPanel painel;
    private JButton botaoCadastro, botaoLogin, botaoSair;
    private Scanner sc;

    public Executavel() {
        sc = new Scanner(System.in);
    }

    public void menuPrincipal() throws SQLException {
        init();
    }

    public void init() {
        janela = new JFrame("NutriSoft - Consultório de Nutrição");
        janela.setBounds(0, 0, 1370, 730);
        janela.setResizable(false);

        painel = new JPanel(null);
        painel.setBackground(new Color(247, 247, 249));
        painel.setBounds(0, 0, 1370, 730);

        titulo = new JLabel("NutriSoft - Consultório de Nutrição");
        titulo.setFont(new Font("Roboto", 1, 20));
        titulo.setForeground(new Color(68, 95, 232));
        titulo.setBounds(510, 160, 600, 45);
        painel.add(titulo);

        botaoCadastro = new JButton();
        botaoCadastro.setBackground(new Color(68, 95, 232));
        botaoCadastro.setForeground(Color.WHITE);
        botaoCadastro.setText("Cadastrar novo usuário");
        botaoCadastro.setBounds(380, 260, 600, 30);
        painel.add(botaoCadastro);

        botaoLogin = new JButton();
        botaoLogin.setBackground(new Color(68, 95, 232));;
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setText("Efetuar login");
        botaoLogin.setBounds(380, 320, 600, 30);
        painel.add(botaoLogin);

        botaoSair = new JButton();
        botaoSair.setBackground(Color.WHITE);
        botaoSair.setForeground(new Color(68, 95, 232));
        botaoSair.setText("Sair");
        botaoSair.setBounds(380, 380, 600, 30);
        painel.add(botaoSair);

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janela.dispose();
                CadastrarView cv = new CadastrarView();
                cv.init();
            }
        });

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janela.dispose();
                LoginView lv = new LoginView();
                lv.init();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janela.dispose();
            }
        });

        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) throws SQLException {
        Executavel ex = new Executavel();
        ex.menuPrincipal();
    }
}
