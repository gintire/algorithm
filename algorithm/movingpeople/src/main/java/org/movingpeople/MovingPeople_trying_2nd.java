package org.movingpeople;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 해결 작전
 * 1. 전체 지도를 그린다.
 * 2. 지도를 스캔해서 인구 이동이 가능한지 확인 (boolean scan())
 * 3. 각 국가별로 이동이 인구 이동이 일어날수 있는 연합 만들기 (dfs(), visit[][])
 * 	  dfs를 쓰는 이유는 하나의 연합이 아니고 여러 연합이 생겨날 수 있기 때문에 국가별로 dfs를 한다.
 *    tip. 유사문제 치킨배달
 * 4. 연합별로 인구이동을 시킨다 (mvmtPop(연합 array) )
 * */
public class MovingPeople_trying_2nd {
	static int N, L, R;
	static int worldMap[];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static boolean visit[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		String[] NLR = tempStr.split(" ");
		N = Integer.parseInt(NLR[0]); L = Integer.parseInt(NLR[1]); R = Integer.parseInt(NLR[2]);
		// Step1 : Initiate WorldMap
		worldMap = new int[N*N];
		for (int i=0; i<N; i++) {
			String tempMapStr = br.readLine();
			String[] tempMap = tempMapStr.split(" ");
			for(int j=0; j<N; j++) {
				worldMap[i*N+j] = Integer.parseInt(tempMap[j]);
			}
		}
		
		// Step2 : calc the result
		int res = 0;
		while(true) {
			visit = new boolean[N][N]; //방문 초기화
			if(scan())
				res++;
			else
				break;
		}
		System.out.println(res);
	}
	private static boolean scan() {
		ArrayList<Integer> union;
		boolean changeable = false;
		// Step4 : Observe all country
		for (int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j]) {
					union = new ArrayList<Integer>();
					union.add(i*N + j);
					dfs(i, j, union);
					if(union.size() > 1) {
						mvmntPop(union);
						changeable = true;
					}
				}
			}
		}
		return changeable;
	}

	// Step3 : movement of population function
	private static void mvmntPop(ArrayList<Integer> union) {
		int unionSize = union.size();
		int totUnionPop = 0;
		int thisCountry, x, y;
		for(int i=0; i<unionSize; i++) {
			thisCountry = union.get(i);
			totUnionPop += worldMap[thisCountry]; 
		}
		for(int i=0; i<unionSize; i++) {
			thisCountry = union.get(i);
			worldMap[thisCountry] = totUnionPop / unionSize;
		}
	}

	// Step4 : dfs - Operation of each country
	private static void dfs(int x, int y, ArrayList<Integer> union) {
		// 방문
		visit[x][y] = true;
		int nextX, nextY;
		for(int i=0; i<4; i++) {
			nextX = x+dx[i]; nextY = y+dy[i];
			if(nextX < 0 || nextX >=N ||nextY < 0 || nextY >=N) continue;
			if(!visit[nextX][nextY]){
				if(openBorder(worldMap[x*N+y], worldMap[nextX*N+nextY])) {
					union.add(nextX*N + nextY);
					dfs(nextX, nextY, union);
				}
			}
		}
	}

	public static int getDiff (int a, int b) {
		return a>b?a-b:b-a;
	}

	static boolean openBorder(int countryA, int countryB) {
		int diff = Math.abs(countryA - countryB); 
		if(diff < L) return false;
		if(diff > R) return false;
		return true;
	}
}
