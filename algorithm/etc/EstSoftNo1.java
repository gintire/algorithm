package algorithm;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class EstSoftNo1 {

	public static void main(String[] args) {
		int[] arr = new int[]{5, -2, 6, -3, 7, -4, 8, 9};				// 6
//		int[] arr = new int[]{5, -2, 3, 8, 6};							// 3
		//int[] arr = new int[]{-5,-5,-5,-42,6,12};
		int ans = new EstSoftNo1().solution(arr);
		System.out.println(ans);
	}
	public static int solution(int[] T) {
		
		Map<Integer,Integer> map = new TreeMap<Integer,Integer>();			
	
		/* 시간복잡도 O(nlogn) */
		for (int i = 0; i < T.length; i++) {
			if (map.containsKey(T[i])) {
				map.put(T[i], map.get(T[i])+1);
			} else {
				map.put(T[i], 1);
			}
		}
			
		int max = Integer.MIN_VALUE;
		/* 시간복잡도 O(nlogn)*/
		for (int i = 0; i < T.length; i++) {						// 시간복잡도 O(N)
			max = Math.max(max, T[i]);
			Iterator<Integer> it = map.keySet().iterator();
			if (it.hasNext()) {										// Root만 접근 O(1)
				int v = it.next();
				if (T[i] == v && (int)map.get(v) >= 2) {
					map.put(v, map.get(v)-1);
				} else {
					map.remove(T[i]);								// logN
				}
							
			}
			it = map.keySet().iterator();
			if (it.hasNext()) { 
				int v = it.next();
				if (max < v) {
					return i+1;
				}
			}
		}
		
		return 0;
	}


}
