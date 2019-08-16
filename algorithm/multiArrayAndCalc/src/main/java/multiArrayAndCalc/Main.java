package multiArrayAndCalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {

	/**
	 * 풀이 전략
	 * 1. 초기화 
	 *    - 전역 변수 
	 *    - int[101][101] map
	 *    - int res;
	 *    - int r, c;
	 *    - int [101] idx
	 * 2. A[r][c] = k 확인 함수
	 *    - check();
	 * 3. if( r >= c ) rCalc
	 *    if( r < c ) cCalc
	 * 4. rCalc ()
	 *    - for idx[map[i]] ++;
	 *    - 등장 횟수 큰 수, 등장 횟수 
	 */
	private static int[][] MAP = new int[200][200];
	private static int res = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] RCK = br.readLine().split(" ");
		int r = Integer.parseInt(RCK[0]);
		int c = Integer.parseInt(RCK[1]);
		int k = Integer.parseInt(RCK[2]);
		
		for(int i=1; i<=3; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=1; j<=3; j++) {
				MAP[i][j] = Integer.parseInt(temp[j-1]);
			}
		}
		
		int idxR = 3, idxC = 3;
		
		while(!check(r, c, k)) {
			if(res > 100) {
				res = -1;
				break;
			}
			if(idxR > 100) { 
				idxR = 100;
			}
			if(idxC > 100) {
			   idxC = 100;
			}
			
			if(idxC <= idxR) idxC = rCalc(idxR, idxC);
			else idxR = cCalc(idxR, idxC);
		
			res++;
			/*System.out.println("=========================");
			System.out.println(res + " " + idxR + " " + idxC);
			System.out.println("=========================");*/
		}
		System.out.println(res);
	}
	private static int cCalc(int idxR, int idxC) {
		int maxR = 0;
		for(int i = 1; i <= idxC; i++) {
			ArrayList<NumsFreq> tempArr = new ArrayList<NumsFreq>();
			int[] numbers = new int[101];
			for(int j=1; j<= idxR; j++) {
				numbers[MAP[j][i]]++;
				MAP[j][i] = 0;
			}
			for(int k=1; k <= 100; k++) {
				if(numbers[k]==0) continue;
				tempArr.add(new NumsFreq(k, numbers[k]));
			}
			Collections.sort(tempArr);
			
			int thisRCount = tempArr.size();
			if(maxR < thisRCount * 2) maxR = thisRCount * 2;
			int idx = 1;
			for(int l=0; l<thisRCount; l++) {
				MAP[idx++][i] = tempArr.get(l).numbers;
				MAP[idx++][i] = tempArr.get(l).freq;
			}
		}
		//print();
		return maxR;
	}
	private static int rCalc(int idxR, int idxC) {
		int maxC = 0;
		for(int i = 1; i <= idxR; i++) {
			ArrayList<NumsFreq> tempArr = new ArrayList<NumsFreq>();
			int[] numbers = new int[101];
			for(int j=1; j<= idxC; j++) {
				numbers[MAP[i][j]]++;
				MAP[i][j] = 0;
			}
			for(int k=1; k <= 100; k++) {
				if(numbers[k]==0) continue;
				tempArr.add(new NumsFreq(k, numbers[k]));
			}
			Collections.sort(tempArr);
			
			int thisRCount = tempArr.size();
			if(maxC < thisRCount * 2) maxC = thisRCount * 2;
			int idx = 1;
			for(int l=0; l<thisRCount; l++) {
				MAP[i][idx++] = tempArr.get(l).numbers;
				MAP[i][idx++] = tempArr.get(l).freq;
			}
		}
		//print();
		return maxC;
	}
	
	private static void print() {
		for(int i=1; i<=22; i++) {
			for(int j=1; j<=22; j++) {
				//if(MAP[i][j] == 0)continue;
				System.out.print(MAP[i][j] + " ");
			}
			//if(MAP[i][1] == 0)continue;
			System.out.println();
		}
	}
	private static boolean check(int r, int c, int k) {
		if(MAP[r][c] == k) return true;
		return false;
	}

}

class NumsFreq implements Comparable<NumsFreq> {
	int numbers;
	int freq;
	public NumsFreq(int numbers, int freq) {
		super();
		this.numbers = numbers;
		this.freq = freq;
	}

	public int compareTo(NumsFreq o) {
		if(this.freq < o.freq) {
			return -1;
		} else if (this.freq == o.freq) {
			if(this.numbers < o.numbers)
				return -1;
		}
		return 1;
	}
	@Override
	public String toString() {
		return numbers + " " + freq;
	}
}
