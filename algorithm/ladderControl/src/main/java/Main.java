import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    public static int minValue;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NMH = br.readLine().split(" ");
        N = Integer.parseInt(NMH[0]); M = Integer.parseInt(NMH[1]); H = Integer.parseInt(NMH[2]);

        ladder = new boolean[H+1][N+1];
        //visited = new boolean[H+1][N+1];
        //초기화
        for(int i=1; i<=M; i++) {
            String[] ij = br.readLine().split(" ");
            ladder[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = true;
            //visited[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = true;
        }
        int res = 0;
        for(int i=1; i<=N; i++) {
            visited = new boolean[H+1][N+1];
            minValue = N*M;
            doLadder(i, 1, i, 0);
            System.out.println(minValue);
            System.out.println();
            System.out.println();

            res += minValue;
        }
        System.out.println("정답 : "+res);
    }
    //dfs (세로줄 n, 가로줄 m)
    public static int doLadder(int v, int p, int initV, int addedLadder) {
        //1. 가로선이 없는 경우
        if(M==0) return 0;
        //2. 사다리를 다 탔을 경우
        if(p>H) {
            System.out.println("사다리 마지막: " + v);
            if(v == initV) {
                System.out.println("최솟값 : "+minValue + "  더해진 사다리 수 : "+addedLadder);
                minValue = Math.min(minValue, addedLadder);
            }
            return minValue;
        }
        //3. 방문하지 않은 경우
        //if(!visited[p][v]) {
           //4. 사다리가 없는 경우 ( 해당 세로줄의 옆줄도 확인해줘야함)
            if(!ladder[p][v]) {
                //5. 사다리는 v - v+1 로 연결되므로 왼쪽 사다리유무도 확인
                if(v-1 > 0) {
                    // 사다리 없는 경우
                     if(!ladder[p][v-1]) {
                         // 10. [사다리 추가] 왼쪽으로 움직임
                         visited[p][v] = true;
                         ladder[p][v-1]= true;
                         addedLadder+=1;
                         doLadder(v-1, p, initV, addedLadder);
                         addedLadder-=1;
                         visited[p][v] = false;
                         ladder[p][v-1]= false;
                         // 11. [사다리 추가] 오른쪽으로 움직임
                         if(v+1 <= N ) {
                             if(!ladder[p][v+1]) {
                                 visited[p][v] = true;
                                 ladder[p][v] = true;
                                 addedLadder+=1;
                                 doLadder(v + 1, p, initV, addedLadder);
                                 addedLadder-=1;
                                 visited[p][v] = false;
                                 ladder[p][v] = false;
                             }
                         }
                         // 아래로 이동
                         doLadder(v, p + 1, initV, addedLadder);
                     }
                     //6. 현재 세로줄엔 사다리 없는데 옆줄로 연결되있음
                     else {
                         if(!visited[p][v-1]) {
                             visited[p][v] = true;
                             doLadder(v - 1, p, initV, addedLadder);
                         }
                         // 7. 이미 사다리를 건넜으므로 아래로 움직임
                         else {
                             doLadder(v, p+1, initV, addedLadder);
                         }
                     }
                }
                else {
                    if(!ladder[p][v+1]) {
                        visited[p][v] = true;
                        ladder[p][v] = true;
                        addedLadder+=1;
                        doLadder(v + 1, p, initV, addedLadder);
                        addedLadder-=1;
                        visited[p][v] = false;
                        ladder[p][v] = false;
                    }
                    doLadder(v, p+1, initV, addedLadder);
                }
            }
            //8. 사다리가 있는 경우
            else {
                if(!visited[p][v+1]) {
                    visited[p][v] = true;
                    doLadder(v+1, p, initV, addedLadder);
                }
                //9. 이미 사다리를 건너온 경우 아래로 움직임
                else {
                    doLadder(v, p+1, initV, addedLadder);
                }
            //}
        }

        return minValue;
    }
    //사다리 추가 (가로줄 a, 세로줄 b)
    public static void addLadder (int a, int b) {
        ladder[a][b] = true;
        visited[a][b] = true;
    }
    //사다리 삭제 (가로줄 a, 세로줄 b)
    public static void delLadder (int a, int b) {
        ladder[a][b] = false;
    }
}
