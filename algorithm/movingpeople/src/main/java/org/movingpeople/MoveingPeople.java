package org.movingpeople;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 2018 하반기 삼성전자 DS 
 * 백준저지 16234번 인구이동
 * https://www.acmicpc.net/problem/16234
 */

/**
 * 1. 두개의 숫자를 받아 비교하는 함수
 * 2. 위, 아래, 좌, 우 움직이는 함수
 * 
 */
public class MoveingPeople 
{
	static Integer[][] worldMap;
	static int N, L, R;
	static Integer[] dx = {1, -1, 0, 0};
	static Integer[] dy = {0, 0, 1, -1};
	
	// 정답을 본 후 수정 1. 방문한 도시
	static boolean visited[][];
	
	
    public static void main( String[] args ) throws IOException
    {
    	int res = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] inputArr = input.split(" ");
        N = Integer.parseInt(inputArr[0]); L = Integer.parseInt(inputArr[1]); R = Integer.parseInt(inputArr[2]);
        worldMap = new Integer[N][N];
        for(int i=0; i<N ;i++) {
        	String[] peopleCnt = br.readLine().split(" ");
        	for(int j=0; j<N; j++) worldMap[i][j] = Integer.parseInt(peopleCnt[j]);
        }
       //정답지 보고 수정 2. visit 초기화 하고 check() 함수를 호출
        while(true) {
        	visited = new boolean[N][N]; //방문 초기화
        	if(!check()) {
        		res++;
        	} else {
        		break;
        	}
        }
        System.out.println(res);
    }
    
    // 정답지 보고 수정 3. 인구 이동 필요한지 전체 지도를 스캔
    private static boolean check() {
		ArrayList<Integer> union;
    	boolean isDone = true;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		// 정답지 보고 수정 4. 방문하지 않은 경우
        		if(!visited[i][j]){
        			union = new ArrayList<Integer>();
        			union.add(i*N + j);
        			// sum - 리스트에 저장된 값의 합.
        			int sum = dfs(i, j, union, 0);
        			if(union.size()>1) { //리스트의 크기가 1이상이 경우 (인구 이동이 있을 경우)
        				movingPeople(sum, union);
        				isDone=false;
        			}
        		}
        	}
        }
		return isDone;
	}

    //정답지 보고 수정 5. dfs 함수
	private static int dfs(int x, int y, ArrayList<Integer> union, int sum) {
		// 방문
		visited[x][y] = true;
		sum = worldMap[x][y];
		
		for(int i=0; i<4; i++){
			int next_x = x+dx[i];
			int next_y = y+dy[i];
			
			if(next_x < 0 || next_x >=N ||next_y < 0 || next_y >=N) continue;
			if(!visited[next_x][next_y]) {
				int diff = diffNumber(worldMap[x][y], worldMap[next_x][next_y]);
				if(diff >= L && diff <= R) {
					union.add(next_x*N + next_y);
					sum+=dfs(next_x, next_y, union, sum);
    			}
			}
		}
		return sum;
	}

	private static void printmap() {
	 for(int i=0; i<N; i++){
        	for(int j=0; j<N;j++) {
        		System.out.print(worldMap[i][j]+" ");
        	}
        	System.out.println();
        }
	 System.out.println();
    }

   //평균값으로 map 갱신
   public static void movingPeople(int sum, ArrayList<Integer> union) {
	   int unionPeople = sum / union.size();
	   for(int i=0; i<union.size(); i++) {
		   int point = union.get(i);
		   worldMap[point/N][point%N] = unionPeople;
	   }
   } 
    
  // 연합 수
    public static int calUnionPeople(ArrayList<Integer> union) {
    	int totPeople = 0;
    	int unionCnt = union.size();
    	for(int i=0; i<unionCnt;i++) {
    		int point = union.get(i);
    		totPeople +=worldMap[point/N][point%N];
    	}
    	return totPeople /unionCnt;
    }
    
    // 두 수의 차이
    public static int diffNumber(int a, int b) {
		return a-b>0?a-b:b-a;
    }
}