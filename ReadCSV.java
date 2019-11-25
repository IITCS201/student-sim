import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
	public ReadCSV() {

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
}

