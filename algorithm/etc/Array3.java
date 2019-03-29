package algorithm;

public class Array3 {

	public static void main(String[] args) {
		int[] A = {-1000, 1000};
		System.out.println(solution(A));
	}
	public static int solution(int[] A) {
		int[] D =new int[A.length];
		D[0] = A[0];
		int sum =0;
		for(int i=0; i<A.length; i++) { 
			sum+=A[i];
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i<A.length-1; i++) {
			int tmp = sum-D[i];
			System.out.println(tmp);
			int diff = Math.abs(tmp-D[i]);
			System.out.println(diff);
			if(diff<min)
				min = diff;
			
			D[i+1] =D[i] + A[i+1];
		}
		return min;
    }
}
