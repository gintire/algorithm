package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ���� 11657�� Ÿ�Ӹӽ�
 * 
 * ���ͽ�Ʈ�� / Floyd-Warshall ����
 * 
 * Floyd-Warshall
 * �׷������� ��� ���� ������ �ִ� �Ÿ��� ���ϴ� �˰���
 * ���� ����ġ ó���� ����� ���ͽ�Ʈ�� �˰��� ���� �÷��̵� �ͼ� �˰����� ����Ŭ�� ���� ��� ���� ����ġ ó���� �����ϴ�.
 * 
 * 
 * 1. �ʱ�ȭ
 *  - N ���ð��� , M ���� ����
 *  - M ���� ���� ���� �ޱ� ( �����, ������, �ɸ��� �ð� )
 * 2. �Ÿ������ ���� int[][] D �迭 - �ʱ�ȭ INF
 *    ���� ������ ��ġ�� �����ϴ� int[][] P �迭 ���� - �ʱ�ȭ NIL
 * 3. D[i][j]�� ��ο� ���� �ּ��� �Ÿ��� ������ ������ ������Ʈ   
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
