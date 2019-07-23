package cityDefence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 풀이 전략
 * 1. 초기화 N M, 맵 입력
 * 2. Point 클래스 생성 (x, y좌표)
 * 3. 거리를 계산 함수 int getDist(Point a, Point b)
 * 4. 운영 비용 계산 함수 int getOperCost(int k)
 * 5. K가 N보다 작을때 까지 도시 scan(int k)
 *    K를 1씩 증가하며 이익을 내며 최대로 방범을 할 수 있는 집 갯수 찾기
 * 6. 전역 변수
 *    N M map
 */
public class Solution {
	static int N, M;
	static ArrayList<Integer> house;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String[] NM = br.readLine().split(" ");
			N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
			house = new ArrayList<Integer>(); 
			//  1. 초기화 N M, 맵 입력
			for(int i=0; i<N; i++) {
				String[] temp = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					if(Integer.parseInt(temp[j]) == 1) house.add(i*N + j);
				}
			}
			int res = 1;
			for(int k=2; k <= N+1; k++) {
				int thisCount = scan(k); 
				if(res < thisCount) res = thisCount;
			}
			System.out.println("#"+t+" "+res);
		}
	}
	
	// 5. K가 N보다 작을때 까지 도시 scan(int k)
	private static int scan(int k) {
		int securityCost = getOperCost(k);
		int maxCityCount = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cityCount = 0;
				for(int n=0; n <house.size();n++) {
					if(getDist(i, j, house.get(n)) <= (k-1)) {
						cityCount++;
					}
				}
				if(maxCityCount < cityCount) maxCityCount = cityCount;
			}
		}
		if(maxCityCount * M >= securityCost) return maxCityCount;
		else return -1;
	}
	
	//4. 운영 비용 계산 함수 int getOperCost(int k)
	private static int getOperCost(int k) {
		return (k*k) + ((k-1)*(k-1));
	}
	
	//3. 거리를 계산 함수 int getDist(Point a, Point b)
	private static int getDist(int x, int y, int houseXY) {
		int houseX = houseXY / N;
		int houseY = houseXY % N;
		return Math.abs(x - houseX) + Math.abs(y -houseY);
	}	
}