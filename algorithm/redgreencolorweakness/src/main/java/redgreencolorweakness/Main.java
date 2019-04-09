package redgreencolorweakness;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 풀이 전략
 * 1. 초기화 ( N과 N*N 그리드를 받는다)
 * 2. 맵을 스캔하는 함수 생성 (scan())
 *    방문한적이 없는 곳이면 컬러 그룹에 넣는다. (arraylist cGroup, rgcGroup)
 *    cGroup - 일반인, rgcGroup - 적록색약인
 * 3. 연속된 같은 색을 찾는다. (dfs)
 *    방문한 곳은 방문한것 표시 (visited)
 *    색약일 경우 (R, G를 같은 색상으로 취급)
 * 4. 색 비교 함수 생성 ( 색약일 경우와, 색약이 아닐 경우 boolean isSameColor(char a, char b))
 * 5. 전역 변수 N
 *    char[][] map
 *    boolean[][] visited
 */

public class Main {
	private static char[][] map;
	private static boolean[][] visited;
	private static boolean[][] rgVisited;
	private static int N;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static ArrayList<Character> cGroup = new ArrayList<Character>();
	private static ArrayList<Character> rgcGroup = new ArrayList<Character>();
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:/Users/jin36/Documents/out3.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		/*
		여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			rgVisited = new boolean[N][N];
			
			//1. 초기화 ( N과 N*N 그리드를 받는다)
			for(int i=0; i<N; i++) {
				String temp = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = temp.charAt(j);
				}
			}
			//print();
			scan();
			System.out.println(cGroup.toString());
			System.out.println(rgcGroup.toString());
			System.out.println(cGroup.size() + " "+ rgcGroup.size());
		}
	}
	//4. 색 비교 함수 생성 ( 색약일 경우와, 색약이 아닐 경우 boolean isSameColor())
	// flag true - 색약, false - 색약 아님
	private static boolean isSameColor (char a, char b, boolean flag) {
		// 색약일 경우
		if(a == b) return true;
		if(flag) {
			if (a == 'R' && b == 'G') return true;
			if (a == 'G' && b == 'R') return true;
		}
		return false;
	}
	
	//3, 연속된 같은 색을 찾는다. (dfs)
		private static void dfs2(int x, int y) {
			rgVisited[x][y] = true;
			int next_x = 0, next_y = 0;
			for(int i=0; i < 4; i++) {
				next_x = x + dx[i];
				next_y = y + dy[i];
				if(next_x >= N || next_x < 0 || next_y >= N || next_y < 0) continue;
				if(!rgVisited[next_x][next_y]) {
					if(isSameColor(map[x][y], map[next_x][next_y], true)) {
						dfs2(next_x, next_y);
					}
				}
			}
			
		}
	
	//3, 연속된 같은 색을 찾는다. (dfs)
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		int next_x = 0, next_y = 0;
		for(int i=0; i < 4; i++) {
			next_x = x + dx[i];
			next_y = y + dy[i];
			if(next_x >= N || next_x < 0 || next_y >= N || next_y < 0) continue;
			if(!visited[next_x][next_y]) {
				if(isSameColor(map[x][y], map[next_x][next_y], false)) {
					dfs(next_x, next_y);
				}
			}
		}
	}
	
	//2. 맵을 스캔하는 함수 생성 (scan())
	private static void scan() {
		for(int i=0; i < N; i++) {
			for(int j=0; j < N ; j++) {
				if(!visited[i][j]) {
					cGroup.add(map[i][j]);
					dfs(i, j);
				}
				if(!rgVisited[i][j]) {
					rgcGroup.add(map[i][j]);
					dfs2(i, j);
				}
			}
		}
	}
	
	//0. test
	private static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
