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
import javax.swing.JOptionPane;
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
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

public class Gui extends JPanel{
	private Collection SongCol;
	private JList SongDisplay;
	private JComboBox IdInput;
	private JComboBox GenreInput;
	private JComboBox ArtistInput;
	private JLabel cSong;
    
	public Gui() {
		SongCol = new Collection("./projectFrontEnd/finalTracks.csv");
		setLayout(null);
		setBackground(new Color(188, 143, 143));
		setPreferredSize(new Dimension(649, 411));
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 103, 22);
		add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(new Color(176, 224, 230));
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit Song");
		menuBar.add(mnEdit);
		
		JMenuItem mntmEditId = new JMenuItem("Edit Id");
		mntmEditId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isError = true;
				do {
					try {
						String newId = JOptionPane.showInputDialog("Sample Id: 000000\n\nNew Id: ");
						if (newId.length() == 6) {
							isError = false;
							String song = (String)SongDisplay.getSelectedValue();
							String[] tokens = song.split(" ");
							SongCol.findSongByID(tokens[0]).setId(newId);
							IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
						}
						else {
							JOptionPane.showMessageDialog(null, "Something went wrong. Please enter Id EXACTLY like sample.");
						}
					} catch (Exception x) {
						continue;
					}
				} while (isError);
			}
		});
		mnEdit.add(mntmEditId);
		
		JMenuItem mntmEditArtist = new JMenuItem("Edit Artist");
		mntmEditArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isError = true;
				do {
					try {
						String newArt = JOptionPane.showInputDialog("Sample Artist: Halsey\n\nNew Artist: ");
						isError = false;
						String song = (String)SongDisplay.getSelectedValue();
						String[] tokens = song.split(" ");
						SongCol.findSongByID(tokens[0]).setArtist(newArt);
						ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
					} catch (Exception x) {
						continue;
					}
				} while (isError);
			}
		});
		mnEdit.add(mntmEditArtist);
		
		// Comboboxes
		GenreInput = new JComboBox();
		GenreInput.setForeground(new Color(188, 143, 143));
		GenreInput.setBackground(new Color(255, 255, 255));
		GenreInput.setModel(new DefaultComboBoxModel(SongCol.getGenres()));
		GenreInput.setSelectedIndex(0);
		GenreInput.setToolTipText(GenreInput.getSelectedItem().toString());
		GenreInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenreInput.setToolTipText(GenreInput.getSelectedItem().toString());
				SongDisplay.setModel(new AbstractListModel() {
					Collection songs = SongCol.findSongByGenre(""+GenreInput.getSelectedItem());
					String[] values = songs.getSongs();
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});

		GenreInput.setBounds(25, 108, 160, 22);
		add(GenreInput);
		
		ArtistInput = new JComboBox();
		ArtistInput.setForeground(new Color(188, 143, 143));
		ArtistInput.setBackground(new Color(255, 255, 255));
		ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
		ArtistInput.setSelectedIndex(0);
		ArtistInput.setToolTipText(ArtistInput.getSelectedItem().toString());
		ArtistInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArtistInput.setToolTipText(ArtistInput.getSelectedItem().toString());
				SongDisplay.setModel(new AbstractListModel() {
					Collection songs = SongCol.findSongByArtist(""+ArtistInput.getSelectedItem());
					String[] values = songs.getSongs();
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});
		ArtistInput.setBounds(25, 162, 160, 22);
		add(ArtistInput);
		
		IdInput = new JComboBox();
		IdInput.setForeground(new Color(188, 143, 143));
		IdInput.setBackground(new Color(255, 255, 255));
		IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
		IdInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
				SongDisplay.setModel(new AbstractListModel() {
					String song = SongCol.findSongByID(""+IdInput.getSelectedItem()).toString();
					public int getSize() {
						return 1;
					}
					public Object getElementAt(int index) {
						return song;
					}
				});
			}
		});
		IdInput.setBounds(25, 54, 160, 22);
		add(IdInput);
	
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 205, 600, 185);
		add(scrollPane);
		
		SongDisplay = new JList();
		SongDisplay.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(SongDisplay);
		
		JLabel GenreLabel = new JLabel("Genre:");
		GenreLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GenreLabel.setForeground(Color.WHITE);
		GenreLabel.setBounds(25, 87, 46, 14);
		add(GenreLabel);
		
		JLabel ArtistLabel = new JLabel("Artist:");
		ArtistLabel.setForeground(Color.WHITE);
		ArtistLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		ArtistLabel.setBounds(25, 141, 46, 14);
		add(ArtistLabel);
		
		JLabel IdLabel = new JLabel("Id:");
		IdLabel.setForeground(Color.WHITE);
		IdLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		IdLabel.setBounds(25, 33, 18, 14);
		add(IdLabel);
		
		JToggleButton PlayPause = new JToggleButton("Play/Pause");
		PlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String song = (String)SongDisplay.getSelectedValue();
				cSong.setText(song);
			}
		});
		PlayPause.setForeground(new Color(255, 255, 255));
		PlayPause.setBackground(new Color(188, 143, 143));
		PlayPause.setBounds(371, 107, 121, 23);
		add(PlayPause);
		
		JButton AddSong = new JButton("Add Song");
		AddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newId = JOptionPane.showInputDialog("Sample Id: 000000\n\nNew Id: ");
				String newArt = JOptionPane.showInputDialog("Sample Artist: Artist\n\nNew Artist: ");
				String newGen = JOptionPane.showInputDialog("Sample Genre: Pop\n\nNew Genre: ");
				String newTrack = JOptionPane.showInputDialog("Sample Track: Track\n\nNew Track: ");
				String newAlb = JOptionPane.showInputDialog("Sample Album: Album\n\nNew Album: ");
				String newYe = JOptionPane.showInputDialog("Sample Year: 0000\n\nNew Year: ");
				int Ye = Integer.parseInt(newYe);
				String newLo = JOptionPane.showInputDialog("Sample Longitude: 0.0\n\nNew Longitude: ");
				double Lo = Double.parseDouble(newLo);
				Song s = new Song(newId, newArt, newGen, newTrack, newAlb, Ye, Lo);
				SongCol.add(s);
				ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
				IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
				GenreInput.setModel(new DefaultComboBoxModel(SongCol.getGenres()));
			}
		});
		AddSong.setBackground(new Color(188, 143, 143));
		AddSong.setForeground(new Color(255, 255, 255));
		AddSong.setBounds(285, 161, 121, 23);
		add(AddSong);
		
		JButton RemoveSong = new JButton("Remove Song");
		RemoveSong.setBackground(new Color(188, 143, 143));
		RemoveSong.setForeground(new Color(255, 255, 255));
		RemoveSong.setBounds(463, 161, 121, 23);
		add(RemoveSong);
		
		cSong = new JLabel("No Song Selected");
		cSong.setForeground(Color.WHITE);
		cSong.setFont(new Font("Tahoma", Font.BOLD, 15));
		cSong.setBounds(279, 59, 346, 18);
		add(cSong);
		
		JLabel Current = new JLabel("Currently Playing:");
		Current.setForeground(Color.WHITE);
		Current.setFont(new Font("Tahoma", Font.BOLD, 16));
		Current.setBounds(362, 30, 147, 18);
		add(Current);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Gui.class.getResource("/projectFrontEnd/m.png")));
		label.setBounds(236, 54, 21, 31);
		add(label);
		

	}
}
