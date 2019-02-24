package chicken_delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solve2 {

	/*
	 * 해결 전략
	 * 1. 두 칸 사이의 거리를 구하는 함수. 반환값 int (getDist)
	 * 2. 치킨집의 위치를 기록 ( arrChickenRest )
	 * 3. 각 "집"에서 가장 가까운 치킨집과의 치킨 거리를 구한다. ( 집 스캔 함수 - scan )
	 * 4. 치킨집이 M개 일 때 최소의 치킨 거리 구하기 ( dfs ) -> 경우의 수 kCm
	 * */
	static int N, M;
	static ArrayList<Pt> house = new ArrayList<Pt>();
	static ArrayList<Pt> chickenRest = new ArrayList<Pt>();
	static int minDist = Integer.MAX_VALUE;
	static int cntHouse, cntChicken;
	static int[] chk = new int[50];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmpNM = br.readLine().split(" ");
		Arrays.fill(chk, -1);
		// Step1. Map Initialize
		N = Integer.parseInt(tmpNM[0]); M = Integer.parseInt(tmpNM[1]);
		
		int tmp = 0;
		for(int i=1; i<=N; i++) {
			String[] tmpMap = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				tmp = Integer.parseInt(tmpMap[j-1]);
				if(tmp == 1) house.add(new Pt(i, j));
				if(tmp == 2) chickenRest.add(new Pt(i, j));
			}
		}
		cntHouse = house.size(); cntChicken = chickenRest.size(); 
		dfs (0, 0);
		System.out.println(minDist);
	}
	
	/*
	 * parameter
	 * idx : n번째 치킨집
	 * cnt : 선택된 치킨집 갯수
	 * */
	private static void dfs(int idx, int cnt) {
		// idx가 치킨집보다 많으면 종료
		if(idx>cntChicken) return;
		if(cnt==M) {
			// 임의의 최소거리
			int tmp = 0;
			// 주문하는 집에서 가장 가까운 건물의 위치를 찾아 dist에 저장
			for(int i=0; i<cntHouse; i++) {
				int dist = Integer.MAX_VALUE;
				for(int j=0; chk[j]!=-1; j++) {
					dist = Math.min(dist, getDist(house.get(i), chickenRest.get(chk[j])));					
				}
				tmp+=dist;
			}
			minDist = Math.min(minDist, tmp);
		}
		// idx번째 치킨집 선택
		chk[cnt] = idx;
		dfs(idx+1, cnt+1);
		// idx번째 치킨집 선택 X
		chk[cnt] = -1;
		dfs(idx+1, cnt);
	}

	private static int getDist(Pt p1, Pt p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y-p2.y);
	};
}

class Pt {
	int x;
	int y;
	public Pt(int x, int y) {
		this.x = x;
		this.y = y;
	}
}