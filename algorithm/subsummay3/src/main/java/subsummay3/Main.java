package subsummay3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/**
	 * 구간합 구하기3
	 * 백준 11658번
	 * 
	 * 풀이 전략
	 * 1. 초기화 (nums[N][N])
	 * 2. 입력값 받기
	 * 3. 행 별 세그먼트 트리를 생성하여 구간 합 구한뒤
	 *    구간에 맞는 행들의 합을 도출
	 * 4. segmentTree 클래스 생성
	 *    - init (tree[], nums[], node, start, end)
	 *    - update (tree[], node, start, end, idx, diff)
	 *    - sum (tree[], node, start, end, left, right)
	 * 5. segmentTree[N]
	 *    - N 개의 세그먼트 트리 생성
	 * 6. 
	 */
	
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		
		N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		// 초기화
		int bit[][] = new int[N+1][N+1];		
		int[][] nums = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				nums[i][j] = Integer.parseInt(temp[j-1]);
				update(bit, i, j,  nums[i][j]);
			}
		}
		
		for(int i=0; i<M; i++) {
			String[] commands = br.readLine().split(" ");
			int w = Integer.parseInt(commands[0]);
			// 구간 합 출력
			if(w == 1) {
				int x1 = Integer.parseInt(commands[1]);
				int y1 = Integer.parseInt(commands[2]);
				int x2 = Integer.parseInt(commands[3]);
				int y2 = Integer.parseInt(commands[4]);
				
				//System.out.println(sums[x2][y2]);
				//System.out.println(sums[x2][y1 - 1]);
				//System.out.println(sums[x1-1][y2]);
				//System.out.println(sums[x1-1][y1-1]);
				if(x1>x2) swap(x1,x2);
				if(y1>y2) swap(y1,y2);
				
				System.out.println(sum(bit, x2, y2) - sum(bit, x2, y1 - 1) - sum(bit, x1 - 1, y2) + sum(bit, x1 - 1, y1 - 1));
			} 
			// 값 변경
			else if(w == 0) {
				int x = Integer.parseInt(commands[1]);
				int y = Integer.parseInt(commands[2]);
				int c = Integer.parseInt(commands[3]);
				
				int diff = c - nums[x][y];
				
				update(bit, x, y, diff); 
				nums[x][y] = c;
			} else continue;
		}
	}
	
	private static void swap(int a, int b) {
		int temp;
		temp = a;
		a = b;
		b = temp;
	}
	
	static void update(int[][] bit, int x, int y,  int diff){
	    while(x <= N){
	        int j = y;
	        while(j <= N){
	            bit[x][j] += diff;
	            j += (j & -j);
	        }
	        x += (x & -x);
	    }
	    return;
	}
	
	static int sum(int[][] bit , int x, int y) {
	    int s = 0;
	    while(x > 0){
	        int j = y;
	        while(j > 0){
	            s += bit[x][j];
	            j -= (j & -j);
	        }
	        x -= (x & -x);
	    }
	    return s;
	}
}