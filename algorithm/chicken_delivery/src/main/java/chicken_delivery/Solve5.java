package chicken_delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solve5 {
	/**
	 * 전략
	 * 1. 초기화
	 * 치킨집과 가정집 좌표를 담는 배열 생성 (Point 클래스 사용)
	 * 2. dfs로 선택하며 M개의 치킨집을 선택하면 치킨거리를 계산 ( 최소의 치킨거리를 구함 )
	 * @throws IOException 
	 */
	public static int N, M;
	public static ArrayList<Point2> houses, chickenMarkets;
	public static int res = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		
		N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
		
		houses = new ArrayList<Point2>();
		chickenMarkets = new ArrayList<Point2>();
		
		for(int i=0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j < N; j++) {
				if(Integer.parseInt(temp[j]) == 1) houses.add(new Point2(i, j));
				if(Integer.parseInt(temp[j]) == 2) chickenMarkets.add(new Point2(i, j));
			}
		}
		boolean[] selected = new boolean[chickenMarkets.size()];
		int housesCount = houses.size();
		int chickenMarketsCount = chickenMarkets.size();
		dfs(selected, 0, 0 , housesCount, chickenMarketsCount);
		System.out.println(res);
	}

	private static int dfs(boolean[] selected, int idx, int selectCount, int housesCount, int chickenMarketsCount) {
		if(selectCount == M) {
			int thisChickenDist = getChickenDistance(selected, housesCount, chickenMarketsCount);
			if(res > thisChickenDist) res = thisChickenDist;
		}
		if(idx >= chickenMarkets.size() ) return 0;
		selected[idx] = true;
		dfs(selected, idx+1, selectCount+1, housesCount, chickenMarketsCount);
		selected[idx] = false;
		dfs(selected, idx+1, selectCount, housesCount, chickenMarketsCount);
		
		return 0;
	}

	private static int getChickenDistance(boolean[] selected, int housesCount, int chickenMarketsCount) {
		int thisChickenDist = 0;
		for(int i=0; i< housesCount; i++) {
			int tempDist = Integer.MAX_VALUE;
			for(int j=0; j<chickenMarketsCount; j++) {
				if(selected[j]) {
					int thisDist = houses.get(i).getDist(chickenMarkets.get(j));
					if(tempDist > thisDist) tempDist = thisDist;
				}
			}
			thisChickenDist += tempDist;
		}
		return thisChickenDist;
	}

}
