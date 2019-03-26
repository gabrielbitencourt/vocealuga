package pages;

import javax.swing.JPanel;

import routes.Router;

import javax.swing.JLabel;

public class Home extends JPanel {
	
	Router router;

	/**
	 * Create the panel.
	 */
	public Home(Router router) {
		this.router = router;
		
		JLabel lblHome = new JLabel("Home");
		add(lblHome);

	}

}
