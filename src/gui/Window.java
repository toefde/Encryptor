package gui;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 7446192599263749847L;

	public Window() {
		this.setTitle("Encryptor");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setVisible(true);
		
		this.add(new ViewPanel());
	}
	
}
