package setMirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * ���� 2151 �ſ� ��ġ
 * Ǯ�� ����
 * 1. �ʱ�ȭ
 *  - Point �������̽� ����. �� Ŭ������ �����Ͽ� �� ��ġ ����
 *  - map�� ��(1)�� �ſ� ��ġ(2), �� ��ġ(0) ����
 * 2. dfs�� Ǯ��
 *  - ���� ���������� �����̴� �ſ��� ������ �ſ��� ��������� �����̵��� ��� ȣ��
 *    ���� ���� ���� �̵��ϰ� �־��ٸ� ��, �� ��������
 *    ���� ���� ���� �̵��ϰ� �־��ٸ� ��, �� ��������
 *  - ���� ������ �����Ƿ� ��ǥ�� ������ ������ Ŭ���� ���� ( Light )
 *  - �ſ��� ������ �ſ� count++
 * 3. ���� �߰��Ѵٸ� count�� ���ؼ� �ּҰ��� �������� �Ѵ�.
 *
 */
public class Main {
	private static int res = Integer.MAX_VALUE;
	private static int N; 
	private static int[][] map;
	private static boolean[][][] visited;
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
		Direction d = null;
		/*
		 * // ���� ���� ���� ���� ��� if(startDoor.getX() == 0) d = Direction.DOWN; // �ð����� �Ʒ���
		 * ���� ��� if(startDoor.getX() == N-1) d = Direction.UP; // ���۹��� ���ʿ� ���� ���
		 * if(startDoor.getY() == 0) d = Direction.RIGHT; // ���� ���� �����ʿ� ���� ���
		 * if(startDoor.getY() == N-1) d = Direction.LEFT;
		 */
		
		// UP
		dfs(startDoor.getX(), startDoor.getY(), destDoor, UP, 0);
		// LEFT
		dfs(startDoor.getX(), startDoor.getY(), destDoor, LEFT, 0);
		// RIGHT
		dfs(startDoor.getX(), startDoor.getY(), destDoor, RIGHT, 0);
		// DOWN
		dfs(startDoor.getX(), startDoor.getY(), destDoor, DOWN, 0);
		if(res == Integer.MAX_VALUE) res = 0;
		System.out.println(res);
	}
	private static void dfs(int thisX, int thisY, Door destDoor, int direction, int count) {
		if(thisX < 0 || thisY < 0 || thisX >= N || thisY >= N) return;
		if(thisX == destDoor.getX() && thisY == destDoor.getY()) {
			res = res>count?count:res;
		}
		int nextX = thisX, nextY = thisY;
		int[] nextD = new int[2];
		if(direction == DOWN) {
			nextX = thisX + 1;
			nextD[0] = LEFT;
			nextD[1] = RIGHT;
		} else if(direction == UP ) {
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
		if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) return;
		if(map[nextX][nextY] == 3 ) return;
		if(map[nextX][nextY] == 0 || map[nextX][nextY] == 1) dfs(nextX, nextY, destDoor, direction, count);
		else if(map[nextX][nextY] == 2) {
			if(!visited[nextX][nextY][direction]) {
				visited[nextX][nextY][direction] = false;
				dfs(nextX, nextY, destDoor, nextD[0], count+1);
                visited[nextX][nextY][direction] = true;
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
	}
}

enum Direction {
	UP, DOWN, RIGHT, LEFT;
}
class Light implements Point, Comparable<Light> {
	int x, y;
	int mirrorCnt;
	int direction;
	public Light(int x, int y, int mirrorCnt, int direction) {
		this.x = x;
		this.y = y;
		this.mirrorCnt = mirrorCnt;
		this.direction = direction;
	}
	public int getMirrorCnt() {
		return mirrorCnt;
	}
	public void setMirrorCnt(int mirrorCnt) {
		this.mirrorCnt = mirrorCnt;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
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
	public int compareTo(Light l2) {
		return this.mirrorCnt - l2.mirrorCnt;
	}
}
// 1.2 Door Ŭ����
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
	public boolean equals(Light obj) {
		if(this.x == obj.x) {
			if(this.y == obj.y) return true;
		}
		return false;
	}
	
}
// 1.1Point �������̽�
interface Point {
}