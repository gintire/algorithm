package matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * 백준 문제
	 * No 1080 행렬
	 * 그리디 알고리즘 문제
	 * 1. 초기화 ( 1차원 배열 )
	 *   - A 행렬 : 변화 하는 행렬 
	 *   - B 행렬 : A 행렬을 변화시켜 같은 모양을 만들 행렬
	 * 2. A, B 행렬을 비교하여 다른 부분을 확인
	 *   - (0, 0) ~ (m - 3, n - 3) A, B의 값이 다른 칸을 기준으로
	 *   3.부분행렬 3*3 부분을 변경시킨다.
	 *   - 이게 가능한 이유는 행렬의 값이 1아니면 0이기 때문에 
	 *   같은 부분을 2번 이상 변경시 의미 없는 변경임 ( 최적이 아님 )
	 * 4. A, B 행렬의 다른 부분을 1회 순회 하며 
	 *    다른 부분만 단위 행렬로 뒤집고 난 후에도
	 *    아직 다른 부분이 있을 경우 A를 B로 바꿀 수 없다 (-1)
	 * 참고 사이트 : https://mizzo-dev.tistory.com/entry/baekjoon1080
	 */
	static int n;
	static int m;
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. 초기화
		String[] MN = br.readLine().split(" ");
		m = Integer.parseInt(MN[0]); n = Integer.parseInt(MN[1]);
		int [] A = new int[m*n];
		int [] B = new int[m*n];
		boolean[] differenceAB = new boolean[m*n];
		
		initMatrix(A);
		initMatrix(B);

		int result = doMatrix(A, B, differenceAB);
		System.out.println(result);
	}
	
	// 2. A, B 행렬을 비교하여 다른 부분을 확인
	private static int doMatrix(int[] mapA, int[] mapB, boolean[] mapAB) {
		int countReverse = 0;
	
		// A, B 행렬을 비교하여 mapAB에 두 행렬이 같거나 다른 부분 체크
		// A, B 행렬이 처음부터 같으면 0번 변경 하고 끝
		if(initCompreMatrix(mapA, mapB, mapAB)) return 0;
		// A, B 행렬이 다른데 A, B 행렬의 크기가
		// 부분 행렬의 크기인 3보다 작을시 변경 불가능 (-1)
		if( m < 3 || n < 3 ) return -1;
		
		// 만약 A 행렬의 r, c 이 B행렬의 r, c과 다를 경우 부분 행렬을 뒤집는다.
		for(int i=0; i<=m-3; i++) {
			for(int j=0; j<=n-3; j++) {
				if(mapAB[i*n + j]) {
					setReverse(i, j, mapAB);
					countReverse+=1;
				}
			}
		}
		// 4. A, B 행렬의 다른 부분을 1회 순회 하며 
		// 다른 부분만 단위 행렬로 뒤집고 난 후에도
		// 아직 다른 부분이 있을 경우 A를 B로 바꿀 수 없다 (-1)
		if(!checkAll(mapAB)) return -1;
		return countReverse;
	}
	
	// 입력 받은 두개의 행렬 비교.
	public static boolean checkAll(boolean[] map) {
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(map[i*n + j]) return false;
			}
		}
		return true;
	}
	
	// 3.부분행렬 3*3 부분을 변경시킨다.
	public static void setReverse(int r, int c, boolean[] map) {
		for(int i = r ; i < r+3; i++) {
			for(int j = c; j < c+3; j++) {
				map[i*n + j] = !map[i*n + j];
			}
		}
	}
	
	// 2.입력 받은 두개의 행렬 비교.
	// A, B 행렬이 처음부터 같을 경우 끝냄
	public static boolean initCompreMatrix(int[] mapA, int[] mapB, boolean[] mapAB) {
		boolean sameOrNot = true;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) { 
				if(mapA[i*n + j] != mapB[i*n + j]) {
					mapAB[i*n + j] = true;
					sameOrNot = false;
				}
			}
		}
		if(sameOrNot) return true;
		return false;
	}
	
	// 1. 초기화
	public static void initMatrix(int[] map) throws IOException {
		for(int i=0; i<m; i++) {
			String[] temp;
			temp = br.readLine().split("");
			for(int j=0; j<n; j++) {
				map[i*n+j] = Integer.parseInt(temp[j]);
			}
		}
	}
}
