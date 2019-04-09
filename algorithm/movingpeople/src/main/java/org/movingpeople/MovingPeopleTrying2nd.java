package org.movingpeople;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MovingPeopleTrying2nd {

	/**
	 * 
	 * @param args
	 * 1. 국경을 열 수 있는가 확인하는 함수 작성 (boolean openBorder(int a, int b))
	 * 2. 초기화 ( 맵 입력)
	 * 3. 인구이동이 가능한 나라 검색 - union array에 저장
	 * 4. dfs (int x, int y) 
	 * 5. 인구 이동 함수 작성 ( void move())
	 * 6. 인구 이동이 없을 때 까지 반복
	 */
	public static int[][] map;
	public static boolean[][] visited;
	public static int N, L, R;
	public static int[] dx = {1, -1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	public static List<List<Integer>> unionList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NLR = br.readLine().split(" ");
		N = Integer.parseInt(NLR[0]); L = Integer.parseInt(NLR[1]); R = Integer.parseInt(NLR[2]);
		
		map = new int[N][N];
		// 2. 초기화
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		int res=0;
		
		while(true) {
			visited = new boolean[N][N]; //방문 초기화
			if(scan()) {
				res++;
			} else {
				break;
			}
		}
		printMap();
		System.out.println(res);
	} 
	//5. 인구 이동
	static void move(List<Integer> union) {
		int x =0; int y=0;
		int thisXY=0;
		int totPeople = 0;
		
		for(int j=0; j<union.size(); j++) {
			thisXY = union.get(j);
			x = thisXY / N;
			y = thisXY % N;
			totPeople += map[x][y];
		}
		for(int j=0; j<union.size(); j++) {
			thisXY = union.get(j);
			x = thisXY / N;
			y = thisXY % N;
			map[x][y] = totPeople / union.size();
		}
	}
	
	//4. 인구 이동이 가능한 나라의 연합을 만듬
	static void dfs(int x, int y, List<Integer> union) {
		visited[x][y] = true;
		int next_x = 0; int next_y = 0;
		for(int i=0; i<4; i++) {
			next_x = x + dx[i];
			next_y = y + dy[i];
			if(next_x < 0 || next_x >=N ||next_y < 0 || next_y >=N) continue;
			if(!visited[next_x][next_y]) {
				if(openBorder(map[x][y], map[next_x][next_y])) {
					union.add(next_x * N + next_y);
					dfs(next_x, next_y, union);
				}
			}
		}
	}
	
	//3. 인구 이동이 가능한 나라 있는지 검색
	static boolean scan() {
		ArrayList<Integer> union;
		boolean isPossibleMove = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					union = new ArrayList<Integer>();
					union.add(i*N + j);
					dfs(i, j, union);
					if(union.size() > 1) {
						move(union);
						isPossibleMove = true;
					}
				}
			}
		}
		return isPossibleMove;
	}
	
	//1. 국경을 열 수 있는가 확인 하는 함수 작성
	static boolean openBorder(int countryA, int countryB) {
		int diff = Math.abs(countryA - countryB); 
		if(diff < L) return false;
		if(diff > R) return false;
		return true;
	}
	
	//0. Test
	static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+ " ");
			}
			System.out.println();
		}
	}
}
