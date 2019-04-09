import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateSlope {
	public static void main(String[] args) {
		try {
			getString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void getString() throws IOException {
		FileWriter fw = new FileWriter("C:/Users/jin36/Documents/out2.txt");
		try {
			Random rnd = new Random();
			int N = 2 + rnd.nextInt(98);
			int L = 1 + rnd.nextInt(N-1);
			
			fw.write(N+" "+L +"\r\n");
			for (int i = 0; i < N; i++) {
				StringBuffer sb = new StringBuffer();
				for(int j=0; j<N; j++) {
					// a-z
					sb.append(rnd.nextInt(10));
					sb.append(" ");
				}
				fw.write(sb.toString()+"\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			fw.close();
		}
	}
}
