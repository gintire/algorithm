package algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class CyclicRotation {

	public static void main(String[] args) {
		
	}
	public int[] solution(int[] A, int K) {
		Deque<Integer> deque = new LinkedList<Integer>();
		int[] B = new int[A.length];
		for(int i=0; i<A.length; i++) {
			deque.addLast(A[i]);
		}
		while(K-->0) {
			if(!deque.isEmpty())
				deque.addFirst(deque.removeLast());
		}
		for(int i=0; i<A.length; i++) {
			B[i] = deque.removeFirst();
		}
		return B;
    }
}
