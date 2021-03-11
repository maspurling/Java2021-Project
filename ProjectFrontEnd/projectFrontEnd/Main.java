package projectFrontEnd;

import javax.swing.JFrame;

import projectFrontEnd.Collection;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Push Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Gui panel = new Gui();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}
