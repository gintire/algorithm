package org.movingpeople;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 풀이 전략
 * 1. 초기화 ( map에 각 나라의 인구를 저장)
 * 2. 국경선을 열 수 있는지 확인 하는 함수 작성 ( isPossibleMove(int a, int b))
 * 3. 전체 맵에서 인구 이동이 가능한 나라 찾는 함수 작성 (scan())
 *    scan은 하루 단위로 이뤄져야한다. 더이상 인구 이동이 없으면 중지 (return 값 boolean으로 하는게 좋을듯)
 *    scan을 돌며 인구 이동이 가능한 나라들을 연합으로 만듬 ( array union)
 * 4. 전체 맵에서 여러개의 연합이 생길수 있다. -> 이미 방문한 나라들을 배제한채 다른 연합을 만들수 있는 국가들을 검색 (dfs)
 * 5. 인구 이동을 진행 하는 함수 작성 ( void move(union))
 * 6. 전역 변수 - N, L, R
 *    전체 맵 int[][] map
 *    방문한 나라 확인 boolean[][] visited
 */
public class Main {
	private static int N, L, R;
	private static int[] map;
	private static boolean[] visited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NLR = br.readLine().split(" ");
		N = Integer.parseInt(NLR[0]); L = Integer.parseInt(NLR[1]); R = Integer.parseInt(NLR[2]);
		map = new int[N*N];
		//1. 초기화
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i*N + j] = Integer.parseInt(temp[j]);
			}
		}
		int res = 0;
		while(true) {
			visited = new boolean[N*N];
			if(scan()) res ++;
			else break;
		}
		System.out.println(res);
	}
	
	//5. 인구 이동을 진행 하는 함수 작성 ( void move(union))
	private static void move(ArrayList<Integer> union) {
		int unionN = union.size();

		final int totPeole = union.stream()
				.mapToInt(i->i.intValue())
				.map(i->map[i])
				.sum();
		/*for(int i=0; i<unionN; i++) {
			int xy = union.get(i);
			totPeole += map[xy];
		}*/
		/*for(int i=0; i<unionN; i++) {
			int xy = union.get(i);
			map[xy] = totPeole / unionN;
		}*/
		union.stream().forEach(i-> {
			map[i] = totPeole / unionN;
		});
	}
	//4. 전체 맵에서 여러개의 연합이 생길수 있다. -> 이미 방문한 나라들을 배제한채 다른 연합을 만들수 있는 국가들을 검색 (dfs)
	private static void dfs(int x, int y, ArrayList<Integer> union) {
		visited[x*N + y] = true;
		int next_x = 0, next_y=0;
		for(int i=0; i<4; i++) {
			next_x = x+dx[i];
			next_y = y+dy[i];
			if(next_x < 0 || next_x >=N ||next_y < 0 || next_y >=N) continue;
			if(!visited[next_x*N + next_y]) {
				if(isPossibleMove(map[x*N + y], map[next_x * N + next_y])) {
					union.add(next_x * N + next_y);
					dfs(next_x, next_y, union);
				}
			}
		}
	}
	
	//3. 전체 맵에서 인구 이동이 가능한 나라 찾는 함수 작성 (scan())
	private static boolean scan() {
		ArrayList<Integer> union;
		boolean isPosibleCountinue = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i*N + j]) {
					union = new ArrayList<Integer>();
					union.add(i*N + j);
					dfs(i, j, union);
					if(union.size() > 1) {
						isPosibleCountinue = true;
						move(union);
					}
				}
			}
		}
		return isPosibleCountinue;
	}

	//2. 국경선을 열 수 있는지 확인 하는 함수 작성 ( isPossibleMove(int a, int b))
	private static boolean isPossibleMove(int countryA, int countryB) {
		int diff = Math.abs(countryA-countryB);
		if(diff < L) return false;
		if(diff > R) return false;
		return true;
	}

}
