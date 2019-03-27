package pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import models.Cliente;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AdicionarCliente {

	private JFrame frame;
	private JTextField nomeField;
	private JTextField sobrenomeField;
	private JTextField enderecoField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarCliente window = new AdicionarCliente();
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
	public AdicionarCliente() {
		try {
			initialize();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(40, 85, 80, 14);
		frame.getContentPane().add(lblNome);
		
		nomeField = new JTextField();
		nomeField.setBounds(130, 79, 140, 20);
		frame.getContentPane().add(nomeField);
		nomeField.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSobrenome.setBounds(290, 85, 100, 14);
		frame.getContentPane().add(lblSobrenome);
		
		sobrenomeField = new JTextField();
		sobrenomeField.setBounds(400, 79, 140, 20);
		frame.getContentPane().add(sobrenomeField);
		sobrenomeField.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereo.setBounds(40, 110, 80, 14);
		frame.getContentPane().add(lblEndereo);
		
		enderecoField = new JTextField();
		enderecoField.setBounds(130, 104, 410, 20);
		frame.getContentPane().add(enderecoField);
		enderecoField.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCelular.setBounds(310, 160, 80, 14);
		frame.getContentPane().add(lblCelular);
		
		JFormattedTextField formattedCelular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		formattedCelular.setBounds(400, 154, 140, 20);
		frame.getContentPane().add(formattedCelular);
		
		JLabel lblCnh = new JLabel("CNH");
		lblCnh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCnh.setBounds(290, 135, 100, 14);
		frame.getContentPane().add(lblCnh);
		
		JFormattedTextField formattedCNH = new JFormattedTextField(new MaskFormatter("###########"));
		formattedCNH.setBounds(400, 129, 140, 20);
		frame.getContentPane().add(formattedCNH);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(40, 135, 80, 14);
		frame.getContentPane().add(lblCpf);
		
		JFormattedTextField formattedCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		formattedCPF.setBounds(130, 129, 140, 20);
		frame.getContentPane().add(formattedCPF);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDeNascimento.setBounds(0, 160, 120, 14);
		frame.getContentPane().add(lblDataDeNascimento);
		
		JFormattedTextField formattedNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		formattedNascimento.setBounds(130, 154, 140, 20);
		frame.getContentPane().add(formattedNascimento);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date nascimento = null;
				try {
					nascimento = new SimpleDateFormat("dd/MM/yyy").parse(formattedNascimento.getText());
					
				} catch (ParseException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame.getContentPane(), "A data deve ser inserida no formato dia/mês/ano, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Cliente cliente = new Cliente(nomeField.getText(), sobrenomeField.getText(),
						enderecoField.getText(), formattedCPF.getText().replaceAll("\\.", "").replaceFirst("-", ""), formattedCNH.getText(),
						emailField.getText(), formattedCelular.getText(), nascimento);
				try {
					cliente.save();
					if (JOptionPane.showConfirmDialog(frame.getContentPane(), "Cliente cadastrado com sucesso. Deseja cadastrar outro?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						new AdicionarCliente();
						frame.setVisible(false);
						return;
					}
					else {
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						return;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame.getContentPane(), "Erro ao cadastrar cliente, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdicionar.setBounds(130, 220, 410, 50);
		frame.getContentPane().add(btnAdicionar);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(40, 185, 80, 14);
		frame.getContentPane().add(lblEmail);
		
		emailField = new JTextField();
		emailField.setBounds(130, 179, 410, 20);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
	}
}
