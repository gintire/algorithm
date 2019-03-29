package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryGap {

	public static void main(String[] args) {
		System.out.println(solution(51712));

	}
	public static int solution(int N) {
		Queue<Integer> q = new LinkedList<Integer>();
		while(N>0) {
			q.offer(N%2);
			N = N/2;
		}
		int tempLength = 0;
		int maxLength = 0;
		boolean flag = false;
		while(!q.isEmpty()) {
			int now = q.poll();
			System.out.print(now);
			if(flag) {
				if(now == 1) {
					maxLength = maxLength < tempLength ? tempLength : maxLength;
					tempLength=0;
				} else {
					tempLength+=1;
				}
			} else {
				if(now == 1) {
					flag = true;
				}
			}
		}
		System.out.println();
		return maxLength;
	}

}
