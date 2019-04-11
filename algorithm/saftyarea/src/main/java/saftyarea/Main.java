package saftyarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 풀이 전략
 * 1. 초기화 N 과 map을 입력 받는다.
 *    초기화를 할 때 물 높이의 최솟값과 최대값을 저장 한다 ( int minH, int maxH)
 * 2. 잠겼는지 잠기지 않았는지 확인하는 함수 작성 ( isDrawn(int x, int y, int n)
 * 3. 맵을 검사하며 안전 지대의 시작점인지 확인한다. ( void scan(int n)) n은 비가 온 양
 *    방문하지 않고 안전 지대라면 array saftyArea에 추가
 *    visited배열을 사용해서 만약 방문한 부분이라면 방문하지 않는다.
 * 4. dfs(int x, int y, int n)을 통해 잠긴곳과 연속된 안전지대인 부분을 visited로 처리
 * 5. 비가온 양 n에 따라 안전지대의 최소수 저장 ( int minN )
 * 6. 결과 출력
 * 7. 전역 변수
 *    int N;
 *    int[] map;
 *    boolean[] visited;
 *    int minSafetyArea;
 *    int[] dx, int[] dy
 */
public class Main {
	static int N;
	static int[] map;
	static boolean[] visited;
	//static int[] saftyMap;
	static int maxSafetyArea = 0;
	static int minH = 101, maxH = 0;
	static ArrayList<Integer> safetyArea;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N * N];
		//1.초기화 N 과 map을 입력 받는다.
		//초기화를 할 때 물 높이의 최솟값과 최대값을 저장 한다 ( int minH, int maxH)
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int thisNum = Integer.parseInt(temp[j]);
				if(minH > thisNum) minH = thisNum;
				if(maxH < thisNum) maxH = thisNum;
				map[i*N + j] = thisNum;
			}
		}
		//5. 비가온 양 n에 따라 안전지대의 최소수 저장 ( int minN )
		for(int i = 0; i<maxH; i++) {
			safetyArea = new ArrayList<Integer>();
			//saftyMap = new int[N * N];
			visited = new boolean[N * N];
			scan(i);
			int thisSafetyAreaN = safetyArea.size();
			if(maxSafetyArea <= thisSafetyAreaN) maxSafetyArea = thisSafetyAreaN;
		}
		
		//6. 결과 출력
		System.out.println(maxSafetyArea);
		
	}
	//4. dfs(int x, int y, int n)을 통해 잠긴곳과 연속된 안전지대인 부분을 visited로 처리
	private static void dfs(int x, int y, int n) {
		visited[x*N + y] = true;
		int next_x, next_y;
		for(int i=0; i<4; i++) {
			next_x = x + dx[i];
			next_y = y + dy[i];
			if(next_x < 0 || next_x >=N || next_y < 0 || next_y >=N) continue;
			if(!visited[next_x*N + next_y]) {
				if(!isDrawn(next_x, next_y, n)) {
					//saftyMap[next_x*N + next_y] = 1;
					dfs(next_x, next_y, n);
				}
			}
		}
	}
	//3. 맵을 검사하며 안전 지대의 시작점인지 확인한다. ( void scan(int n)) n은 비가 온 양
	private static void scan(int n) {
		//방문하지 않고 안전 지대라면 array saftyArea에 추가
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i*N + j]) {
					if(!isDrawn(i, j, n)) {
						safetyArea.add(i*N + j);
						//saftyMap[i*N + j] = 1;
						dfs(i, j, n);
					} else {
						visited[i*N + j] = true;
					}
				}
			}
		}
		
	}
	// 2. 잠겼는지 잠기지 않았는지 확인하는 함수 작성 ( isDrawn(int x, int y, int n)
	private static boolean isDrawn(int x, int y, int n) {
		if(map[x*N + y] <= n) return true;
		return false;
	}
	
	private static void test() {
		for(int i=0; i<N ;i++) {
			for(int j=0; j<N; j++) {
				//System.out.print(saftyMap[i*N + j] + " ");
			}
			System.out.println();
		}
	}
}
