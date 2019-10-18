package treasureisland;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 2589 - 보물섬
 * 풀이 전략
 * 1. 초기화
 *  - 지도 입력 받음
 */

public class Main {
	static int N, M;
	static String map[][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM;
		NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		map = new String[N][M];
		
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = temp[j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j].equals("L")) {
					Queue<Point> queue = new LinkedList<Point>();
					boolean[][] visited = new boolean[N][M];
					queue.offer(new Point(i, j, 0));
					visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						Point thisXY = queue.poll();
						int x = thisXY.x;
						int y = thisXY.y;
						int count = thisXY.count;
						
						max=count>max?count:max;
						
						for(int k=0;k<4;k++) {
							int nextX = x + dx[k];
							int nextY = y + dy[k];
							if(validate(nextX, nextY)) {
								if(!visited[nextX][nextY] && map[nextX][nextY].equals("L")) {
									queue.offer(new Point(nextX, nextY, count+1));
									visited[nextX][nextY] = true;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(max);
	}
	
	private static boolean validate(int nextX, int nextY) {
		if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) return false;
		return true;
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
