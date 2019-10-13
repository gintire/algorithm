package greedypanda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이 전략
 * 1. 초기화
 * 2. 주변에 자신보다 적은 양의 나무가 있는 숲은 처음 스타트할 곳이 아님
 *    만약 주변에 자신이 가장 작은 값이라면 dfs()
 * 3. dfs()
 *   - visited[][] : 방문한 곳을 제외하고 최대로 판다가 돌아 댕길 수 있는 범위 구한다.
 */
public class Main {
	private static int count = 1;
	private static boolean visited[][];
	private static int map[][];
	private static int N;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				boolean isMin = true;
				for(int k=0; k<4; k++) {
					if((i + dx[k]) < 0 || (i + dx[k]) >=N || (j + dy[k]) < 0 || (j + dy[k]) >=N ) continue;
					if(map[i][j] > map[i+dx[k]][j+dy[k]]) {
						isMin = false;
						break;
					}
				}
				if(isMin) {
					visited = new boolean[N][N];
					visited[i][j] = true;
					dfs(i, j, 1);				
				}
			}
		}
		
		System.out.println(count);
	}
	private static void dfs(int x, int y, int liveDates) {
		int nextX, nextY;
		for(int k=0; k<4; k++) {
			nextX = x + dx[k];
			nextY = y + dy[k];
			if(nextX < 0 || nextX >=N || nextY < 0 || nextY >=N) continue;
			if(visited[nextX][nextY]) continue;
			if(map[x][y] < map[nextX][nextY]) {
				visited[nextX][nextY] = true;
				dfs(nextX, nextY, liveDates+1);
				visited[nextX][nextY] = false;
			}
		}
		count = count<liveDates?liveDates:count;
	}

}
