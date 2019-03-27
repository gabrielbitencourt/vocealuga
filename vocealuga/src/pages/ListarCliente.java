package pages;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.UIManager;

import database.ConexaoBanco;
import models.Cliente;

import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JList;

public class ListarCliente {

	private JFrame frame;
	private DefaultListModel<String> listaModelo = new DefaultListModel<String>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarCliente window = new ListarCliente();
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
	public ListarCliente() {
		try {
			initialize();
			loadData();
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
		
		
		JList<String> list = new JList<String>(listaModelo);
		list.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(list);
	}
	
	private void loadData() {
		try {
			Connection conn = new ConexaoBanco().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM clientes");
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente(rs);
				clientes.add(cliente);
				listaModelo.addElement(cliente.nome + " " + cliente.sobrenome);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
