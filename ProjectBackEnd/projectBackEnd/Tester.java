package projectBackEnd;

public class Tester {

	public static void main(String[] args) {
		// Basic test: Get collection from the .csv
		Collection col1 = new Collection("./projectBackEnd/finalTracks.csv");
		//System.out.println(col1);
		
		// End Test: Write to file
		col1.writeFile("./projectBackEnd/textWrite.csv");
	}
}
