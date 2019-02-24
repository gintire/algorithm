package chicken_delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int cityMap[][];
	static int N, M;
	static Point[] chickenChain;
	static Point[] house;
	static int chickenChainIdx=0;
	static int houseIdx=0;
	static int[] chk = new int[50];
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		init();

		Arrays.fill(chk, -1);
		dfs(0, 0);
		System.out.println(ans);
	}
	// Step2. 도시 초기화
	public static void init() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM;
		try {
			NM = br.readLine().split(" ");
			N = Integer.parseInt(NM[0]);
			M = Integer.parseInt(NM[1]);
			chickenChain = new Point[51];
			house = new Point[101];
			// r, c 는 1부터 시작하므로
			cityMap = new int[N+1][N+1];
			
			for(int i =1; i<N+1 ;i++) {
				String[] citypMapping = br.readLine().split(" ");
				for(int j=1; j<N+1; j++) {
					int n = Integer.parseInt(citypMapping[j-1]);
					if(n==1) house[houseIdx++] = new Point(i, j);
					if(n==2) chickenChain[chickenChainIdx++] = new Point(i, j);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void dfs(int idx, int cnt) {
		//index가 치킨집 보다 많으면 종료
		if(idx > chickenChainIdx ) return;
		// 선택된 치킨집의 수가 최적의 치킨집일 경우
		if(cnt == M) {
			// 임의의 최소거리
			int temp = 0;
			// 주문하는 집에서 가장 가까운 건물의 위치를 찾아 dist에 저장
			for(int i=0; i<houseIdx; i++)  {
				int dist = 100000;
				for(int j=0; -1!=chk[j]; j++) {
					dist = Math.min(dist, calDistance(house[i], chickenChain[chk[j]]));
				}
				temp+=dist;
			}
			ans= Math.min(ans, temp);
		}
		// idx번째 건물 선택
		chk[cnt] = idx;
		dfs(idx+1, cnt+1);
		// idx번째 건물 선택 x
		chk[cnt] = -1;
		dfs(idx+1, cnt);
	}
	// Step1. 거리 계산 함수
	public static int calDistance(Point A, Point B) {
		return Math.abs(A.r - B.r) + Math.abs(A.c - B.c);
	}
}
class Point {
	int r;
	int c;
	Point (int x, int y) {
		this.r = x;
		this.c = y;
	}
}