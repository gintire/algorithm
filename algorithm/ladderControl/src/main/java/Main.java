import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

/**
 * 백준 No 15684 사다리 조작
 * 2018 상반기 삼성전자 DS 문제
 * 전략
 * - 세로선의 갯수 N, 가로선의 갯수 M, 세로선마다 가로선을 높을 수 있는 위치의 개수 H
 * - DFS
 *   - 사다리가 놓아진 선 저장 배열 (boolean ladder[][])
 *   - 방문한 사다리는 다시 방문할 필요가 없다. (boolean visited[][])
 *   - 세로줄을 타면서 i번째 세로줄 i 로 가도록 조작
 *     - 사다리의 결과값을 모두 다르므로 1번부터 N번까지 차례대로 맞추면된다.(DFS)
 *     - * 사다리를 타고 내려가는 길에 사다리 추가 (핵심)
 *     - 각 사다리마다 최소로 놓을 수 있는 사다리 확인 Math.min(a, b)
 *     - 사다리 추가 함수 addLadder(int a, int b)
 *     - 사다리 제거 함수 delLadder(int a, int b)
 */
public class Main {
    private static int M;
    private static int N;
    private static int H;
    public static boolean[][] ladder;
    public static boolean[][] visited;
    public static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NMH = br.readLine().split(" ");
        N = Integer.parseInt(NMH[0]); M = Integer.parseInt(NMH[1]); H = Integer.parseInt(NMH[2]);

        ladder = new boolean[H+1][N+1];
        //초기화
        for(int i=1; i<=M; i++) {
            String[] ij = br.readLine().split(" ");
            ladder[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = true;
        }
        solve(0, 1, 1);
        if(minValue > 3) System.out.println("-1");
        else System.out.println(minValue);
    }

    public static void solve(int cnt, int v, int p){
        if(cnt>=minValue) return;
        if(doLadder()) {
            minValue = cnt;
            return;
        }
        if(cnt == 3) return;
        for(int i=p;i<=H;i++) {
            v = 1;
            for(int j=v; j<N; j++) {
                if(ladder[i][j]) {
                    j++;
                    continue;
                }
                ladder[i][j] = true;
                solve(cnt+1, v+2, i);
                ladder[i][j] = false;
            }
        }
    }
    //dfs (세로줄 n, 가로줄 m)
    public static boolean doLadder() {
       for(int i=1; i<=N; i++) {
           int v = i;
           for(int j=1; j<=H; j++) {
               if(ladder[j][v]) v++;
               else if(ladder[j][v-1]) v--;
           }
           if(i != v) return false;
       }
       return true;
    }
}
