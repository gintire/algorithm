package algorithm;

import java.util.Arrays;
import java.util.Stack;

public class OddOccurrencesInArray {

	public static void main(String[] args) {
		int[] A = {42};
		System.out.println(solution(A));
		
	}
	public static int solution(int[] A) {
		Arrays.sort(A);
		Stack<Integer> st = new Stack<Integer>();
		for(int i=0 ; i<A.length ; i++) {
			if(!st.isEmpty()) {
				if(A[i]!=st.peek())
					st.push(A[i]);
				else
					st.pop();
			} else {
				st.push(A[i]);
			}
		}
		return st.pop();
    }
}
