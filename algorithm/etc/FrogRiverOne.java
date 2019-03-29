package algorithm;

public class FrogRiverOne {

	public static void main(String[] args) {
		/*int[] A = new int [100001];
		for(int i=0; i<100000; i++) {
			A[i] = i;
		}*/
		int[] A = {2,2,2,2,2};
		System.out.println(solution(2, A));
	}
	public static int solution(int X, int[] A) {
		boolean[] m = new boolean[X+1];
		Long sum = (long) 0.0;
		Long check_sum = (long)0;
		if(X%2==0)
			check_sum= (long) X/2*(X+1);
		else
			check_sum = (long) X*((X+1)/2);
		for(int i=0; i<A.length; i++) {
			if(!m[A[i]]) {
				m[A[i]]= true;
				sum+=A[i];
			}
			if(m[A[i]]) {
				if(i<X-1) continue;
				if(check_sum.equals(sum))
					return i;
			}
		}
		return -1;
	}
	public static boolean check(boolean[] m) {
		for(int i=1; i<m.length; i++) {
			if(!m[i])
				return false;
		}
		return true;
	}
}
