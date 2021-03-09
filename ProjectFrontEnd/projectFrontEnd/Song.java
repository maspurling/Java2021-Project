package projectFrontEnd;

public class Song {
	private String id;
	private String artist;
	private String genre;
	private String track;
	private String album;
	private int year;
	private double longitude;
	
	// Default Constructor
	public Song() {
		id = "000000";
		artist = "not set";
		genre = "not set";
		track = "not set";
		album = "not set";
		year = 0;
		longitude = 0;
	}
	
	// Useful Constructor
	public Song(String i, String a, String g, String t, String b, int y, double l) {
		id = i;
		artist = a;
		genre = g;
		track = t;
		album = b;
		year = y;
		longitude = l;
	}
	
	// Getters & Setters
	public String getId() {
		return id;
	}
	
	public void setId(String i) {
		id = i;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String a) {
		artist = a;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String g) {
		genre = g;
	}
	
	public String getTrack() {
		return track;
	}
	
	public void setTrack(String t) {
		track = t;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String a) {
		album = a;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int y) {
		year = y;
	}
	
	public double getLong() {
		return longitude;
	}
	
	public void setLong(double l) {
		longitude = l;
	}
	
	// toString because every class NEEDS a toString
	// Items separated by space
	public String toString() {
		return id+" "+artist+" "+genre+" "+track+" "+album+" "+year+" "+longitude;
	}
	
	
}
