package escape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 3055번 문제 탈출
 * 풀이 전략
 * 1. 초기화
 *   - 비버 굴의 위치, 고스도치 위치, 물의 위치 저장.
 *   - 맵에도 저장
 * 2. dfs로 풀이
 *   - 고슴도치가 움직일 수 있는 방향으로 이동 (dx, dy에 따라)
 *   - 초가 흐를때, 물의 위치를 늘려준다.
 *   - 물을 만나지 않고 비버 굴로 갈 최소 거리
 *   - 물이 퍼지는건 dfs로
 * 3. 결과 출력
 */
public class Main {
	public static String[][] map;
	public static boolean[][] visited;
	public static boolean[][] Svisited;
	public static int minTime = Integer.MAX_VALUE;
	public static int R, C;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		// 1.초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] RC = br.readLine().split(" ");
		R = Integer.parseInt(RC[0]);
		C = Integer.parseInt(RC[1]);
		map = new String[R][C];
		visited = new boolean[R][C];
		Svisited = new boolean[R][C];
		Queue<Position> waterQ = new LinkedList<Position>();
		Queue<Position> SQ = new LinkedList<Position>();
		for(int i=0; i<R; i++) {
			String[] temp = br.readLine().split("");
			for(int j=0; j<C; j++) {
				if(temp[j].equals("S")) {
					SQ.offer(new Position(i, j));
					map[i][j] = ".";
					Svisited[i][j] = true;
					continue;
				}
				if(temp[j].equals("*")) {
					waterQ .offer(new Position(i, j));
					visited[i][j] = true;
				}
				map[i][j] = temp[j];
			}
		}
		
		int count = 0;
		while(!SQ.isEmpty()) {
			// print();
			count+=1;
			int qSize = waterQ.size(); 
			for(int i=0; i<qSize; i++) {
				Position thisW = waterQ.poll();
				if(map[thisW.x][thisW.y].equals("*")) {
					for(int k=0; k<4; k++) {
						int nextX = thisW.x + dx[k];
						int nextY = thisW.y + dy[k];
						if(checkValidate(nextX, nextY)) {
							if(!visited[nextX][nextY]) {
								if(map[nextX][nextY].equals("X")) continue;
								if(map[nextX][nextY].equals("D")) continue;
								map[nextX][nextY] = "*";
								waterQ.offer(new Position(nextX, nextY));
								visited[nextX][nextY] = true;
							}	
						}
					}
				}
			}
			
			int sSize = SQ.size();
			for(int i=0; i<sSize; i++) {
				Position thisS = SQ.poll(); 
				int thisX = thisS.x;
				int thisY = thisS.y;
				// print(thisX, thisY);
				for(int k=0 ; k<4; k++) {
					int nextX = thisX + dx[k];
					int nextY = thisY + dy[k];
					if(checkValidate(nextX, nextY)) {
						if(!Svisited[nextX][nextY]) {
							if(map[nextX][nextY].equals("X")) continue;
							if(map[nextX][nextY].equals("*")) continue;
							if(map[nextX][nextY].equals("D")) {
								minTime = minTime > count?count:minTime;
								break;
							};
							Svisited[nextX][nextY] = true;
							SQ.offer(new Position(nextX, nextY));
						}
					}
				}
			}
		}
		if(minTime == Integer.MAX_VALUE) System.out.println("KAKTUS");
		else System.out.println(minTime);
		
	}
	private static void print() {
		System.out.println("=================");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("=================");
	}
	private static void print(int x, int y) {
		System.out.println("=================");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(i==x && j == y) System.out.print("S");
				else System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("=================");
	}
	private static boolean checkValidate(int nextX, int nextY) {
		if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) return false;
		return true;
	}
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Position pos) {
			if(this.x == pos.x)
				if(this.y == pos.y)
					return true;
			return false;
		}
	}	
}


