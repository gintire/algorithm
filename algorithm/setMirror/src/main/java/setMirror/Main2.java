package setMirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 백준 2151 거울 설치
 * 풀이 전략
 * 1. 초기화
 *  - Point 인터페이스 생성. 문 클래스를 생성하여 문 위치 저장
 *  - map에 문(1)과 거울 위치(2), 벽 위치(0) 저장
 * 2. dfs로 풀이
 *  - 빛은 일직선으로 움직이니 거울을 만나면 거울의 양방향으로 움직이도록 재귀 호출
 *    수직 으로 빛이 이동하고 있었다면 좌, 우 방향으로
 *    수평 으로 빛이 이동하고 있었다면 상, 하 방향으로
 *  - 빛은 방향이 있으므로 좌표와 방향을 가지는 클래스 생성 ( Light )
 *  - 거울을 만나면 거울 count++
 * 3. 문을 발견한다면 count값 비교해서 최소값을 정답으로 한다.
 *
 */
public class Main2 {
	private static int res = Integer.MAX_VALUE;
	private static int N; 
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static int UP = 0;
	private static int LEFT = 1;
	private static int RIGHT = 2;
	private static int DOWN = 3;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] tempStr;
		ArrayList<Door> doors = new ArrayList<Door>();
		map = new int[N][N]; 
		visited = new boolean[N][N][4];
		PriorityQueue<Light> q = new PriorityQueue<Light>();
		for(int i=0; i<N; i++) {
			tempStr = br.readLine().split("");
			for(int j=0; j<N; j++) {
				if(tempStr[j].equals("#")) {
					doors.add(new Door(i, j));
					map[i][j] = 1;
				} else if (tempStr[j].equals("!")) {
					map[i][j] = 2;
				} else if (tempStr[j].equals("*")) {
					map[i][j] = 3;
				}
			}
		}
		Door startDoor = doors.get(0);
		Door destDoor = doors.get(1);
		
		
		q.offer(new Light(startDoor.getX(), startDoor.getY(), 0, UP));
		q.offer(new Light(startDoor.getX(), startDoor.getY(), 0, LEFT));
		q.offer(new Light(startDoor.getX(), startDoor.getY(), 0, RIGHT));
		q.offer(new Light(startDoor.getX(), startDoor.getY(), 0, DOWN));
		
		while(!q.isEmpty()) {
			Light thisPnt = q.poll();
			int thisX = thisPnt.getX();
			int thisY = thisPnt.getY();
			int count = thisPnt.getMirrorCnt();
			int direction = thisPnt.getDirection();
			int[] nextD = new int[2];
			int nextX = thisX;
			int nextY = thisY;
			if(destDoor.equals(thisPnt)) {
				res = res>count?count:res;
				continue;
			}
			if(direction == DOWN) {
				nextX = thisX + 1;
				nextD[0] = LEFT;
				nextD[1] = RIGHT;
			} else if(direction == UP) {
				nextX = thisX - 1;
				nextD[0] = LEFT;
				nextD[1] = RIGHT;
			} else if(direction == RIGHT) {
				nextY = thisY + 1;
				nextD[0] = UP;
				nextD[1] = DOWN;
			} else if(direction == LEFT) {
				nextY = thisY - 1;
				nextD[0] = UP;
				nextD[1] = DOWN;
			}
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
			if(visited[thisX][thisY][direction]) continue;
			visited[thisX][thisY][direction] = true;
			if(map[nextX][nextY] == 3) continue;
			if(map[nextX][nextY] == 2 ){
				q.offer(new Light(nextX, nextY, count+1, nextD[0]));
				q.offer(new Light(nextX, nextY, count+1, nextD[1]));
			}
			q.offer(new Light(nextX, nextY, count, direction));
			print(thisX, thisY);
		}
		
		if(res == Integer.MAX_VALUE) res = 0;
		System.out.println(res);
	}
	private static void print(int x, int y) {
		System.out.println("============================");
		for(int i=0; i < N; i++) {
			for(int j=0; j<N; j++) {
				if(i == x && j ==y) System.out.print("@");
				else System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}