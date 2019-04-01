import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateString {
	
	public static void main(String[] args) {
		try {
			getString(2000, 2000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getString(int x, int y) throws IOException {
		FileWriter fw = new FileWriter("C:/Users/jin36/Documents/out.txt");
		fw.write(1+"\r\n");
		fw.write(x + " "+ y +"\r\n");
		try {
			Random rnd = new Random();
			for (int i = 0; i < x; i++) {
				StringBuffer sb = new StringBuffer();
				for(int j=0; j<y; j++) {
					// a-z
					sb.append((char) ((int) (rnd.nextInt(26)) + 97));
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
