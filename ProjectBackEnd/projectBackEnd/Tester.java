package projectBackEnd;

public class Tester {

	public static void main(String[] args) {
		// Basic test: Get collection from the .csv
		Collection col1 = new Collection("./projectBackEnd/finalTracks.csv");
		
		// Remove Test: Remove a song based on ID
		
		
		// End Test1: Write to different file
		col1.writeFile("./projectBackEnd/textWrite.txt");
		
		// End Test2: Write to same file
//		col1.writeFile("./projectBackEnd/textWrite.txt");
	}
}
