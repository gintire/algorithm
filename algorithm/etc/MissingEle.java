package algorithm;

import java.util.Arrays;
import java.util.HashSet;

public class MissingEle {

	public static void main(String[] args) {
		int[] a = {2};
		
		
		System.out.println(solution(a));

	}
	public static int solution(int[] A) {
		Arrays.sort(A);
		HashSet <Integer> s = new HashSet<Integer>();
		
		for(int i=0; i<A.length; i++) {
			s.add(A[i]);
		}
		for(int i=1; i<=A.length; i++) {
			if(!s.contains(i))
				return i;
		}
		return A.length+1;
    }
}
