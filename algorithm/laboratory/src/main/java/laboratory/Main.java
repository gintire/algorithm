package laboratory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author jin36
 * 전략
 * 1. 전체 연구소 검사 ( scanLab() )
 *     1.1 return 안전 지대 크기
 * 2. 바이러스 감염 ( doInfection() )
 * 3. 연구소 벽 설치 ( doDefence(), dfs() )
 *     3.1 연구소 벽이 설치된 지역은 설치 하지 않는다. ( visitedMap 필요 )
 *     3.2 바이러스나 연구소 이외의 지역은 벽을 설치 할 수 없다.
 * 4. 안전지대 최댓값 조사 ( maxSafetyArea 필요 )
 */
public class Main {

	public static int N, M;
	public static int[][] map;
	public static int[][] tempMap;
	public static int maxSafteyArea = 0;
	public static BufferedReader br;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * 연구소 초기화
		 */
		init ();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					copyMap(tempMap, map);
					tempMap[i][j] = 1;
					doDefence(1);
					tempMap[i][j] = 0;	
				}
			}
		}
		System.out.println(maxSafteyArea);
		
	}
	
	// 연구소 벽 설치
	public static void doDefence(int setedUpWall) {
		if(setedUpWall == 3) {
			int temp;
			int[][] scanMap = new int[N][M];
			scanMap = copyMap(scanMap, tempMap);
			doInfection(scanMap, 0, 0, new boolean[N][M]);
			temp = scanLab(scanMap);
			if(maxSafteyArea < temp ) {
				maxSafteyArea = temp;
			}
		} else {
			// 연구소 내부에 안전지대 중 벽을 설치 하지 않은 지역
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(tempMap[i][j]==0) {
						tempMap[i][j]=1;
						doDefence(setedUpWall+1);
						tempMap[i][j]=0;
					}
				}
			}
		}
	}
	
	//바이러스 감염
	public static void doInfection(int[][] tempMap, int x, int y, boolean[][] tVisited) {
        if(tempMap[x][y]==2) {
        	if(!tVisited[x][y]) {
	        	tVisited[x][y] = true;
				for(int k=0; k<4; k++) {
					if(x + dx[k] < 0 || x+dx[k] >= N || y+dy[k] < 0 || y+dy[k] >= M);
					else {
						int nextArea = tempMap[x+dx[k]][y+dy[k]];
						if(nextArea == 0) {
							tempMap[x+dx[k]][y+dy[k]] = 2;
							
						}
						doInfection(tempMap, x+dx[k], y+dy[k], tVisited);
					}
				}
        	}
		} else {
			for(int i=x; i<N; i++) {
				for(int j=y; j<M; j++) {
					if(tempMap[i][j]==2) doInfection(tempMap, i, j, tVisited);
				}
			}
		}
	}
	
	//연구소 검사, 반환값은 안전지대 크기
	public static int scanLab(int[][] tempMap) {
		int safetyArea = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempMap[i][j]==0) safetyArea++;
			}
		}
		
		return safetyArea;
	}
	
	// 연구소 복제
	public static int[][] copyMap(int[][]a, int[][]b) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				a[i][j] = b[i][j];
			}
		}
		return a;
	}
	// 연구소 초기화
	public static void init() throws IOException {
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
		map = new int[N][M];
		tempMap = new int[N][M];
		//visited = new boolean[N][M];
		String[] temp;
		int tempNum;
		for(int i=0; i<N; i++) {
			temp = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				tempNum = Integer.parseInt(temp[j]);
				map[i][j] = tempNum;
			}
		}
	}
	
	public static void printMap (int[][] tempMap) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(tempMap[i][j] +" ");
			}
			System.out.println();
		}
	}

}
