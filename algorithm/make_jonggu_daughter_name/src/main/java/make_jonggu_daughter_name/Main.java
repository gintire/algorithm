package make_jonggu_daughter_name;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;

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
	static char[][] map = new char[2002][2002];
	static String[][] visited = new String[2002][2002];
	static String restStr;
	static StringBuilder sb = new StringBuilder();
	
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
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			restStr = "";
			String[] NM = br.readLine().split(" ");
			N = Integer.parseInt(NM[0]); M = Integer.parseInt(NM[1]);
			//map = new String[N+2][M+2];
			//visited = new String[N+2][M+2];
			for (int i=0; i<=N+1; i++) {
				for(int j=0; j<=M+1; j++) {
					visited[i][j] = "z";
				}
			}
			for (int i=1; i<=N; i++) {
				String line = br.readLine();
				for(int j=1; j<=M; j++) {
					map[i][j] = line.charAt(j-1);
				}
			}
			visited[1][1] = String.valueOf(map[1][1]);
			
			Instant start = Instant.now();
			getNameList();
			Instant stop = Instant.now();
			Duration d1 = Duration.between(start, stop);
			System.out.println("#"+test_case+" "+visited[N][M]);
			System.out.println("걸린 시간 : " + d1.getNano());
		}
	}
	//bfs
	private static void getNameList() {
		for(int x=1; x<N+1; x++) {
			for(int y=1; y<M+1; y++) {
				sb.setLength(0);
				if(x==1 && y==1) continue;
				if(visited[x-1][y].compareTo(visited[x][y-1]) <= 0) {
					sb.append(visited[x-1][y]);
				}
				else {
					sb.append(visited[x][y-1]);
				}
				sb.append(map[x][y]);
				visited[x][y] = sb.toString();
			}
		}
	}
}
