package algorithm;

public class No5 {
	static final int MAX_VALUE = 1048575;
	public static void main(String[]args) {
		int[] a = {6, 1, 4, 6, 3, 2, 7, 4};
		System.out.println(solution(a, 3, 2));

	}
	public static int solution(int[] A, int K, int L) {
		int aliceSum = 0;
		int maxASum = 0;
		int bobSum = 0;
		int maxBSum = 0;
		int tmpSum;
		if(A.length < K+L) return -1;
		
		for(int i=0; i<A.length- K; i++) {
			//alice 바구니
			for(int j=0; j<K; j++) {
				aliceSum += A[i+j];
			}
			//bob 바구니
			for(int j=i+K; j<=A.length - L; j++) {
				for(int k=0; k<L; k++) {
					bobSum +=A[j+k];
				}
				tmpSum = aliceSum + bobSum;
				bobSum=0;
				if(maxASum < tmpSum)
					maxASum = tmpSum;
			}
			aliceSum=0;
		}
		for(int i=0; i<A.length - L; i++) {
			//bob 바구니
			for(int j=0; j<L; j++) {
				bobSum += A[i+j];
			}
			//Alice 바구니
			for(int j=i+L; j<=A.length - K; j++) {
				for(int k=0; k<K; k++) {
					aliceSum +=A[j+k];
				}
				tmpSum = aliceSum + bobSum;
				aliceSum = 0;
				if(maxBSum < tmpSum)
					maxBSum = tmpSum;
			}
			bobSum=0;
		}
		System.out.println(maxASum);
		System.out.println(maxBSum);
		return maxASum < maxBSum? maxBSum : maxASum;
    }
}
