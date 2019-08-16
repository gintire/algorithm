package correctCalc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class TestMain {
	@Test
	public void test() throws IOException {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		Random generator = new Random();
		FileWriter fw = new FileWriter("C:\\Users\\jin36\\Documents\\test.txt");
		
		int t = 400;
		int n = 20;
		
		sb.append(t).append("\n");
		//sb.append(n).append("\n");
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				sb.append(i + " " + j).append("\n");
			}
			  //sb.append(Math.abs(generator.nextInt(1000000000))).append(" ");
		}
		
		fw.write(sb.toString());
		
		fw.close();
	}

}
