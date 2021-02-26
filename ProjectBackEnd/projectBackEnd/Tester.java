package projectBackEnd;

/* Makenzie Spurling
 * CSCI 3381: Java
 * Project 1: Song Directory */

import java.util.Iterator;

public class Tester {

	public static void main(String[] args) {
		
		// Basic Test #1: Empty Collection
		Collection testCol = new Collection();
		
		// Basic Test #2: Creating a song & adding to empty collection
		Song testSong = new Song();
		Song testSong2 = new Song("000035", "FakeArtist", "Pop", "Track1", "Album1", 2020, 0.0);
		testCol.addSong(testSong);
		testCol.addSong(testSong2);
		System.out.println("Test Song Collection:\n" + testCol);
		
		
		// Main Test: Get collection from the .csv
		Collection SongCol = new Collection("./projectBackEnd/finalTracks.csv");
		
		
		/* Note: Main functionalities tested on a TEST COLLECTION in
		 * order to show case methods without having to print a giant text
		 * file every time. */
		// Remove Test: Remove a song based on ID
		testCol.removeSong("000000");
		System.out.println("Song Removed:\n" + testCol);
		SongCol.removeSong("000002"); // Removes from actual SongCol! 
		
		
		// Edit Tests: Getters & Setters using SongCol.
		Song editTest = SongCol.getSong("000005");
		
		// Before Edits
		System.out.println("Before Edits:\n" + editTest + "\n");
		
		editTest.setAlbum("NewAlb");									// SetAlbum
		System.out.print("Changes: " + editTest.getAlbum() + " "); 		// GetAlbum
		
		editTest.setId("000005");										// SetId
		System.out.print(editTest.getId() + " ");						// GetId

		editTest.setGenre("Indie");										// SetGenre
		System.out.print(editTest.getGenre() + " ");					// GetGenre
		
		editTest.setArtist("NEW Artist");								// SetArtist
		System.out.print(editTest.getArtist() + " ");					// GetArtist
		
		editTest.setLong(-73.2);										// SetLong
		System.out.print(editTest.getLong() + " "); 					// GetLong
		
		editTest.setTrack("TRACK NAME");								// SetTrack
		System.out.print(editTest.getTrack() + " ");					// GetTrack
		
		editTest.setYear(2004);											// SetYear
		System.out.println(editTest.getYear() + "\n");					// GetYear
		
		// After Edits
		System.out.println("After Edits:\n" + SongCol.getSong("000005") + "\n");

		
		// Iterator Test: Checks that the iterator works
		Iterator<Song> itr = testCol.getIterator();
		System.out.println("Iterator Test:");
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println();
		
		
		// Query Tests using the actual SongCol
		// Query Test #1: Get a song based on artist 
		System.out.println("Query Test #1:\n" + SongCol.InputOneToOne("AWOL") + "\n");
		
		// Query Test #2: Get a song based on artist and album
		System.out.println("Query Test #2:\n" + SongCol.InputManyToOne("Fósforo", "Macondo") + "\n");
		
		// Query Test #3: Get a variety of songs based on genre
		System.out.println("Query Test #3:\n" + SongCol.InputOneToMany("Latin America") + "\n");
		
		// Query Test #4: Get a variety of songs based on genre and year
		System.out.println("Query Test #4:\n" + SongCol.InputManyToMany("Psych-Folk", 2008) + "\n");
		
		
		// End Test: Write to same file
		SongCol.writeFile("./projectBackEnd/finalTracks.csv");
	}
}
