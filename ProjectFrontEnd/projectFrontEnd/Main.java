// Makenzie Spurling
// CSCI 3381: O-O Software Development Java
// Java Project 2: GUI & Front End Development
// 
package projectFrontEnd;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import projectFrontEnd.Collection;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Spotify"); 					// Create Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Gui panel = new Gui();									// Create Panel
		frame.getContentPane().add(panel);						// Add Panel to frame
		
		frame.pack();
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {			// Write to file when program closes
			public void windowClosing(WindowEvent we) {
				panel.doClose();
			}
		});
	}
}
