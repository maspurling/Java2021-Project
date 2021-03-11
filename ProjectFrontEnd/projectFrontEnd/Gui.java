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
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JList;

public class Gui extends JPanel{
	private Collection SongCol;
	private JTextArea textArea;
    
	public Gui() {
		SongCol = new Collection("./projectFrontEnd/finalTracks.csv");
		String [] genres = SongCol.getGenres();
		setLayout(null);
		setBackground(new Color(102, 205, 170));
		setPreferredSize(new Dimension(1000, 400));
		
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
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection songs = SongCol.findSongByGenre(""+comboBox.getSelectedItem());
				String toReturn = songs.toString();
				textArea.setText(toReturn);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(SongCol.getGenres()));
		comboBox.setBounds(101, 97, 102, 22);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection songs = SongCol.findSongByArtist(""+comboBox_1.getSelectedItem());
				String toReturn = songs.toString();
				textArea.setText(toReturn);
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
		comboBox_1.setBounds(101, 130, 102, 22);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song songs = SongCol.findSongByID(""+comboBox_2.getSelectedItem());
				String toReturn = songs.toString();
				textArea.setText(toReturn);
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(SongCol.getIds()));
		comboBox_2.setBounds(101, 62, 102, 22);
		add(comboBox_2);
		
		textArea = new JTextArea();
		textArea.setBounds(36, 177, 697, 184);
		add(textArea);
	
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGenre.setForeground(Color.WHITE);
		lblGenre.setBounds(45, 100, 46, 14);
		add(lblGenre);
		
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setForeground(Color.WHITE);
		lblArtist.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblArtist.setBounds(45, 133, 46, 14);
		add(lblArtist);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(45, 65, 18, 14);
		add(lblNewLabel);
		
		JButton btnEditSong = new JButton("Edit Song");
		btnEditSong.setBounds(769, 255, 121, 23);
		add(btnEditSong);
		
		JToggleButton tglbtnPlaypause = new JToggleButton("Play/Pause");
		tglbtnPlaypause.setBounds(231, 96, 121, 23);
		add(tglbtnPlaypause);
		
		JButton btnAddSong = new JButton("Add Song");
		btnAddSong.setBounds(769, 215, 121, 23);
		add(btnAddSong);
		
		JButton btnRemoveSong = new JButton("Remove Song");
		btnRemoveSong.setBounds(769, 295, 121, 23);
		add(btnRemoveSong);
	}
}
