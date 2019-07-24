package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 백준 1261 - 알고스팟
 * 
 * 풀이 전략
 * 1. 초기화
 * 2. Point 클래스 생성 ( x좌표, y좌표, 벽을 부순 최저 수 )
 * 3. 현재 위치까지 최소한으로 벽을 부순 수를 d[][]에 저장하고,
 *    priority queue를 사용해서 다음 방으로 이동할 때 최소의 가중치를 가진 점으로 이동하게 한다.
 *    이동은 좌, 우, 위, 아래 다 이동 가능해야한다. 벽을 뚫지않을 경우 가중치가 올라가지 않으므로 좌측이나 윗방향으로도 이동가능
 *
 */
public class Main {
	static int M, N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int INF = 100 * 100 + 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 초기화
		String[] MN = br.readLine().split(" ");
		N = Integer.parseInt(MN[0]); M = Integer.parseInt(MN[1]);
		int[][] map = new int[M][N];
		String temp[] = new String[N];
		for(int i=0; i<M; i++) {
			temp = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		int[][]res = getShortestWay(map);
		print(res);
		// (0, 0) -> (M, N) 으로 가는 최소의 거리를 찾으면 되는데
		// 다익스트라를 이용할 경우 d[M][N]에 최단거리가 들어있다.
		
		System.out.println(res[M-1][N-1]);
	}

	//3. 현재 위치까지 최소한으로 벽을 부순 수를 d[][]에 저장하고,
	//priority queue를 사용해서 다음 방으로 이동할 때 최소의 가중치를 가진 점으로 이동하게 한다.
	//이동은 좌, 우, 위, 아래 다 이동 가능해야한다. 벽을 뚫지않을 경우 가중치가 올라가지 않으므로 좌측이나 윗방향으로도 이동가능
	private static int[][] getShortestWay(int[][] map) {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.offer(new Point(0, 0, 0));
		int[][] d = new int[M][N];
		for(int i = 0; i<M; i++) {
			for(int j=0; j<N; j++) {
				d[i][j] = INF;
			}
		}
		d[0][0] = 0;
		while(!pq.isEmpty()) {
			Point thisPoint = pq.poll();
			int thisX = thisPoint.getX();
			int thisY = thisPoint.getY();
			int thisW = thisPoint.getW();
			for(int i=0; i<4; i++) {
				int nextX = thisX + dx[i];
				int nextY = thisY + dy[i];
				if(nextX < 0 || nextY < 0 ||nextX >= M || nextY >= N) continue;
				if(map[nextX][nextY] == 0) {
					if(d[nextX][nextY] > thisW) {
						d[nextX][nextY] = thisW;
						pq.offer(new Point(nextX, nextY, thisW));
					}
				} else {
					if(d[nextX][nextY] > thisW + 1) {
						d[nextX][nextY] = thisW + 1;
						pq.offer(new Point(nextX, nextY, thisW + 1));
					}
				}
			}
		}
		return d;
	}
	private static void print(int[][] map){
		for(int i = 0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}

//2. Point 클래스 생성 ( x좌표, y좌표, 벽을 부순 최저 수 )
class Point implements Comparable<Point>{
	int x;
	int y;
	int w;
	public Point(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int compareTo(Point o) {
		return this.w > o.w ? 1 : -1;
	}
}
