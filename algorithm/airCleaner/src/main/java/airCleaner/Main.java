package airCleaner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 17144
 * 미세먼지 안녕!
 * 삼성전자 DS 2019상반기 1번 문제
 *
 * 문제해결
 * 1. 초기화 (입력값 받기)
 * 2. 미세먼지 확산 함수 작성 ( doSpread )
 *     - (r,c)에있는 미세먼지는 인접한 네방향으로 확산
 *     - 확산량 Arc/5
 *     - * 남은양 Arc - (Arc/5)x확산된 방향의 개수 *
 * 3. 공기 청정기 동작 ( doCleaner )
 *     - Swap 함수로 한칸씩 밀어냄
 */
public class Main {
	public static int R, C, T;
	public static int airCleanerR;
	public static int[][] map;
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] BCT = br.readLine().split(" ");
		R = Integer.parseInt(BCT[0]); C = Integer.parseInt(BCT[1]); T = Integer.parseInt(BCT[2]);
		
		map = new int[R+2][C+2];
		for(int i=0; i<=R+1; i++) {
			for(int j=0; j<=C+1; j++) {
				if(i == 0) map[i][j] = -1;
				if(i == R+1) map[i][j] = -1;
				if(j == 0) map[i][j] = -1;
				if(j == C+1) map[i][j] = -1;
			}
		}
		for(int i=1; i<=R; i++) {
			String[] tempStr = br.readLine().split(" ");
			for(int j=1; j<=C; j++) {
				int thisNum = Integer.parseInt(tempStr[j-1]);
				if(thisNum == -1) airCleanerR = i;
				map[i][j] = thisNum;
			}
		}
		
		for(int i=0; i<T; i++) {
			mapCopy(map, doSpread());
			
			// 0  현재 칸의 먼지, 1 다음 칸의 먼지
			int[] buffer = new int[2];
			buffer[0] = 0;
			buffer[1] = map[airCleanerR-1][2];
			doCleaning(airCleanerR-1, 2, true, buffer);
			buffer[0] = 0;
			buffer[1] = map[airCleanerR][2];
			doCleaning(airCleanerR, 2, false, buffer);
		}
		System.out.println(calDust());
	} 
	
	//4.미세 먼지 양 출력
	private static int calDust () {
		int result = 0;
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j] == -1) continue;
				if(map[i][j]!=0) result+=map[i][j];
			}
		}
		return result;
	}
	
	//3. 공기 청정기 돌리는 함수
	private static void doCleaning(int airCleanerPosition, int c, boolean flag, int[] buffer) {
		if(airCleanerPosition > R || c > C || airCleanerPosition < 1 || c < 1) return;
		map[airCleanerPosition][c] = buffer[0];
		buffer[0] = buffer[1];
		int nextAirCleanerPosition;
		int nextC;
		// 1. 끝까지 닿았을 때
		if(c == C) {
			// 상단 공기청정기
			if(flag) {
				if(airCleanerPosition == 1) {
					nextAirCleanerPosition = airCleanerPosition;
					nextC = c-1;
				} else {
					nextAirCleanerPosition = airCleanerPosition -1;
					nextC = c;
				}
			} 
			// 하단 공기청정기
			else {
				if(airCleanerPosition == R) {
					nextAirCleanerPosition = airCleanerPosition;
					nextC = c-1;
				} else {
					nextAirCleanerPosition = airCleanerPosition + 1;
					nextC = c;
				}
			}
		}
		// 2. 좌측 끝까지 공기가 순환한 경우
		else if(c == 1) {
			// 상단 공기청정기
			if(flag) {
				// 공기청정기로 돌아왔을 경우
				if(airCleanerPosition == airCleanerR-1) {
					map[airCleanerPosition][c] = -1;
					return;
				} else {
					nextAirCleanerPosition = airCleanerPosition + 1;
					nextC = c;
				}
			}
			// 하단 공기청정기			
			else {
				// 공기청정기로 돌아왔을 경우
				if(airCleanerPosition == airCleanerR) {
					map[airCleanerPosition][c] = -1;
					return;
				} else {
					nextAirCleanerPosition = airCleanerPosition - 1;
					nextC = c;
				}
			}
		}
		// 끝까지 가서 시계 반대 방향으로 돌아 가는 경우
		else if(airCleanerPosition == 1 || airCleanerPosition==R) {
			nextAirCleanerPosition = airCleanerPosition;
			nextC = c-1;
		}
		// 3. 일반 상황
		else {
			nextAirCleanerPosition = airCleanerPosition;
			nextC = c+1;
		}
		buffer[1] = map[nextAirCleanerPosition][nextC];
		doCleaning(nextAirCleanerPosition, nextC, flag, buffer);
	}
	// 2. 1초사이에 미세먼지가 확산되는 함수
	public static int[][] doSpread() {
		int[][] tempMap = new int[R+2][C+2];
		mapCopy(tempMap, map);
		int possibility = 0;
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				//case 1. 네 귀퉁이 일 경우 2개만 확산
				if((i == 1 && j==1) || (i==1 && j==C) || (i==R && j==1) || (i==R && j==C)) {
					possibility = 2;
				}
				// 상단, 우측끝, 하단 3개로 확산
				else if (i == 1 || j==C || i==R) {
					possibility = 3;
				}
				// 좌측끝
				else if (j==1) {
					// 공기 청정기 위아래 2개로 확산 
					if(i==airCleanerR-2 || i==airCleanerR+1) {
						possibility = 2;
					}
					//공기청정기에 맞다아 있을경우 확산 x 
					else if(map[i][j]==-1) {
						possibility = 0;
					}
					// 그외는 3개로 확산
					else {
						possibility = 3;
					}
				}
				//공기 청정기 바로 우측일 경우 3곳으로 확산
				else if (j==2) {
					if(i==airCleanerR || i==airCleanerR-1) {
						possibility = 3;
					}
					else possibility = 4;
				}
				// 그냥 확산 4곳으로 확산
				else {
					possibility = 4;
				}
				if(map[i][j]!=0) {
					for(int k=0; k<4; k++) {
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						if(map[nextX][nextY] != -1) {
							tempMap[nextX][nextY] = tempMap[nextX][nextY] + (map[i][j] / 5);
						}
					}
					if(map[i][j] != -1) tempMap[i][j] = tempMap[i][j] - ((map[i][j] / 5) * possibility);
				}
			}
		}
		return tempMap;
	}
	public static void mapCopy (int[][]mapA, int[][] mapB) {
		for(int i=0; i<R+2 ;i++) {
			for(int j=0; j<C+2; j++) {
				mapA[i][j] = mapB[i][j];
			}
		}
	}
	// 0. 검증
	public static void print() {
		for(int i=0; i<=R+1; i++) {
			for(int j=0; j<=C+1; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}