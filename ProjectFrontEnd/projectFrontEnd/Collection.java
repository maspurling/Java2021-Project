package projectFrontEnd;

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
	
	// Useful Constructor using ReadFile
	public Collection(String fn) {
		this(); // Calls default constructor
		fileName = fn;
		readFile();
	}
	
	// Add Song to Collection
	public void add (Song s) {
		this.list.add(s);
	}
	// Remove Song from Collection
	public void remove (Song s) {
		list.remove(s);
	}
	
	// Returns array of all Songs(Strings)
	public String[] getSongs () {
		String[] toReturn = new String[list.size()];
		int index = 0;
		for (Song s : list) {
			toReturn[index] = s.toString();
			index++;
		}
		return toReturn;
	}
	
	// Returns array of all Genres(Strings)
	public String[] getGenres () {
		String[] toReturn = new String[list.size()];
		int index = 0;
		for (Song s : list) {
			toReturn[index] = s.getGenre();
			index++;
		}
		return toReturn;
	}
	
	// Returns array of all Artists(Strings)
	public String[] getArtists () {
		String[] toReturn = new String[list.size()];
		int index = 0;
		for (Song s : list) {
			toReturn[index] = s.getArtist();
			index++;
		}
		return toReturn;
	}
	
	// Returns array of all Ids(Strings)
	public String[] getIds () {
		String[] toReturn = new String[list.size()];
		int index = 0;
		for (Song s : list) {
			toReturn[index] = s.getId();
			index++;
		}
		return toReturn;
	}
	
	// Return an iterator for list
	public Iterator<Song> getIterator() {
		return list.iterator();
	}
	
	// Returns whether Collection is Empty
	public boolean empty () {
		return list.isEmpty();
	}
	
	// Returns number of Songs in Collection
	public int size () {
		return list.size();
	}
	
	//Return first element in Collection
	public Song returnFirst () {
		if (empty())
			return null;
		
		return list.get(0);
	}
	
	// Returns whether or not Collection contains song.
	public boolean contains (Song song) {
		if (list.contains(song))
			return true;
		
		else
			return false;
	}
	
	// Combine two collections together.
	public Collection addAll (Collection rhs) {
		if (rhs.empty())
			return this;
		Iterator<Song> iterator = rhs.getIterator();
		while (iterator.hasNext()) {
			Song song = iterator.next();
			this.add(song);
		}
		
		return this;
	}
	
	
	// Finds Song based on ID
	public Song findSongByID (String id) {
		Iterator<Song> iterator = this.getIterator();
		while (iterator.hasNext()) {
			Song song = iterator.next();
			if (song.getId().equals(id)) {
				return song;
			}
		}
		return null;
	}
	
	// Finds Song(s) based on Artist
	public Collection findSongByArtist (String artist) {
		Collection results = new Collection();
		Iterator<Song> iterator = this.getIterator();

		// Iterate through allSongs
		while (iterator.hasNext()) {
			Song song = iterator.next();

			// Find only values with matching artist name
			if (song.getArtist().equals(artist)) {
				results.add(song);
			}
		}
		return results;
	}
	
	// Finds Song(s) based on Genre
	public Collection findSongByGenre (String genre) {
		Collection results = new Collection();
		Iterator<Song> iterator = this.getIterator();

		// Iterate through allSongs
		while (iterator.hasNext()) {
			Song song = iterator.next();

			// Find only values with matching artist name
			if (song.getGenre().equals(genre)) {
				results.add(song);
			}
		}
		return results;
	}
	
	// Suggests Song, one input one output
	public Song suggestSong_1I1O (Song song) {
		Collection sameGenre = new Collection();
		Collection sameArtistandGenre = new Collection();
		
		sameGenre.addAll(this.findSongByGenre(song.getGenre()));
		sameArtistandGenre.addAll(sameGenre.findSongByArtist(song.getArtist()));
		
		if (sameArtistandGenre.size() > 1)
		{
			sameArtistandGenre.remove(song);
			return sameArtistandGenre.returnFirst();
		}
		
		else if (sameGenre.size() > 1)
		{
			sameGenre.remove(song);
			return sameGenre.returnFirst();
		}
		
		return song;
	}
	
	// Suggests Song, one input multiple output
	public Collection suggestSong_1IMO (Song song) {
		Collection sameGenre = new Collection();
		Collection sameArtistandGenre = new Collection();
		
		sameGenre.addAll(this.findSongByGenre(song.getGenre()));
		sameArtistandGenre.addAll(sameGenre.findSongByArtist(song.getArtist()));
		
		if (sameArtistandGenre.size() > 2)
		{
			sameArtistandGenre.remove(song);
			return sameArtistandGenre;
		}
		
		else if (sameGenre.size() > 2)
		{
			sameGenre.remove(song);
			return sameGenre;
		}
		
		else if (sameArtistandGenre.size() == 2)
		{
			sameArtistandGenre.remove(song);
			return sameGenre;
		}
		
		else if (sameGenre.size() == 2)
		{
			sameGenre.remove(song);
			return sameGenre;
		}
		
		return sameGenre;
	}

	// Suggests Song, multiple input one output
	public Song suggestSong_MI1O (Collection collection) {
		Song na = new Song();
		Collection sameGenre = new Collection();
		
		Iterator<Song> iterator = collection.getIterator();
		while (iterator.hasNext())
		{
			Song song = iterator.next();
			sameGenre.addAll(this.findSongByGenre(song.getGenre()));

		}
		
		Collection sameArtistandGenre = new Collection();
		
		Iterator<Song> iterator2 = collection.getIterator();
		while (iterator2.hasNext())
		{
			Song song = iterator2.next();
			sameArtistandGenre.addAll(sameGenre.findSongByArtist(song.getArtist()));

		}
		
		Iterator<Song> iterator3 = collection.getIterator();
		while (iterator3.hasNext())
		{
			Song song = iterator3.next();
			if (sameGenre.contains(song))
				sameGenre.remove(song);
			if (sameArtistandGenre.contains(song))
				sameArtistandGenre.remove(song);
		}
	
		if (sameArtistandGenre.size() > 0)
		{
			return sameArtistandGenre.returnFirst();
		}
		
		else if (sameGenre.size() > 0)
		{
			return sameGenre.returnFirst();
		}
		
		return na;
		
	}

	// Suggests Song, multiple input multiple output
	public Collection suggestSong_MIMO (Collection collection) {
		Collection sameGenre = new Collection();
		
		Iterator<Song> iterator = collection.getIterator();
		while (iterator.hasNext())
		{
			Song song = iterator.next();
			sameGenre.addAll(this.findSongByGenre(song.getGenre()));

		}
		
		Collection sameArtistandGenre = new Collection();
		
		Iterator<Song> iterator2 = sameGenre.getIterator();
		while (iterator2.hasNext())
		{
			Song song = iterator2.next();
			sameArtistandGenre.addAll(sameGenre.addAll(findSongByArtist(song.getArtist())));

		}
		
		Iterator<Song> iterator3 = collection.getIterator();
		while (iterator3.hasNext())
		{
			Song song = iterator3.next();
			if (sameGenre.contains(song))
				sameGenre.remove(song);
			if (sameArtistandGenre.contains(song))
				sameGenre.remove(song);
		}
	
		
		if (sameArtistandGenre.size() > 0)
		{
			return sameArtistandGenre;
		}
		
		else if (sameGenre.size() > 0)
		{
			return sameGenre;
		}
		
		return null;
		
	}
	
	// toString method since every class needs one
	public String toString() {
		String toReturn = "";
		for (Song s : list) {
			toReturn += s.toString() + "\n";
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
				this.add(new Song(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6])));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					this.add(new Song(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6])));
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
		// This method writes all of the data in the songs array to a file
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			
			
			for (Song s: list) {
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
