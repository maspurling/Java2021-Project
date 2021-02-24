package projectBackEnd;

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
		System.out.println(testCol);
		
		// Main Test: Get collection from the .csv
		Collection col1 = new Collection("./projectBackEnd/finalTracks.csv");
		
		// Remove Test: Remove a song based on ID
		testCol.removeSong("000000");
		System.out.println(testCol);
		col1.removeSong("000002");
		
		// Edit Test: Edit using Setters of a Song
		Song editTest = col1.getSong("000005");
		System.out.println(editTest.getAlbum() + "\n");
		
		Song editTest2 = testCol.getSong("000035");
		editTest2.setGenre("Indie");
		System.out.println(editTest2.getGenre() + "\n");
		
		// Query Test #1: Get a song based on artist 
		System.out.println(col1.InputOneToOne("AWOL") + "\n");

		// Iterator Test: Checks that the iterator works
		Iterator<Song> itr = testCol.getIterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println();
		
		// Query Test #2: Get a song based on artist and album
		System.out.println(col1.InputManyToOne("Fósforo", "Macondo") + "\n");
		
		// Query Test #3: Get a variety of songs based on genre
		System.out.println(col1.InputOneToMany("Latin America") + "\n");
		
		// Query Test #4: Get a variety of songs based on genre and year
		System.out.println(col1.InputManyToMany("Psych-Folk", 2008) + "\n");
		
		// End Test2: Write to same file
		col1.writeFile("./projectBackEnd/textWrite.txt");
	}
}
