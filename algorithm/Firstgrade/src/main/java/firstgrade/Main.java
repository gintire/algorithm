package firstgrade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;


/**
 * 풀이 전략
 * 1. 초기화 ( 숫자를 차례대로 입력 받음 )
 * 2. 비트 연산 이용
 *   - 0이면 + 1이면 -
 *   - 계산중 - 값 나오면 정지
 *
 */
public class Main {
	static int N;
	static long cnt = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int arr[] = new int[101];
		long[][] dp = new long[101][21];
		String temp[] = br.readLine().split(" ");
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(temp[i-1]);
		}
		/***********************************
		 * i번째 인덱스까지 정답이 될 수 있는 경우의 수를 저장
		 * 즉, 마지막 결과를 얻을 때는 arr[N][list.get(N)]
		 * 더하거나 뻇을 때, 음수이거나 20 초과일 경우 저장 x 
		 */
		dp[1][arr[1]] = 1;
		for(int i=2; i<N; i++) {
			for(int j=0; j<=20; j++) {
				int thisNum = arr[i];
				if(dp[i-1][j] != 0) {
					if( j+thisNum <= 20 ) dp[i][j+thisNum]+=dp[i-1][j];
					if( j-thisNum >= 0 ) dp[i][j-thisNum]+=dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N-1][arr[N]]);
	}

}