package make_jonggu_daughter_name;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/

class Main
{
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static int N;
	static int M;
	static String[][] map;
	static String restStr;
	
	public static void main(String args[]) throws Exception
	{
		/*
		아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		*/
		
		System.setIn(new FileInputStream("C:/Users/jin36/Documents/out.txt"));
		/*
		표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		*/
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		/*
		여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
		
		//StringBuffer sb = new StringBuffer();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			restStr = "";
			String[] NM = br.readLine().split(" ");
			N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
			map = new String[N+1][M+1];
			
			for (int i=0; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					map[i][j]= "z";
				}
			}
			
			for (int i=0; i<N; i++) {
				String line = br.readLine();
				//StringTokenizer str = new StringTokenizer(line, "");
				for(int j=0; j<M; j++) {
					map[i][j] = line.substring(j, j+1);
					//map[i] = line.split("");
				}
			}
			Instant start = Instant.now();
			getNameList(0, 0, "");
			Instant stop = Instant.now();
			Duration d1 = Duration.between(start, stop);
			System.out.println(d1.getSeconds());
			//Arrays.sort(nameList, new ComparatorAlphbet());
			System.out.println("#"+test_case+" "+restStr);
			//sb.append("#"+test_case+" "+restStr).append("\n");
		}
		
		//System.out.println(sb.toString());
		
	}
	private static void getNameList(int x, int y, String str) {
		if(x >= N || y >= M) {
			return;
		} else {
			System.out.println("["+x+", "+y+"] : "+str+" + "+map[x][y]);
			str += map[x][y];
			if(x == N-1 && y==M-1) {
				if(restStr.equals("")) restStr = str;
				else if (restStr.compareTo(str) >= 0) {
					restStr = str;
				}
			} 
		}
		if(x+1 <= N && y+1 <= M){
			// 오른쪽이 작을 때
			if(map[x+1][y].compareTo(map[x][y+1]) < 0) {
				calc(x+1, y, str);
			} 
			//아래가 작을 때
			else if (map[x+1][y].compareTo(map[x][y+1]) > 0) {
				calc(x, y+1, str);
			} else {
				calc(x+1, y, str);
				calc(x, y+1, str);
			}
		}
	}
	private static void calc (int next_x, int next_y, String str) {
		if(restStr != "") {
			if(restStr.compareTo(str+map[next_x][next_y]) <= 0) {
				//System.out.println("!!!!!!!!!!!!!!!!!!work!!!!!!!!!!!!!!!!!!!" + restStr.compareTo(str+map[next_x][next_y]));
				return;
			} else {
				getNameList(next_x, next_y, str);
			}  
		} else {
			getNameList(next_x, next_y, str);
		}
	}
}
class ComparatorAlphbet implements Comparator<String> {
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
}
