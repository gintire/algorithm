package tree_investment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
/**
 * 해결 전략
 * 1. AraryList 생성. 
 * 		Deque를 쓰는 이유 한칸에 여러 나무가 있을 때, 어린 나무 순서로 정렬하기 위해서
 * 2. 계절마다 요구사항 처리
 * 		봄 - 양분먹기, 나무죽이기 ( public void putEnergy(Deque))
 *        - 죽은 나무 List 생성 및 값 추가
 *      여름 - 죽은 나무 양분으로 주기 ( public void putMapEnergy(List) )
 *         - 다른 지역과 의존관계가 없으므로 이 행위는 봄에 같이 하면 될듯
 *      가을 - 나무 번식 ( public void postTrees() )
 *      겨울 - 양분 추가 (public void postMapEnergy() )
 */
	public static int N, M, K;
	public static ArrayList<Integer>[][] trees;
	public static int[][] map;
	public static int[][] A;
	public static int[] dx = {-1, 0, 1, -1, +1, -1, 0, 1};
	public static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NMK = br.readLine().split(" ");
		N = Integer.parseInt(NMK[0]); M = Integer.parseInt(NMK[1]); K = Integer.parseInt(NMK[2]);
		
		trees = new ArrayList[N+1][N+1];
		map = new int[N+1][N+1];
		A = new int[N+1][N+1];
		
		// A 배열 
		for(int i=1; i<=N; i++) {
			String[] tempA = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(tempA[j-1]);
				map[i][j] = 5;
				trees[i][j] = new ArrayList<Integer>();
			}
		}
		
		//심어진 나무 정보
		for(int i=0; i<M; i++) {
			String[] tempTrees = br.readLine().split(" ");
			int r = Integer.parseInt(tempTrees[0]);
			int c = Integer.parseInt(tempTrees[1]);
			int k = Integer.parseInt(tempTrees[2]);
			trees[r][c].add(k);
		}
		
		// K 년동안
		int res = 0;
		while (K-->0) {
			// 봄
			// 여름
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(trees[i][j].isEmpty()) continue;
					else putEnergy(i, j);
				}
			}
			// 가을 
			postTrees();
			// 겨울
			postMapEnergy();
		}
		
		// 살아있는 나무 검색
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(trees[i][j].isEmpty()) continue;
				else {
			 		res += trees[i][j].size();
				}
			}
		}
		
		System.out.println(res);
		
	}
	
	// 봄, 여름
	public static void putEnergy(int r, int c) {
		ArrayList<Integer> deadTrees = new ArrayList<Integer>();
		Collections.sort(trees[r][c]);
		for(int i=0; i < trees[r][c].size(); i++) {
				int now = trees[r][c].get(i);
				//양분이 충분할 경우
				if(now <= map[r][c]) {
					map[r][c] -= now;
					trees[r][c].set(i, now+1);
				} 
				// 양분이 부족할 경우
				else {
					deadTrees.add(i);
				}
			}
		int initIndex = 0;
		if(deadTrees.isEmpty()) ;
		else initIndex = deadTrees.get(0);
		while(!deadTrees.isEmpty()) {
			if(!trees[r][c].isEmpty()) {
				map[r][c] += trees[r][c].get(initIndex) / 2;
				trees[r][c].remove(initIndex);	
			}
			deadTrees.remove(0);
		}
		
	}
	
	//가을
	public static void postTrees() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int k=0; k<trees[i][j].size(); k++) {
					//나무의 나이가 5의 배수일 때 번식
					if(trees[i][j].get(k)%5==0) {
						for(int dIdx =0; dIdx<8;dIdx++) {
							int r = i + dx[dIdx];
							int c = j + dy[dIdx];
							if(r < 1 || r > N || c < 1 || c > N) continue;
							else {
								trees[r][c].add(1);
							}
						}
					}
				}
			}
		}
	}
	
	//겨울
	public static void postMapEnergy() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
}
