package searchNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
/**
 * 백준 No1920
 * 수찾기
 * 
 * 1. 초기화
 * 2. B 배열의 값을 n으로 돌며 A 배열에서 있는지 이진 탐색(logn)으로 해결
 * 시간 복잡도 : nlogN
 * @param args
 * @throws IOException 
 * @throws NumberFormatException 
 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1. 초기화
		int n = Integer.parseInt(br.readLine());
		List<Integer> A = new ArrayList<Integer>();
		String[] tempString = br.readLine().split(" ");
		for(int i=0; i<n; i++) A.add(Integer.parseInt(tempString[i]));
		
		int m = Integer.parseInt(br.readLine());
		List<Integer> B = new ArrayList<Integer>();
		tempString = br.readLine().split(" ");
		for(int i=0; i<m; i++) B.add(Integer.parseInt(tempString[i]));
		Collections.sort(A);
		System.out.println(A);
		//2. B 배열의 값을 n으로 돌며 A 배열에서 있는지 이진 탐색(logn)으로 해결
		for(int i=0; i<m; i++) {
			if(Collections.binarySearch(A, B.get(i))<0)
				System.out.println(0);
			else System.out.println(1);
		}
	}
}