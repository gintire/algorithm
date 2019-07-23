package virus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * 백준 No2606 바이러스
	 * 문제 해결
	 * 1. 입력값을 받는다.
	 * 2. 연결된 네트워크 상태를 배열로 표현 ( networkArray )
	 * 		a[출발지] = 도착지
	 * 3. 방문한 배열 생성 ( visit )
	 * 4. 총 방문한 컴터 갯수 카운트 ( getAddictedCount ) 
	 */
	static boolean[][] networkArray;
	static boolean[] visit;
	static boolean[] addictedCom;
	static int totalComputerCount;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 컴퓨터의 수
		totalComputerCount = 0;
		totalComputerCount = Integer.parseInt(br.readLine());
		// 직접 연결되어 있는 컴퓨터 쌍의 수
		int totalPairCount = 0;
		totalPairCount = Integer.parseInt(br.readLine());
		
		// 초기화
		networkArray = new boolean[totalComputerCount + 1][totalComputerCount + 1];
		visit = new boolean[totalComputerCount + 1];
		addictedCom = new boolean[totalComputerCount + 1];
		
		int from ; int to ;
		String[] tempFromTo;
		for(int i = 0; i < totalPairCount; i++) {
			from = 0; to = 0;
			tempFromTo = br.readLine().split(" ");
			from = Integer.parseInt(tempFromTo[0]); to = Integer.parseInt(tempFromTo[1]); 
			networkArray[from][to] = true;
			networkArray[to][from] = true;
		}
		//바이러스 확산
		DFS(1, 1);
		//showMap();
		// 확산된 바이러스 확인
		int res = getAddictedCount();
		
		System.out.println(res);
	}
	private static int DFS(int virusStartPnt, int cnt) {
		//showMap();
		//printf("%d ",comnum);
		visit[virusStartPnt] = true;
		for(int i=1; i<=totalComputerCount; i++){
			if(networkArray[virusStartPnt][i] && !visit[i]){
				DFS(i, ++cnt);
			}
		}
		return cnt;
	}
	private static int getAddictedCount() {
		int cnt = 0;
		for(int i=2; i<=totalComputerCount; i++) {
				if(visit[i]) cnt++;
		}
		return cnt;
	}
	
	private static void showMap () {
		for(int i=1 ; i<= totalComputerCount; i++)
			System.out.print(visit[i]+" ");
		System.out.println();
	}

}
