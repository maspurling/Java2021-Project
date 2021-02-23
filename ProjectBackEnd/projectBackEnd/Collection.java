package projectBackEnd;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class Collection {
	private ArrayList<Song> list;
	private String fileName;
	
	// Default Constructor
	public Collection() {
		list = new ArrayList<Song>();
		fileName = null;
	}
	
	// Useful Constructor
	public Collection(String fn) {
		this(); // Calls default constructor
		fileName = fn;
		readFile();
	}
	
	public void addSong(Song newSong) {
		this.list.add(newSong);
	}
	
	// Remove a song using iterator
	public void removeSong(String i) {
		Iterator<Song> itr = list.iterator();
		while(itr.hasNext()) {
			Song s = itr.next();
			if (s.getId().equals(i))
				this.list.remove(s);
			return;
		}
	}
	
	// Return an iterator for list
	public Iterator<Song> iterator() {
		Iterator<Song> itr = list.iterator();
		return itr;
	}
	
	// Suggest a song based on artist
	public Song InputOneToOne(String a) {
		Iterator<Song> itr = list.iterator();
		while(itr.hasNext()) {
			Song s = itr.next();
			if (s.getArtist().equals(a)) {
				return s;
			}
		}
		return null;
	}
	
	// Suggest a song based on artist and album
	public Song InputManyToOne(String a, String b) {
		Iterator<Song> itr = list.iterator();
		while(itr.hasNext()) {
			Song s = itr.next();
			if (s.getArtist().equals(a) && s.getAlbum().equals(b)) {
				return s;
			}
		}
		return null;
	}
	
	// Suggest many songs based on genre
	public Collection InputOneToMany(String g) {
		Iterator<Song> itr = list.iterator();
		Collection temp = new Collection();
		while(itr.hasNext()) {
			Song s = itr.next();
			if (s.getGenre().equals(g)) {
				temp.addSong(s);
			}
		}
		return temp;
	}
	
	// Suggest many songs based on genre and year
	public Collection InputManyToMany(String g, int y) {
		Iterator<Song> itr = list.iterator();
		Collection temp2 = new Collection();
		while(itr.hasNext()) {
			Song s = itr.next();
			if (s.getGenre().equals(g) && s.getYear() == y) {
				temp2.addSong(s);
			}
		}
		return temp2;
	}
	
	// toString method since every class needs one
	public String toString() {
		String toReturn = "Song Collection:\n";
		for (int i = 0; i < list.size(); i++) {
			toReturn += list.get(i)+"\n";
		}
		return toReturn;
	}
	
	// Readfile method
	private void readFile () {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				// Split line into tokens and then call addSong with the tokens
				String[] tokens = line.split(",");
				addSong(new Song(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6])));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					addSong(new Song(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6])));
				}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {;
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
	
	public void writeFile () {
		// Overloaded method: this calls doWrite with file used to read data
		// Use this for saving data between runs
		doWrite(fileName);
	} // End of writeFile method

	public void writeFile(String altFileName) {
		// Overloaded method: this calls doWrite with different file name 
		// Use this for testing write
		doWrite(altFileName);		
	}// End of writeFile method
	
	private void doWrite(String fn) {
		// This method writes all of the data in the persons array to a file
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			
			
			Iterator<Song> itr = list.iterator();
			while(itr.hasNext()) {
				Song s = itr.next();
				myOutfile.write (s.getId() + ",");
				myOutfile.write (s.getArtist()+ ","); 
				myOutfile.write (s.getGenre()+ ",");
				myOutfile.write (s.getTrack()+ ",");
				myOutfile.write (s.getAlbum()+ ",");
				myOutfile.write (s.getYear()+ ",");
				myOutfile.write (String.valueOf((s.getLong())) + "\n");
			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}	
	
}
