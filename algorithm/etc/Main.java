package algorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(bf.readLine());
        
        while (t-- > 0) {
            int n = Integer.valueOf(bf.readLine());
            int[] cost = new int[n];
            String[] nums = bf.readLine().split(" ");
            for (int i=0; i<n; i++) {
            	cost[i] = Integer.valueOf(nums[i]);
            }
            System.out.println(solution(cost));
        }
    }

    static public int solution(int[] A) {

        int ret = 0;

        Arrays.sort(A);
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < A.length; i++) {
            deque.add(A[i]);
        }

        while (deque.size() >= 2) {

            int first = deque.poll();
            int second = deque.poll();
            int sum = first + second;
            deque.push(sum);
            ret += sum;
        }

        return ret;
    }
}