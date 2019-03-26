package pages;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import models.Funcionario;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField usuarioField;
	private JPasswordField senhaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Voc\u00EA Aluga - Login");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usuarioField = new JTextField();
		usuarioField.setBounds(132, 70, 200, 20);
		usuarioField.setColumns(10);
		frame.getContentPane().add(usuarioField);

		senhaField = new JPasswordField();
		senhaField.setBounds(132, 120, 200, 20);
		frame.getContentPane().add(senhaField);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(42, 73, 80, 14);
		frame.getContentPane().add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(42, 123, 80, 14);
		frame.getContentPane().add(lblSenha);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					Funcionario.authenticate(usuarioField.getText(), senhaField.getPassword());
					new Janela();
					frame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame.getContentPane(), "Usuário e/ou senha inválidos", "Acesso negado", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnLogin.setBounds(243, 175, 89, 23);
		frame.getContentPane().add(btnLogin);

	}

}
