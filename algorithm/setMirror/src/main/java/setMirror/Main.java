package setMirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
public class Main {
	private static int res = Integer.MAX_VALUE;
	private static int N; 
	private static int[][] map;
	private static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] tempStr;
		ArrayList<Door> doors = new ArrayList<Door>();
		map = new int[N][N]; 
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			tempStr = br.readLine().split("");
			for(int j=0; j<N; j++) {
				if(tempStr[j].equals("#")) {
					doors.add(new Door(i, j));
					map[i][j] = 1;
				} else if (tempStr[j].equals("!")) {
					map[i][j] = 2;
				}
			}
		}
		Door startDoor = doors.get(0);
		Door destDoor = doors.get(1);
		Direction d = null;
		// 시작 문이 위에 있을 경우
		if(startDoor.getX() == 0) d = Direction.DOWN;
		// 시간문이 아래에 있을 경우
		if(startDoor.getX() == N-1) d = Direction.UP;
		// 시작문이 왼쪽에 있을 경우
		if(startDoor.getY() == 0) d = Direction.RIGHT;
		// 시작 문이 오른쪽에 있을 경우
		if(startDoor.getY() == N-1) d = Direction.LEFT;
		dfs(startDoor.getX(), startDoor.getY(), destDoor, d, 0);
		if(res == Integer.MAX_VALUE) res = 0;
		System.out.println(res);
	}
	private static void dfs(int thisX, int thisY, Door destDoor, Direction direction, int count) {
		print(thisX, thisY);
		if(thisX < 0 || thisY < 0 || thisX >= N || thisY >= N) return;
		if(thisX == destDoor.getX() && thisY == destDoor.getY()) {
			System.out.println("find");
			res = res>count?count:res;
		}
		int nextX = thisX, nextY = thisY;
		Direction[] nextD = new Direction[2];
		if(direction.equals(Direction.DOWN)) {
			nextX = thisX + 1;
			nextD[0] = Direction.LEFT;
			nextD[1] = Direction.RIGHT;
		} else if(direction.equals(Direction.UP)) {
			nextX = thisX - 1;
			nextD[0] = Direction.LEFT;
			nextD[1] = Direction.RIGHT;
		} else if(direction.equals(Direction.RIGHT)) {
			nextY = thisY + 1;
			nextD[0] = Direction.UP;
			nextD[1] = Direction.DOWN;
		} else if(direction.equals(Direction.LEFT)) {
			nextY = thisY - 1;
			nextD[0] = Direction.UP;
			nextD[1] = Direction.DOWN;
		}
		if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) return;
		if(map[nextX][nextY] == 0 || map[nextX][nextY] == 1) dfs(nextX, nextY, destDoor, direction, count);
		else if(map[nextX][nextY] == 2) {
			if(!visited[nextX][nextY]) {
				visited[nextX][nextY] = true;
				dfs(nextX, nextY, destDoor, nextD[0], count+1);
				dfs(nextX, nextY, destDoor, nextD[1], count+1);
			}
		}
	}
	private static void print(int x, int y) {
		for(int i=0; i < N; i++) {
			for(int j=0; j<N; j++) {
				if(i == x && j ==y) System.out.print("*");
				else System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("======================");
	}
}

enum Direction {
	UP, DOWN, RIGHT, LEFT;
}
class Mirror implements Point {
	int x, y;
	Direction direction;
	public Mirror(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
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
}
// 1.2 Door 클래스
class Door implements Point {
	int x, y;
	public Door(int x, int y) {
		this.x = x;
		this.y = y;
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
	public boolean equals(Door obj) {
		if(this.x == obj.x) {
			if(this.y == obj.y) return true;
		}
		return false;
	}
	
}
// 1.1Point 인터페이스
interface Point {
}
