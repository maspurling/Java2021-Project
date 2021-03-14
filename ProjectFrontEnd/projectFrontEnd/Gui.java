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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Gui extends JPanel{
	private Collection SongCol;		// Main Song Collection
	private JList SongDisplay;		// Jlist for displaying songs based on selection
	private JComboBox IdInput;		// Combobox for Id's
	private JComboBox GenreInput;	// Combobox for Genre's
	private JComboBox ArtistInput;	// Combobox for Artist's
	private JLabel cSong;			// Holds current selected song
    
	public Gui() {
		// Initialize the song collection from file 
		SongCol = new Collection("./projectFrontEnd/finalTracks.csv");
		
		// Initial GUI setup
		setLayout(null);
		setBackground(new Color(188, 143, 143));
		setPreferredSize(new Dimension(649, 411));
		
		// Menu Creation
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 103, 22);
		add(menuBar);
		
		// Add File option to Menu
		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(new Color(176, 224, 230));
		menuBar.add(mnFile);
		
		// Add Exit option to File
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		// Add Edit Song option to Menu
		JMenu mnEdit = new JMenu("Edit Song");
		menuBar.add(mnEdit);
		
		// Add Edit Id option to Edit Song
		JMenuItem mntmEditId = new JMenuItem("Edit Id");
		
		// New action listener for when Edit Id is pressed
		mntmEditId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cont = true; // Boolean for do-while loop
				do {
					try {
						// Request the new id via user input
						String newId = JOptionPane.showInputDialog("Sample Id: 000000\n\nNew Id: ");
						
						// Error Checking
						// If input id has six characters change the song's Id in SongCol
						if (newId.length() == 6) {
							String song = (String)SongDisplay.getSelectedValue();
							String[] tokens = song.split(" ");
							SongCol.findSongByID(tokens[0]).setId(newId);
							
							// Reset the combobox and do not continue the loop
							IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
							cont = false;
						}
					} catch (Exception x) {
						cont = false; // If exception thrown, don't loop
					}
				} while (cont); // Loop until input is correct
			}
		});
		mnEdit.add(mntmEditId);
		
		// Add Edit Artist to Edit Song
		JMenuItem mntmEditArtist = new JMenuItem("Edit Artist");
		
		// New action listener for when Edit Artist is pressed
		mntmEditArtist.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked") // Suppresses yellow warnings
			public void actionPerformed(ActionEvent e) {
				boolean cont = true; // Boolean for do-while loop
				do {
					try {
						// Request the new Artist name via user input
						String newArt = JOptionPane.showInputDialog("Sample Artist: Halsey\n\nNew Artist: ");
						String song = (String)SongDisplay.getSelectedValue();
						String[] tokens = song.split(" ");
						SongCol.findSongByID(tokens[0]).setArtist(newArt);
						
						// Reset the combobox and do not continue the loop
						ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
						cont = false;
					} catch (Exception x) {
						cont = false; // If exception thrown, don't loop
					}
				} while (cont); // Loop until input is correct
			}
		});
		mnEdit.add(mntmEditArtist);
		
		// Creation of Genre Input Combobox
		GenreInput = new JComboBox();
		GenreInput.setForeground(new Color(188, 143, 143));
		GenreInput.setBackground(new Color(255, 255, 255));
		
		// Populates GenreInput with genres
		GenreInput.setModel(new DefaultComboBoxModel(SongCol.getGenres()));
		
		// Sets tool tips for Genre Inputs
		GenreInput.setSelectedIndex(0);
		GenreInput.setToolTipText(GenreInput.getSelectedItem().toString());
		
		// New action listener for when Genre is selected from GenreInput
		GenreInput.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked") // Suppresses the yellow warnings
			public void actionPerformed(ActionEvent e) {
				GenreInput.setToolTipText(GenreInput.getSelectedItem().toString());
				
				// Populates the Jlist with songs based on selected genre
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
		
		// Creation of Artist Input Combobox
		ArtistInput = new JComboBox();
		ArtistInput.setForeground(new Color(188, 143, 143));
		ArtistInput.setBackground(new Color(255, 255, 255));
		
		// Populates ArtistInput with Artists
		ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
		
		// Sets tool tips for Genre Inputs
		ArtistInput.setSelectedIndex(0);
		ArtistInput.setToolTipText(ArtistInput.getSelectedItem().toString());
		
		// New action listener for when Artist is selected from ArtistInput
		ArtistInput.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked") // Suppresses the yellow warnings
			public void actionPerformed(ActionEvent e) {
				ArtistInput.setToolTipText(ArtistInput.getSelectedItem().toString());
				
				// Populates the Jlist with songs based on selected artist
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
		
		// Creation of Id Input Combobox
		IdInput = new JComboBox();
		IdInput.setForeground(new Color(188, 143, 143));
		IdInput.setBackground(new Color(255, 255, 255));
		
		// Populates IdInput with Id's
		IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds())); 
		
		// New action listener for when Id is selected from IdInput
		IdInput.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked") // Suppresses yellow warnings
			public void actionPerformed(ActionEvent e) {
				
				// Populates the Jlist with songs based on selected artist
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
	
		// ScrollPane for Jlist
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 205, 600, 185);
		add(scrollPane);
		
		// Creation of Jlist
		SongDisplay = new JList();
		SongDisplay.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(SongDisplay);
		
		// Label for GenreInput
		JLabel GenreLabel = new JLabel("Genre:");
		GenreLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GenreLabel.setForeground(Color.WHITE);
		GenreLabel.setBounds(25, 87, 46, 14);
		add(GenreLabel);
		
		// Label for ArtistInput
		JLabel ArtistLabel = new JLabel("Artist:");
		ArtistLabel.setForeground(Color.WHITE);
		ArtistLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		ArtistLabel.setBounds(25, 141, 46, 14);
		add(ArtistLabel);
		
		// Label for IdInput
		JLabel IdLabel = new JLabel("Id:");
		IdLabel.setForeground(Color.WHITE);
		IdLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		IdLabel.setBounds(25, 33, 18, 14);
		add(IdLabel);
		
		// Creation of Show Selected Song Toggle Button
		JToggleButton SelectedSong = new JToggleButton("Show Selected Song");
		SelectedSong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				
				// If toggled it will show the selected song
				if (state == ItemEvent.SELECTED) {
					String song = (String)SongDisplay.getSelectedValue();
					cSong.setText(song);
				}
				// when untoggled it will show no selected song
				else {
					cSong.setText("No Selected Song");
				}
			}
		});
		SelectedSong.setForeground(new Color(255, 255, 255));
		SelectedSong.setBackground(new Color(188, 143, 143));
		SelectedSong.setBounds(362, 108, 160, 23);
		add(SelectedSong);
		
		// Creation of Add Song button
		JButton AddSong = new JButton("Add Song");
		
		// New action listener for when Add Song is pressed
		AddSong.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked") // Supresses yellow warnings
			// Prompts user input for a song via dialog boxes
			public void actionPerformed(ActionEvent e) {
				boolean cont = true; // Boolean for do-while lop
				do {
						try {
							// Get song input from user and then add the song to the collection and the Jlist
							String newSong = JOptionPane.showInputDialog("Sample Song: 000000,Mark Zoel,Pop,Make it Rain,City Skys,2000,0.0\nSong Parts: Id,Artist,Genre,Track,Album,Year,Longitude\n\nEnter Song EXACTLY as shown seperated by commas: ");
							String tokens[] = newSong.split(",");
							
							if (tokens[0].length() == 6 && Integer.parseInt(tokens[5]) <= 2021) {
								Song s = new Song(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Double.parseDouble(tokens[6]));
								cont = false;
								SongCol.add(s);
								
								// Resets Comboboxes and stops the loop
								ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
								IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
								GenreInput.setModel(new DefaultComboBoxModel(SongCol.getGenres()));
								cont = false;
								
								// Completion message.
								// DISCLAIMER: Song will be added to the very bottom of the SongCol
								JOptionPane.showMessageDialog(null, "Song Created - Song added to end of Song Collection");
							}
					} catch (Exception x) {
						cont = false; // If exception thrown, don't loop
					}
				} while(cont); // Loop until input is correct
				
			}
		});
		AddSong.setBackground(new Color(188, 143, 143));
		AddSong.setForeground(new Color(255, 255, 255));
		AddSong.setBounds(285, 161, 121, 23);
		add(AddSong);
		
		// Creation of RemoveSong button
		JButton RemoveSong = new JButton("Remove Song");
		
		// New action listener for when Remove Song is pressed
		RemoveSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Get the currently selected song
					String song = (String)SongDisplay.getSelectedValue();
					
					// Find the song's Id and remove it from SongCol
					String[] tokens = song.split(" ");
					Song s = SongCol.findSongByID(tokens[0]);
					SongCol.remove(s);
					
					// Resets the combobox's so that the song is no longer there
					ArtistInput.setModel(new DefaultComboBoxModel(SongCol.getArtists()));
					IdInput.setModel(new DefaultComboBoxModel(SongCol.getIds()));
					GenreInput.setModel(new DefaultComboBoxModel(SongCol.getGenres()));
				}
				// If no song selected, give error message
				catch (Exception x) {JOptionPane.showMessageDialog(null, "No Song Currently Selected - Please select a Song.");}
			}
		});
		RemoveSong.setBackground(new Color(188, 143, 143));
		RemoveSong.setForeground(new Color(255, 255, 255));
		RemoveSong.setBounds(464, 162, 121, 23);
		add(RemoveSong);
		
		// Labels for Selected Song
		cSong = new JLabel("No Song Selected");
		cSong.setForeground(Color.WHITE);
		cSong.setFont(new Font("Tahoma", Font.BOLD, 15));
		cSong.setBounds(279, 59, 346, 18);
		add(cSong);

		JLabel Current = new JLabel("Selected Song:");
		Current.setForeground(Color.WHITE);
		Current.setFont(new Font("Tahoma", Font.BOLD, 16));
		Current.setBounds(362, 30, 147, 18);
		add(Current);
		
		// Label for displaying music note
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Gui.class.getResource("/projectFrontEnd/m.png")));
		label.setBounds(236, 54, 21, 31);
		add(label);
	}
	
	// Upon closing the program, update the finalTracks file
	public void doClose() {
		SongCol.writeFile("./ProjectFrontEnd/finalTracks.csv");
	}
}
