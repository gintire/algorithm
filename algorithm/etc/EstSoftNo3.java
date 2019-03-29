package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EstSoftNo3 {
    static int solve(int[] A)
    {
    	int n = A.length;
        int[][] a = new int[n+1][n+1];
	    int[][] opt = new int[n+1][n+1];
        int[] sum = new int[n+1];
    	for (int i = 1; i <= n; i++)sum[i] = sum[i-1] + A[i-1];
    	for (int i = 1; i <= n; i++) {opt[i-1][i]=0; a[i-1][i] = i;}
		for (int d = 2; d <= n; d++){
			for (int i = 0; i + d <= n; i++){
				int j = i + d;
				opt[i][j] = Integer.MAX_VALUE;
				for (int k = a[i][j-1]; k <= a[i+1][j]; k++){
					if (opt[i][j] > opt[i][k] + opt[k][j] + sum[j] - sum[i]){
						opt[i][j] = opt[i][k] + opt[k][j] + sum[j] - sum[i];
						a[i][j] = k;
					}
				}
			}
		}
		return opt[0][n];
    }
    /**
	 * 
	 * @author : ginTire
	 * @comment : 시간복잡도 N
	 */
    public static void main(String args[]) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(bf.readLine());
        
        while (t-- > 0) {
            int n = Integer.valueOf(bf.readLine());
            int[] cost = new int[n];
            String[] nums = bf.readLine().split(" ");
            for (int i=0; i<n; i++) {
            	cost[i] = Integer.valueOf(nums[i]);
            }
            System.out.println(solve(cost));
        }
    }
}
