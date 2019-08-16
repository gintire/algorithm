import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			CreatePartSummary.getString("test123.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
