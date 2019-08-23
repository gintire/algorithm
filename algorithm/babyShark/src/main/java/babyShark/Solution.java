package babyShark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static int N;
	public static Fish2[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new Fish2[N+1][N+1]; 
		Fish2[] fishes = new Fish2[N * N];
		boolean visited[] = new boolean[N * N];
		
		Shark2 shark = null;
		int idx = 0;
		// 1. 초기화
		for(int i=1; i<=N; i++) {
			String[] tempString = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				int thisFish = Integer.parseInt(tempString[j-1]);
				if(thisFish == 0) continue;
				if(thisFish == 9) {
					shark = new Shark2(i, j, 2);
					map[i][j] = shark;
				}
				else {
					fishes[idx] = new Fish2(i, j, thisFish);
					visited[idx] = true;
					map[i][j] = fishes[idx];
					idx++;
				}
			}
		}
		
		int res = move(fishes, shark, visited, 0);
		System.out.println(res);
	}
	private static int move(Fish2[] fishes, Shark2 shark, boolean[] visited, int count) {
		for(int i=0; i<N*N; i++) {
			if(!visited[i]) continue;
			int nextfishIdx = moveEable(fishes, shark, visited);
			if(nextfishIdx == -1) break;
			Fish2 nextFish = fishes[nextfishIdx];
			if(shark.compareTo(nextFish) > 0) {
				count += shark.getDistance(nextFish);
				shark.x = nextFish.x;
				shark.y = nextFish.y;
				shark.ateAmnt+=1;
				if(shark.ateAmnt == shark.weight) {
					shark.weight+=1;
					shark.ateAmnt = 0;
				}
				visited[nextfishIdx] = false;
				count = move(fishes, shark, visited, count);
			} else if(shark.compareTo(nextFish) == 0) {
				count += shark.getDistance(nextFish);
				shark.x = nextFish.x;
				shark.y = nextFish.y;
				//visited[nextfishIdx] = false;
				count = move(fishes, shark, visited, count);
			}
			
		}
		return count;
	}
	private static int moveEable(Fish2[] fishes, Shark2 shark, boolean[] visited) {
		ArrayList<Integer> eatableFish = new ArrayList<Integer>();
		for(int i=0; i<N*N; i++) {
			if(!visited[i]) continue;
			if(shark.compareTo(fishes[i]) > 0) {
				eatableFish.add(i);
			}
		}
		if(eatableFish.size() == 0) return -1; 
		int nextFish = getNextFish(eatableFish, fishes, shark);
		return nextFish;
	}
	private static int getNextFish(ArrayList<Integer> eatableFish, Fish2[] fishes, Shark2 shark) {
		int nextfish = 0;
		int dist = Integer.MAX_VALUE;
		for(int i=0 ; i < eatableFish.size(); i++) {
			int thisFish = eatableFish.get(i);
			if(shark.getDistance(fishes[thisFish]) == 0) continue;
			if(dist > shark.getDistance(fishes[thisFish])){
				dist = shark.getDistance(fishes[thisFish]);
				nextfish = thisFish;
			}
		}
		return nextfish;
	}
}

class Shark2 extends Fish2 {
	int ateAmnt;
	public Shark2(int x, int y, int weight) {
		super(x, y, weight);
		ateAmnt = 0;
	}
	
}

//2. 물고기 클래스
class Fish2 implements Comparable<Fish2> {
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	
	int weight;
	int x;
	int y;
	
	public Fish2(int x, int y, int weight) {
		this.weight = weight;
		this.x = x;
		this.y = y;
	}

	public int compareTo(Fish2 o) {
		return this.weight - o.weight;
	}
	
	public int getDistance(Fish2 o, Fish2[][] map) {
		return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
	}
	private int bfs(Fish2 a, Fish2 b, Fish2[][] map, int N) {
		Fish2 movingShark = new Fish2(a.x, a.y, a.weight);
		
		boolean[][] visited = new boolean[N+1][N+1];
		
		int nextX = 0;
		int nextY = 0;
		
		Queue<Fish2> moveSharkQueue = new LinkedList<Fish2>();	
		moveSharkQueue.offer(movingShark);
		
		while(!moveSharkQueue.isEmpty()) {
			Fish2 thisPosition = moveSharkQueue.poll();
			for(int i=0; i<4; i++) {
				nextX = thisPosition.x + dx[i];
				nextY = thisPosition.y + dy[i];
				
				if(nextX < 1 || nextY < 1 || nextX > N || nextY > N) continue;
				if()
				
			}
		}
		
		return 0;
	}
}