package tomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 7576번 - 토마토문제
 *
 * 풀이전략
 * 1. 초기화
 * 2. bfs로 풀이
 * 3. q가 다돌았을 때, 안익은 토마토가 있다면 -1, 다익었으면 minday출력
 *
 */
public class Main {
	static int min_day = 0;
	static int map[][];
	static int M, N;
	static boolean visited[][];
	static int[] dx = { -1, 1, 0, 0};
	static int[] dy = { 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] MN = br.readLine().split(" ");
		M = Integer.parseInt(MN[1]);
		N = Integer.parseInt(MN[0]);
		
		Queue<Point> q = new LinkedList<Point>();
		map = new int[M][N];
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int status = Integer.parseInt(temp[j]);  
				map[i][j] = status;
				if(status == 1) q.offer(new Point(i, j, 0));
			}
		}
		
		while(!q.isEmpty()) {
			print();
			Point thisTomato = q.poll();
			int x = thisTomato.x;
			int y = thisTomato.y;
			int count = thisTomato.count;
			
			min_day = min_day < count ? count:min_day;
			for(int k=0; k<4; k++) {
				int nextX = x + dx[k];
				int nextY = y + dy[k];
				if(!validate(nextX, nextY)) continue;
				if(map[nextX][nextY] == -1 || map[nextX][nextY] == 1) continue;
				if(map[nextX][nextY] == 0 && !visited[nextX][nextY]) {
					q.offer(new Point(nextX, nextY, count+1));
					map[nextX][nextY] = 1;
					visited[nextX][nextY] = true;
				}
			}
		}
		
		if(done()) System.out.println(min_day);
		else System.out.println("-1");
	}
	
	private static boolean done() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
	private static boolean validate(int nextX, int nextY) {
		if(nextX < 0 || nextX >=M || nextY < 0 || nextY >= N) return false;
		return true;
	}

	private static void print() {
		System.out.println("==============");
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==============");
	}
}
class Point {
	int x;
	int y;
	int count;
	public Point(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
}
