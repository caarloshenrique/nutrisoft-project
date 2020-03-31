package br.com.nutrisoft.view;

import br.com.nutrisoft.controller.LoginController;
import br.com.nutrisoft.executavel.Executavel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView {

    private LoginController lc;
    private Scanner sc;
    private JFrame janela;
    private JLabel rotulo, titulo;
    private JPanel painel;
    private JButton botaoLogin, botaoVoltar;
    private JTextField email;
    private JPasswordField password;

    public LoginView() {
        lc = new LoginController();
        sc = new Scanner(System.in);
    }

    public void init() {
        janela = new JFrame("NutriSoft - Consultório de Nutrição");
        janela.setBounds(0, 0, 1370, 730);
        janela.setResizable(false);

        painel = new JPanel(null);
        painel.setBackground(new Color(247, 247, 249));
        painel.setBounds(0, 0, 1370, 730);

        titulo = new JLabel("Autenticação de usuário");
        titulo.setFont(new Font("Comic Sans", 1, 20));
        titulo.setBounds(565, 160, 600, 45);
        titulo.setForeground(new Color(68, 95, 232));
        painel.add(titulo);

        rotulo = new JLabel("E-mail: ");
        rotulo.setFont(new Font("Comic Sans", 1, 15));
        rotulo.setForeground(new Color(68, 95, 232));
        rotulo.setHorizontalAlignment(JLabel.RIGHT);
        rotulo.setBounds(320, 230, 70, 45);
        painel.add(rotulo);

        email = new JTextField();
        email.setFont(new Font("Comic Sans", 1, 15));
        email.setBounds(390, 240, 600, 30);
        painel.add(email);

        rotulo = new JLabel("Senha: ");
        rotulo.setFont(new Font("Comic Sans", 1, 15));
        rotulo.setForeground(new Color(68, 95, 232));
        rotulo.setHorizontalAlignment(JLabel.RIGHT);
        rotulo.setBounds(320, 270, 70, 45);
        painel.add(rotulo);

        password = new JPasswordField();
        password.setBounds(390, 280, 600, 30);
        painel.add(password);

        botaoLogin = new JButton();
        botaoLogin.setBackground(new Color(68, 95, 232));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setText("Efetuar login");
        botaoLogin.setBounds(390, 330, 600, 30);
        painel.add(botaoLogin);

        botaoVoltar = new JButton();
        botaoVoltar.setBackground(Color.WHITE);
        botaoVoltar.setForeground(new Color(68, 95, 232));
        botaoVoltar.setText("Voltar");
        botaoVoltar.setBounds(390, 370, 600, 30);
        painel.add(botaoVoltar);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!validarCampos()) {

                    janela.dispose();
                    try {
                        efetuarLogin();
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    public boolean validarCampos() {
        if ((email.getText() == null || email.getText().trim().isEmpty())
                || (password.getText() == null || password.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos !");
            return true;
        } else {
            return false;
        }
    }

    public void efetuarLogin() throws SQLException {
        lc.getUs().setEmail(email.getText());
        lc.getUs().setSenha(password.getText());
        if (lc.efetuarLogin()) {
            ServicoView av = new ServicoView(lc.getUs().getId());
            av.initMenu();
        } else {
            JOptionPane.showMessageDialog(null, "E-mail ou senha não conferem!");
        }
    }
}
