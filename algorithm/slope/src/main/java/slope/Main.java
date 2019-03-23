package slope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author gintire
 * 풀이 전략
 * 0. 초기화
 * 1. 높이 차이 함수 생성 ( int diffH (int a, int b)
 * 2. 행, 열 방향으로 순회한다.
 * 3. 높이 차이 (h), 길의 길이 (l)을 계산한다.
 * 4. 110011 l=2일 경우 방향이 바뀌는거 고려
 * 5. 11000011 l=2 일 경우 방향이 바뀌지만 2개의 다리를 놓을 수 있는것 고려
 * 4. 결과 res 출력
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
		System.out.println(solve()+solve2());
	}
	//2. 행, 열 방향으로 순회
	public static int solve() {
		int result = 0;
		//횡으로 이동
		for(int i=0; i<N; i++) {
			int l = 1;
			int diff = 0;

			int flag = 0;
			// 높이가 낮아질 경우
			if(diffH(map[i][0], map[i][1]) > 0)
				flag = -1;
			// 높이가 높아질 경우			
			else
				flag = 1;
			for(int j=1; j<N; j++) {
				diff = diffH(map[i][j-1], map[i][j]);
				//System.out.println(diff+" "+ flag + " "+i+" "+j);
				if(Math.abs(diff)>1) break;
				
				if(diff == 0) {
					l++;
				} else {
					// 높이가 낮아졌다가
					if(flag < 0) {
						//높아지는 경우
						if(diff < 0) {
							if(l < L*2) break;
						}
					} 
					if(diff < 0) {
						if(l < L) {
							break;
						}
					}
					l=1;
				}
				if(flag!=0) {
					if(diff < 0) flag= 1;
					else if(diff > 0) flag = -1;
				}
				else flag = 0;
				
				
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
		for(int i=2; i<N; i++) {
			int l = 1;
			int diff = 0;
			
			int flag = 0;
			// 높이가 낮아질 경우
			if(diffH(map[0][i], map[1][i]) > 0)
				flag = -1;
			// 높이가 높아질 경우			
			else
				flag = 1;
			for(int j=1; j<N; j++) {
				diff = diffH(map[j-1][i], map[j][i]);
				
				if(Math.abs(diff)>1) break;
				
				if(diff == 0) {
					l++;
				} else {
					// 높이가 낮아졌다가 
					if(flag < 0) {
						//높아지는 경우
						if(diff < 0) {
							if(l < L*2) break;
						}
					}
					if(diff < 0) {
						System.out.println(i+" "+j+" "+l+" "+L+" "+diff+" "+flag);
						if(l < L) {
							break;
						}
					}
					l=1;
				}
				
				if(flag!=0) {
					if(diff < 0) flag= 1;
					else if(diff > 0) flag = -1;
				}
				else flag = 0;
				
				if(j==N-1) {
					if(l<L) break;
					System.out.println("res i, j = "+i+" "+j);
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
