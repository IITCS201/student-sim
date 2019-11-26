import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ReadCSV {
	public ReadCSV() {

	}

	// toList method
	public List<List<String>> toList(String file) {
		BufferedReader br = null;
		List<List<String>> arr = new ArrayList<> ();

		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String [] values = line.split(",");
				arr.add(Arrays.asList(values));
			}
			return arr;

		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		return arr;
	}
	
	// search method
	public String[] search(String file, String search) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data[0].equals(search)) {
					return data;
				}
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		return (new String[0]);
	}

	public static void main(String[] args) {
		ReadCSV csv = new ReadCSV();

		List<List<String>> arr = csv.toList("major.csv");
		
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(i).size(); j++) {
				System.out.print(arr.get(i).get(j) + " ");
			}
			System.out.println();
		}




	}
}

