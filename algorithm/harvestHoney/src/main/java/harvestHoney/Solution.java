package harvestHoney;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 풀이 전략
 * 1. 초기화 ( TC를 반복할 T 입력, N, M, C 입력 )
 *    map을 입력받는다.
 * 2. 전체 맵을 순회 (void scan())
 * 3. 현재 위치 (x, y)에서 (x+M, y)까지의 좌표까지의 벌꿀 판매액을 ( int calHoney(int x, int y))
 *    memo[x][y]에 입력 ( 총 벌꿀의 양이 C이상이라면 작은 수는 제거한 판매액 - filterHoneyAmount())
 * 4. memo를 순회하며 가로로 중복되지 않으면서 최대 값을 갖는 상위 두개의 수 탐색 ( void getRest())
 * 5. 결과 out
 * 6. 전역 변수
 *    int N, M, C
 *    int[][] map
 *    int[][] memo  
 */
public class Solution {
	static int N;
	static int M;
	static int C;
	static int [][] map;
	static int [][] memo;
	static boolean [] visited;
	static boolean [][] selectVisit;
	static ArrayList<Integer> resList;
	static int rest = 0;
 	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T;t++) {
			// 시작
			// 1. 초기화 ( TC를 반복할 T 입력, N, M, C 입력 )
			String[] NMC = br.readLine().split(" ");
			N = Integer.parseInt(NMC[0]); M = Integer.parseInt(NMC[1]); C = Integer.parseInt(NMC[2]);
			map = new int[N][N];
			memo = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String[] temp = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(temp[j]);
				}
			}
			scan();
			print();
			System.out.println("#"+t+" "+getRest());
		}
	}
	//4. memo를 순회하며 가로로 중복되지 않으면서 최대 값을 갖는 상위 두개의 수 탐색 ( void getRest())
	// 꿀 채취하는 곳이 겹치면 안됨. memo[x][y] memo[x][y+M]
	private static int getRest() {
		int maxVal = 0;
		int maxValX = 0, maxValY=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(maxVal < memo[i][j]) {
					maxVal = memo[i][j];
					maxValX = i;
					maxValY = j;
				}
			}
		}
		int secondMaxVal = 0;
		for(int i=0 ;i <N; i++) {
			for(int j=0; j<N; j++) {
				if(i == maxValX) {
					if(j >= maxValY -(M-1) && j<=maxValY + M-1) continue;
				}
				if(secondMaxVal < memo[i][j]) secondMaxVal = memo[i][j];
			}
		}
		return maxVal + secondMaxVal;
	}
	private static void dfs(ArrayList<Integer> honeys, int n, int harversted, int price) {
		// 채칩한다면
		if(visited[n]) {
			int thisHoney = honeys.get(n);
			harversted += thisHoney;
			price += thisHoney * thisHoney;
		}
		if(harversted > C) return;
		
		if(n == M-1){ 
			resList.add(price);
			return;
		}
		visited[n+1] = true;
		dfs(honeys, n+1, harversted, price);
		visited[n+1] = false;
		dfs(honeys, n+1, harversted, price);
	}
	// 3.1 ( 총 벌꿀의 양이 C이상이라면 작은 수는 제거한 판매액 - filterHoneyAmount())
	private static int filterHoneyAmount(ArrayList<Integer> honeys, int honeyAmount) {
		resList = new ArrayList<Integer>();
		visited = new boolean[M];
		visited[0] = true;
		dfs(honeys, 0, 0, 0);
		visited[0] = false;
		dfs(honeys, 0, 0, 0);
		Integer max = resList.stream().mapToInt(i -> i).max().orElseThrow(NoSuchElementException::new);
		return max;
	}
	// 3. 현재 위치 (x, y)에서 (x+M, y)까지의 좌표까지의 벌꿀 판매액을 ( int calHoney(int x, int y))
	private static int calHoney(int x, int y) {
		ArrayList<Integer> honeys = new ArrayList<Integer> ();
		int honeyAmount = 0;
		if(y + M > N) return 0;
		for(int i=0; i<M; i++) {
			honeys.add(map[x][y+i]);
		}
		honeyAmount = honeys.stream()
				.mapToInt(i->i.intValue())
				.sum();
		
		int honeyPrices = 0;
		
		if(honeyAmount > C) {
			honeyPrices = filterHoneyAmount(honeys, honeyAmount);
		} else {
			honeyPrices = honeys.stream()
				.mapToInt(i->i.intValue())
		        .map(i->i*i)
		        .sum();
		}
		
		return honeyPrices;
	}
	// 2. 전체 맵을 순회 (void scan())
	private static void scan() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				memo[i][j] = calHoney(i, j);
			}
		}
	}

	// 0. test
	private static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(memo[i][j] + " ");
			}
			System.out.println();
		}
	}
}
