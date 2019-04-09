import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateRGBMap {
	private static char[] RGB = {'R', 'G', 'B'};
	public static void main(String[] args) {
		try {
			getRGBMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void getRGBMap() throws IOException{
		FileWriter fw = new FileWriter("C:/Users/jin36/Documents/out3.txt");
		try {
			Random rnd = new Random();
			int N = 0;
			fw.write(10+"\r\n");
			for(int k=0;k<10;k++) {
				N = 1 + rnd.nextInt(10);
				fw.write(N +"\r\n");
				for (int i = 0; i < N; i++) {
					StringBuffer sb = new StringBuffer();
					for(int j=0; j<N; j++) {
						// a-z
						sb.append(RGB[rnd.nextInt(3)]);
					}
					fw.write(sb.toString()+"\r\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			fw.close();
		}
	}
}
