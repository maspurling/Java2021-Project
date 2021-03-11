package projectFrontEnd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import projectFrontEnd.Collection;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class Gui extends JPanel{
	private Collection SongCol;
    
	public Gui() {
		SongCol = new Collection("./projectFrontEnd/finalTracks.csv");
		
		setLayout(null);
		setBackground(new Color(102, 205, 170));
		setPreferredSize(new Dimension(700, 400));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 36, 22);
		add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(143, 106, 109, 22);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(143, 138, 109, 22);
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(484, 150, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(484, 218, 89, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(484, 252, 89, 23);
		add(btnNewButton_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(58, 183, 308, 148);
		add(textArea_1);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setBounds(83, 110, 46, 14);
		add(lblGenre);
		
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setBounds(83, 142, 46, 14);
		add(lblArtist);
		
		JSlider slider = new JSlider();
		slider.setBounds(414, 303, 200, 26);
		add(slider);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(469, 67, 121, 23);
		add(tglbtnNewToggleButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(327, 106, 97, 23);
		add(chckbxNewCheckBox);
	}
}
