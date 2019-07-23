package chicken_delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Solve4 {

	/* 문제해결
	 * 1. 두집 사이의 거리를 구하는 함수 (getDist)
	 * 2. 도시 지도를 입력 받는다.
	 * 3. 치킨집의 위치를 저장
	 * 4. 집의 위치를 저장
	 * 5. 각 집을 순회하며 치킨 거리 계산
	 * 6. M개의 치킨집을 선택
	 *   선택하고 dfs M개의 치킨집이 선택되었을 경우 치킨 거리 계산
	 * 7. 치킨 거리의 최솟값을 구한다.
	 * */
	static int N, M;
	static ArrayList<Point2> chickenRest = new ArrayList<>();
	static ArrayList<Point2> houseRest = new ArrayList<>();
	 
	static int houseCnt, chickenCnt, res = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
		Stack<Point2> selected = new Stack<>();
		
		for(int i = 0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int intnum = Integer.parseInt(line[j]);
				if(intnum==2) chickenRest.add(new Point2(i, j));
				if(intnum==1) houseRest.add(new Point2(i, j));
			}
		}
		
		houseCnt = houseRest.size();
		chickenCnt = chickenRest.size();
		//집을 순회하며 치킨 거리 계산
		selectChickenRest(0, selected);
		System.out.println(res);
	}
	private static void selectChickenRest(int idx, Stack<Point2> selected) {
		if(selected.size() >= M) {
			//print(selected);
			res = Math.min(dfs(selected), res);
			//System.out.println(res);
		}
		//선택된 치킨집
		else if(idx < chickenCnt) {
			selectChickenRest(idx+1, selected);
			selected.push(chickenRest.get(idx));
			selectChickenRest(idx+1, selected);
			selected.pop();
			
		}
	}
	private static int dfs(Stack<Point2> selected) {
		int resDist = 0;
		int tempDist;
		int minDist;
		
		for(int i=0; i<houseCnt; i++) {
			minDist = Integer.MAX_VALUE;
			for(int j=0; j<selected.size();j++) {
				tempDist = houseRest.get(i).getDist(selected.get(j));
				minDist = Math.min(tempDist, minDist);
			}
			resDist += minDist;
		}
		return resDist;
	}
	private static void print(Stack<Point2> selected) {
		for(int i=0; i<selected.size();i++) {
			System.out.println(selected.get(i));
		}
	}
}

class Point2 {
	int x;
	int y;
	public Point2(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDist(Point2 point) {
		return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
	}
	@Override
	public String toString() {
		return "Point2 [x=" + x + ", y=" + y + "]";
	}
}
