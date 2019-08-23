package babyShark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아기 상어
 * 백준 16236번 문제
 * 
 * 1. 초기화
 *     - 상 하 좌 우 움직임
 *     - 
 * 2. 물고기 인터페이스 생성
 *     - 크기
 *     - 비교 함수
 * 3. 상어 클래스 생성
 *     - 먹은 크기
 * 4. 물고기 클래스 생성
 * 5. 아기상어 이동 ( dfs )
 *     - 
 * 6. 
 */
public class Main {
	public static int[] dx = {1, -1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static int N;
	public static int res = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int sharkPositionX = 0;
		int sharkPositionY = 0;
		// 1. 초기화
		Fish[][] map = new Fish[N + 2][N + 2];
		
		for(int i=1; i<=N; i++) {
			String[] tempString = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				int thisFish = Integer.parseInt(tempString[j-1]);
				if(thisFish == 0) map[i][j] = null;
				else if(thisFish == 9) {
					sharkPositionX = i;
					sharkPositionY = j;
					map[i][j] = new Shark(2);
				}
				else map[i][j] = new Fishes(thisFish);			
			}
		}
		moveShark(sharkPositionX, sharkPositionY, map);
		System.out.println(res);
	}
	
	private static int moveShark(int x, int y, Fish[][] map) {
		int count = 0;
		boolean visited[][] = new boolean[N+2][N+2];
		visited[x][y] = true;
		dfs(x, y, count, map, visited);
		return count;
	}
	
	private static void dfs(int x, int y, int count, Fish[][] map, boolean[][] visited) {
		Fish[][] tempMap = copytempMap(map);
		boolean[][] tempvisited = copytempVisitedMap(visited);
		int nextX, nextY;
		for(int i=0; i<4; i++) {
			nextX = x + dx[i];
			nextY = y + dy[i];
			// 아기상어가 움직일 수 있는 지 없는지 확인
			if( nextX > N || nextY > N || nextX <= 0 || nextY <= 0) continue;
			if( tempvisited[nextX][nextY] ) continue;
			if(tempMap[nextX][nextY] == null) {
				tempMap[nextX][nextY] = tempMap[x][y];
				tempMap[x][y] = null;
				tempvisited[nextX][nextY] = true;
				dfs(nextX, nextY, count+1, tempMap, tempvisited);
				res = Math.max(res, count);
			} else {
				if(tempMap[x][y].compareTo(tempMap[nextX][nextY]) < 0) continue;
				if( tempMap[x][y].compareTo(tempMap[nextX][nextY]) > 0) {
					if(tempMap[x][y] instanceof Shark) {
						Shark thisShark = (Shark) tempMap[x][y];
						thisShark.ateAmnt += 1;
						if(thisShark.ateAmnt == thisShark.weight) {
							thisShark.weight += 1;
							thisShark.ateAmnt = 0;
						}
						tempMap[x][y] = thisShark;
					}
				}
				tempMap[nextX][nextY] = tempMap[x][y];
				tempMap[x][y] = null;
				tempvisited[nextX][nextY] = true;
				dfs(nextX, nextY, count+1, tempMap, tempvisited);
				res = Math.max(res, count);
			}
		}
	}
	private static boolean[][] copytempVisitedMap(boolean[][] map) {
		boolean[][] copiedMap = new boolean[N+2][N+2];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) copiedMap[i][j] = map[i][j];
		}
		return copiedMap;
	}

	private static Fish[][] copytempMap(Fish[][] map) {
		Fish[][] copiedMap = new Fish[N+2][N+2];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) copiedMap[i][j] = map[i][j];
		}
		return copiedMap;
	}

	private static void print(Fish[][] map) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if (map[i][j] ==null) System.out.print(0);
				else System.out.print(map[i][j].weight + " ");
			}
			System.out.println();
		}
	}
}

class Fishes extends Fish {
	public Fishes(int weight) {
		super(weight);
	}
}
//3. 상어 클래스
class Shark extends Fish {
	public Shark(int weight) {
		super(weight);
		ateAmnt = 0;
	}
	int ateAmnt;
	public int getAteAmnt() {
		return ateAmnt;
	}
	public void setAteAmnt(int ateAmnt) {
		this.ateAmnt = ateAmnt;
	}
}

//2. 물고기 클래스
abstract class Fish implements Comparable<Fish> {
	int weight;
	
	public Fish(int weight) {
		super();
		this.weight = weight;
	}

	public int compareTo(Fish o) {
		return this.weight - o.weight;
	}
}