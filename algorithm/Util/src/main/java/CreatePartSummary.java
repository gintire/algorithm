import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreatePartSummary {
	public static void getString(String fileName) throws IOException {
		FileWriter fw = new FileWriter("C:/Users/jin36/Documents/" + fileName);
		StringBuilder sb = new StringBuilder();
		try {
			Random rnd = new Random();
			int N = 1 + rnd.nextInt(100000);
			int M = 1 + rnd.nextInt(10);
			int K = 1 + rnd.nextInt(10);
			
			sb.append(N + " "+ M+" "+K+"\n");
			
			for(int i=0; i<N; i++) {
				sb.append((1+rnd.nextInt(1000000))+"\n");
			}
			
			fw.write(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			fw.close();
		}
	}
}
