package numberingApartment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 백준 2667 : 단지 번호 붙이기
 * 풀이 전략
 * 1. 초기화
 * 2. DFS로 풀이
 *   - 상하좌우로 움직이며 단지가 형성되는지 확인 (dx, dy)
 *   - 단지가 형성되면 dfs( count + 1)
 *   - 아파트를 욺직일 때 마다 visited 행렬 업데이트
 *   - 움직일 곳이 없다면 다음 단지로 이동
 *     - visited = false인 곳을 처음 시작지로 한다.
 *   - 단지가 완성되면 오름차순으로 정렬되는 pq에 저장
 * 3. 출력
 *
 */
public class Main {
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static PriorityQueue<Integer> pq;
	private static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		pq = new PriorityQueue<Integer>();
		
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		// 2. dfs로 풀이
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					visited[i][j] = true;
					int count = dfs(i, j, 1);
					pq.offer(count);
				}
			}
		}
		System.out.println(pq.size());
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
	private static int dfs(int x, int y, int count) {
		for(int k=0; k<4; k++) {
			int nextX = x + dx[k];
			int nextY = y + dy[k];
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >=N) continue;
			if(map[nextX][nextY]==0) continue;
			if(!visited[nextX][nextY]) {
				if(map[nextX][nextY]!=0) {
					visited[nextX][nextY] = true;
					count += dfs(nextX, nextY, 1);
				}
			}
		}
		return count;
	}

}
