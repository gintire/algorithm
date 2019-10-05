package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 11657번 타임머신
 * 
 * 다익스트라 / Floyd-Warshall 문제
 * 
 * Floyd-Warshall
 * 그래프에서 모든 저점 사이의 최단 거리를 구하는 알고리즘
 * 음수 가중치 처리가 어려운 다익스트라 알고리즘에 비해 플로이드 와샬 알고리즘은 사이클이 없는 경우 음수 가중치 처리가 가능하다.
 * 
 * 
 * 1. 초기화
 *  - N 도시개수 , M 버스 갯수
 *  - M 번의 버스 정보 받기 ( 출발지, 도착지, 걸리는 시간 )
 * 2. 거리계산을 위한 int[][] D 배열 - 초기화 INF
 *    직전 간선의 위치를 저장하는 int[][] P 배열 선언 - 초기화 NIL
 * 3. D[i][j]가 경로에 따라 최소의 거리를 가지는 값으로 업데이트   
 * 
 */
public class Timemachine {
	private static int INF = 987654321;
	private static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		Edge[] edges = new Edge[M];
		int[] dist = new int[N];
		
		for(int i=0; i<N; i++) {
			dist[i] = INF;
		}
		
		String[] abc;
		int a,b,c;
		for(int i=0; i<M; i++) {
			abc = br.readLine().split(" ");
			a = Integer.parseInt(abc[0]);
			b = Integer.parseInt(abc[1]);
			c = Integer.parseInt(abc[2]);
			edges[i] = new Edge(a-1, b-1, c);
		}
		
		dist[0] = 0;
		for(int i=1; i<N; i++) {
			for(int j=0; j<M; j++) {
				int src = edges[j].src;
				int dest = edges[j].dest;
				int weight = edges[j].weight;
				if(dist[src] == INF) continue;
				if(dist[dest] > dist[src] + weight)
					dist[dest] = dist[src] + weight;
			}
		}
		
		int[] checkDist = copy(dist);
		boolean flag = true;
		
		for(int j=0; j<M; j++) {
			int src = edges[j].src;
			int dest = edges[j].dest;
			int weight = edges[j].weight;
			if(checkDist[src] == INF) continue;
			if(checkDist[dest] > checkDist[src] + weight)
				flag = false;
		}
		
		if(flag) {
			for(int i=1; i<N; i++) {
				if(dist[i] == INF) System.out.println(-1);
				else System.out.println(dist[i]);
			}
		} else {
			System.out.println(-1);
		}
	}
	static class Edge {
		int src, dest, weight;
		
		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}
	static int[] copy(int[] map) {
		int[] copyMap = new int[N];
		for(int i=0; i<N; i++) {
			copyMap[i] = map[i];
		}
		return copyMap;
	}
}
