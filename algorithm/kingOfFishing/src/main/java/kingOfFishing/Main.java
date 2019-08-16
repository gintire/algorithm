package kingOfFishing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Main {

	/**
	 * @author gintonic
	 * 
	 * 전략
	 * 1. 초기화  (입력값 받기)
	 * 2. 상어 객체 생성  Comparator ( 상어 크기 )
	 * 3. C만큼 순회하며 상어 움직임 (while n ++ < C )
	 *   - tempMap 생성하여 움직임
	 *   - 상어마다 움직 속도가 다르다.
	 * 4. C의 R이 가장 낮은 상어 낚시 성공 ( 잡은 결과에 추가 )
	 * 5. 상어가 같은 칸에 있다면 compare해서 큰 상어가 작은 상어 잡아먹음
	 * 
	 * 
	 */
	public static int R, C, M;
	public static int res = 0;
	public static Shark newMap[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 초기화
		String[] RCM = br.readLine().split(" ");
		R = Integer.parseInt(RCM[0]); C = Integer.parseInt(RCM[1]); M = Integer.parseInt(RCM[2]);
		Shark map[][];
		map = new Shark[R + 1][C + 1];
		
		String[] sharkInfo;
		int r, c, s, d, z;
		for(int i = 0; i<M; i++) {
			sharkInfo = br.readLine().split(" ");
			r = Integer.parseInt(sharkInfo[0]);
			c = Integer.parseInt(sharkInfo[1]);
			s = Integer.parseInt(sharkInfo[2]);
			d = Integer.parseInt(sharkInfo[3]);
			z = Integer.parseInt(sharkInfo[4]);
			map[r][c] = new Shark(i+1, s, d, z);
		}
		
		//print(map);
		//System.out.println();
		for(int i=1; i<C+1; i++) {
			doFishing(i, map);
			map = moveShark(map);
			//System.out.println();
			//print(map);
		}
		//System.out.println();
		
		System.out.println(res);
	}
	
	private static void doFishing(int col, Shark map[][]) {
		for(int i=1; i<=R; i++) {
			if(map[i][col] == null) continue;
			else {
				res+=map[i][col].getSize();
				map[i][col] = null;
				break;
			}
		}
	}

	private static Shark[][] moveShark(Shark map[][]) {
		newMap = new Shark[R+1][C+1];
		int distRow = R - 1;
		int distCol = C - 1;
		for(int i=1; i<R + 1; i++) {
			for(int j=1; j< C+1; j++) {
				if(map[i][j] == null) continue;
				else {
					Shark thisShark = map[i][j];
					
					int thisX = i;
					int thisY = j;
					
					int nextX = i;
					int nextY = j;
					
					int thisSharkSpeed = thisShark.speed;
					int thisSharkDirection = thisShark.direction;
					
					// up
					if(thisSharkDirection == 1){
						if(thisSharkSpeed - (thisX - 1) > 0) {
							int direction = (thisSharkSpeed - (thisX - 1)) / distRow;
							int goDist = (thisSharkSpeed - (thisX - 1)) % distRow;
							
							if(direction % 2 == 0) {
								thisShark.setDirection(2);
								nextX = 1 + goDist;
							} else {
								nextX = R - goDist;
							}
							
							if(goDist == 0) thisShark.setDirection(2);
							if(goDist == R - 1) thisShark.setDirection(1);
							
							
						} else {
							nextX = thisX - thisSharkSpeed;
						}
					}
					// down
					else if(thisSharkDirection == 2){
						if(thisSharkSpeed - (R - thisX) > 0) {
							int direction = (thisSharkSpeed - (R - thisX)) / distRow;
							int goDist = (thisSharkSpeed - (R - thisX)) % distRow;
							
							if(direction % 2 == 0) {
								thisShark.setDirection(1);
								nextX = R - goDist;
							} else {
								nextX = 1 + goDist;
							}
							
							if(goDist == 0) thisShark.setDirection(2);
							if(goDist == R - 1) thisShark.setDirection(1);
							
							
						} else {
							nextX = thisX + thisSharkSpeed;
						}
					}
					// left
					else if(thisSharkDirection == 4){
						if(thisSharkSpeed - (thisY - 1) > 0) {
							int direction = (thisSharkSpeed - (thisY - 1)) / distCol;
							int goDist = (thisSharkSpeed - (thisY - 1)) % distCol;
							
							if(direction % 2 == 0) {
								thisShark.setDirection(3);
								nextY = 1 + goDist;
							} else {
								nextY = C - goDist;
							}
							if(goDist == 0) thisShark.setDirection(3);
							if(goDist == C - 1) thisShark.setDirection(4);
							
						} else {
							nextY = thisY - thisSharkSpeed;
						}
					}
					// right
					else if(thisSharkDirection == 3){
						if(thisSharkSpeed - (C - thisY) > 0) {
							int direction = (thisSharkSpeed - (C - thisY)) / distCol;
							int goDist = (thisSharkSpeed - (C - thisY)) % distCol;
							
							if(direction % 2 == 0) {
								thisShark.setDirection(4);
								nextY = C - goDist;
							} else {
								nextY = 1 + goDist;
							}
							if(goDist == 0) thisShark.setDirection(3);
							if(goDist == C - 1) thisShark.setDirection(4);
							
						} else {
							nextY = thisY + thisSharkSpeed;
						}
					}
					
					if(newMap[nextX][nextY] == null) 
						newMap[nextX][nextY] = thisShark;
					else {
						if(newMap[nextX][nextY].compareTo(thisShark) < 0) {
							newMap[nextX][nextY] = thisShark;
						}
					}
				}
			}
		}
		return newMap;
	}

	// 검증
	public static void print (Shark[][] map) {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j] == null) System.out.print(0);
				else System.out.print(map[i][j].getId());
			}
			System.out.println();
		}
	}

}

// 2. 상성 클래스
class Shark implements Comparable<Shark>{
	int id, speed, direction, size;

	
	public Shark(int id, int speed, int direction, int size) {
		this.id = id;
		this.speed = speed;
		this.direction = direction;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Shark(int speed, int direction, int size) {
		this.speed = speed;
		this.direction = direction;
		this.size = size;
	}

	public int compareTo(Shark o) {
		return this.size > o.size ? 1 : -1;
	}
	
	
}
