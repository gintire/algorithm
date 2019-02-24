package robot_cleaner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M, r, c, d, tempR, tempC, tempD;
    static int[][] map;
    static boolean[][] visited;
	public static void main( String[] args ) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
		String[] rcd = br.readLine().split(" ");
		// r = row, c = column, d = direction
		r = Integer.parseInt(rcd[0]); c = Integer.parseInt(rcd[1]); d = Integer.parseInt(rcd[2]);
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		String[] temp;
		int thisNum = 0;
		for(int i=0; i<N; i++) {
			temp = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				thisNum = Integer.parseInt(temp[j]);
				map[i][j] = thisNum;
				if(thisNum == 1) visited[i][j] = true;
			}
		}
		
		int res;
		if(map[r][c]==0) res = 1;
		else res = 0;
		
		while(true) {
			int i = 0;
			if(checkAround()) {
				getBack();
				if(tempR < 0 || tempC < 0 || tempR >= N || tempC >= M ||map[tempR][tempC]==1) break;
				else {
					r = tempR;
					c = tempC;
				}
			}
			while(i<4) {
				if(checkLeft()) {
					res++;
					move();
					break;
				}
				if(d-1<0) d=3;
				else d-=1;
				
				i++;
			}
		}
		System.out.print(res);
	}
	// Step1. check left direction
	public static boolean checkLeft() {
		getRC();
		if(tempR < 0 || tempR >= N || tempC < 0 || tempC>=M) {
			return false;
		}
		
		if(!visited[tempR][tempC] && map[tempR][tempC] == 0) {
			return true;
		}
		return false;
	}
	
	//Step2. Move Robot Cleaner
	public static void move() {
		getRC();
		visited[r][c] = true;
		r = tempR;
		c = tempC;
		d = tempD;
	}
	private static boolean checkAround() {
		if(r-1 >=0 && c-1 >=0 && r+1<N && c+1 < M)
			if(visited[r-1][c] && visited[r][c-1] && visited[r+1][c] && visited[r][c+1]) return true;
		return false;
	}
	public static void getRC() {
		tempR = r;
		tempC = c;
		
		if(d == 0) {
			tempC-=1;
			tempD = 3;
		}
		if(d == 1) {
			tempR-=1;
			tempD = 0;
		}
		if(d == 2) {
			tempC+=1;
			tempD = 1;
		}
		if(d == 3) {
			tempR+=1;
			tempD = 2;
		}
	}
	public static void getBack() {
		tempR = r;
		tempC = c;
		visited[r][c] = true;
		if(d == 0) {
			tempR+=1;
		}
		if(d == 1) {
			tempC-=1;
		}
		if(d == 2) {
			tempR-=1;
		}
		if(d == 3) {
			tempC+=1;
		}
	}
	
	public static void getMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i==r && j==c)
					System.out.print("*");
				else System.out.print(map[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]==true)
					System.out.print("O");
				else System.out.print("X");
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
}
