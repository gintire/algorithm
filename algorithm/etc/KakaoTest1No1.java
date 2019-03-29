package algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class KakaoTest1No1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//int n = sc.nextInt();
		String str = "13S#200D*3T";
		String reg ="(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)|(?=[*]+)|(?=\\#+)";
		String[] res = str.split(reg);
		
		for(int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}
	}
	

}
