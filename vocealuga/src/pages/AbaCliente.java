package pages;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AbaCliente extends JPanel {

	/**
	 * Create the panel.
	 */
	public AbaCliente() {
		initialize();
	}
	
	private void initialize() {
		JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
		btnAdicionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AdicionarCliente();
			}
		});
		JButton btnListaDeClientes = new JButton("Lista de Clientes");
		btnListaDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarCliente();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(this);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(124)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnListaDeClientes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(btnAdicionarCliente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
					.addGap(112))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(79)
					.addComponent(btnAdicionarCliente, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnListaDeClientes, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addGap(75))
		);
		this.setLayout(gl_panel_1);	
	}

}
