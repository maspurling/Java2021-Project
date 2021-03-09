package projectFrontEnd;

import javax.swing.JFrame;

import projectFrontEnd.Collection;

public class Main {

	public static void main(String[] args) {
		
		// Read in the Song Collection
		Collection SongCol = new Collection("./projectFrontEnd/finalTracks.csv");
		
		
		JFrame frame = new JFrame("Push Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PushCounterPanel panel = new PushCounterPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}
