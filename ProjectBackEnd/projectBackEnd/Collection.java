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
	
	public Collection() {
		list = new ArrayList<Song>();
		fileName = null;
	}
	
	public Collection(String fn) {
		this();
		fileName = fn;
		readFile();
	}
	
	public void addSong(Song newSong) {
		this.list.add(newSong);
	}
	
	public void removeSong(int i) {
		Iterator<Song> itr = list.iterator();
		while(itr.hasNext()) {
			Song s = itr.next();
			if (s.getId() == i)
				this.list.remove(s);
			return;
		}
	}
	
	public Iterator<Song> iterator() {
		Iterator<Song> itr = list.iterator();
		return itr;
	}
	
	// Suggest a random song from an artist
	public Song Input11() {
		
		return null;
	}
	
	public Song InputM1() {
		
		return null;
	}
	
	public Song Input1M() {
		
		return null;
	}
	
	public Song InputMM() {
		
		return null;
	}
	
	
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < list.size(); i++) {
			toReturn += "Song: "+list.get(i)+"\n";
		}
		return toReturn;
	}
	
	private void readFile () {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				addSong(new Song(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6])));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					addSong(new Song(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6])));
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
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method

	public void writeFile(String altFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method
	
	private void doWrite(String fn) {
		// this method writes all of the data in the persons array to a file
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
