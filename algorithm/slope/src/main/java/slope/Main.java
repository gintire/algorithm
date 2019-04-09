package slope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 풀이 전략
 * 0. 초기화 (N과 L)
 * 1. 높이 차이 함수 생성 ( int diffH (int a, int b)
 * 2. 행, 열 방향으로 순회한다.
 * 3. 높이 차이 (h), 길의 길이 (l)을 계산한다.
 * 4. 상승 하는 경우 지금까지 평지였던 부분이 L 이상
 * 5. 하강 하는 경우 하강후 L 만큼의 길이의 평지가 존재
 * 6. 하강 후 상승 (110011 L=2)일 경우 방향이 바뀌는거 고려
 * 7. 하강 후 상승 (11000011 L=2) 일 경우 방향이 바뀌지만 2개의 다리를 놓을 수 있는것 고려
 * 8. 결과 res 출력
 */
public class Main {
	public static int N, L;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		L = Integer.parseInt(NM[1]);
		
		// 0. initialize
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<temp.length; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		System.out.println("횡으로 이동 : "+ solve());
		System.out.println("종으로 이동 : "+ solve2());
		//System.out.println(solve()+solve2());
	}
	//2. 행, 열 방향으로 순회
	public static int solve() {
		int result = 0;
		
		//횡으로 이동
		for(int i=0; i<N; i++) {
			int l = 1;
			int diff = 0;
			boolean isdown = false;
			for(int j=1; j<N; j++) {
				diff = diffH(map[i][j-1], map[i][j]);
				if(Math.abs(diff)>1) break;
				if(diff == 0) {
					l++;
				} else {
					// 상승
					if(diff < 0) {
						// 한번 하강했으면 l의 두배의 길이가 필요로 한다.
						if(isdown) if(l < 2*L) break;
						if(l < L) break;
						isdown = false;
					}
					// 하강
					else {
						isdown = true;
						int tempL = 0;
						for(int k=0; k<L; k++) {
							if(j+k == N) break;
							if(map[i][j+k] == map[i][j]) {
								tempL++;
							}
						}
						if(tempL < L) break;
					}
					if(j!=N-1) l=1;
				}
				if(j==N-1) {
					if(l<L) break;
					System.out.println("res i, j = "+i+" "+j);
					result++;
				}
			}
		}
		return result;
	}
	public static int solve2() {
		int result = 0;
		//종으로 이동
		for(int i=0; i<N; i++) {
			boolean isdown = false;
			int l = 1;
			int diff = 0;
			for(int j=1; j<N; j++) {
				diff = diffH(map[j-1][i], map[j][i]);
				if(Math.abs(diff)>1) break;
				if(diff == 0) {
					l++;
				} else {
					// 상승
					if(diff < 0) {
						// 한번 하강했으면 l의 두배의 길이가 필요로 한다.
						if(isdown) if(l < 2*L) break;
						if(l < L) break;
						isdown = false;
					}
					// 하강
					else {
						isdown = true;
						int tempL = 0;
						for(int k=0; k<L; k++) {
							if(j+k == N) break;
							if(map[j+k][i] == map[j][i]) {
								tempL++;
							}
						}
						if(tempL < L) break;
					}
					if(j!=N-1) l=1;
				}
				if(j==N-1) {
					if(l<L) break;
					System.out.println("res j, i = "+j+" "+i);
					result++;
				} 
			}
		}
		return result;
	}
	//1. 높이 차이 함수
	public static Integer diffH (int a, int b) {
		return a-b;
	}

}
