package laboratory3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 연구소 3 - 백준 17142번
 * 
 * 전략
 * 1. 초기화
 * 2. dfs 
 *   - 활성화 할 수 있는 바이러스칸에 퍼트릴 바이러스 선택
 *   - array에 퍼트릴수 있는 바이러스 좌표를 넣고
 *   - 선택 or 선택 x
 *   - M개 만큼 선택이 됐다면 바이러스 활성화
 *   - 최단 시간 구함
 * 3. 바이러스 확산 ( int goAction() )
 *   - 무한 반복문
 *   - 퍼트린 갯수가 없을 경우 정지
 *   - 전체 탐색을 하며 바이러스 퍼트림
 *   - 전체 탐색할 때마다 count
 *
 */
public class Main {
	public static int[][] map;
	public static int N, M;
	public static int res = Integer.MAX_VALUE;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		map = new int[N][N];
		
		ArrayList<Pnt> enableVirus = new ArrayList<Pnt>();
		Pnt[] selectedVirus = new Pnt[M];
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int tempNum = Integer.parseInt(temp[j]);
				if(tempNum == 2) {
					enableVirus.add(new Pnt(i, j));
					map[i][j] = -2;
				}
				else if(tempNum == 1) map[i][j] = -1;
				else map[i][j] = tempNum;
			}
		}
		
		dfs(enableVirus, selectedVirus, 0, 0);
		if(res == Integer.MAX_VALUE) res = 0;
		System.out.println(res);
	}
	private static void dfs(ArrayList<Pnt> enableVirus, Pnt[] selectedVirus, int idx, int selected) {
		if(validate(map)) {res = 0; return;}
		if(selected == M) {
			int spreadTime = doAction(selectedVirus);
			
			if(!flag) {
				if(spreadTime > 0) flag = true;
				if(spreadTime == -1) res = -1;
				if(spreadTime > 0) {
					if(res == -1) res = spreadTime;
					else if(res > spreadTime) res = spreadTime;
				}
			} else {
				if(spreadTime > 0) {
					if(res > spreadTime) res = spreadTime;
				}
			}
		}
		if(idx < enableVirus.size()) {
			if(selected < M) {
				dfs(enableVirus, selectedVirus, idx+1, selected);
				selectedVirus[selected] = enableVirus.get(idx);
				dfs(enableVirus, selectedVirus, idx+1, selected+1);
			}
		}
	}
	private static int doAction(Pnt[] selectedVirus) {
		int[][] tempMap = copyMap();
		Queue<Pnt> virusQue = new LinkedList<Pnt>();
		for(int i=0; i < M; i++) {
			Pnt thisPnt = selectedVirus[i];
			virusQue.add(thisPnt);
			int thisX = thisPnt.getX();
			int thisY = thisPnt.getY();
			tempMap[thisX][thisY] = 1; 
		}
		
		
		while(!virusQue.isEmpty()) {
			Pnt thisXY = virusQue.poll();
			int x = thisXY.getX();
			int y = thisXY.getY();
			int thisTime = tempMap[x][y];
			
			for(int k=0; k<4; k++) {
				int nextX = x + dx[k];
				int nextY = y + dy[k];
				
				if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
					if(tempMap[nextX][nextY]==-2) {
						if(validate(tempMap)) break;
						tempMap[nextX][nextY] = 0;
						virusQue.add(new Pnt(x, y));
							
					}
					if(tempMap[nextX][nextY] == 0) {
						if(thisTime == 1) {
							tempMap[nextX][nextY] = thisTime+1;
							virusQue.add(new Pnt(nextX, nextY));
						}
						else if(tempMap[nextX][nextY] != thisTime-1) {
							tempMap[nextX][nextY] = thisTime+1;
							virusQue.add(new Pnt(nextX, nextY));
						}
					}
				}
			}
			
		}
		int time = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(time < tempMap[i][j]) time = tempMap[i][j];
			}
		}
	
		System.out.println("==============");
		print(tempMap);
		System.out.println("==============");
		/*while(count != 0) {
			count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(tempMap[i][j] == time) {
						for(int k=0; k<4; k++) {
							int nextX = i + dx[k];
							int nextY = j + dy[k];
							
							if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
								if(tempMap[nextX][nextY]==-2) {
									if(validate(tempMap)) break;
									else tempMap[nextX][nextY] = 0;
								}
								if(tempMap[nextX][nextY] == 0) {
									if(time == 1) {
										tempMap[nextX][nextY] = time+1;
										count++;
									}
									else if(tempMap[nextX][nextY] != time-1) {
										tempMap[nextX][nextY] = time+1;
										count++;
									}
								}
							}
						}
					}
				}
			}
			time++;
		}
		if(count==0 && validate(tempMap)) {
			System.out.println("==============");
			print(tempMap);
			System.out.println("==============");
		}*/
		if(!validate(tempMap)) return -1;
		return time - 1;
	}
	private static int[][] copyMap() {
		int[][] tempMap = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++)
				tempMap[i][j] = map[i][j];
		}
		return tempMap;
	}
	
	private static boolean validate(int[][] validMap) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) {
				if(validMap[i][j] == 0) return false;
			}
		}
		return true;
	}
	private static void print(int[][] printMap) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) System.out.print(printMap[i][j] +" ");
		System.out.println();
		}
	}
}

class Pnt {
	private int x;
	private int y;
	public Pnt(int x, int y) {
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
}