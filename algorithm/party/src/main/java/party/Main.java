package party;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 백준 1238 파티문제
 * 다익스트라 알고리즘
 * 
 * 하나의 정점에서 다른 모든 정점까지의 최단 경로를 구하는 문제
 * 
 * 첫 정점을 기준으로 연결되어 있는 정점들을 추가해가며, 최단거리를 갱신하는 것이다.
 * 풀이 전략
 * 1. 입력값 받음 ( MNX, map[a][b] 간선 사이의 가중치)
 * 2. 파티가 일어나는 마을을 기준으로 각 마을에서 가장 거리가 먼 학생을 찾는다.
 *    d[n] = min(d[n], d[x] + d[x][n])
 * 3. 주의 해야할 점은 마을간 통행은 단방향이다
 * 
 */
public class Main {
	static int N, M, X;
	static int[][] toHome;
	static int[][] toParty;

	static int INF = 1000*100+1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMX = br.readLine().split(" ");
		N = Integer.parseInt(NMX[0]); M = Integer.parseInt(NMX[1]); X = Integer.parseInt(NMX[2]) -1;
		
		// 지도 초기화 및 가중치 넣어줌
		// toHome - 파티가 끝나고 집으로 돌아가는거
		// toParty - 파티를 가는 최저시간 계산
		toHome = new int[N][N];
		toParty = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) {
					toHome[i][j] = 0;
					toParty[i][j] = 0;
				}
				toHome[i][j] = INF;
				toParty[i][j] = INF;	
			}
			
		}

		String[] temp;
		int from, to, x;
		for(int i=0; i<M; i++) {
			temp = br.readLine().split(" ");
			from = Integer.parseInt(temp[0]) -1;
			to = Integer.parseInt(temp[1]) -1;
			x = Integer.parseInt(temp[2]);
			toHome[from][to] = x;
			toParty[to][from] = x;
		}


		int[] costToHome = dijkstra(X, toHome);
		int[] costToParty = dijkstra(X, toParty);
		
		int maxNum = -1; 
		for(int i=0; i<N; i++) {
			if(costToHome[i] >= INF || costToParty[i] >= INF) continue;
			maxNum = Math.max(maxNum, costToHome[i] + costToParty[i]); 
		}
		print(costToHome, costToParty);
		System.out.println(maxNum);
	}
	
	public static int[] dijkstra(int start, int[][] direction) {
		Queue<Integer> pq = new LinkedList<Integer>();
		pq.offer(start);
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int thisPnt = pq.poll();
			for(int i=0; i<N; i++) {
				if(dist[i] > direction[thisPnt][i] + dist[thisPnt]) {
					dist[i] = direction[thisPnt][i] + dist[thisPnt];
					pq.offer(i);
				}
			}
		}
		return dist;
	}
	
	public static void print(int[] arrA, int[] arrB) {
		for(int i=0; i<N; i++)
			System.out.print(arrA[i] + " ");
		System.out.println();
		for(int i=0; i<N; i++)
			System.out.print(arrB[i] + " ");
		System.out.println();
	}
}