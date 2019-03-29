package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class No11866 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=n; i++) {
			q.add(i);
		}
		int i = 0;
		while(!q.isEmpty()) {
			if(i==m-1) {
				sb.append(q.poll());
				sb.append(", ");
				i=0;
			}
			i+=1;
			q.add(q.poll());
		}
		sb.append(">");
		
		System.out.println(sb.toString());
	}

}
